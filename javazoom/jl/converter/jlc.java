package javazoom.jl.converter;

import java.io.PrintWriter;
import javazoom.jl.decoder.Crc16;
import javazoom.jl.decoder.JavaLayerException;

public class jlc {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int argc = args.length + 1;
    String[] argv = new String[argc];
    argv[0] = "jlc";
    for (int i = 0; i < args.length; i++)
      argv[i + 1] = args[i]; 
    jlcArgs ma = new jlcArgs();
    if (!ma.processArgs(argv))
      System.exit(1); 
    Converter conv = new Converter();
    int detail = ma.verbose_mode ? ma.verbose_level : 0;
    Converter.ProgressListener listener = new Converter.PrintWriterProgressListener(new PrintWriter(System.out, true), detail);
    try {
      conv.convert(ma.filename, ma.output_filename, listener);
    } catch (JavaLayerException ex) {
      System.err.println("Convertion failure: " + ex);
    } 
    System.exit(0);
  }
  
  static class jlcArgs {
    public int which_c;
    
    public int output_mode;
    
    public boolean use_own_scalefactor;
    
    public float scalefactor;
    
    public String output_filename;
    
    public String filename;
    
    public boolean verbose_mode;
    
    public int verbose_level = 3;
    
    public jlcArgs() {
      this.which_c = 0;
      this.use_own_scalefactor = false;
      this.scalefactor = 32768.0F;
      this.verbose_mode = false;
    }
    
    public boolean processArgs(String[] argv) {
      this.filename = null;
      Crc16[] crc = new Crc16[1];
      int argc = argv.length;
      this.verbose_mode = false;
      this.output_mode = 0;
      this.output_filename = "";
      if (argc < 2 || argv[1].equals("-h"))
        return Usage(); 
      int i = 1;
      while (i < argc) {
        if (argv[i].charAt(0) == '-') {
          if (argv[i].startsWith("-v")) {
            this.verbose_mode = true;
            if (argv[i].length() > 2)
              try {
                String level = argv[i].substring(2);
                this.verbose_level = Integer.parseInt(level);
              } catch (NumberFormatException ex) {
                System.err.println("Invalid verbose level. Using default.");
              }  
            System.out.println("Verbose Activated (level " + this.verbose_level + ")");
          } else if (argv[i].equals("-p")) {
            if (++i == argc) {
              System.out.println("Please specify an output filename after the -p option!");
              System.exit(1);
            } 
            this.output_filename = argv[i];
          } else {
            return Usage();
          } 
        } else {
          this.filename = argv[i];
          System.out.println("FileName = " + argv[i]);
          if (this.filename == null)
            return Usage(); 
        } 
        i++;
      } 
      if (this.filename == null)
        return Usage(); 
      return true;
    }
    
    public boolean Usage() {
      System.out.println("JavaLayer Converter :");
      System.out.println("  -v[x]         verbose mode. ");
      System.out.println("                default = 2");
      System.out.println("  -p name    output as a PCM wave file");
      System.out.println("");
      System.out.println("  More info on http://www.javazoom.net");
      return false;
    }
  }
}
