package me.byteful.cloudlink.api.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface GsonProvider {
  Gson STANDARD = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
}