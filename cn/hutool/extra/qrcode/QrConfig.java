package cn.hutool.extra.qrcode;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;

public class QrConfig {
  private static final int BLACK = -16777216;
  
  private static final int WHITE = -1;
  
  protected int width;
  
  protected int height;
  
  protected Integer foreColor = Integer.valueOf(-16777216);
  
  protected Integer backColor = Integer.valueOf(-1);
  
  protected Integer margin = Integer.valueOf(2);
  
  protected Integer qrVersion;
  
  protected ErrorCorrectionLevel errorCorrection = ErrorCorrectionLevel.M;
  
  protected Charset charset = CharsetUtil.CHARSET_UTF_8;
  
  protected Image img;
  
  protected int ratio = 6;
  
  protected SymbolShapeHint shapeHint = SymbolShapeHint.FORCE_NONE;
  
  public static QrConfig create() {
    return new QrConfig();
  }
  
  public QrConfig() {
    this(300, 300);
  }
  
  public QrConfig(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public QrConfig setWidth(int width) {
    this.width = width;
    return this;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public QrConfig setHeight(int height) {
    this.height = height;
    return this;
  }
  
  public int getForeColor() {
    return this.foreColor.intValue();
  }
  
  @Deprecated
  public QrConfig setForeColor(int foreColor) {
    this.foreColor = Integer.valueOf(foreColor);
    return this;
  }
  
  public QrConfig setForeColor(Color foreColor) {
    if (null == foreColor) {
      this.foreColor = null;
    } else {
      this.foreColor = Integer.valueOf(foreColor.getRGB());
    } 
    return this;
  }
  
  public int getBackColor() {
    return this.backColor.intValue();
  }
  
  @Deprecated
  public QrConfig setBackColor(int backColor) {
    this.backColor = Integer.valueOf(backColor);
    return this;
  }
  
  public QrConfig setBackColor(Color backColor) {
    if (null == backColor) {
      this.backColor = null;
    } else {
      this.backColor = Integer.valueOf(backColor.getRGB());
    } 
    return this;
  }
  
  public Integer getMargin() {
    return this.margin;
  }
  
  public QrConfig setMargin(Integer margin) {
    this.margin = margin;
    return this;
  }
  
  public Integer getQrVersion() {
    return this.qrVersion;
  }
  
  public QrConfig setQrVersion(Integer qrVersion) {
    this.qrVersion = qrVersion;
    return this;
  }
  
  public ErrorCorrectionLevel getErrorCorrection() {
    return this.errorCorrection;
  }
  
  public QrConfig setErrorCorrection(ErrorCorrectionLevel errorCorrection) {
    this.errorCorrection = errorCorrection;
    return this;
  }
  
  public Charset getCharset() {
    return this.charset;
  }
  
  public QrConfig setCharset(Charset charset) {
    this.charset = charset;
    return this;
  }
  
  public Image getImg() {
    return this.img;
  }
  
  public QrConfig setImg(String imgPath) {
    return setImg(FileUtil.file(imgPath));
  }
  
  public QrConfig setImg(File imgFile) {
    return setImg(ImgUtil.read(imgFile));
  }
  
  public QrConfig setImg(Image img) {
    this.img = img;
    return this;
  }
  
  public int getRatio() {
    return this.ratio;
  }
  
  public QrConfig setRatio(int ratio) {
    this.ratio = ratio;
    return this;
  }
  
  public QrConfig setShapeHint(SymbolShapeHint shapeHint) {
    this.shapeHint = shapeHint;
    return this;
  }
  
  public HashMap<EncodeHintType, Object> toHints() {
    return toHints(BarcodeFormat.QR_CODE);
  }
  
  public HashMap<EncodeHintType, Object> toHints(BarcodeFormat format) {
    HashMap<EncodeHintType, Object> hints = new HashMap<>();
    if (null != this.charset)
      hints.put(EncodeHintType.CHARACTER_SET, this.charset.toString().toLowerCase()); 
    if (null != this.errorCorrection) {
      Object value;
      if (BarcodeFormat.AZTEC == format || BarcodeFormat.PDF_417 == format) {
        value = Integer.valueOf(this.errorCorrection.getBits());
      } else {
        value = this.errorCorrection;
      } 
      hints.put(EncodeHintType.ERROR_CORRECTION, value);
      hints.put(EncodeHintType.DATA_MATRIX_SHAPE, this.shapeHint);
    } 
    if (null != this.margin)
      hints.put(EncodeHintType.MARGIN, this.margin); 
    if (null != this.qrVersion)
      hints.put(EncodeHintType.QR_VERSION, this.qrVersion); 
    return hints;
  }
}
