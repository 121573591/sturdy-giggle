package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class SizeFileComparator extends AbstractFileComparator implements Serializable {
  private static final long serialVersionUID = -1201561106411416190L;
  
  public static final Comparator<File> SIZE_COMPARATOR = new SizeFileComparator();
  
  public static final Comparator<File> SIZE_REVERSE = new ReverseFileComparator(SIZE_COMPARATOR);
  
  public static final Comparator<File> SIZE_SUMDIR_COMPARATOR = new SizeFileComparator(true);
  
  public static final Comparator<File> SIZE_SUMDIR_REVERSE = new ReverseFileComparator(SIZE_SUMDIR_COMPARATOR);
  
  private final boolean sumDirectoryContents;
  
  public SizeFileComparator() {
    this.sumDirectoryContents = false;
  }
  
  public SizeFileComparator(boolean sumDirectoryContents) {
    this.sumDirectoryContents = sumDirectoryContents;
  }
  
  public int compare(File file1, File file2) {
    long size1, size2;
    if (file1.isDirectory()) {
      size1 = (this.sumDirectoryContents && file1.exists()) ? FileUtils.sizeOfDirectory(file1) : 0L;
    } else {
      size1 = file1.length();
    } 
    if (file2.isDirectory()) {
      size2 = (this.sumDirectoryContents && file2.exists()) ? FileUtils.sizeOfDirectory(file2) : 0L;
    } else {
      size2 = file2.length();
    } 
    long result = size1 - size2;
    if (result < 0L)
      return -1; 
    if (result > 0L)
      return 1; 
    return 0;
  }
  
  public String toString() {
    return super.toString() + "[sumDirectoryContents=" + this.sumDirectoryContents + "]";
  }
}
