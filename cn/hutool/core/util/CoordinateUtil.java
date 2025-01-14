package cn.hutool.core.util;

import java.io.Serializable;
import java.util.Objects;

public class CoordinateUtil {
  public static final double X_PI = 52.35987755982988D;
  
  public static final double PI = 3.141592653589793D;
  
  public static final double RADIUS = 6378245.0D;
  
  public static final double CORRECTION_PARAM = 0.006693421622965943D;
  
  public static boolean outOfChina(double lng, double lat) {
    return (lng < 72.004D || lng > 137.8347D || lat < 0.8293D || lat > 55.8271D);
  }
  
  public static Coordinate wgs84ToGcj02(double lng, double lat) {
    return (new Coordinate(lng, lat)).offset(offset(lng, lat, true));
  }
  
  public static Coordinate wgs84ToBd09(double lng, double lat) {
    Coordinate gcj02 = wgs84ToGcj02(lng, lat);
    return gcj02ToBd09(gcj02.lng, gcj02.lat);
  }
  
  public static Coordinate gcj02ToWgs84(double lng, double lat) {
    return (new Coordinate(lng, lat)).offset(offset(lng, lat, false));
  }
  
  public static Coordinate gcj02ToBd09(double lng, double lat) {
    double z = Math.sqrt(lng * lng + lat * lat) + 2.0E-5D * Math.sin(lat * 52.35987755982988D);
    double theta = Math.atan2(lat, lng) + 3.0E-6D * Math.cos(lng * 52.35987755982988D);
    double bd_lng = z * Math.cos(theta) + 0.0065D;
    double bd_lat = z * Math.sin(theta) + 0.006D;
    return new Coordinate(bd_lng, bd_lat);
  }
  
  public static Coordinate bd09ToGcj02(double lng, double lat) {
    double x = lng - 0.0065D;
    double y = lat - 0.006D;
    double z = Math.sqrt(x * x + y * y) - 2.0E-5D * Math.sin(y * 52.35987755982988D);
    double theta = Math.atan2(y, x) - 3.0E-6D * Math.cos(x * 52.35987755982988D);
    double gg_lng = z * Math.cos(theta);
    double gg_lat = z * Math.sin(theta);
    return new Coordinate(gg_lng, gg_lat);
  }
  
  public static Coordinate bd09toWgs84(double lng, double lat) {
    Coordinate gcj02 = bd09ToGcj02(lng, lat);
    return gcj02ToWgs84(gcj02.lng, gcj02.lat);
  }
  
  public static Coordinate wgs84ToMercator(double lng, double lat) {
    double x = lng * 2.0037508342789244E7D / 180.0D;
    double y = Math.log(Math.tan((90.0D + lat) * Math.PI / 360.0D)) / 0.017453292519943295D;
    y = y * 2.0037508342789244E7D / 180.0D;
    return new Coordinate(x, y);
  }
  
  public static Coordinate mercatorToWgs84(double mercatorX, double mercatorY) {
    double x = mercatorX / 2.0037508342789244E7D * 180.0D;
    double y = mercatorY / 2.0037508342789244E7D * 180.0D;
    y = 57.29577951308232D * (2.0D * Math.atan(Math.exp(y * Math.PI / 180.0D)) - 1.5707963267948966D);
    return new Coordinate(x, y);
  }
  
  private static Coordinate offset(double lng, double lat, boolean isPlus) {
    double dlng = transLng(lng - 105.0D, lat - 35.0D);
    double dlat = transLat(lng - 105.0D, lat - 35.0D);
    double magic = Math.sin(lat / 180.0D * Math.PI);
    magic = 1.0D - 0.006693421622965943D * magic * magic;
    double sqrtMagic = Math.sqrt(magic);
    dlng = dlng * 180.0D / 6378245.0D / sqrtMagic * Math.cos(lat / 180.0D * Math.PI) * Math.PI;
    dlat = dlat * 180.0D / 6335552.717000426D / magic * sqrtMagic * Math.PI;
    if (false == isPlus) {
      dlng = -dlng;
      dlat = -dlat;
    } 
    return new Coordinate(dlng, dlat);
  }
  
  private static double transLng(double lng, double lat) {
    double ret = 300.0D + lng + 2.0D * lat + 0.1D * lng * lng + 0.1D * lng * lat + 0.1D * Math.sqrt(Math.abs(lng));
    ret += (20.0D * Math.sin(6.0D * lng * Math.PI) + 20.0D * Math.sin(2.0D * lng * Math.PI)) * 2.0D / 3.0D;
    ret += (20.0D * Math.sin(lng * Math.PI) + 40.0D * Math.sin(lng / 3.0D * Math.PI)) * 2.0D / 3.0D;
    ret += (150.0D * Math.sin(lng / 12.0D * Math.PI) + 300.0D * Math.sin(lng / 30.0D * Math.PI)) * 2.0D / 3.0D;
    return ret;
  }
  
  private static double transLat(double lng, double lat) {
    double ret = -100.0D + 2.0D * lng + 3.0D * lat + 0.2D * lat * lat + 0.1D * lng * lat + 0.2D * Math.sqrt(Math.abs(lng));
    ret += (20.0D * Math.sin(6.0D * lng * Math.PI) + 20.0D * Math.sin(2.0D * lng * Math.PI)) * 2.0D / 3.0D;
    ret += (20.0D * Math.sin(lat * Math.PI) + 40.0D * Math.sin(lat / 3.0D * Math.PI)) * 2.0D / 3.0D;
    ret += (160.0D * Math.sin(lat / 12.0D * Math.PI) + 320.0D * Math.sin(lat * Math.PI / 30.0D)) * 2.0D / 3.0D;
    return ret;
  }
  
  public static class Coordinate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private double lng;
    
    private double lat;
    
    public Coordinate(double lng, double lat) {
      this.lng = lng;
      this.lat = lat;
    }
    
    public double getLng() {
      return this.lng;
    }
    
    public Coordinate setLng(double lng) {
      this.lng = lng;
      return this;
    }
    
    public double getLat() {
      return this.lat;
    }
    
    public Coordinate setLat(double lat) {
      this.lat = lat;
      return this;
    }
    
    public Coordinate offset(Coordinate offset) {
      this.lng += offset.lng;
      this.lat += offset.lat;
      return this;
    }
    
    public boolean equals(Object o) {
      if (this == o)
        return true; 
      if (o == null || getClass() != o.getClass())
        return false; 
      Coordinate that = (Coordinate)o;
      return (Double.compare(that.lng, this.lng) == 0 && Double.compare(that.lat, this.lat) == 0);
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Double.valueOf(this.lng), Double.valueOf(this.lat) });
    }
    
    public String toString() {
      return "Coordinate{lng=" + this.lng + ", lat=" + this.lat + '}';
    }
  }
}
