package me.byteful.cloudlink.api.http.response.impl;

import com.google.gson.JsonObject;
import kong.unirest.HttpResponse;
import me.byteful.cloudlink.api.http.response.WrappedHttpResponse;
import org.jetbrains.annotations.NotNull;

public class ServiceListResponse implements WrappedHttpResponse {
  @Override
  public void read(@NotNull HttpResponse<JsonObject> raw) {

  }
}
