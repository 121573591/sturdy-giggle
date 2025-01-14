package cn.hutool.core.io;

import cn.hutool.core.util.ArrayUtil;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public enum FileMagicNumber {
  UNKNOWN(null, null) {
    public boolean match(byte[] bytes) {
      return false;
    }
  },
  JPEG("image/jpeg", "jpg") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-1)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-40)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-1)));
    }
  },
  JXR("image/vnd.ms-photo", "jxr") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-68)));
    }
  },
  APNG("image/apng", "apng") {
    public boolean match(byte[] bytes) {
      boolean b = (bytes.length > 8 && Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-119)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)80)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)78)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)71)) && Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)13)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)10)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)26)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)10)));
      if (b) {
        int i = 8;
        while (i < bytes.length) {
          try {
            int dataLength = (new BigInteger(1, Arrays.copyOfRange(bytes, i, i + 4))).intValue();
            i += 4;
            byte[] bytes1 = Arrays.copyOfRange(bytes, i, i + 4);
            String chunkType = new String(bytes1);
            i += 4;
            if (Objects.equals(chunkType, "IDAT") || Objects.equals(chunkType, "IEND"))
              return false; 
            if (Objects.equals(chunkType, "acTL"))
              return true; 
            i += dataLength + 4;
          } catch (Exception e) {
            return false;
          } 
        } 
      } 
      return false;
    }
  },
  PNG("image/png", "png") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-119)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)80)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)78)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)71)));
    }
  },
  GIF("image/gif", "gif") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)71)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)70)));
    }
  },
  BMP("image/bmp", "bmp") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 1 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)66)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)77)));
    }
  },
  TIFF("image/tiff", "tiff") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 4)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)73)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)73)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)42)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)0)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)0)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)42)));
      return (flag1 || flag2);
    }
  },
  DWG("image/vnd.dwg", "dwg") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 10 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)65)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)67)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)49)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)48)));
    }
  },
  WEBP("image/webp", "webp") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 11 && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)87)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)69)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)66)) && 
        Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)80)));
    }
  },
  PSD("image/vnd.adobe.photoshop", "psd") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)56)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)66)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)80)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)83)));
    }
  },
  ICO("image/x-icon", "ico") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)1)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)0)));
    }
  },
  XCF("image/x-xcf", "xcf") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 9 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)103)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)105)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)109)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)112)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)32)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)120)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)99)) && 
        Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)102)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)32)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)118)));
    }
  },
  WAV("audio/x-wav", "wav") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 11 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)82)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)87)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)65)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)86)) && 
        Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)69)));
    }
  },
  MIDI("audio/midi", "midi") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)84)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)104)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)100)));
    }
  },
  MP3("audio/mpeg", "mp3") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 2)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)73)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)68)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)51)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-1)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-5)));
      boolean flag3 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-1)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-13)));
      boolean flag4 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-1)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-14)));
      return (flag1 || flag2 || flag3 || flag4);
    }
  },
  OGG("audio/ogg", "ogg") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)79)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)103)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)103)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)83)));
    }
  },
  FLAC("audio/x-flac", "flac") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)102)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)76)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)97)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)67)));
    }
  },
  M4A("audio/mp4", "m4a") {
    public boolean match(byte[] bytes) {
      return ((bytes.length > 10 && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && 
        Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)52)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)65))) || (
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)52)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)65)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)32))));
    }
  },
  AAC("audio/aac", "aac") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 1)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-1)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-15)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-1)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-7)));
      return (flag1 || flag2);
    }
  },
  AMR("audio/amr", "amr") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 11)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)35)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)33)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)65)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)82)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)10)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)35)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)33)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)65)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)82)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)95)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)67)) && Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)49)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)46)) && Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)48)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)10)));
      return (flag1 || flag2);
    }
  },
  AC3("audio/ac3", "ac3") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)11)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)119)));
    }
  },
  AIFF("audio/x-aiff", "aiff") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 11 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)79)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)82)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)65)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)70)));
    }
  },
  WOFF("font/woff", "woff") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 8)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)119)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)79)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)70)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)0)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)1)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)0)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)0)));
      boolean flag3 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)79)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)84)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)84)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)79)));
      boolean flag4 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)114)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)117)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)101)));
      return (flag1 && (flag2 || flag3 || flag4));
    }
  },
  WOFF2("font/woff2", "woff2") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 8)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)119)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)79)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)70)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)50)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)0)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)1)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)0)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)0)));
      boolean flag3 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)79)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)84)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)84)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)79)));
      boolean flag4 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)114)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)117)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)101)));
      return (flag1 && (flag2 || flag3 || flag4));
    }
  },
  TTF("font/ttf", "ttf") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 4 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)1)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)0)));
    }
  },
  OTF("font/otf", "otf") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 4 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)79)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)84)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)84)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)79)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)0)));
    }
  },
  EPUB("application/epub+zip", "epub") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 58 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)80)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)75)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)3)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)4)) && 
        Objects.equals(Byte.valueOf(bytes[30]), Byte.valueOf((byte)109)) && Objects.equals(Byte.valueOf(bytes[31]), Byte.valueOf((byte)105)) && 
        Objects.equals(Byte.valueOf(bytes[32]), Byte.valueOf((byte)109)) && Objects.equals(Byte.valueOf(bytes[33]), Byte.valueOf((byte)101)) && 
        Objects.equals(Byte.valueOf(bytes[34]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[35]), Byte.valueOf((byte)121)) && 
        Objects.equals(Byte.valueOf(bytes[36]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[37]), Byte.valueOf((byte)101)) && 
        Objects.equals(Byte.valueOf(bytes[38]), Byte.valueOf((byte)97)) && Objects.equals(Byte.valueOf(bytes[39]), Byte.valueOf((byte)112)) && 
        Objects.equals(Byte.valueOf(bytes[40]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[41]), Byte.valueOf((byte)108)) && 
        Objects.equals(Byte.valueOf(bytes[42]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[43]), Byte.valueOf((byte)99)) && 
        Objects.equals(Byte.valueOf(bytes[44]), Byte.valueOf((byte)97)) && Objects.equals(Byte.valueOf(bytes[45]), Byte.valueOf((byte)116)) && 
        Objects.equals(Byte.valueOf(bytes[46]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[47]), Byte.valueOf((byte)111)) && 
        Objects.equals(Byte.valueOf(bytes[48]), Byte.valueOf((byte)110)) && Objects.equals(Byte.valueOf(bytes[49]), Byte.valueOf((byte)47)) && 
        Objects.equals(Byte.valueOf(bytes[50]), Byte.valueOf((byte)101)) && Objects.equals(Byte.valueOf(bytes[51]), Byte.valueOf((byte)112)) && 
        Objects.equals(Byte.valueOf(bytes[52]), Byte.valueOf((byte)117)) && Objects.equals(Byte.valueOf(bytes[53]), Byte.valueOf((byte)98)) && 
        Objects.equals(Byte.valueOf(bytes[54]), Byte.valueOf((byte)43)) && Objects.equals(Byte.valueOf(bytes[55]), Byte.valueOf((byte)122)) && 
        Objects.equals(Byte.valueOf(bytes[56]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[57]), Byte.valueOf((byte)112)));
    }
  },
  ZIP("application/zip", "zip") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 4)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)80)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)75)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)3)) || Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)5)) || Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)7)));
      boolean flag3 = (Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)4)) || Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)6)) || Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)8)));
      return (flag1 && flag2 && flag3);
    }
  },
  TAR("application/x-tar", "tar") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 261 && 
        Objects.equals(Byte.valueOf(bytes[257]), Byte.valueOf((byte)117)) && 
        Objects.equals(Byte.valueOf(bytes[258]), Byte.valueOf((byte)115)) && 
        Objects.equals(Byte.valueOf(bytes[259]), Byte.valueOf((byte)116)) && 
        Objects.equals(Byte.valueOf(bytes[260]), Byte.valueOf((byte)97)) && 
        Objects.equals(Byte.valueOf(bytes[261]), Byte.valueOf((byte)114)));
    }
  },
  RAR("application/x-rar-compressed", "rar") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 6 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)82)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)97)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)33)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)26)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)7)) && (
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)0)) || Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)1))));
    }
  },
  GZ("application/gzip", "gz") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)31)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-117)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)8)));
    }
  },
  BZ2("application/x-bzip2", "bz2") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)66)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)90)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)104)));
    }
  },
  SevenZ("application/x-7z-compressed", "7z") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 6 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)55)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)122)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-68)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)-81)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)39)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)28)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)0)));
    }
  },
  PDF("application/pdf", "pdf") {
    public boolean match(byte[] bytes) {
      if (bytes.length > 3 && Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-17)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-69)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-65)))
        bytes = Arrays.copyOfRange(bytes, 3, bytes.length); 
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)37)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)80)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)68)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)));
    }
  },
  EXE("application/x-msdownload", "exe") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 1 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)90)));
    }
  },
  SWF("application/x-shockwave-flash", "swf") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 2 && (
        Objects.equals(Byte.valueOf(bytes[0]), Integer.valueOf(67)) || Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)70))) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)87)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)83)));
    }
  },
  RTF("application/rtf", "rtf") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 4 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)123)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)92)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)116)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)));
    }
  },
  NES("application/x-nintendo-nes-rom", "nes") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)78)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)69)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)83)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)26)));
    }
  },
  CRX("application/x-google-chrome-extension", "crx") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)67)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)50)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)52)));
    }
  },
  CAB("application/vnd.ms-cab-compressed", "cab") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 4)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)83)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)67)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)73)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)83)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)99)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)40)));
      return (flag1 || flag2);
    }
  },
  PS("application/postscript", "ps") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 1 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)37)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)33)));
    }
  },
  XZ("application/x-xz", "xz") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 5 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-3)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)55)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)122)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)88)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)90)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)0)));
    }
  },
  SQLITE("application/x-sqlite3", "sqlite") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 15 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)83)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)81)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)76)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)105)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)101)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)32)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)102)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)111)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)109)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)97)) && 
        Objects.equals(Byte.valueOf(bytes[12]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[13]), Byte.valueOf((byte)32)) && 
        Objects.equals(Byte.valueOf(bytes[14]), Byte.valueOf((byte)51)) && Objects.equals(Byte.valueOf(bytes[15]), Byte.valueOf((byte)0)));
    }
  },
  DEB("application/x-deb", "deb") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 20 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)33)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)60)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)97)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)99)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)104)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)62)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)10)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)100)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)101)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)98)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)105)) && 
        Objects.equals(Byte.valueOf(bytes[12]), Byte.valueOf((byte)97)) && Objects.equals(Byte.valueOf(bytes[13]), Byte.valueOf((byte)110)) && 
        Objects.equals(Byte.valueOf(bytes[14]), Byte.valueOf((byte)45)) && Objects.equals(Byte.valueOf(bytes[15]), Byte.valueOf((byte)98)) && 
        Objects.equals(Byte.valueOf(bytes[16]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[17]), Byte.valueOf((byte)110)) && 
        Objects.equals(Byte.valueOf(bytes[18]), Byte.valueOf((byte)97)) && Objects.equals(Byte.valueOf(bytes[19]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[20]), Byte.valueOf((byte)121)));
    }
  },
  AR("application/x-unix-archive", "ar") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 6 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)33)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)60)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)97)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)114)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)99)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)104)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)62)));
    }
  },
  LZOP("application/x-lzop", "lzo") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 7 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-119)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)76)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)90)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)79)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)13)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)10)) && 
        Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)26)));
    }
  },
  LZ("application/x-lzip", "lz") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)76)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)90)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)80)));
    }
  },
  ELF("application/x-executable", "elf") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 52 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf(127)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)69)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)76)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)));
    }
  },
  LZ4("application/x-lz4", "lz4") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 4 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)4)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)34)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)24)));
    }
  },
  BR("application/x-brotli", "br") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-50)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-78)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-49)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)-127)));
    }
  },
  DCM("application/x-dicom", "dcm") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 128 && 
        Objects.equals(Byte.valueOf(bytes[128]), Byte.valueOf((byte)68)) && 
        Objects.equals(Byte.valueOf(bytes[129]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[130]), Byte.valueOf((byte)67)) && 
        Objects.equals(Byte.valueOf(bytes[131]), Byte.valueOf((byte)77)));
    }
  },
  RPM("application/x-rpm", "rpm") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 4 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)-19)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-85)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-18)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)-37)));
    }
  },
  ZSTD("application/x-zstd", "zst") {
    public boolean match(byte[] bytes) {
      int length = bytes.length;
      if (length < 5)
        return false; 
      byte[] buf1 = { 34, 35, 36, 37, 38, 39, 40 };
      boolean flag1 = (ArrayUtil.contains(buf1, bytes[0]) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)-75)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)47)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)-3)));
      if (flag1)
        return true; 
      if ((bytes[0] & 0xF0) == 80)
        return (bytes[1] == 42 && bytes[2] == 77 && bytes[3] == 24); 
      return false;
    }
  },
  MP4("video/mp4", "mp4") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 13)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)83)) && Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)78)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)86)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)115)) && Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)111)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)109)));
      return (flag1 || flag2);
    }
  },
  AVI("video/x-msvideo", "avi") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 11 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)82)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)65)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)86)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)73)) && 
        Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)32)));
    }
  },
  WMV("video/x-ms-wmv", "wmv") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 9 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)48)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)38)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-78)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)117)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)-114)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)102)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)-49)) && 
        Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)17)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)-90)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)-39)));
    }
  },
  M4V("video/x-m4v", "m4v") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 12)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)77)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)52)) && Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)86)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)32)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)109)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)52)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)50)));
      return (flag1 || flag2);
    }
  },
  FLV("video/x-flv", "flv") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)70)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)76)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)86)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)1)));
    }
  },
  MKV("video/x-matroska", "mkv") {
    public boolean match(byte[] bytes) {
      boolean flag1 = (bytes.length > 11 && Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)26)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)69)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-33)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)-93)));
      if (flag1) {
        byte[] bytes1 = { 
            66, -126, -120, 109, 97, 116, 114, 111, 115, 107, 
            97 };
        int index = indexOf(bytes, bytes1);
        return (index > 0);
      } 
      return false;
    }
  },
  WEBM("video/webm", "webm") {
    public boolean match(byte[] bytes) {
      boolean flag1 = (bytes.length > 8 && Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)26)) && Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)69)) && Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)-33)) && Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)-93)));
      if (flag1) {
        byte[] bytes1 = { 66, -126, -120, 119, 101, 98, 109 };
        int index = indexOf(bytes, bytes1);
        return (index > 0);
      } 
      return false;
    }
  },
  MOV("video/quicktime", "mov") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 12)
        return false; 
      boolean flag1 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)113)) && Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)116)) && Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)32)) && Objects.equals(Byte.valueOf(bytes[11]), Byte.valueOf((byte)32)));
      boolean flag2 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)109)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)111)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)111)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)118)));
      boolean flag3 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)114)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)101)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)101)));
      boolean flag4 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)109)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)100)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)97)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)116)));
      boolean flag5 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)119)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)100)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)101)));
      boolean flag6 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)112)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)110)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)111)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)116)));
      boolean flag7 = (Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)115)) && Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)107)) && Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)105)) && Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)));
      return (flag1 || flag2 || flag3 || flag4 || flag5 || flag6 || flag7);
    }
  },
  MPEG("video/mpeg", "mpg") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 3 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)1)) && bytes[3] >= -80 && bytes[3] <= -65);
    }
  },
  RMVB("video/vnd.rn-realvideo", "rmvb") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 4 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)46)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)82)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)77)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)70)));
    }
  },
  M3GP("video/3gpp", "3gp") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 10 && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)102)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)116)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)121)) && 
        Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)112)) && 
        Objects.equals(Byte.valueOf(bytes[8]), Byte.valueOf((byte)51)) && 
        Objects.equals(Byte.valueOf(bytes[9]), Byte.valueOf((byte)103)) && 
        Objects.equals(Byte.valueOf(bytes[10]), Byte.valueOf((byte)112)));
    }
  },
  DOC("application/msword", "doc") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { -48, -49, 17, -32, -95, -79, 26, -31 };
      boolean flag1 = (bytes.length > 515 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 8), byte1));
      if (flag1) {
        byte[] byte2 = { -20, -91, -63, 0 };
        boolean flag2 = Arrays.equals(Arrays.copyOfRange(bytes, 512, 516), byte2);
        byte[] byte3 = { 
            0, 10, 0, 0, 0, 77, 83, 87, 111, 114, 
            100, 68, 111, 99, 0, 16, 0, 0, 0, 87, 
            111, 114, 100, 46, 68, 111, 99, 117, 109, 101, 
            110, 116, 46, 56, 0, -12, 57, -78, 113 };
        byte[] range = Arrays.copyOfRange(bytes, 2075, 2142);
        boolean flag3 = (bytes.length > 2142 && indexOf(range, byte3) > 0);
        return (flag2 || flag3);
      } 
      return false;
    }
  },
  XLS("application/vnd.ms-excel", "xls") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { -48, -49, 17, -32, -95, -79, 26, -31 };
      boolean flag1 = (bytes.length > 520 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 8), byte1));
      if (flag1) {
        byte[] byte2 = { -3, -1, -1, -1 };
        boolean flag2 = (Arrays.equals(Arrays.copyOfRange(bytes, 512, 516), byte2) && (bytes[518] == 0 || bytes[518] == 2));
        byte[] byte3 = { 9, 8, 16, 0, 0, 6, 5, 0 };
        boolean flag3 = Arrays.equals(Arrays.copyOfRange(bytes, 512, 520), byte3);
        byte[] byte4 = { 
            -30, 0, 0, 0, 92, 0, 112, 0, 4, 0, 
            0, 67, 97, 108, 99 };
        boolean flag4 = (bytes.length > 2095 && Arrays.equals(Arrays.copyOfRange(bytes, 1568, 2095), byte4));
        return (flag2 || flag3 || flag4);
      } 
      return false;
    }
  },
  PPT("application/vnd.ms-powerpoint", "ppt") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { -48, -49, 17, -32, -95, -79, 26, -31 };
      boolean flag1 = (bytes.length > 524 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 8), byte1));
      if (flag1) {
        byte[] byte2 = { -96, 70, 29, -16 };
        byte[] byteRange = Arrays.copyOfRange(bytes, 512, 516);
        boolean flag2 = Arrays.equals(byteRange, byte2);
        byte[] byte3 = { 0, 110, 30, -16 };
        boolean flag3 = Arrays.equals(byteRange, byte3);
        byte[] byte4 = { 15, 0, -24, 3 };
        boolean flag4 = Arrays.equals(byteRange, byte4);
        byte[] byte5 = { -3, -1, -1, -1 };
        boolean flag5 = (Arrays.equals(byteRange, byte5) && bytes[522] == 0 && bytes[523] == 0);
        byte[] byte6 = { 
            0, -71, 41, -24, 17, 0, 0, 0, 77, 83, 
            32, 80, 111, 119, 101, 114, 80, 111, 105, 110, 
            116, 32, 57, 55 };
        boolean flag6 = (bytes.length > 2096 && Arrays.equals(Arrays.copyOfRange(bytes, 2072, 2096), byte6));
        return (flag2 || flag3 || flag4 || flag5 || flag6);
      } 
      return false;
    }
  },
  DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx") {
    public boolean match(byte[] bytes) {
      return Objects.equals(matchDocument(bytes), DOCX);
    }
  },
  PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx") {
    public boolean match(byte[] bytes) {
      return Objects.equals(matchDocument(bytes), PPTX);
    }
  },
  XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx") {
    public boolean match(byte[] bytes) {
      return Objects.equals(matchDocument(bytes), XLSX);
    }
  },
  WASM("application/wasm", "wasm") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 7 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)97)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)115)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)109)) && 
        Objects.equals(Byte.valueOf(bytes[4]), Byte.valueOf((byte)1)) && 
        Objects.equals(Byte.valueOf(bytes[5]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[6]), Byte.valueOf((byte)0)) && 
        Objects.equals(Byte.valueOf(bytes[7]), Byte.valueOf((byte)0)));
    }
  },
  DEX("application/vnd.android.dex", "dex") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 36 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)100)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)101)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)120)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)10)) && 
        Objects.equals(Byte.valueOf(bytes[36]), Byte.valueOf((byte)112)));
    }
  },
  DEY("application/vnd.android.dey", "dey") {
    public boolean match(byte[] bytes) {
      return (bytes.length > 100 && 
        Objects.equals(Byte.valueOf(bytes[0]), Byte.valueOf((byte)100)) && 
        Objects.equals(Byte.valueOf(bytes[1]), Byte.valueOf((byte)101)) && 
        Objects.equals(Byte.valueOf(bytes[2]), Byte.valueOf((byte)121)) && 
        Objects.equals(Byte.valueOf(bytes[3]), Byte.valueOf((byte)10)) && DEX
        .match(Arrays.copyOfRange(bytes, 40, 100)));
    }
  },
  EML("message/rfc822", "eml") {
    public boolean match(byte[] bytes) {
      if (bytes.length < 8)
        return false; 
      byte[] byte1 = { 70, 114, 111, 109, 32, 32, 32 };
      byte[] byte2 = { 70, 114, 111, 109, 32, 63, 63, 63 };
      byte[] byte3 = { 70, 114, 111, 109, 58, 32 };
      byte[] byte4 = { 
          82, 101, 116, 117, 114, 110, 45, 80, 97, 116, 
          104, 58, 32 };
      return (Arrays.equals(Arrays.copyOfRange(bytes, 0, 7), byte1) || 
        Arrays.equals(Arrays.copyOfRange(bytes, 0, 8), byte2) || 
        Arrays.equals(Arrays.copyOfRange(bytes, 0, 6), byte3) || (bytes.length > 13 && 
        Arrays.equals(Arrays.copyOfRange(bytes, 0, 13), byte4)));
    }
  },
  MDB("application/vnd.ms-access", "mdb") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { 
          0, 1, 0, 0, 83, 116, 97, 110, 100, 97, 
          114, 100, 32, 74, 101, 116, 32, 68, 66 };
      return (bytes.length > 18 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 18), byte1));
    }
  },
  CHM("application/vnd.ms-htmlhelp", "chm") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { 73, 84, 83, 70 };
      return (bytes.length > 4 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 4), byte1));
    }
  },
  CLASS("application/java-vm", "class") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { -54, -2, -70, -66 };
      return (bytes.length > 4 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 4), byte1));
    }
  },
  TORRENT("application/x-bittorrent", "torrent") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { 
          100, 56, 58, 97, 110, 110, 111, 117, 110, 99, 
          101 };
      return (bytes.length > 11 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 11), byte1));
    }
  },
  WPD("application/vnd.wordperfect", "wpd") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { -1, 87, 80, 67 };
      return (bytes.length > 4 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 4), byte1));
    }
  },
  DBX("", "dbx") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { -49, -83, 18, -2 };
      return (bytes.length > 4 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 4), byte1));
    }
  },
  PST("application/vnd.ms-outlook-pst", "pst") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { 33, 66, 68, 78 };
      return (bytes.length > 4 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 4), byte1));
    }
  },
  RAM("audio/x-pn-realaudio", "ram") {
    public boolean match(byte[] bytes) {
      byte[] byte1 = { 46, 114, 97, -3, 0 };
      return (bytes.length > 5 && Arrays.equals(Arrays.copyOfRange(bytes, 0, 5), byte1));
    }
  };
  
  private final String mimeType;
  
  private final String extension;
  
  FileMagicNumber(String mimeType, String extension) {
    this.mimeType = mimeType;
    this.extension = extension;
  }
  
  public static FileMagicNumber getMagicNumber(byte[] bytes) {
    FileMagicNumber number = Arrays.<FileMagicNumber>stream(values()).filter(fileMagicNumber -> fileMagicNumber.match(bytes)).findFirst().orElse(UNKNOWN);
    if (number.equals(ZIP)) {
      FileMagicNumber fn = matchDocument(bytes);
      return (fn == UNKNOWN) ? ZIP : fn;
    } 
    return number;
  }
  
  public String getMimeType() {
    return this.mimeType;
  }
  
  public String getExtension() {
    return this.extension;
  }
  
  private static int indexOf(byte[] array, byte[] target) {
    if (array == null || target == null || array.length < target.length)
      return -1; 
    if (target.length == 0)
      return 0; 
    for (int i = 0; i < array.length - target.length + 1; i++) {
      int j = 0;
      while (true) {
        if (j < target.length) {
          if (array[i + j] != target[j])
            break; 
          j++;
          continue;
        } 
        return i;
      } 
    } 
    return -1;
  }
  
  private static boolean compareBytes(byte[] buf, byte[] slice, int startOffset) {
    int sl = slice.length;
    if (startOffset + sl > buf.length)
      return false; 
    byte[] sub = Arrays.copyOfRange(buf, startOffset, startOffset + sl);
    return Arrays.equals(sub, slice);
  }
  
  private static FileMagicNumber matchOpenXmlMime(byte[] bytes, int offset) {
    byte[] word = { 119, 111, 114, 100, 47 };
    byte[] ppt = { 112, 112, 116, 47 };
    byte[] xl = { 120, 108, 47 };
    if (compareBytes(bytes, word, offset))
      return DOCX; 
    if (compareBytes(bytes, ppt, offset))
      return PPTX; 
    if (compareBytes(bytes, xl, offset))
      return XLSX; 
    return UNKNOWN;
  }
  
  private static FileMagicNumber matchDocument(byte[] bytes) {
    FileMagicNumber fileMagicNumber = matchOpenXmlMime(bytes, 30);
    if (false == fileMagicNumber.equals(UNKNOWN))
      return fileMagicNumber; 
    byte[] bytes1 = { 
        91, 67, 111, 110, 116, 101, 110, 116, 95, 84, 
        121, 112, 101, 115, 93, 46, 120, 109, 108 };
    byte[] bytes2 = { 
        95, 114, 101, 108, 115, 47, 46, 114, 101, 108, 
        115 };
    byte[] bytes3 = { 100, 111, 99, 80, 114, 111, 112, 115 };
    boolean flag1 = compareBytes(bytes, bytes1, 30);
    boolean flag2 = compareBytes(bytes, bytes2, 30);
    boolean flag3 = compareBytes(bytes, bytes3, 30);
    if (false == ((flag1 || flag2 || flag3) ? true : false))
      return UNKNOWN; 
    int index = 0;
    for (int i = 0; i < 4; i++) {
      index = searchSignature(bytes, index + 4, 6000);
      if (index != -1) {
        FileMagicNumber fn = matchOpenXmlMime(bytes, index + 30);
        if (false == fn.equals(UNKNOWN))
          return fn; 
      } 
    } 
    return UNKNOWN;
  }
  
  private static int searchSignature(byte[] bytes, int start, int rangeNum) {
    byte[] signature = { 80, 75, 3, 4 };
    int length = bytes.length;
    int end = start + rangeNum;
    if (end > length)
      end = length; 
    int index = indexOf(Arrays.copyOfRange(bytes, start, end), signature);
    return (index == -1) ? -1 : (start + index);
  }
  
  public abstract boolean match(byte[] paramArrayOfbyte);
}
