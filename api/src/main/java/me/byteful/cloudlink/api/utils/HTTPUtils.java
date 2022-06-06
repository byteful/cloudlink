package me.byteful.cloudlink.api.utils;

import kong.unirest.UnirestInstance;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public final class HTTPUtils {
  public static <T> T get(@NotNull UnirestInstance inst, @NotNull String path, @NotNull Class<T> type) {
    return inst.get(path)
      .asObject(type)
      .getBody();
  }

  public static <T> T post(@NotNull UnirestInstance inst, @NotNull String path, @NotNull Class<T> type, @NotNull Object body) {
    return inst.post(path)
      .charset(StandardCharsets.UTF_8)
      .contentType("application/json")
      .body(body)
      .asObject(type)
      .getBody();
  }
}