package cn.pixellive.mc.game.event;

public class CancellableEvent {
  private boolean cancelled;
  
  public final boolean getCancelled() {
    return this.cancelled;
  }
  
  public final void setCancelled(boolean paramBoolean) {
    this.cancelled = paramBoolean;
  }
}
