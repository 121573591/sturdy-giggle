package org.tritonus.share.sampled;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import org.tritonus.sampled.file.AiffAudioOutputStream;
import org.tritonus.sampled.file.AuAudioOutputStream;
import org.tritonus.sampled.file.WaveAudioOutputStream;
import org.tritonus.share.sampled.file.AudioOutputStream;
import org.tritonus.share.sampled.file.TDataOutputStream;
import org.tritonus.share.sampled.file.TNonSeekableDataOutputStream;
import org.tritonus.share.sampled.file.TSeekableDataOutputStream;

public class AudioSystemShadow {
  public static TDataOutputStream getDataOutputStream(File file) throws IOException {
    return (TDataOutputStream)new TSeekableDataOutputStream(file);
  }
  
  public static TDataOutputStream getDataOutputStream(OutputStream stream) throws IOException {
    return (TDataOutputStream)new TNonSeekableDataOutputStream(stream);
  }
  
  public static AudioOutputStream getAudioOutputStream(AudioFileFormat.Type type, AudioFormat audioFormat, long lLengthInBytes, TDataOutputStream dataOutputStream) {
    WaveAudioOutputStream waveAudioOutputStream;
    AudioOutputStream audioOutputStream = null;
    if (type.equals(AudioFileFormat.Type.AIFF) || type
      .equals(AudioFileFormat.Type.AIFF)) {
      AiffAudioOutputStream aiffAudioOutputStream = new AiffAudioOutputStream(audioFormat, type, lLengthInBytes, dataOutputStream);
    } else if (type.equals(AudioFileFormat.Type.AU)) {
      AuAudioOutputStream auAudioOutputStream = new AuAudioOutputStream(audioFormat, lLengthInBytes, dataOutputStream);
    } else if (type.equals(AudioFileFormat.Type.WAVE)) {
      waveAudioOutputStream = new WaveAudioOutputStream(audioFormat, lLengthInBytes, dataOutputStream);
    } 
    return (AudioOutputStream)waveAudioOutputStream;
  }
  
  public static AudioOutputStream getAudioOutputStream(AudioFileFormat.Type type, AudioFormat audioFormat, long lLengthInBytes, File file) throws IOException {
    TDataOutputStream dataOutputStream = getDataOutputStream(file);
    AudioOutputStream audioOutputStream = getAudioOutputStream(type, audioFormat, lLengthInBytes, dataOutputStream);
    return audioOutputStream;
  }
  
  public static AudioOutputStream getAudioOutputStream(AudioFileFormat.Type type, AudioFormat audioFormat, long lLengthInBytes, OutputStream outputStream) throws IOException {
    TDataOutputStream dataOutputStream = getDataOutputStream(outputStream);
    AudioOutputStream audioOutputStream = getAudioOutputStream(type, audioFormat, lLengthInBytes, dataOutputStream);
    return audioOutputStream;
  }
}
