package org.tritonus.share.midi;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import org.tritonus.share.ArraySet;
import org.tritonus.share.TDebug;

public abstract class TSequencer extends TMidiDevice implements Sequencer {
  private static final float MPQ_BPM_FACTOR = 6.0E7F;
  
  private static final Sequencer.SyncMode[] EMPTY_SYNCMODE_ARRAY = new Sequencer.SyncMode[0];
  
  private boolean m_bRunning;
  
  private Sequence m_sequence;
  
  private Set<MetaEventListener> m_metaListeners;
  
  private Set<ControllerEventListener>[] m_aControllerListeners;
  
  private float m_fNominalTempoInMPQ;
  
  private float m_fTempoFactor;
  
  private Collection<Sequencer.SyncMode> m_masterSyncModes;
  
  private Collection<Sequencer.SyncMode> m_slaveSyncModes;
  
  private Sequencer.SyncMode m_masterSyncMode;
  
  private Sequencer.SyncMode m_slaveSyncMode;
  
  private BitSet m_muteBitSet;
  
  private BitSet m_soloBitSet;
  
  private BitSet m_enabledBitSet;
  
  private long m_lLoopStartPoint;
  
  private long m_lLoopEndPoint;
  
  private int m_nLoopCount;
  
  protected TSequencer(MidiDevice.Info info, Collection<Sequencer.SyncMode> masterSyncModes, Collection<Sequencer.SyncMode> slaveSyncModes) {
    super(info);
    this.m_bRunning = false;
    this.m_sequence = null;
    this.m_metaListeners = (Set<MetaEventListener>)new ArraySet();
    this.m_aControllerListeners = (Set<ControllerEventListener>[])new Set[128];
    setTempoFactor(1.0F);
    setTempoInMPQ(500000.0F);
    this.m_masterSyncModes = masterSyncModes;
    this.m_slaveSyncModes = slaveSyncModes;
    if ((getMasterSyncModes()).length > 0)
      this.m_masterSyncMode = getMasterSyncModes()[0]; 
    if ((getSlaveSyncModes()).length > 0)
      this.m_slaveSyncMode = getSlaveSyncModes()[0]; 
    this.m_muteBitSet = new BitSet();
    this.m_soloBitSet = new BitSet();
    this.m_enabledBitSet = new BitSet();
    updateEnabled();
    setLoopStartPoint(0L);
    setLoopEndPoint(-1L);
    setLoopCount(0);
  }
  
  public void setSequence(Sequence sequence) throws InvalidMidiDataException {
    if (getSequence() != sequence) {
      this.m_sequence = sequence;
      setSequenceImpl();
      setTempoFactor(1.0F);
    } 
  }
  
  protected void setSequenceImpl() {}
  
  public void setSequence(InputStream inputStream) throws InvalidMidiDataException, IOException {
    Sequence sequence = MidiSystem.getSequence(inputStream);
    setSequence(sequence);
  }
  
  public Sequence getSequence() {
    return this.m_sequence;
  }
  
  public void setLoopStartPoint(long lTick) {
    this.m_lLoopStartPoint = lTick;
  }
  
  public long getLoopStartPoint() {
    return this.m_lLoopStartPoint;
  }
  
  public void setLoopEndPoint(long lTick) {
    this.m_lLoopEndPoint = lTick;
  }
  
  public long getLoopEndPoint() {
    return this.m_lLoopEndPoint;
  }
  
  public void setLoopCount(int nLoopCount) {
    this.m_nLoopCount = nLoopCount;
  }
  
  public int getLoopCount() {
    return this.m_nLoopCount;
  }
  
  public synchronized void start() {
    checkOpen();
    if (!isRunning()) {
      this.m_bRunning = true;
      startImpl();
    } 
  }
  
  protected void startImpl() {}
  
  public synchronized void stop() {
    checkOpen();
    if (isRunning()) {
      stopImpl();
      this.m_bRunning = false;
    } 
  }
  
  protected void stopImpl() {}
  
  public synchronized boolean isRunning() {
    return this.m_bRunning;
  }
  
  protected void checkOpen() {
    if (!isOpen())
      throw new IllegalStateException("Sequencer is not open"); 
  }
  
  protected int getResolution() {
    int nResolution;
    Sequence sequence = getSequence();
    if (sequence != null) {
      nResolution = sequence.getResolution();
    } else {
      nResolution = 1;
    } 
    return nResolution;
  }
  
  protected void setRealTempo() {
    float fTempoFactor = getTempoFactor();
    if (fTempoFactor == 0.0F)
      fTempoFactor = 0.01F; 
    float fRealTempo = getTempoInMPQ() / fTempoFactor;
    if (TDebug.TraceSequencer)
      TDebug.out("TSequencer.setRealTempo(): real tempo: " + fRealTempo); 
    setTempoImpl(fRealTempo);
  }
  
  public float getTempoInBPM() {
    float fBPM = 6.0E7F / getTempoInMPQ();
    return fBPM;
  }
  
  public void setTempoInBPM(float fBPM) {
    float fMPQ = 6.0E7F / fBPM;
    setTempoInMPQ(fMPQ);
  }
  
  public float getTempoInMPQ() {
    return this.m_fNominalTempoInMPQ;
  }
  
  public void setTempoInMPQ(float fMPQ) {
    this.m_fNominalTempoInMPQ = fMPQ;
    setRealTempo();
  }
  
  public void setTempoFactor(float fFactor) {
    this.m_fTempoFactor = fFactor;
    setRealTempo();
  }
  
  public float getTempoFactor() {
    return this.m_fTempoFactor;
  }
  
  protected abstract void setTempoImpl(float paramFloat);
  
  public long getTickLength() {
    long lLength = 0L;
    if (getSequence() != null)
      lLength = getSequence().getTickLength(); 
    return lLength;
  }
  
  public long getMicrosecondLength() {
    long lLength = 0L;
    if (getSequence() != null)
      lLength = getSequence().getMicrosecondLength(); 
    return lLength;
  }
  
  public boolean addMetaEventListener(MetaEventListener listener) {
    synchronized (this.m_metaListeners) {
      return this.m_metaListeners.add(listener);
    } 
  }
  
  public void removeMetaEventListener(MetaEventListener listener) {
    synchronized (this.m_metaListeners) {
      this.m_metaListeners.remove(listener);
    } 
  }
  
  protected Iterator<MetaEventListener> getMetaEventListeners() {
    synchronized (this.m_metaListeners) {
      return this.m_metaListeners.iterator();
    } 
  }
  
  protected void sendMetaMessage(MetaMessage message) {
    Iterator<MetaEventListener> iterator = getMetaEventListeners();
    while (iterator.hasNext()) {
      MetaEventListener metaEventListener = iterator.next();
      MetaMessage copiedMessage = (MetaMessage)message.clone();
      metaEventListener.meta(copiedMessage);
    } 
  }
  
  public int[] addControllerEventListener(ControllerEventListener listener, int[] anControllers) {
    synchronized (this.m_aControllerListeners) {
      if (anControllers == null) {
        for (int i = 0; i < 128; i++)
          addControllerListener(i, listener); 
      } else {
        for (int i = 0; i < anControllers.length; i++)
          addControllerListener(anControllers[i], listener); 
      } 
    } 
    return getListenedControllers(listener);
  }
  
  private void addControllerListener(int i, ControllerEventListener listener) {
    if (this.m_aControllerListeners[i] == null)
      this.m_aControllerListeners[i] = (Set<ControllerEventListener>)new ArraySet(); 
    this.m_aControllerListeners[i].add(listener);
  }
  
  public int[] removeControllerEventListener(ControllerEventListener listener, int[] anControllers) {
    synchronized (this.m_aControllerListeners) {
      if (anControllers == null) {
        for (int i = 0; i < 128; i++)
          removeControllerListener(i, listener); 
      } else {
        for (int i = 0; i < anControllers.length; i++)
          removeControllerListener(anControllers[i], listener); 
      } 
    } 
    return getListenedControllers(listener);
  }
  
  private void removeControllerListener(int i, ControllerEventListener listener) {
    if (this.m_aControllerListeners[i] != null)
      this.m_aControllerListeners[i].add(listener); 
  }
  
  private int[] getListenedControllers(ControllerEventListener listener) {
    int[] anControllers = new int[128];
    int nIndex = 0;
    for (int nController = 0; nController < 128; nController++) {
      if (this.m_aControllerListeners[nController] != null && this.m_aControllerListeners[nController]
        .contains(listener)) {
        anControllers[nIndex] = nController;
        nIndex++;
      } 
    } 
    int[] anResultControllers = new int[nIndex];
    System.arraycopy(anControllers, 0, anResultControllers, 0, nIndex);
    return anResultControllers;
  }
  
  protected void sendControllerEvent(ShortMessage message) {
    int nController = message.getData1();
    if (this.m_aControllerListeners[nController] != null) {
      Iterator<ControllerEventListener> iterator = this.m_aControllerListeners[nController].iterator();
      while (iterator.hasNext()) {
        ControllerEventListener controllerEventListener = iterator.next();
        ShortMessage copiedMessage = (ShortMessage)message.clone();
        controllerEventListener.controlChange(copiedMessage);
      } 
    } 
  }
  
  protected void notifyListeners(MidiMessage message) {
    if (message instanceof MetaMessage) {
      sendMetaMessage((MetaMessage)message);
    } else if (message instanceof ShortMessage && ((ShortMessage)message).getCommand() == 176) {
      sendControllerEvent((ShortMessage)message);
    } 
  }
  
  public Sequencer.SyncMode getMasterSyncMode() {
    return this.m_masterSyncMode;
  }
  
  public void setMasterSyncMode(Sequencer.SyncMode syncMode) {
    if (this.m_masterSyncModes.contains(syncMode)) {
      if (!getMasterSyncMode().equals(syncMode)) {
        this.m_masterSyncMode = syncMode;
        setMasterSyncModeImpl(syncMode);
      } 
    } else {
      throw new IllegalArgumentException("sync mode not allowed: " + syncMode);
    } 
  }
  
  protected void setMasterSyncModeImpl(Sequencer.SyncMode syncMode) {}
  
  public Sequencer.SyncMode[] getMasterSyncModes() {
    Sequencer.SyncMode[] syncModes = this.m_masterSyncModes.<Sequencer.SyncMode>toArray(EMPTY_SYNCMODE_ARRAY);
    return syncModes;
  }
  
  public Sequencer.SyncMode getSlaveSyncMode() {
    return this.m_slaveSyncMode;
  }
  
  public void setSlaveSyncMode(Sequencer.SyncMode syncMode) {
    if (this.m_slaveSyncModes.contains(syncMode)) {
      if (!getSlaveSyncMode().equals(syncMode)) {
        this.m_slaveSyncMode = syncMode;
        setSlaveSyncModeImpl(syncMode);
      } 
    } else {
      throw new IllegalArgumentException("sync mode not allowed: " + syncMode);
    } 
  }
  
  protected void setSlaveSyncModeImpl(Sequencer.SyncMode syncMode) {}
  
  public Sequencer.SyncMode[] getSlaveSyncModes() {
    Sequencer.SyncMode[] syncModes = this.m_slaveSyncModes.<Sequencer.SyncMode>toArray(EMPTY_SYNCMODE_ARRAY);
    return syncModes;
  }
  
  public boolean getTrackSolo(int nTrack) {
    boolean bSoloed = false;
    if (getSequence() != null)
      if (nTrack < (getSequence().getTracks()).length)
        bSoloed = this.m_soloBitSet.get(nTrack);  
    return bSoloed;
  }
  
  public void setTrackSolo(int nTrack, boolean bSolo) {
    if (getSequence() != null)
      if (nTrack < (getSequence().getTracks()).length) {
        boolean bOldState = this.m_soloBitSet.get(nTrack);
        if (bSolo != bOldState) {
          if (bSolo) {
            this.m_soloBitSet.set(nTrack);
          } else {
            this.m_soloBitSet.clear(nTrack);
          } 
          updateEnabled();
          setTrackSoloImpl(nTrack, bSolo);
        } 
      }  
  }
  
  protected void setTrackSoloImpl(int nTrack, boolean bSolo) {}
  
  public boolean getTrackMute(int nTrack) {
    boolean bMuted = false;
    if (getSequence() != null)
      if (nTrack < (getSequence().getTracks()).length)
        bMuted = this.m_muteBitSet.get(nTrack);  
    return bMuted;
  }
  
  public void setTrackMute(int nTrack, boolean bMute) {
    if (getSequence() != null)
      if (nTrack < (getSequence().getTracks()).length) {
        boolean bOldState = this.m_muteBitSet.get(nTrack);
        if (bMute != bOldState) {
          if (bMute) {
            this.m_muteBitSet.set(nTrack);
          } else {
            this.m_muteBitSet.clear(nTrack);
          } 
          updateEnabled();
          setTrackMuteImpl(nTrack, bMute);
        } 
      }  
  }
  
  protected void setTrackMuteImpl(int nTrack, boolean bMute) {}
  
  private void updateEnabled() {
    BitSet oldEnabledBitSet = (BitSet)this.m_enabledBitSet.clone();
    boolean bSoloExists = (this.m_soloBitSet.length() > 0);
    if (bSoloExists) {
      this.m_enabledBitSet = (BitSet)this.m_soloBitSet.clone();
    } else {
      for (int j = 0; j < this.m_muteBitSet.size(); j++) {
        if (this.m_muteBitSet.get(j)) {
          this.m_enabledBitSet.clear(j);
        } else {
          this.m_enabledBitSet.set(j);
        } 
      } 
    } 
    oldEnabledBitSet.xor(this.m_enabledBitSet);
    for (int i = 0; i < oldEnabledBitSet.size(); i++) {
      if (oldEnabledBitSet.get(i))
        setTrackEnabledImpl(i, this.m_enabledBitSet.get(i)); 
    } 
  }
  
  protected void setTrackEnabledImpl(int nTrack, boolean bEnabled) {}
  
  protected boolean isTrackEnabled(int nTrack) {
    return this.m_enabledBitSet.get(nTrack);
  }
  
  public void setLatency(int nMilliseconds) {}
  
  public int getLatency() {
    return -1;
  }
}
