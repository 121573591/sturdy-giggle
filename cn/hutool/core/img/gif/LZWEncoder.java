package cn.hutool.core.img.gif;

import java.io.IOException;
import java.io.OutputStream;

class LZWEncoder {
  private static final int EOF = -1;
  
  private final int imgW;
  
  private final int imgH;
  
  private final byte[] pixAry;
  
  private final int initCodeSize;
  
  private int remaining;
  
  private int curPixel;
  
  static final int BITS = 12;
  
  static final int HSIZE = 5003;
  
  int n_bits;
  
  int maxbits = 12;
  
  int maxcode;
  
  int maxmaxcode = 4096;
  
  int[] htab = new int[5003];
  
  int[] codetab = new int[5003];
  
  int hsize = 5003;
  
  int free_ent = 0;
  
  boolean clear_flg = false;
  
  int g_init_bits;
  
  int ClearCode;
  
  int EOFCode;
  
  int cur_accum = 0;
  
  int cur_bits = 0;
  
  final int[] masks = new int[] { 
      0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 
      1023, 2047, 4095, 8191, 16383, 32767, 65535 };
  
  int a_count;
  
  byte[] accum = new byte[256];
  
  LZWEncoder(int width, int height, byte[] pixels, int color_depth) {
    this.imgW = width;
    this.imgH = height;
    this.pixAry = pixels;
    this.initCodeSize = Math.max(2, color_depth);
  }
  
  void char_out(byte c, OutputStream outs) throws IOException {
    this.accum[this.a_count++] = c;
    if (this.a_count >= 254)
      flush_char(outs); 
  }
  
  void cl_block(OutputStream outs) throws IOException {
    cl_hash(this.hsize);
    this.free_ent = this.ClearCode + 2;
    this.clear_flg = true;
    output(this.ClearCode, outs);
  }
  
  void cl_hash(int hsize) {
    for (int i = 0; i < hsize; i++)
      this.htab[i] = -1; 
  }
  
  void compress(int init_bits, OutputStream outs) throws IOException {
    this.g_init_bits = init_bits;
    this.clear_flg = false;
    this.n_bits = this.g_init_bits;
    this.maxcode = MAXCODE(this.n_bits);
    this.ClearCode = 1 << init_bits - 1;
    this.EOFCode = this.ClearCode + 1;
    this.free_ent = this.ClearCode + 2;
    this.a_count = 0;
    int ent = nextPixel();
    int hshift = 0;
    int fcode;
    for (fcode = this.hsize; fcode < 65536; fcode *= 2)
      hshift++; 
    hshift = 8 - hshift;
    int hsize_reg = this.hsize;
    cl_hash(hsize_reg);
    output(this.ClearCode, outs);
    int c;
    label35: while ((c = nextPixel()) != -1) {
      fcode = (c << this.maxbits) + ent;
      int i = c << hshift ^ ent;
      if (this.htab[i] == fcode) {
        ent = this.codetab[i];
        continue;
      } 
      if (this.htab[i] >= 0) {
        int disp = hsize_reg - i;
        if (i == 0)
          disp = 1; 
        do {
          if ((i -= disp) < 0)
            i += hsize_reg; 
          if (this.htab[i] == fcode) {
            ent = this.codetab[i];
            continue label35;
          } 
        } while (this.htab[i] >= 0);
      } 
      output(ent, outs);
      ent = c;
      if (this.free_ent < this.maxmaxcode) {
        this.codetab[i] = this.free_ent++;
        this.htab[i] = fcode;
        continue;
      } 
      cl_block(outs);
    } 
    output(ent, outs);
    output(this.EOFCode, outs);
  }
  
  void encode(OutputStream os) throws IOException {
    os.write(this.initCodeSize);
    this.remaining = this.imgW * this.imgH;
    this.curPixel = 0;
    compress(this.initCodeSize + 1, os);
    os.write(0);
  }
  
  void flush_char(OutputStream outs) throws IOException {
    if (this.a_count > 0) {
      outs.write(this.a_count);
      outs.write(this.accum, 0, this.a_count);
      this.a_count = 0;
    } 
  }
  
  final int MAXCODE(int n_bits) {
    return (1 << n_bits) - 1;
  }
  
  private int nextPixel() {
    if (this.remaining == 0)
      return -1; 
    this.remaining--;
    byte pix = this.pixAry[this.curPixel++];
    return pix & 0xFF;
  }
  
  void output(int code, OutputStream outs) throws IOException {
    this.cur_accum &= this.masks[this.cur_bits];
    if (this.cur_bits > 0) {
      this.cur_accum |= code << this.cur_bits;
    } else {
      this.cur_accum = code;
    } 
    this.cur_bits += this.n_bits;
    while (this.cur_bits >= 8) {
      char_out((byte)(this.cur_accum & 0xFF), outs);
      this.cur_accum >>= 8;
      this.cur_bits -= 8;
    } 
    if (this.free_ent > this.maxcode || this.clear_flg)
      if (this.clear_flg) {
        this.maxcode = MAXCODE(this.n_bits = this.g_init_bits);
        this.clear_flg = false;
      } else {
        this.n_bits++;
        if (this.n_bits == this.maxbits) {
          this.maxcode = this.maxmaxcode;
        } else {
          this.maxcode = MAXCODE(this.n_bits);
        } 
      }  
    if (code == this.EOFCode) {
      while (this.cur_bits > 0) {
        char_out((byte)(this.cur_accum & 0xFF), outs);
        this.cur_accum >>= 8;
        this.cur_bits -= 8;
      } 
      flush_char(outs);
    } 
  }
}
