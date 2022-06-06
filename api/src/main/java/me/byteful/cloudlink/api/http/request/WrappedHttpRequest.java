package me.byteful.cloudlink.api.http.request;

import kong.unirest.UnirestInstance;
import me.byteful.cloudlink.api.http.response.WrappedHttpResponse;
import org.jetbrains.annotations.NotNull;

public interface WrappedHttpRequest<T extends WrappedHttpResponse> {
  T getResponse(@NotNull UnirestInstance unirest);
}
