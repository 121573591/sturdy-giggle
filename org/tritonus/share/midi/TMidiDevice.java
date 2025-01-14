package org.tritonus.share.midi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import org.tritonus.share.TDebug;

public abstract class TMidiDevice implements MidiDevice {
  private MidiDevice.Info m_info;
  
  private boolean m_bDeviceOpen;
  
  private boolean m_bUseTransmitter;
  
  private boolean m_bUseReceiver;
  
  private List<Receiver> m_receivers;
  
  private List<Transmitter> m_transmitters;
  
  public TMidiDevice(MidiDevice.Info info) {
    this(info, true, true);
  }
  
  public TMidiDevice(MidiDevice.Info info, boolean bUseTransmitter, boolean bUseReceiver) {
    this.m_info = info;
    this.m_bUseTransmitter = bUseTransmitter;
    this.m_bUseReceiver = bUseReceiver;
    this.m_bDeviceOpen = false;
    this.m_receivers = new ArrayList<Receiver>();
    this.m_transmitters = new ArrayList<Transmitter>();
  }
  
  public MidiDevice.Info getDeviceInfo() {
    return this.m_info;
  }
  
  public synchronized void open() throws MidiUnavailableException {
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.open(): begin"); 
    if (!isOpen()) {
      openImpl();
      this.m_bDeviceOpen = true;
    } 
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.open(): end"); 
  }
  
  protected void openImpl() throws MidiUnavailableException {
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.openImpl(): begin"); 
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.openImpl(): end"); 
  }
  
  public synchronized void close() {
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.close(): begin"); 
    if (isOpen()) {
      closeImpl();
      this.m_bDeviceOpen = false;
    } 
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.close(): end"); 
  }
  
  protected void closeImpl() {
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.closeImpl(): begin"); 
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.closeImpl(): end"); 
  }
  
  public boolean isOpen() {
    return this.m_bDeviceOpen;
  }
  
  protected boolean getUseTransmitter() {
    return this.m_bUseTransmitter;
  }
  
  protected boolean getUseReceiver() {
    return this.m_bUseReceiver;
  }
  
  public long getMicrosecondPosition() {
    return -1L;
  }
  
  public int getMaxReceivers() {
    int nMaxReceivers = 0;
    if (getUseReceiver())
      nMaxReceivers = -1; 
    return nMaxReceivers;
  }
  
  public int getMaxTransmitters() {
    int nMaxTransmitters = 0;
    if (getUseTransmitter())
      nMaxTransmitters = -1; 
    return nMaxTransmitters;
  }
  
  public Receiver getReceiver() throws MidiUnavailableException {
    if (!getUseReceiver())
      throw new MidiUnavailableException("Receivers are not supported by this device"); 
    return new TReceiver();
  }
  
  public Transmitter getTransmitter() throws MidiUnavailableException {
    if (!getUseTransmitter())
      throw new MidiUnavailableException("Transmitters are not supported by this device"); 
    return new TTransmitter();
  }
  
  public List<Receiver> getReceivers() {
    return Collections.unmodifiableList(this.m_receivers);
  }
  
  public List<Transmitter> getTransmitters() {
    return Collections.unmodifiableList(this.m_transmitters);
  }
  
  protected void receive(MidiMessage message, long lTimeStamp) {
    if (TDebug.TraceMidiDevice)
      TDebug.out("### [should be overridden] TMidiDevice.receive(): message " + message); 
  }
  
  protected void addReceiver(Receiver receiver) {
    synchronized (this.m_receivers) {
      this.m_receivers.add(receiver);
    } 
  }
  
  protected void removeReceiver(Receiver receiver) {
    synchronized (this.m_receivers) {
      this.m_receivers.remove(receiver);
    } 
  }
  
  protected void addTransmitter(Transmitter transmitter) {
    synchronized (this.m_transmitters) {
      this.m_transmitters.add(transmitter);
    } 
  }
  
  protected void removeTransmitter(Transmitter transmitter) {
    synchronized (this.m_transmitters) {
      this.m_transmitters.remove(transmitter);
    } 
  }
  
  protected void sendImpl(MidiMessage message, long lTimeStamp) {
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.sendImpl(): begin"); 
    Iterator<Transmitter> transmitters = this.m_transmitters.iterator();
    while (transmitters.hasNext()) {
      TTransmitter transmitter = (TTransmitter)transmitters.next();
      MidiMessage copiedMessage = null;
      if (message instanceof MetaMessage) {
        MetaMessage origMessage = (MetaMessage)message;
        MetaMessage metaMessage = new MetaMessage();
        try {
          metaMessage.setMessage(origMessage.getType(), origMessage.getData(), (origMessage.getData()).length);
        } catch (InvalidMidiDataException e) {
          if (TDebug.TraceAllExceptions)
            TDebug.out(e); 
        } 
        copiedMessage = metaMessage;
      } else {
        copiedMessage = (MidiMessage)message.clone();
      } 
      if (message instanceof MetaMessage) {
        if (TDebug.TraceMidiDevice)
          TDebug.out("TMidiDevice.sendImpl(): MetaMessage.getData().length (original): " + (((MetaMessage)message).getData()).length); 
        if (TDebug.TraceMidiDevice)
          TDebug.out("TMidiDevice.sendImpl(): MetaMessage.getData().length (cloned): " + (((MetaMessage)copiedMessage).getData()).length); 
      } 
      transmitter.send(copiedMessage, lTimeStamp);
    } 
    if (TDebug.TraceMidiDevice)
      TDebug.out("TMidiDevice.sendImpl(): end"); 
  }
  
  public class TReceiver implements Receiver {
    private boolean m_bOpen;
    
    public TReceiver() {
      TMidiDevice.this.addReceiver(this);
      this.m_bOpen = true;
    }
    
    protected boolean isOpen() {
      return this.m_bOpen;
    }
    
    public void send(MidiMessage message, long lTimeStamp) {
      if (TDebug.TraceMidiDevice)
        TDebug.out("TMidiDevice.TReceiver.send(): message " + message); 
      if (this.m_bOpen) {
        TMidiDevice.this.receive(message, lTimeStamp);
      } else {
        throw new IllegalStateException("receiver is not open");
      } 
    }
    
    public void close() {
      TMidiDevice.this.removeReceiver(this);
      this.m_bOpen = false;
    }
  }
  
  public class TTransmitter implements Transmitter {
    private boolean m_bOpen;
    
    private Receiver m_receiver;
    
    public TTransmitter() {
      this.m_bOpen = true;
      TMidiDevice.this.addTransmitter(this);
    }
    
    public void setReceiver(Receiver receiver) {
      synchronized (this) {
        this.m_receiver = receiver;
      } 
    }
    
    public Receiver getReceiver() {
      return this.m_receiver;
    }
    
    public void send(MidiMessage message, long lTimeStamp) {
      if (getReceiver() != null && this.m_bOpen)
        getReceiver().send(message, lTimeStamp); 
    }
    
    public void close() {
      TMidiDevice.this.removeTransmitter(this);
      this.m_bOpen = false;
    }
  }
  
  public static class Info extends MidiDevice.Info {
    public Info(String a, String b, String c, String d) {
      super(a, b, c, d);
    }
  }
}
