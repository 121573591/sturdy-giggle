package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Route;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020#\n\002\b\003\030\0002\0020\001B\007¢\006\004\b\002\020\003J\025\020\007\032\0020\0062\006\020\005\032\0020\004¢\006\004\b\007\020\bJ\025\020\n\032\0020\0062\006\020\t\032\0020\004¢\006\004\b\n\020\bJ\025\020\f\032\0020\0132\006\020\005\032\0020\004¢\006\004\b\f\020\rR\032\020\017\032\b\022\004\022\0020\0040\0168\002X\004¢\006\006\n\004\b\017\020\020¨\006\021"}, d2 = {"Lokhttp3/internal/connection/RouteDatabase;", "", "<init>", "()V", "Lokhttp3/Route;", "route", "", "connected", "(Lokhttp3/Route;)V", "failedRoute", "failed", "", "shouldPostpone", "(Lokhttp3/Route;)Z", "", "failedRoutes", "Ljava/util/Set;", "okhttp"})
public final class RouteDatabase {
  @NotNull
  private final Set<Route> failedRoutes = new LinkedHashSet<>();
  
  public final synchronized void failed(@NotNull Route failedRoute) {
    Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
    this.failedRoutes.add(failedRoute);
  }
  
  public final synchronized void connected(@NotNull Route route) {
    Intrinsics.checkNotNullParameter(route, "route");
    this.failedRoutes.remove(route);
  }
  
  public final synchronized boolean shouldPostpone(@NotNull Route route) {
    Intrinsics.checkNotNullParameter(route, "route");
    return this.failedRoutes.contains(route);
  }
}
