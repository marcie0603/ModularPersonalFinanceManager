package com.mpfm.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mpfm.model.Entry;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntryStorage {
    private static final String FILE_PATH = "entries.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, context) -> LocalDate.parse(json.getAsString()))
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, type, context) -> new JsonPrimitive(src.toString()))
            .setPrettyPrinting()
            .create();

    public static void save(List<Entry> entries) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            gson.toJson(entries, writer);
        } catch (IOException e) {
            System.err.println("Error saving entries: " + e.getMessage());
        }
    }

    public static List<Entry> load() {
        if (!Files.exists(Paths.get(FILE_PATH))) return new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            Type listType = new TypeToken<List<Entry>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error loading entries: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
