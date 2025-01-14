package okio.internal;

import kotlin.Metadata;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\020\t\n\002\b\002\n\002\020\b\n\002\b\013\b\002\030\0002\0020\001B\037\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\006\032\0020\005¢\006\004\b\007\020\bR\027\020\004\032\0020\0028\006¢\006\f\n\004\b\004\020\t\032\004\b\n\020\013R\027\020\006\032\0020\0058\006¢\006\f\n\004\b\006\020\f\032\004\b\r\020\016R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\t\032\004\b\017\020\013¨\006\020"}, d2 = {"Lokio/internal/EocdRecord;", "", "", "entryCount", "centralDirectoryOffset", "", "commentByteCount", "<init>", "(JJI)V", "J", "getCentralDirectoryOffset", "()J", "I", "getCommentByteCount", "()I", "getEntryCount", "okio"})
final class EocdRecord {
  private final long entryCount;
  
  private final long centralDirectoryOffset;
  
  private final int commentByteCount;
  
  public EocdRecord(long entryCount, long centralDirectoryOffset, int commentByteCount) {
    this.entryCount = entryCount;
    this.centralDirectoryOffset = centralDirectoryOffset;
    this.commentByteCount = commentByteCount;
  }
  
  public final long getEntryCount() {
    return this.entryCount;
  }
  
  public final long getCentralDirectoryOffset() {
    return this.centralDirectoryOffset;
  }
  
  public final int getCommentByteCount() {
    return this.commentByteCount;
  }
}
