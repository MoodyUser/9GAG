package me.storm.ninegag.model;

import android.database.Cursor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import me.storm.ninegag.dao.FeedsDataHelper;

/**
 * Created by storm on 14-3-25.
 */
public class Language extends BaseModel {
    private static final HashMap<String, Language> CACHE = new HashMap<String, Language>();

    public int id;
    public String title;
    public String file_ending;
    public int parent;

    private static void addToCache(Language lang) {
        CACHE.put(Integer.toString(lang.id), lang);
    }

    public static Language getFromCache(int id) {
        return CACHE.get(id);
    }

    public static Language fromJson(String json) {
        return new Gson().fromJson(json, Language.class);
    }

    public static Language fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(FeedsDataHelper.FeedsDBInfo.ID));

        Language lang = getFromCache(id);
        if (lang != null) {
            return lang;
        }
        lang = new Gson().fromJson(
                cursor.getString(cursor.getColumnIndex(FeedsDataHelper.FeedsDBInfo.JSON)),
                Language.class);
        addToCache(lang);
        return lang;
    }

    public static class LangRequestData extends ArrayList<Language> {

    }
}
