package org.tritonus.share.midi;

import java.util.Collection;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequencer;
import org.tritonus.share.TDebug;

public abstract class TPreloadingSequencer extends TSequencer {
  private static final int DEFAULT_LATENCY = 100;
  
  private int m_nLatency;
  
  private Thread m_loaderThread;
  
  protected TPreloadingSequencer(MidiDevice.Info info, Collection<Sequencer.SyncMode> masterSyncModes, Collection<Sequencer.SyncMode> slaveSyncModes) {
    super(info, masterSyncModes, slaveSyncModes);
    if (TDebug.TraceSequencer)
      TDebug.out("TPreloadingSequencer.<init>(): begin"); 
    this.m_nLatency = 100;
    if (TDebug.TraceSequencer)
      TDebug.out("TPreloadingSequencer.<init>(): end"); 
  }
  
  public void setLatency(int nLatency) {
    this.m_nLatency = nLatency;
  }
  
  public int getLatency() {
    return this.m_nLatency;
  }
  
  protected void openImpl() {
    if (TDebug.TraceSequencer)
      TDebug.out("AlsaSequencer.openImpl(): begin"); 
  }
  
  public abstract void sendMessageTick(MidiMessage paramMidiMessage, long paramLong);
}
