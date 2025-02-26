package javazoom.jl.converter;

import javazoom.jl.decoder.Obuffer;

public class WaveFileObuffer extends Obuffer {
  private short[] buffer;
  
  private short[] bufferp;
  
  private int channels;
  
  private WaveFile outWave;
  
  public WaveFileObuffer(int number_of_channels, int freq, String FileName) {
    if (FileName == null)
      throw new NullPointerException("FileName"); 
    this.buffer = new short[2304];
    this.bufferp = new short[2];
    this.channels = number_of_channels;
    for (int i = 0; i < number_of_channels; i++)
      this.bufferp[i] = (short)i; 
    this.outWave = new WaveFile();
    int rc = this.outWave.OpenForWrite(FileName, freq, (short)16, (short)this.channels);
  }
  
  public void append(int channel, short value) {
    this.buffer[this.bufferp[channel]] = value;
    this.bufferp[channel] = (short)(this.bufferp[channel] + this.channels);
  }
  
  short[] myBuffer = new short[2];
  
  public void write_buffer(int val) {
    int k = 0;
    int rc = 0;
    rc = this.outWave.WriteData(this.buffer, this.bufferp[0]);
    for (int i = 0; i < this.channels; ) {
      this.bufferp[i] = (short)i;
      i++;
    } 
  }
  
  public void close() {
    this.outWave.Close();
  }
  
  public void clear_buffer() {}
  
  public void set_stop_flag() {}
}
