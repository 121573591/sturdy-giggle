package org.openjdk.nashorn.internal.runtime.regexp.joni.constants;

public interface OPCode {
  public static final int FINISH = 0;
  
  public static final int END = 1;
  
  public static final int EXACT1 = 2;
  
  public static final int EXACT2 = 3;
  
  public static final int EXACT3 = 4;
  
  public static final int EXACT4 = 5;
  
  public static final int EXACT5 = 6;
  
  public static final int EXACTN = 7;
  
  public static final int EXACT1_IC = 14;
  
  public static final int EXACTN_IC = 15;
  
  public static final int CCLASS = 16;
  
  public static final int CCLASS_MB = 17;
  
  public static final int CCLASS_MIX = 18;
  
  public static final int CCLASS_NOT = 19;
  
  public static final int CCLASS_MB_NOT = 20;
  
  public static final int CCLASS_MIX_NOT = 21;
  
  public static final int CCLASS_NODE = 22;
  
  public static final int ANYCHAR = 23;
  
  public static final int ANYCHAR_ML = 24;
  
  public static final int ANYCHAR_STAR = 25;
  
  public static final int ANYCHAR_ML_STAR = 26;
  
  public static final int ANYCHAR_STAR_PEEK_NEXT = 27;
  
  public static final int ANYCHAR_ML_STAR_PEEK_NEXT = 28;
  
  public static final int WORD = 29;
  
  public static final int NOT_WORD = 30;
  
  public static final int WORD_BOUND = 31;
  
  public static final int NOT_WORD_BOUND = 32;
  
  public static final int WORD_BEGIN = 33;
  
  public static final int WORD_END = 34;
  
  public static final int BEGIN_BUF = 35;
  
  public static final int END_BUF = 36;
  
  public static final int BEGIN_LINE = 37;
  
  public static final int END_LINE = 38;
  
  public static final int SEMI_END_BUF = 39;
  
  public static final int BEGIN_POSITION = 40;
  
  public static final int BACKREF1 = 41;
  
  public static final int BACKREF2 = 42;
  
  public static final int BACKREFN = 43;
  
  public static final int BACKREFN_IC = 44;
  
  public static final int BACKREF_MULTI = 45;
  
  public static final int BACKREF_MULTI_IC = 46;
  
  public static final int BACKREF_WITH_LEVEL = 47;
  
  public static final int MEMORY_START = 48;
  
  public static final int MEMORY_START_PUSH = 49;
  
  public static final int MEMORY_END_PUSH = 50;
  
  public static final int MEMORY_END_PUSH_REC = 51;
  
  public static final int MEMORY_END = 52;
  
  public static final int MEMORY_END_REC = 53;
  
  public static final int FAIL = 54;
  
  public static final int JUMP = 55;
  
  public static final int PUSH = 56;
  
  public static final int POP = 57;
  
  public static final int PUSH_OR_JUMP_EXACT1 = 58;
  
  public static final int PUSH_IF_PEEK_NEXT = 59;
  
  public static final int REPEAT = 60;
  
  public static final int REPEAT_NG = 61;
  
  public static final int REPEAT_INC = 62;
  
  public static final int REPEAT_INC_NG = 63;
  
  public static final int REPEAT_INC_SG = 64;
  
  public static final int REPEAT_INC_NG_SG = 65;
  
  public static final int NULL_CHECK_START = 66;
  
  public static final int NULL_CHECK_END = 67;
  
  public static final int NULL_CHECK_END_MEMST = 68;
  
  public static final int NULL_CHECK_END_MEMST_PUSH = 69;
  
  public static final int PUSH_POS = 70;
  
  public static final int POP_POS = 71;
  
  public static final int PUSH_POS_NOT = 72;
  
  public static final int FAIL_POS = 73;
  
  public static final int PUSH_STOP_BT = 74;
  
  public static final int POP_STOP_BT = 75;
  
  public static final int LOOK_BEHIND = 76;
  
  public static final int PUSH_LOOK_BEHIND_NOT = 77;
  
  public static final int FAIL_LOOK_BEHIND_NOT = 78;
  
  public static final int CALL = 79;
  
  public static final int RETURN = 80;
  
  public static final int STATE_CHECK_PUSH = 81;
  
  public static final int STATE_CHECK_PUSH_OR_JUMP = 82;
  
  public static final int STATE_CHECK = 83;
  
  public static final int STATE_CHECK_ANYCHAR_STAR = 84;
  
  public static final int STATE_CHECK_ANYCHAR_ML_STAR = 85;
  
  public static final int SET_OPTION_PUSH = 86;
  
  public static final int SET_OPTION = 87;
}
