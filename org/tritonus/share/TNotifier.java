package org.tritonus.share;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class TNotifier extends Thread {
  public static class NotifyEntry {
    private EventObject m_event;
    
    private List<LineListener> m_listeners;
    
    public NotifyEntry(EventObject event, Collection<LineListener> listeners) {
      this.m_event = event;
      this.m_listeners = new ArrayList<LineListener>(listeners);
    }
    
    public void deliver() {
      Iterator<LineListener> iterator = this.m_listeners.iterator();
      while (iterator.hasNext()) {
        LineListener listener = iterator.next();
        listener.update((LineEvent)this.m_event);
      } 
    }
  }
  
  public static TNotifier notifier = null;
  
  private List<NotifyEntry> m_entries;
  
  static {
    notifier = new TNotifier();
    notifier.setDaemon(true);
    notifier.start();
  }
  
  public TNotifier() {
    super("Tritonus Notifier");
    this.m_entries = new ArrayList<NotifyEntry>();
  }
  
  public void addEntry(EventObject event, Collection<LineListener> listeners) {
    synchronized (this.m_entries) {
      this.m_entries.add(new NotifyEntry(event, listeners));
      this.m_entries.notifyAll();
    } 
  }
  
  public void run() {
    while (true) {
      NotifyEntry entry = null;
      synchronized (this.m_entries) {
        while (this.m_entries.size() == 0) {
          try {
            this.m_entries.wait();
          } catch (InterruptedException e) {
            if (TDebug.TraceAllExceptions)
              TDebug.out(e); 
          } 
        } 
        entry = this.m_entries.remove(0);
      } 
      entry.deliver();
    } 
  }
}
