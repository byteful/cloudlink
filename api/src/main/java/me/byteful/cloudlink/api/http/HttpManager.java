package me.byteful.cloudlink.api.http;

import kong.unirest.UnirestInstance;
import me.byteful.cloudlink.api.http.request.WrappedHttpRequest;
import me.byteful.cloudlink.api.http.response.WrappedHttpResponse;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class HttpManager implements AutoCloseable {
  private final UnirestInstance unirest;

  public HttpManager(UnirestInstance unirest) {
    this.unirest = unirest;
  }

  public UnirestInstance getUnirest() {
    return unirest;
  }

  @NotNull
  public <T extends WrappedHttpResponse> T sendRequest(@NotNull WrappedHttpRequest<T> request) {
    return request.getResponse(unirest);
  }

  @NotNull
  public File downloadFile(@NotNull String filename, @NotNull Path location) {
    return unirest.get("https://cloudlink.byteful.me/api/files/{filename}")
      .routeParam("filename", filename)
      .asFile(location.toString(), StandardCopyOption.REPLACE_EXISTING)
      .getBody();
  }

  @Override
  public void close() {
    unirest.shutDown();
  }
}
