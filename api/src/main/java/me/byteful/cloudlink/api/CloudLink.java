package me.byteful.cloudlink.api;

import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;
import kong.unirest.gson.GsonObjectMapper;
import me.byteful.cloudlink.api.http.HttpManager;
import me.byteful.cloudlink.api.providers.GsonProvider;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CloudLink {
  private static CloudLink instance;

  @NotNull
  private final CommentedConfigurationNode config;
  @NotNull
  private final HttpManager httpManager;

  private CloudLink() throws IOException {
    final Path configFile = new File(".", "config.yml").toPath();
    if (!Files.exists(configFile)) {
      final InputStream def = CloudLink.class.getClassLoader().getResourceAsStream("config.yml");

      if (def != null) {
        Files.copy(def, configFile);
      } else {
        Files.createFile(configFile);
      }
    }
    this.config = YamlConfigurationLoader.builder()
      .nodeStyle(NodeStyle.BLOCK)
      .indent(2)
      .path(configFile)
//      .source(() -> Files.newBufferedReader(configFile, StandardCharsets.UTF_8))
//      .sink(() -> Files.newBufferedWriter(configFile, StandardCharsets.UTF_8))
      .build()
      .load();

    final UnirestInstance unirest = Unirest.spawnInstance();
    unirest.config()
      .setObjectMapper(new GsonObjectMapper(GsonProvider.STANDARD))
      .defaultBaseUrl("https://cloudlink.byteful.me/api")
      .setDefaultHeader("Accept", "application/json")
      .followRedirects(false)
      .enableCookieManagement(false)
      .socketTimeout(500)
      .connectTimeout(1000)
      .automaticRetries(true)
      .addShutdownHook(true)
      .setDefaultHeader("Authorization", config.node("api_token").getString());
    this.httpManager = new HttpManager(unirest);
  }

  public static CloudLink getInstance() {
    try {
      return instance != null ? instance : (instance = new CloudLink());
    } catch (IOException e) {
      throw new RuntimeException("Failed to load CloudLink API instance...", e);
    }
  }

  public void shutdown() {
    httpManager.close();
  }

  public @NotNull CommentedConfigurationNode getConfig() {
    return config;
  }

  public @NotNull HttpManager getHttpManager() {
    return httpManager;
  }
}
