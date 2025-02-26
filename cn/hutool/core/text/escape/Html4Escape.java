package cn.hutool.core.text.escape;

import cn.hutool.core.text.replacer.LookupReplacer;
import cn.hutool.core.text.replacer.StrReplacer;

public class Html4Escape extends XmlEscape {
  private static final long serialVersionUID = 1L;
  
  protected static final String[][] ISO8859_1_ESCAPE = new String[][] { 
      { " ", "&nbsp;" }, { "¡", "&iexcl;" }, { "¢", "&cent;" }, { "£", "&pound;" }, { "¤", "&curren;" }, { "¥", "&yen;" }, { "¦", "&brvbar;" }, { "§", "&sect;" }, { "¨", "&uml;" }, { "©", "&copy;" }, 
      { "ª", "&ordf;" }, { "«", "&laquo;" }, { "¬", "&not;" }, { "­", "&shy;" }, { "®", "&reg;" }, { "¯", "&macr;" }, { "°", "&deg;" }, { "±", "&plusmn;" }, { "²", "&sup2;" }, { "³", "&sup3;" }, 
      { "´", "&acute;" }, { "µ", "&micro;" }, { "¶", "&para;" }, { "·", "&middot;" }, { "¸", "&cedil;" }, { "¹", "&sup1;" }, { "º", "&ordm;" }, { "»", "&raquo;" }, { "¼", "&frac14;" }, { "½", "&frac12;" }, 
      { "¾", "&frac34;" }, { "¿", "&iquest;" }, { "À", "&Agrave;" }, { "Á", "&Aacute;" }, { "Â", "&Acirc;" }, { "Ã", "&Atilde;" }, { "Ä", "&Auml;" }, { "Å", "&Aring;" }, { "Æ", "&AElig;" }, { "Ç", "&Ccedil;" }, 
      { "È", "&Egrave;" }, { "É", "&Eacute;" }, { "Ê", "&Ecirc;" }, { "Ë", "&Euml;" }, { "Ì", "&Igrave;" }, { "Í", "&Iacute;" }, { "Î", "&Icirc;" }, { "Ï", "&Iuml;" }, { "Ð", "&ETH;" }, { "Ñ", "&Ntilde;" }, 
      { "Ò", "&Ograve;" }, { "Ó", "&Oacute;" }, { "Ô", "&Ocirc;" }, { "Õ", "&Otilde;" }, { "Ö", "&Ouml;" }, { "×", "&times;" }, { "Ø", "&Oslash;" }, { "Ù", "&Ugrave;" }, { "Ú", "&Uacute;" }, { "Û", "&Ucirc;" }, 
      { "Ü", "&Uuml;" }, { "Ý", "&Yacute;" }, { "Þ", "&THORN;" }, { "ß", "&szlig;" }, { "à", "&agrave;" }, { "á", "&aacute;" }, { "â", "&acirc;" }, { "ã", "&atilde;" }, { "ä", "&auml;" }, { "å", "&aring;" }, 
      { "æ", "&aelig;" }, { "ç", "&ccedil;" }, { "è", "&egrave;" }, { "é", "&eacute;" }, { "ê", "&ecirc;" }, { "ë", "&euml;" }, { "ì", "&igrave;" }, { "í", "&iacute;" }, { "î", "&icirc;" }, { "ï", "&iuml;" }, 
      { "ð", "&eth;" }, { "ñ", "&ntilde;" }, { "ò", "&ograve;" }, { "ó", "&oacute;" }, { "ô", "&ocirc;" }, { "õ", "&otilde;" }, { "ö", "&ouml;" }, { "÷", "&divide;" }, { "ø", "&oslash;" }, { "ù", "&ugrave;" }, 
      { "ú", "&uacute;" }, { "û", "&ucirc;" }, { "ü", "&uuml;" }, { "ý", "&yacute;" }, { "þ", "&thorn;" }, { "ÿ", "&yuml;" } };
  
  protected static final String[][] HTML40_EXTENDED_ESCAPE = new String[][] { 
      { "ƒ", "&fnof;" }, { "Α", "&Alpha;" }, { "Β", "&Beta;" }, { "Γ", "&Gamma;" }, { "Δ", "&Delta;" }, { "Ε", "&Epsilon;" }, { "Ζ", "&Zeta;" }, { "Η", "&Eta;" }, { "Θ", "&Theta;" }, { "Ι", "&Iota;" }, 
      { "Κ", "&Kappa;" }, { "Λ", "&Lambda;" }, { "Μ", "&Mu;" }, { "Ν", "&Nu;" }, { "Ξ", "&Xi;" }, { "Ο", "&Omicron;" }, { "Π", "&Pi;" }, { "Ρ", "&Rho;" }, { "Σ", "&Sigma;" }, { "Τ", "&Tau;" }, 
      { "Υ", "&Upsilon;" }, { "Φ", "&Phi;" }, { "Χ", "&Chi;" }, { "Ψ", "&Psi;" }, { "Ω", "&Omega;" }, { "α", "&alpha;" }, { "β", "&beta;" }, { "γ", "&gamma;" }, { "δ", "&delta;" }, { "ε", "&epsilon;" }, 
      { "ζ", "&zeta;" }, { "η", "&eta;" }, { "θ", "&theta;" }, { "ι", "&iota;" }, { "κ", "&kappa;" }, { "λ", "&lambda;" }, { "μ", "&mu;" }, { "ν", "&nu;" }, { "ξ", "&xi;" }, { "ο", "&omicron;" }, 
      { "π", "&pi;" }, { "ρ", "&rho;" }, { "ς", "&sigmaf;" }, { "σ", "&sigma;" }, { "τ", "&tau;" }, { "υ", "&upsilon;" }, { "φ", "&phi;" }, { "χ", "&chi;" }, { "ψ", "&psi;" }, { "ω", "&omega;" }, 
      { "ϑ", "&thetasym;" }, { "ϒ", "&upsih;" }, { "ϖ", "&piv;" }, { "•", "&bull;" }, { "…", "&hellip;" }, { "′", "&prime;" }, { "″", "&Prime;" }, { "‾", "&oline;" }, { "⁄", "&frasl;" }, { "℘", "&weierp;" }, 
      { "ℑ", "&image;" }, { "ℜ", "&real;" }, { "™", "&trade;" }, { "ℵ", "&alefsym;" }, { "←", "&larr;" }, { "↑", "&uarr;" }, { "→", "&rarr;" }, { "↓", "&darr;" }, { "↔", "&harr;" }, { "↵", "&crarr;" }, 
      { "⇐", "&lArr;" }, { "⇑", "&uArr;" }, { "⇒", "&rArr;" }, { "⇓", "&dArr;" }, { "⇔", "&hArr;" }, { "∀", "&forall;" }, { "∂", "&part;" }, { "∃", "&exist;" }, { "∅", "&empty;" }, { "∇", "&nabla;" }, 
      { "∈", "&isin;" }, { "∉", "&notin;" }, { "∋", "&ni;" }, { "∏", "&prod;" }, { "∑", "&sum;" }, { "−", "&minus;" }, { "∗", "&lowast;" }, { "√", "&radic;" }, { "∝", "&prop;" }, { "∞", "&infin;" }, 
      { "∠", "&ang;" }, { "∧", "&and;" }, { "∨", "&or;" }, { "∩", "&cap;" }, { "∪", "&cup;" }, { "∫", "&int;" }, { "∴", "&there4;" }, { "∼", "&sim;" }, { "≅", "&cong;" }, { "≈", "&asymp;" }, 
      { "≠", "&ne;" }, { "≡", "&equiv;" }, { "≤", "&le;" }, { "≥", "&ge;" }, { "⊂", "&sub;" }, { "⊃", "&sup;" }, { "⊆", "&sube;" }, { "⊇", "&supe;" }, { "⊕", "&oplus;" }, { "⊗", "&otimes;" }, 
      { "⊥", "&perp;" }, { "⋅", "&sdot;" }, { "⌈", "&lceil;" }, { "⌉", "&rceil;" }, { "⌊", "&lfloor;" }, { "⌋", "&rfloor;" }, { "〈", "&lang;" }, { "〉", "&rang;" }, { "◊", "&loz;" }, { "♠", "&spades;" }, 
      { "♣", "&clubs;" }, { "♥", "&hearts;" }, { "♦", "&diams;" }, { "Œ", "&OElig;" }, { "œ", "&oelig;" }, { "Š", "&Scaron;" }, { "š", "&scaron;" }, { "Ÿ", "&Yuml;" }, { "ˆ", "&circ;" }, { "˜", "&tilde;" }, 
      { " ", "&ensp;" }, { " ", "&emsp;" }, { " ", "&thinsp;" }, { "‌", "&zwnj;" }, { "‍", "&zwj;" }, { "‎", "&lrm;" }, { "‏", "&rlm;" }, { "–", "&ndash;" }, { "—", "&mdash;" }, { "‘", "&lsquo;" }, 
      { "’", "&rsquo;" }, { "‚", "&sbquo;" }, { "“", "&ldquo;" }, { "”", "&rdquo;" }, { "„", "&bdquo;" }, { "†", "&dagger;" }, { "‡", "&Dagger;" }, { "‰", "&permil;" }, { "‹", "&lsaquo;" }, { "›", "&rsaquo;" }, 
      { "€", "&euro;" } };
  
  public Html4Escape() {
    addChain((StrReplacer)new LookupReplacer(ISO8859_1_ESCAPE));
    addChain((StrReplacer)new LookupReplacer(HTML40_EXTENDED_ESCAPE));
  }
}
