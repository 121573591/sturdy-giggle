package org.tritonus.share;

public class TCircularBuffer {
  private boolean m_bBlockingRead;
  
  private boolean m_bBlockingWrite;
  
  private byte[] m_abData;
  
  private int m_nSize;
  
  private long m_lReadPos;
  
  private long m_lWritePos;
  
  private Trigger m_trigger;
  
  private boolean m_bOpen;
  
  public TCircularBuffer(int nSize, boolean bBlockingRead, boolean bBlockingWrite, Trigger trigger) {
    this.m_bBlockingRead = bBlockingRead;
    this.m_bBlockingWrite = bBlockingWrite;
    this.m_nSize = nSize;
    this.m_abData = new byte[this.m_nSize];
    this.m_lReadPos = 0L;
    this.m_lWritePos = 0L;
    this.m_trigger = trigger;
    this.m_bOpen = true;
  }
  
  public void close() {
    this.m_bOpen = false;
  }
  
  private boolean isOpen() {
    return this.m_bOpen;
  }
  
  public int availableRead() {
    return (int)(this.m_lWritePos - this.m_lReadPos);
  }
  
  public int availableWrite() {
    return this.m_nSize - availableRead();
  }
  
  private int getReadPos() {
    return (int)(this.m_lReadPos % this.m_nSize);
  }
  
  private int getWritePos() {
    return (int)(this.m_lWritePos % this.m_nSize);
  }
  
  public int read(byte[] abData) {
    return read(abData, 0, abData.length);
  }
  
  public int read(byte[] abData, int nOffset, int nLength) {
    if (TDebug.TraceCircularBuffer) {
      TDebug.out(">TCircularBuffer.read(): called.");
      dumpInternalState();
    } 
    if (!isOpen())
      if (availableRead() > 0) {
        nLength = Math.min(nLength, availableRead());
        if (TDebug.TraceCircularBuffer)
          TDebug.out("reading rest in closed buffer, length: " + nLength); 
      } else {
        if (TDebug.TraceCircularBuffer)
          TDebug.out("< not open. returning -1."); 
        return -1;
      }  
    synchronized (this) {
      if (this.m_trigger != null && availableRead() < nLength) {
        if (TDebug.TraceCircularBuffer)
          TDebug.out("executing trigger."); 
        this.m_trigger.execute();
      } 
      if (!this.m_bBlockingRead)
        nLength = Math.min(availableRead(), nLength); 
      int nRemainingBytes = nLength;
      while (nRemainingBytes > 0) {
        while (availableRead() == 0) {
          try {
            wait();
          } catch (InterruptedException e) {
            if (TDebug.TraceAllExceptions)
              TDebug.out(e); 
          } 
        } 
        int nAvailable = Math.min(availableRead(), nRemainingBytes);
        while (nAvailable > 0) {
          int nToRead = Math.min(nAvailable, this.m_nSize - getReadPos());
          System.arraycopy(this.m_abData, getReadPos(), abData, nOffset, nToRead);
          this.m_lReadPos += nToRead;
          nOffset += nToRead;
          nAvailable -= nToRead;
          nRemainingBytes -= nToRead;
        } 
        notifyAll();
      } 
      if (TDebug.TraceCircularBuffer) {
        TDebug.out("After read:");
        dumpInternalState();
        TDebug.out("< completed. Read " + nLength + " bytes");
      } 
      return nLength;
    } 
  }
  
  public int write(byte[] abData) {
    return write(abData, 0, abData.length);
  }
  
  public int write(byte[] abData, int nOffset, int nLength) {
    if (TDebug.TraceCircularBuffer) {
      TDebug.out(">TCircularBuffer.write(): called; nLength: " + nLength);
      dumpInternalState();
    } 
    synchronized (this) {
      if (TDebug.TraceCircularBuffer)
        TDebug.out("entered synchronized block."); 
      if (!this.m_bBlockingWrite)
        nLength = Math.min(availableWrite(), nLength); 
      int nRemainingBytes = nLength;
      while (nRemainingBytes > 0) {
        while (availableWrite() == 0) {
          try {
            wait();
          } catch (InterruptedException e) {
            if (TDebug.TraceAllExceptions)
              TDebug.out(e); 
          } 
        } 
        int nAvailable = Math.min(availableWrite(), nRemainingBytes);
        while (nAvailable > 0) {
          int nToWrite = Math.min(nAvailable, this.m_nSize - getWritePos());
          System.arraycopy(abData, nOffset, this.m_abData, getWritePos(), nToWrite);
          this.m_lWritePos += nToWrite;
          nOffset += nToWrite;
          nAvailable -= nToWrite;
          nRemainingBytes -= nToWrite;
        } 
        notifyAll();
      } 
      if (TDebug.TraceCircularBuffer) {
        TDebug.out("After write:");
        dumpInternalState();
        TDebug.out("< completed. Wrote " + nLength + " bytes");
      } 
      return nLength;
    } 
  }
  
  private void dumpInternalState() {
    TDebug.out("m_lReadPos  = " + this.m_lReadPos + " ^= " + getReadPos());
    TDebug.out("m_lWritePos = " + this.m_lWritePos + " ^= " + getWritePos());
    TDebug.out("availableRead()  = " + availableRead());
    TDebug.out("availableWrite() = " + availableWrite());
  }
  
  public static interface Trigger {
    void execute();
  }
}
