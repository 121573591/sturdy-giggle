package javazoom.jl.converter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Obuffer;

public class Converter {
  public synchronized void convert(String sourceName, String destName) throws JavaLayerException {
    convert(sourceName, destName, (ProgressListener)null, (Decoder.Params)null);
  }
  
  public synchronized void convert(String sourceName, String destName, ProgressListener progressListener) throws JavaLayerException {
    convert(sourceName, destName, progressListener, (Decoder.Params)null);
  }
  
  public void convert(String sourceName, String destName, ProgressListener progressListener, Decoder.Params decoderParams) throws JavaLayerException {
    if (destName.length() == 0)
      destName = null; 
    try {
      InputStream in = openInput(sourceName);
      convert(in, destName, progressListener, decoderParams);
      in.close();
    } catch (IOException ioe) {
      throw new JavaLayerException(ioe.getLocalizedMessage(), ioe);
    } 
  }
  
  public synchronized void convert(InputStream sourceStream, String destName, ProgressListener progressListener, Decoder.Params decoderParams) throws JavaLayerException {
    if (progressListener == null)
      progressListener = PrintWriterProgressListener.newStdOut(0); 
    try {
      if (!(sourceStream instanceof BufferedInputStream))
        sourceStream = new BufferedInputStream(sourceStream); 
      int frameCount = -1;
      if (sourceStream.markSupported()) {
        sourceStream.mark(-1);
        frameCount = countFrames(sourceStream);
        sourceStream.reset();
      } 
      progressListener.converterUpdate(1, frameCount, 0);
      Obuffer output = null;
      Decoder decoder = new Decoder(decoderParams);
      Bitstream stream = new Bitstream(sourceStream);
      if (frameCount == -1)
        frameCount = Integer.MAX_VALUE; 
      int frame = 0;
      long startTime = System.currentTimeMillis();
      try {
        for (; frame < frameCount; frame++) {
          try {
            Header header = stream.readFrame();
            if (header == null)
              break; 
            progressListener.readFrame(frame, header);
            if (output == null) {
              int channels = (header.mode() == 3) ? 1 : 2;
              int freq = header.frequency();
              output = new WaveFileObuffer(channels, freq, destName);
              decoder.setOutputBuffer(output);
            } 
            Obuffer decoderOutput = decoder.decodeFrame(header, stream);
            if (decoderOutput != output)
              throw new InternalError("Output buffers are different."); 
            progressListener.decodedFrame(frame, header, output);
            stream.closeFrame();
          } catch (Exception ex) {
            boolean stop = !progressListener.converterException(ex);
            if (stop)
              throw new JavaLayerException(ex.getLocalizedMessage(), ex); 
          } 
        } 
      } finally {
        if (output != null)
          output.close(); 
      } 
      int time = (int)(System.currentTimeMillis() - startTime);
      progressListener.converterUpdate(2, time, frame);
    } catch (IOException ex) {
      throw new JavaLayerException(ex.getLocalizedMessage(), ex);
    } 
  }
  
  protected int countFrames(InputStream in) {
    return -1;
  }
  
  protected InputStream openInput(String fileName) throws IOException {
    File file = new File(fileName);
    InputStream fileIn = new FileInputStream(file);
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    return bufIn;
  }
  
  public static class PrintWriterProgressListener implements ProgressListener {
    public static final int NO_DETAIL = 0;
    
    public static final int EXPERT_DETAIL = 1;
    
    public static final int VERBOSE_DETAIL = 2;
    
    public static final int DEBUG_DETAIL = 7;
    
    public static final int MAX_DETAIL = 10;
    
    private PrintWriter pw;
    
    private int detailLevel;
    
    public static PrintWriterProgressListener newStdOut(int detail) {
      return new PrintWriterProgressListener(new PrintWriter(System.out, true), detail);
    }
    
    public PrintWriterProgressListener(PrintWriter writer, int detailLevel) {
      this.pw = writer;
      this.detailLevel = detailLevel;
    }
    
    public boolean isDetail(int detail) {
      return (this.detailLevel >= detail);
    }
    
    public void converterUpdate(int updateID, int param1, int param2) {
      if (isDetail(2))
        switch (updateID) {
          case 2:
            if (param2 == 0)
              param2 = 1; 
            this.pw.println();
            this.pw.println("Converted " + param2 + " frames in " + param1 + " ms (" + (param1 / param2) + " ms per frame.)");
            break;
        }  
    }
    
    public void parsedFrame(int frameNo, Header header) {
      if (frameNo == 0 && isDetail(2)) {
        String headerString = header.toString();
        this.pw.println("File is a " + headerString);
      } else if (isDetail(10)) {
        String headerString = header.toString();
        this.pw.println("Prased frame " + frameNo + ": " + headerString);
      } 
    }
    
    public void readFrame(int frameNo, Header header) {
      if (frameNo == 0 && isDetail(2)) {
        String headerString = header.toString();
        this.pw.println("File is a " + headerString);
      } else if (isDetail(10)) {
        String headerString = header.toString();
        this.pw.println("Read frame " + frameNo + ": " + headerString);
      } 
    }
    
    public void decodedFrame(int frameNo, Header header, Obuffer o) {
      if (isDetail(10)) {
        String headerString = header.toString();
        this.pw.println("Decoded frame " + frameNo + ": " + headerString);
        this.pw.println("Output: " + o);
      } else if (isDetail(2)) {
        if (frameNo == 0) {
          this.pw.print("Converting.");
          this.pw.flush();
        } 
        if (frameNo % 10 == 0) {
          this.pw.print('.');
          this.pw.flush();
        } 
      } 
    }
    
    public boolean converterException(Throwable t) {
      if (this.detailLevel > 0) {
        t.printStackTrace(this.pw);
        this.pw.flush();
      } 
      return false;
    }
  }
  
  public static interface ProgressListener {
    public static final int UPDATE_FRAME_COUNT = 1;
    
    public static final int UPDATE_CONVERT_COMPLETE = 2;
    
    void converterUpdate(int param1Int1, int param1Int2, int param1Int3);
    
    void parsedFrame(int param1Int, Header param1Header);
    
    void readFrame(int param1Int, Header param1Header);
    
    void decodedFrame(int param1Int, Header param1Header, Obuffer param1Obuffer);
    
    boolean converterException(Throwable param1Throwable);
  }
}
