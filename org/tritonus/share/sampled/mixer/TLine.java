package org.tritonus.share.sampled.mixer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import org.tritonus.share.TDebug;
import org.tritonus.share.TNotifier;

public abstract class TLine implements Line {
  private static final Control[] EMPTY_CONTROL_ARRAY = new Control[0];
  
  private Line.Info m_info;
  
  private boolean m_bOpen;
  
  private List<Control> m_controls;
  
  private Set<LineListener> m_lineListeners;
  
  private TMixer m_mixer;
  
  protected TLine(TMixer mixer, Line.Info info) {
    setLineInfo(info);
    setOpen(false);
    this.m_controls = new ArrayList<Control>();
    this.m_lineListeners = new HashSet<LineListener>();
    this.m_mixer = mixer;
  }
  
  protected TLine(TMixer mixer, Line.Info info, Collection<Control> controls) {
    this(mixer, info);
    this.m_controls.addAll(controls);
  }
  
  protected TMixer getMixer() {
    return this.m_mixer;
  }
  
  public Line.Info getLineInfo() {
    return this.m_info;
  }
  
  protected void setLineInfo(Line.Info info) {
    if (TDebug.TraceLine)
      TDebug.out("TLine.setLineInfo(): setting: " + info); 
    synchronized (this) {
      this.m_info = info;
    } 
  }
  
  public void open() throws LineUnavailableException {
    if (TDebug.TraceLine)
      TDebug.out("TLine.open(): called"); 
    if (!isOpen()) {
      if (TDebug.TraceLine)
        TDebug.out("TLine.open(): opening"); 
      openImpl();
      if (getMixer() != null)
        getMixer().registerOpenLine(this); 
      setOpen(true);
    } else if (TDebug.TraceLine) {
      TDebug.out("TLine.open(): already open");
    } 
  }
  
  protected void openImpl() throws LineUnavailableException {
    if (TDebug.TraceLine)
      TDebug.out("TLine.openImpl(): called"); 
  }
  
  public void close() {
    if (TDebug.TraceLine)
      TDebug.out("TLine.close(): called"); 
    if (isOpen()) {
      if (TDebug.TraceLine)
        TDebug.out("TLine.close(): closing"); 
      if (getMixer() != null)
        getMixer().unregisterOpenLine(this); 
      closeImpl();
      setOpen(false);
    } else if (TDebug.TraceLine) {
      TDebug.out("TLine.close(): not open");
    } 
  }
  
  protected void closeImpl() {
    if (TDebug.TraceLine)
      TDebug.out("TLine.closeImpl(): called"); 
  }
  
  public boolean isOpen() {
    return this.m_bOpen;
  }
  
  protected void setOpen(boolean bOpen) {
    if (TDebug.TraceLine)
      TDebug.out("TLine.setOpen(): called, value: " + bOpen); 
    boolean bOldValue = isOpen();
    this.m_bOpen = bOpen;
    if (bOldValue != isOpen())
      if (isOpen()) {
        if (TDebug.TraceLine)
          TDebug.out("TLine.setOpen(): opened"); 
        notifyLineEvent(LineEvent.Type.OPEN);
      } else {
        if (TDebug.TraceLine)
          TDebug.out("TLine.setOpen(): closed"); 
        notifyLineEvent(LineEvent.Type.CLOSE);
      }  
  }
  
  protected void addControl(Control control) {
    synchronized (this.m_controls) {
      this.m_controls.add(control);
    } 
  }
  
  protected void removeControl(Control control) {
    synchronized (this.m_controls) {
      this.m_controls.remove(control);
    } 
  }
  
  public Control[] getControls() {
    synchronized (this.m_controls) {
      return this.m_controls.<Control>toArray(EMPTY_CONTROL_ARRAY);
    } 
  }
  
  public Control getControl(Control.Type controlType) {
    synchronized (this.m_controls) {
      Iterator<Control> it = this.m_controls.iterator();
      while (it.hasNext()) {
        Control control = it.next();
        if (control.getType().equals(controlType))
          return control; 
      } 
      throw new IllegalArgumentException("no control of type " + controlType);
    } 
  }
  
  public boolean isControlSupported(Control.Type controlType) {
    try {
      return (getControl(controlType) != null);
    } catch (IllegalArgumentException e) {
      if (TDebug.TraceAllExceptions)
        TDebug.out(e); 
      return false;
    } 
  }
  
  public void addLineListener(LineListener listener) {
    synchronized (this.m_lineListeners) {
      this.m_lineListeners.add(listener);
    } 
  }
  
  public void removeLineListener(LineListener listener) {
    synchronized (this.m_lineListeners) {
      this.m_lineListeners.remove(listener);
    } 
  }
  
  private Set<LineListener> getLineListeners() {
    synchronized (this.m_lineListeners) {
      return new HashSet<LineListener>(this.m_lineListeners);
    } 
  }
  
  protected void notifyLineEvent(LineEvent.Type type) {
    notifyLineEvent(new LineEvent(this, type, -1L));
  }
  
  protected void notifyLineEvent(LineEvent event) {
    TNotifier.notifier.addEntry(event, getLineListeners());
  }
}
