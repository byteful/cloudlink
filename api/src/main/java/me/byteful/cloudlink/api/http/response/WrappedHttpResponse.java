package me.byteful.cloudlink.api.http.response;

import com.google.gson.JsonObject;
import kong.unirest.HttpResponse;
import org.jetbrains.annotations.NotNull;

public interface WrappedHttpResponse {
  void read(@NotNull HttpResponse<JsonObject> raw);
}
