package me.byteful.cloudlink.api.http.request.impl;

import kong.unirest.UnirestInstance;
import me.byteful.cloudlink.api.http.request.WrappedHttpRequest;
import me.byteful.cloudlink.api.http.response.impl.ServiceListResponse;
import org.jetbrains.annotations.NotNull;

public class ServiceListRequest implements WrappedHttpRequest<ServiceListResponse> {
  @Override
  public ServiceListResponse getResponse(@NotNull UnirestInstance unirest) {
    return null;
  }
}
