package me.byteful.cloudlink.api.http.request.impl;

import kong.unirest.UnirestInstance;
import me.byteful.cloudlink.api.http.request.WrappedHttpRequest;
import me.byteful.cloudlink.api.models.ServiceList;
import me.byteful.cloudlink.api.utils.HTTPUtils;
import org.jetbrains.annotations.NotNull;

public class ServiceListRequest implements WrappedHttpRequest<ServiceList> {
  @Override
  public ServiceList send(@NotNull UnirestInstance unirest) {
    return HTTPUtils.get(unirest, "/services/list", ServiceList.class);
  }
}
