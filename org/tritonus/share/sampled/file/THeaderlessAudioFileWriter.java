package org.tritonus.share.sampled.file;

import java.io.IOException;
import java.util.Collection;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.TDebug;

public class THeaderlessAudioFileWriter extends TAudioFileWriter {
  protected THeaderlessAudioFileWriter(Collection<AudioFileFormat.Type> fileTypes, Collection<AudioFormat> audioFormats) {
    super(fileTypes, audioFormats);
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("THeaderlessAudioFileWriter.<init>(): begin"); 
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("THeaderlessAudioFileWriter.<init>(): end"); 
  }
  
  protected AudioOutputStream getAudioOutputStream(AudioFormat audioFormat, long lLengthInBytes, AudioFileFormat.Type fileType, TDataOutputStream dataOutputStream) throws IOException {
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("THeaderlessAudioFileWriter.getAudioOutputStream(): begin"); 
    AudioOutputStream aos = new HeaderlessAudioOutputStream(audioFormat, lLengthInBytes, dataOutputStream);
    if (TDebug.TraceAudioFileWriter)
      TDebug.out("THeaderlessAudioFileWriter.getAudioOutputStream(): end"); 
    return aos;
  }
}
