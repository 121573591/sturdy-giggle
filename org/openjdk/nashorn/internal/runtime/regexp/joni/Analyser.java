package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.AnchorNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.BackRefNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.Node;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

final class Analyser extends Parser {
  private static final int GET_CHAR_LEN_VARLEN = -1;
  
  private static final int GET_CHAR_LEN_TOP_ALT_VARLEN = -2;
  
  private static final int THRESHOLD_CASE_FOLD_ALT_FOR_EXPANSION = 8;
  
  private static final int IN_ALT = 1;
  
  private static final int IN_NOT = 2;
  
  private static final int IN_REPEAT = 4;
  
  private static final int IN_VAR_REPEAT = 8;
  
  private static final int EXPAND_STRING_MAX_LENGTH = 100;
  
  private static final int MAX_NODE_OPT_INFO_REF_COUNT = 5;
  
  protected Analyser(ScanEnvironment env, char[] chars, int p, int end) {
    super(env, chars, p, end);
  }
  
  protected final void compile() {
    reset();
    this.regex.numMem = 0;
    this.regex.numRepeat = 0;
    this.regex.numNullCheck = 0;
    this.regex.repeatRangeLo = null;
    this.regex.repeatRangeHi = null;
    parse();
    this.root = setupTree(this.root, 0);
    this.regex.captureHistory = this.env.captureHistory;
    this.regex.btMemStart = this.env.btMemStart;
    this.regex.btMemEnd = this.env.btMemEnd;
    if (Option.isFindCondition(this.regex.options)) {
      this.regex.btMemEnd = BitStatus.bsAll();
    } else {
      this.regex.btMemEnd = this.env.btMemEnd;
      this.regex.btMemEnd |= this.regex.captureHistory;
    } 
    this.regex.clearOptimizeInfo();
    setOptimizedInfoFromTree(this.root);
    this.env.memNodes = null;
    (new ArrayCompiler(this)).compile();
    if (this.regex.numRepeat != 0 || this.regex.btMemEnd != 0) {
      this.regex.stackPopLevel = 2;
    } else if (this.regex.btMemStart != 0) {
      this.regex.stackPopLevel = 1;
    } else {
      this.regex.stackPopLevel = 0;
    } 
  }
  
  private void swap(Node a, Node b) {
    a.swap(b);
    if (this.root == b) {
      this.root = a;
    } else if (this.root == a) {
      this.root = b;
    } 
  }
  
  private int quantifiersMemoryInfo(Node node) {
    ConsAltNode can;
    QuantifierNode qn;
    EncloseNode en;
    int info = 0;
    switch (node.getType()) {
      case 8:
      case 9:
        can = (ConsAltNode)node;
        do {
          int v = quantifiersMemoryInfo(can.car);
          if (v <= info)
            continue; 
          info = v;
        } while ((can = can.cdr) != null);
        break;
      case 5:
        qn = (QuantifierNode)node;
        if (qn.upper != 0)
          info = quantifiersMemoryInfo(qn.target); 
        break;
      case 6:
        en = (EncloseNode)node;
        switch (en.type) {
          case 1:
            return 2;
          case 2:
          case 4:
            info = quantifiersMemoryInfo(en.target);
            break;
        } 
        break;
    } 
    return info;
  }
  
  private int getMinMatchLength(Node node) {
    BackRefNode br;
    ConsAltNode can, y;
    QuantifierNode qn;
    EncloseNode en;
    int min = 0;
    switch (node.getType()) {
      case 4:
        br = (BackRefNode)node;
        if (br.isRecursion())
          break; 
        if (br.backRef > this.env.numMem)
          throw new ValueException("invalid backref number"); 
        min = getMinMatchLength(this.env.memNodes[br.backRef]);
        break;
      case 8:
        can = (ConsAltNode)node;
        do {
          min += getMinMatchLength(can.car);
        } while ((can = can.cdr) != null);
        break;
      case 9:
        y = (ConsAltNode)node;
        do {
          Node x = y.car;
          int tmin = getMinMatchLength(x);
          if (y == node) {
            min = tmin;
          } else if (min > tmin) {
            min = tmin;
          } 
        } while ((y = y.cdr) != null);
        break;
      case 0:
        min = ((StringNode)node).length();
        break;
      case 2:
        min = 1;
        break;
      case 1:
      case 3:
        min = 1;
        break;
      case 5:
        qn = (QuantifierNode)node;
        if (qn.lower > 0) {
          min = getMinMatchLength(qn.target);
          min = MinMaxLen.distanceMultiply(min, qn.lower);
        } 
        break;
      case 6:
        en = (EncloseNode)node;
        switch (en.type) {
          case 1:
            if (en.isMinFixed()) {
              min = en.minLength;
              break;
            } 
            min = getMinMatchLength(en.target);
            en.minLength = min;
            en.setMinFixed();
            break;
          case 2:
          case 4:
            min = getMinMatchLength(en.target);
            break;
        } 
        break;
    } 
    return min;
  }
  
  private int getMaxMatchLength(Node node) {
    ConsAltNode ln;
    int tmax;
    ConsAltNode an;
    BackRefNode br;
    int i;
    QuantifierNode qn;
    EncloseNode en;
    int max = 0;
    switch (node.getType()) {
      case 8:
        ln = (ConsAltNode)node;
        do {
          tmax = getMaxMatchLength(ln.car);
          max = MinMaxLen.distanceAdd(max, tmax);
        } while ((ln = ln.cdr) != null);
        break;
      case 9:
        an = (ConsAltNode)node;
        do {
          int j = getMaxMatchLength(an.car);
          if (max >= j)
            continue; 
          max = j;
        } while ((an = an.cdr) != null);
        break;
      case 0:
        max = ((StringNode)node).length();
        break;
      case 2:
        max = 1;
        break;
      case 1:
      case 3:
        max = 1;
        break;
      case 4:
        br = (BackRefNode)node;
        if (br.isRecursion()) {
          max = Integer.MAX_VALUE;
          break;
        } 
        if (br.backRef > this.env.numMem)
          throw new ValueException("invalid backref number"); 
        i = getMaxMatchLength(this.env.memNodes[br.backRef]);
        if (max < i)
          max = i; 
        break;
      case 5:
        qn = (QuantifierNode)node;
        if (qn.upper != 0) {
          max = getMaxMatchLength(qn.target);
          if (max != 0) {
            if (!QuantifierNode.isRepeatInfinite(qn.upper)) {
              max = MinMaxLen.distanceMultiply(max, qn.upper);
              break;
            } 
            max = Integer.MAX_VALUE;
          } 
        } 
        break;
      case 6:
        en = (EncloseNode)node;
        switch (en.type) {
          case 1:
            if (en.isMaxFixed()) {
              max = en.maxLength;
              break;
            } 
            max = getMaxMatchLength(en.target);
            en.maxLength = max;
            en.setMaxFixed();
            break;
          case 2:
          case 4:
            max = getMaxMatchLength(en.target);
            break;
        } 
        break;
    } 
    return max;
  }
  
  protected final int getCharLengthTree(Node node) {
    return getCharLengthTree(node, 0);
  }
  
  private int getCharLengthTree(Node node, int levelp) {
    ConsAltNode ln, an;
    boolean varLen;
    int tlen;
    StringNode sn;
    QuantifierNode qn;
    EncloseNode en;
    int level = levelp + 1;
    int len = 0;
    this.returnCode = 0;
    switch (node.getType()) {
      case 8:
        ln = (ConsAltNode)node;
        do {
          int i = getCharLengthTree(ln.car, level);
          if (this.returnCode != 0)
            continue; 
          len = MinMaxLen.distanceAdd(len, i);
        } while (this.returnCode == 0 && (ln = ln.cdr) != null);
      case 9:
        an = (ConsAltNode)node;
        varLen = false;
        tlen = getCharLengthTree(an.car, level);
        while (this.returnCode == 0 && (an = an.cdr) != null) {
          int tlen2 = getCharLengthTree(an.car, level);
          if (this.returnCode == 0 && 
            tlen != tlen2)
            varLen = true; 
        } 
        if (this.returnCode == 0)
          if (varLen) {
            if (level == 1) {
              this.returnCode = -2;
            } else {
              this.returnCode = -1;
            } 
          } else {
            len = tlen;
          }  
      case 0:
        sn = (StringNode)node;
        len = sn.length();
      case 5:
        qn = (QuantifierNode)node;
        if (qn.lower == qn.upper) {
          tlen = getCharLengthTree(qn.target, level);
          if (this.returnCode == 0)
            len = MinMaxLen.distanceMultiply(tlen, qn.lower); 
        } else {
          this.returnCode = -1;
        } 
      case 1:
      case 2:
      case 3:
        len = 1;
      case 6:
        en = (EncloseNode)node;
        switch (en.type) {
          case 1:
            if (en.isCLenFixed()) {
              len = en.charLength;
              break;
            } 
            len = getCharLengthTree(en.target, level);
            if (this.returnCode == 0) {
              en.charLength = len;
              en.setCLenFixed();
            } 
            break;
          case 2:
          case 4:
            len = getCharLengthTree(en.target, level);
            break;
        } 
      case 7:
        return len;
    } 
    this.returnCode = -1;
  }
  
  private static boolean isNotIncluded(Node xn, Node yn) {
    Node x = xn;
    Node y = yn;
    while (true) {
      Node tmp;
      CClassNode xc, yc;
      StringNode xs;
      int i, yType = y.getType();
      switch (x.getType()) {
        case 2:
          switch (yType) {
            case 1:
              tmp = x;
              x = y;
              y = tmp;
              continue;
            case 0:
              tmp = x;
              x = y;
              y = tmp;
              continue;
          } 
          break;
        case 1:
          xc = (CClassNode)x;
          switch (yType) {
            case 1:
              yc = (CClassNode)y;
              for (i = 0; i < 256; i++) {
                boolean v = xc.bs.at(i);
                if ((v && !xc.isNot()) || (!v && xc.isNot())) {
                  v = yc.bs.at(i);
                  if ((v && !yc.isNot()) || (!v && yc.isNot()))
                    return false; 
                } 
              } 
              if ((xc.mbuf == null && !xc.isNot()) || (yc.mbuf == null && !yc.isNot()))
                return true; 
              return false;
            case 0:
              tmp = x;
              x = y;
              y = tmp;
              continue;
          } 
          break;
        case 0:
          xs = (StringNode)x;
          if (xs.length() != 0) {
            CClassNode cc;
            int code;
            StringNode ys;
            int len;
            int j;
            int pt;
            int q;
            switch (yType) {
              case 1:
                cc = (CClassNode)y;
                code = xs.chars[xs.p];
                return !cc.isCodeInCC(code);
              case 0:
                ys = (StringNode)y;
                len = xs.length();
                if (len > ys.length())
                  len = ys.length(); 
                if (xs.isAmbig() || ys.isAmbig())
                  return false; 
                for (j = 0, pt = ys.p, q = xs.p; j < len; j++, pt++, q++) {
                  if (ys.chars[pt] != xs.chars[q])
                    return true; 
                } 
                break;
            } 
          } 
          return false;
      } 
      break;
    } 
    return false;
  }
  
  private Node getHeadValueNode(Node node, boolean exact) {
    StringNode sn;
    QuantifierNode qn;
    EncloseNode en;
    int options;
    AnchorNode an;
    Node n = null;
    switch (node.getType()) {
      case 1:
      case 2:
        if (!exact)
          n = node; 
        break;
      case 8:
        n = getHeadValueNode(((ConsAltNode)node).car, exact);
        break;
      case 0:
        sn = (StringNode)node;
        if (sn.end <= sn.p)
          break; 
        if (exact && !sn.isRaw() && Option.isIgnoreCase(this.regex.options))
          break; 
        n = node;
        break;
      case 5:
        qn = (QuantifierNode)node;
        if (qn.lower > 0) {
          if (qn.headExact != null) {
            n = qn.headExact;
            break;
          } 
          n = getHeadValueNode(qn.target, exact);
        } 
        break;
      case 6:
        en = (EncloseNode)node;
        switch (en.type) {
          case 2:
            options = this.regex.options;
            this.regex.options = en.option;
            n = getHeadValueNode(en.target, exact);
            this.regex.options = options;
            break;
          case 1:
          case 4:
            n = getHeadValueNode(en.target, exact);
            break;
        } 
        break;
      case 7:
        an = (AnchorNode)node;
        if (an.type == 1024)
          n = getHeadValueNode(an.target, exact); 
        break;
    } 
    return n;
  }
  
  private boolean checkTypeTree(Node node, int typeMask, int encloseMask, int anchorMask) {
    ConsAltNode can;
    EncloseNode en;
    AnchorNode an;
    if ((node.getType2Bit() & typeMask) == 0)
      return true; 
    boolean invalid = false;
    switch (node.getType()) {
      case 8:
      case 9:
        can = (ConsAltNode)node;
        do {
          invalid = checkTypeTree(can.car, typeMask, encloseMask, anchorMask);
        } while (!invalid && (can = can.cdr) != null);
        break;
      case 5:
        invalid = checkTypeTree(((QuantifierNode)node).target, typeMask, encloseMask, anchorMask);
        break;
      case 6:
        en = (EncloseNode)node;
        if ((en.type & encloseMask) == 0)
          return true; 
        invalid = checkTypeTree(en.target, typeMask, encloseMask, anchorMask);
        break;
      case 7:
        an = (AnchorNode)node;
        if ((an.type & anchorMask) == 0)
          return true; 
        if (an.target != null)
          invalid = checkTypeTree(an.target, typeMask, encloseMask, anchorMask); 
        break;
    } 
    return invalid;
  }
  
  private Node divideLookBehindAlternatives(Node nodep) {
    Node node = nodep;
    AnchorNode an = (AnchorNode)node;
    int anchorType = an.type;
    Node head = an.target;
    Node np = ((ConsAltNode)head).car;
    swap(node, head);
    Node tmp = node;
    node = head;
    head = tmp;
    ((ConsAltNode)node).setCar(head);
    ((AnchorNode)head).setTarget(np);
    np = node;
    ConsAltNode consAltNode;
    while ((consAltNode = ((ConsAltNode)np).cdr) != null) {
      AnchorNode insert = new AnchorNode(anchorType);
      insert.setTarget(consAltNode.car);
      consAltNode.setCar((Node)insert);
    } 
    if (anchorType == 8192) {
      Node node1 = node;
      ConsAltNode consAltNode1;
      do {
        ((ConsAltNode)node1).toListNode();
      } while ((consAltNode1 = ((ConsAltNode)node1).cdr) != null);
    } 
    return node;
  }
  
  private Node setupLookBehind(Node node) {
    AnchorNode an = (AnchorNode)node;
    int len = getCharLengthTree(an.target);
    switch (this.returnCode) {
      case 0:
        an.charLength = len;
        break;
      case -1:
        throw new SyntaxException("invalid pattern in look-behind");
      case -2:
        if (this.syntax.differentLengthAltLookBehind())
          return divideLookBehindAlternatives(node); 
        throw new SyntaxException("invalid pattern in look-behind");
    } 
    return node;
  }
  
  private void nextSetup(Node nodep, Node nextNode) {
    Node node = nodep;
    while (true) {
      int type = node.getType();
      if (type == 5) {
        QuantifierNode qn = (QuantifierNode)node;
        if (qn.greedy && QuantifierNode.isRepeatInfinite(qn.upper)) {
          StringNode n = (StringNode)getHeadValueNode(nextNode, true);
          if (n != null && n.chars[n.p] != '\000')
            qn.nextHeadExact = (Node)n; 
          if (qn.lower <= 1 && 
            qn.target.isSimple()) {
            Node x = getHeadValueNode(qn.target, false);
            if (x != null) {
              Node y = getHeadValueNode(nextNode, false);
              if (y != null && isNotIncluded(x, y)) {
                EncloseNode en = new EncloseNode(4);
                en.setStopBtSimpleRepeat();
                swap(node, (Node)en);
                en.setTarget(node);
              } 
            } 
          } 
        } 
        break;
      } 
      if (type == 6) {
        EncloseNode en = (EncloseNode)node;
        if (en.isMemory()) {
          node = en.target;
          continue;
        } 
      } 
      break;
    } 
  }
  
  private void updateStringNodeCaseFoldMultiByte(StringNode sn) {
    char[] ch = sn.chars;
    int end = sn.end;
    this.value = sn.p;
    int sp = 0;
    while (this.value < end) {
      int ovalue = this.value;
      char buf = EncodingHelper.toLowerCase(ch[this.value++]);
      if (ch[ovalue] != buf) {
        char[] sbuf = new char[sn.length() << 1];
        System.arraycopy(ch, sn.p, sbuf, 0, ovalue - sn.p);
        this.value = ovalue;
        while (this.value < end) {
          buf = EncodingHelper.toLowerCase(ch[this.value++]);
          if (sp >= sbuf.length) {
            char[] tmp = new char[sbuf.length << 1];
            System.arraycopy(sbuf, 0, tmp, 0, sbuf.length);
            sbuf = tmp;
          } 
          sbuf[sp++] = buf;
        } 
        sn.set(sbuf, 0, sp);
        return;
      } 
      sp++;
    } 
  }
  
  private void updateStringNodeCaseFold(Node node) {
    StringNode sn = (StringNode)node;
    updateStringNodeCaseFoldMultiByte(sn);
  }
  
  private Node expandCaseFoldMakeRemString(char[] ch, int pp, int end) {
    StringNode node = new StringNode(ch, pp, end);
    updateStringNodeCaseFold((Node)node);
    node.setAmbig();
    node.setDontGetOptInfo();
    return (Node)node;
  }
  
  private static boolean expandCaseFoldStringAlt(int itemNum, char[] items, char[] chars, int p, int slen, int end, ObjPtr<Node> node) {
    ConsAltNode altNode = ConsAltNode.newAltNode(null, null);
    StringNode snode = new StringNode(chars, p, p + slen);
    altNode.setCar((Node)snode);
    for (int i = 0; i < itemNum; i++) {
      snode = new StringNode();
      snode.catCode(items[i]);
      ConsAltNode an = ConsAltNode.newAltNode(null, null);
      an.setCar((Node)snode);
      altNode.setCdr(an);
      altNode = an;
    } 
    return false;
  }
  
  private Node expandCaseFoldString(Node node) {
    StringNode sn = (StringNode)node;
    if (sn.isAmbig() || sn.length() <= 0)
      return node; 
    char[] chars1 = sn.chars;
    int pt = sn.p;
    int end = sn.end;
    int altNum = 1;
    ConsAltNode topRoot = null, r = null;
    ObjPtr<Node> prevNode = new ObjPtr();
    StringNode stringNode = null;
    while (pt < end) {
      char[] items = EncodingHelper.caseFoldCodesByString(this.regex.caseFoldFlag, chars1[pt]);
      if (items.length == 0) {
        if (stringNode == null) {
          if (r == null && prevNode.p != null)
            topRoot = r = ConsAltNode.listAdd(null, (Node)prevNode.p); 
          prevNode.p = stringNode = new StringNode();
          if (r != null)
            ConsAltNode.listAdd(r, (Node)stringNode); 
        } 
        stringNode.cat(chars1, pt, pt + 1);
      } else {
        altNum *= items.length + 1;
        if (altNum > 8)
          break; 
        if (r == null && prevNode.p != null)
          topRoot = r = ConsAltNode.listAdd(null, (Node)prevNode.p); 
        expandCaseFoldStringAlt(items.length, items, chars1, pt, 1, end, prevNode);
        if (r != null)
          ConsAltNode.listAdd(r, (Node)prevNode.p); 
        stringNode = null;
      } 
      pt++;
    } 
    if (pt < end) {
      Node srem = expandCaseFoldMakeRemString(chars1, pt, end);
      if (prevNode.p != null && r == null)
        topRoot = r = ConsAltNode.listAdd(null, (Node)prevNode.p); 
      if (r == null) {
        prevNode.p = srem;
      } else {
        ConsAltNode.listAdd(r, srem);
      } 
    } 
    Node xnode = (topRoot != null) ? (Node)topRoot : (Node)prevNode.p;
    swap(node, xnode);
    return xnode;
  }
  
  protected final Node setupTree(Node nodep, int statep) {
    Node node = nodep;
    int state = statep;
    while (true) {
      ConsAltNode lin;
      Node prev;
      ConsAltNode aln;
      BackRefNode br;
      QuantifierNode qn;
      Node target;
      EncloseNode en;
      int options;
      AnchorNode an;
      switch (node.getType()) {
        case 8:
          lin = (ConsAltNode)node;
          prev = null;
          do {
            setupTree(lin.car, state);
            if (prev == null)
              continue; 
            nextSetup(prev, lin.car);
            prev = lin.car;
          } while ((lin = lin.cdr) != null);
          break;
        case 9:
          aln = (ConsAltNode)node;
          do {
            setupTree(aln.car, state | 0x1);
          } while ((aln = aln.cdr) != null);
          break;
        case 0:
          if (Option.isIgnoreCase(this.regex.options) && !((StringNode)node).isRaw())
            node = expandCaseFoldString(node); 
          break;
        case 4:
          br = (BackRefNode)node;
          if (br.backRef > this.env.numMem)
            throw new ValueException("invalid backref number"); 
          this.env.backrefedMem = BitStatus.bsOnAt(this.env.backrefedMem, br.backRef);
          this.env.btMemStart = BitStatus.bsOnAt(this.env.btMemStart, br.backRef);
          ((EncloseNode)this.env.memNodes[br.backRef]).setMemBackrefed();
          break;
        case 5:
          qn = (QuantifierNode)node;
          target = qn.target;
          if ((state & 0x4) != 0)
            qn.setInRepeat(); 
          if (QuantifierNode.isRepeatInfinite(qn.upper) || qn.lower >= 1) {
            int d = getMinMatchLength(target);
            if (d == 0) {
              qn.targetEmptyInfo = 1;
              int info = quantifiersMemoryInfo(target);
              if (info > 0)
                qn.targetEmptyInfo = info; 
            } 
          } 
          state |= 0x4;
          if (qn.lower != qn.upper)
            state |= 0x8; 
          target = setupTree(target, state);
          if (target.getType() == 0 && 
            !QuantifierNode.isRepeatInfinite(qn.lower) && qn.lower == qn.upper && qn.lower > 1 && qn.lower <= 100) {
            StringNode sn = (StringNode)target;
            int len = sn.length();
            if (len * qn.lower <= 100) {
              StringNode str = qn.convertToString(sn.flag);
              int n = qn.lower;
              for (int i = 0; i < n; i++)
                str.cat(sn.chars, sn.p, sn.end); 
              break;
            } 
          } 
          if (qn.greedy && qn.targetEmptyInfo != 0) {
            if (target.getType() == 5) {
              QuantifierNode tqn = (QuantifierNode)target;
              if (tqn.headExact != null) {
                qn.headExact = tqn.headExact;
                tqn.headExact = null;
              } 
              break;
            } 
            qn.headExact = getHeadValueNode(qn.target, true);
          } 
          break;
        case 6:
          en = (EncloseNode)node;
          switch (en.type) {
            case 2:
              options = this.regex.options;
              this.regex.options = en.option;
              setupTree(en.target, state);
              this.regex.options = options;
              break;
            case 1:
              if ((state & 0xB) != 0)
                this.env.btMemStart = BitStatus.bsOnAt(this.env.btMemStart, en.regNum); 
              setupTree(en.target, state);
              break;
            case 4:
              setupTree(en.target, state);
              if (en.target.getType() == 5) {
                QuantifierNode tqn = (QuantifierNode)en.target;
                if (QuantifierNode.isRepeatInfinite(tqn.upper) && tqn.lower <= 1 && tqn.greedy)
                  if (tqn.target.isSimple())
                    en.setStopBtSimpleRepeat();  
              } 
              break;
          } 
          break;
        case 7:
          an = (AnchorNode)node;
          switch (an.type) {
            case 1024:
              setupTree(an.target, state);
              break;
            case 2048:
              setupTree(an.target, state | 0x2);
              break;
            case 4096:
              if (checkTypeTree(an.target, 2031, 1, 4135))
                throw new SyntaxException("invalid pattern in look-behind"); 
              node = setupLookBehind(node);
              if (node.getType() != 7)
                continue; 
              setupTree(((AnchorNode)node).target, state);
              break;
            case 8192:
              if (checkTypeTree(an.target, 2031, 1, 4135))
                throw new SyntaxException("invalid pattern in look-behind"); 
              node = setupLookBehind(node);
              if (node.getType() != 7)
                continue; 
              setupTree(((AnchorNode)node).target, state | 0x2);
              break;
          } 
          break;
      } 
      break;
    } 
    return node;
  }
  
  private void optimizeNodeLeft(Node node, NodeOptInfo opt, OptEnvironment oenv) {
    OptEnvironment nenv;
    NodeOptInfo nodeOptInfo1;
    StringNode sn;
    CClassNode cc;
    AnchorNode an;
    BackRefNode br;
    NodeOptInfo nopt;
    EncloseNode en;
    NodeOptInfo nodeOptInfo3;
    ConsAltNode aln;
    int slen;
    NodeOptInfo nodeOptInfo2;
    QuantifierNode qn;
    int save;
    ConsAltNode lin;
    int min, max;
    opt.clear();
    opt.setBoundNode(oenv.mmd);
    switch (node.getType()) {
      case 8:
        nenv = new OptEnvironment();
        nodeOptInfo3 = new NodeOptInfo();
        nenv.copy(oenv);
        lin = (ConsAltNode)node;
        do {
          optimizeNodeLeft(lin.car, nodeOptInfo3, nenv);
          nenv.mmd.add(nodeOptInfo3.length);
          opt.concatLeftNode(nodeOptInfo3);
        } while ((lin = lin.cdr) != null);
        return;
      case 9:
        nodeOptInfo1 = new NodeOptInfo();
        aln = (ConsAltNode)node;
        do {
          optimizeNodeLeft(aln.car, nodeOptInfo1, oenv);
          if (aln == node) {
            opt.copy(nodeOptInfo1);
          } else {
            opt.altMerge(nodeOptInfo1, oenv);
          } 
        } while ((aln = aln.cdr) != null);
        return;
      case 0:
        sn = (StringNode)node;
        slen = sn.length();
        if (!sn.isAmbig()) {
          opt.exb.concatStr(sn.chars, sn.p, sn.end, sn.isRaw());
          if (slen > 0)
            opt.map.addChar(sn.chars[sn.p]); 
          opt.length.set(slen, slen);
        } else {
          int i;
          if (sn.isDontGetOptInfo()) {
            i = sn.length();
          } else {
            opt.exb.concatStr(sn.chars, sn.p, sn.end, sn.isRaw());
            opt.exb.ignoreCase = true;
            if (slen > 0)
              opt.map.addCharAmb(sn.chars, sn.p, sn.end, oenv.caseFoldFlag); 
            i = slen;
          } 
          opt.length.set(slen, i);
        } 
        if (opt.exb.length == slen)
          opt.exb.reachEnd = true; 
        return;
      case 1:
        cc = (CClassNode)node;
        if (cc.mbuf != null || cc.isNot()) {
          opt.length.set(1, 1);
        } else {
          for (int i = 0; i < 256; i++) {
            boolean z = cc.bs.at(i);
            if ((z && !cc.isNot()) || (!z && cc.isNot()))
              opt.map.addChar(i); 
          } 
          opt.length.set(1, 1);
        } 
        return;
      case 3:
        opt.length.set(1, 1);
        return;
      case 7:
        an = (AnchorNode)node;
        switch (an.type) {
          case 1:
          case 2:
          case 4:
          case 8:
          case 16:
          case 32:
            opt.anchor.add(an.type);
            break;
          case 1024:
            nodeOptInfo2 = new NodeOptInfo();
            optimizeNodeLeft(an.target, nodeOptInfo2, oenv);
            if (nodeOptInfo2.exb.length > 0) {
              opt.expr.copy(nodeOptInfo2.exb);
            } else if (nodeOptInfo2.exm.length > 0) {
              opt.expr.copy(nodeOptInfo2.exm);
            } 
            opt.expr.reachEnd = false;
            if (nodeOptInfo2.map.value > 0)
              opt.map.copy(nodeOptInfo2.map); 
            break;
        } 
        return;
      case 4:
        br = (BackRefNode)node;
        if (br.isRecursion()) {
          opt.length.set(0, 2147483647);
        } else {
          Node[] nodes = oenv.scanEnv.memNodes;
          int i = getMinMatchLength(nodes[br.backRef]);
          max = getMaxMatchLength(nodes[br.backRef]);
          opt.length.set(i, max);
        } 
        return;
      case 5:
        nopt = new NodeOptInfo();
        qn = (QuantifierNode)node;
        optimizeNodeLeft(qn.target, nopt, oenv);
        if (qn.lower == 0 && QuantifierNode.isRepeatInfinite(qn.upper)) {
          if (oenv.mmd.max == 0 && qn.target.getType() == 3 && qn.greedy)
            if (Option.isMultiline(oenv.options)) {
              opt.anchor.add(32768);
            } else {
              opt.anchor.add(16384);
            }  
        } else if (qn.lower > 0) {
          opt.copy(nopt);
          if (nopt.exb.length > 0 && 
            nopt.exb.reachEnd) {
            int i;
            for (i = 2; i <= qn.lower && !opt.exb.isFull(); i++)
              opt.exb.concat(nopt.exb); 
            if (i < qn.lower)
              opt.exb.reachEnd = false; 
          } 
          if (qn.lower != qn.upper) {
            opt.exb.reachEnd = false;
            opt.exm.reachEnd = false;
          } 
          if (qn.lower > 1)
            opt.exm.reachEnd = false; 
        } 
        min = MinMaxLen.distanceMultiply(nopt.length.min, qn.lower);
        if (QuantifierNode.isRepeatInfinite(qn.upper)) {
          max = (nopt.length.max > 0) ? Integer.MAX_VALUE : 0;
        } else {
          max = MinMaxLen.distanceMultiply(nopt.length.max, qn.upper);
        } 
        opt.length.set(min, max);
        return;
      case 6:
        en = (EncloseNode)node;
        switch (en.type) {
          case 2:
            save = oenv.options;
            oenv.options = en.option;
            optimizeNodeLeft(en.target, opt, oenv);
            oenv.options = save;
            break;
          case 1:
            if (++en.optCount > 5) {
              min = 0;
              max = Integer.MAX_VALUE;
              if (en.isMinFixed())
                min = en.minLength; 
              if (en.isMaxFixed())
                max = en.maxLength; 
              opt.length.set(min, max);
              break;
            } 
            optimizeNodeLeft(en.target, opt, oenv);
            if (opt.anchor.isSet(49152) && 
              BitStatus.bsAt(oenv.scanEnv.backrefedMem, en.regNum))
              opt.anchor.remove(49152); 
            break;
          case 4:
            optimizeNodeLeft(en.target, opt, oenv);
            break;
        } 
        return;
    } 
    throw new InternalException("internal parser error (bug)");
  }
  
  protected final void setOptimizedInfoFromTree(Node node) {
    NodeOptInfo opt = new NodeOptInfo();
    OptEnvironment oenv = new OptEnvironment();
    oenv.options = this.regex.options;
    oenv.caseFoldFlag = this.regex.caseFoldFlag;
    oenv.scanEnv = this.env;
    oenv.mmd.clear();
    optimizeNodeLeft(node, opt, oenv);
    this.regex.anchor = opt.anchor.leftAnchor & 0xC005;
    this.regex.anchor |= opt.anchor.rightAnchor & 0x18;
    if ((this.regex.anchor & 0x18) != 0) {
      this.regex.anchorDmin = opt.length.min;
      this.regex.anchorDmax = opt.length.max;
    } 
    if (opt.exb.length > 0 || opt.exm.length > 0) {
      opt.exb.select(opt.exm);
      if (opt.map.value > 0 && opt.exb.compare(opt.map) > 0) {
        this.regex.setOptimizeMapInfo(opt.map);
        this.regex.setSubAnchor(opt.map.anchor);
      } else {
        this.regex.setExactInfo(opt.exb);
        this.regex.setSubAnchor(opt.exb.anchor);
      } 
    } else if (opt.map.value > 0) {
      this.regex.setOptimizeMapInfo(opt.map);
      this.regex.setSubAnchor(opt.map.anchor);
    } else {
      this.regex.subAnchor |= opt.anchor.leftAnchor & 0x2;
      if (opt.length.max == 0)
        this.regex.subAnchor |= opt.anchor.rightAnchor & 0x20; 
    } 
  }
}
