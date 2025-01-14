package cn.hutool.core.swing.clipboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.Serializable;

public abstract class StrClipboardListener implements ClipboardListener, Serializable {
  private static final long serialVersionUID = 1L;
  
  public Transferable onChange(Clipboard clipboard, Transferable contents) {
    if (contents.isDataFlavorSupported(DataFlavor.stringFlavor))
      return onChange(clipboard, ClipboardUtil.getStr(contents)); 
    return null;
  }
  
  public abstract Transferable onChange(Clipboard paramClipboard, String paramString);
}
