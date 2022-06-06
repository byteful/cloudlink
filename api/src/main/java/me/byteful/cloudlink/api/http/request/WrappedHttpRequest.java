package me.byteful.cloudlink.api.http.request;

import kong.unirest.UnirestInstance;
import org.jetbrains.annotations.NotNull;

public interface WrappedHttpRequest<T> {
  T send(@NotNull UnirestInstance unirest);
}
