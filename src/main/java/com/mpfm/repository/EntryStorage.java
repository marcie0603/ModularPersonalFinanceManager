package com.mpfm.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mpfm.model.Entry;
import com.mpfm.util.LocalDateAdaptor;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntryStorage {
    private static final String FILE_PATH = "entries.json";
    private static final Type LIST_TYPE = new TypeToken<List<Entry>>(){}.getType();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdaptor())
            .setPrettyPrinting()
            .create();

    public static void save(List<Entry> entries) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(entries, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Entry> load() {
        if (!Files.exists(Paths.get(FILE_PATH))) return new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, LIST_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
