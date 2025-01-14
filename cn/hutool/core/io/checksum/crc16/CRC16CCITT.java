package cn.hutool.core.io.checksum.crc16;

public class CRC16CCITT extends CRC16Checksum {
  private static final long serialVersionUID = 1L;
  
  private static final int WC_POLY = 33800;
  
  public void update(int b) {
    this.wCRCin ^= b & 0xFF;
    for (int j = 0; j < 8; j++) {
      if ((this.wCRCin & 0x1) != 0) {
        this.wCRCin >>= 1;
        this.wCRCin ^= 0x8408;
      } else {
        this.wCRCin >>= 1;
      } 
    } 
  }
}
