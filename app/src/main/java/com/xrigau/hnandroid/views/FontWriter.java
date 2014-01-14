package com.xrigau.hnandroid.views;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

final class FontWriter {
    private static final Hashtable<String, SoftReference<Typeface>> FONT_CACHE = new Hashtable<String, SoftReference<Typeface>>();

    private final TextView view;
    private final AssetManager assets;
    private final TypedArray typedArray;

    static FontWriter create(TextView view, AttributeSet attributeSet, int[] attributes) {
        Context context = view.getContext();
        AssetManager assets = context.getAssets();
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, attributes);
        return new FontWriter(view, assets, typedArray);
    }

    private FontWriter(TextView view, AssetManager assets, TypedArray typedArray) {
        this.view = view;
        this.assets = assets;
        this.typedArray = typedArray;
    }

    void setCustomFont(int fontId) {
        String customFont = typedArray.getString(fontId);
        setCustomFont(customFont);
        typedArray.recycle();
    }

    void setCustomFont(String fontName) {
        if (TextUtils.isEmpty(fontName)) {
            return;
        }
        view.setTypeface(getFont(fontName));
    }

    private Typeface getFont(String name) {
        synchronized (FONT_CACHE) {
            if (fontExistsInCache(name)) {
                return getCachedTypeFace(name);
            }

            Typeface typeface = createTypeFace(name);
            saveFontToCache(name, typeface);

            return typeface;
        }
    }

    private boolean fontExistsInCache(String name) {
        return FONT_CACHE.get(name) != null && getCachedTypeFace(name) != null;
    }

    private Typeface getCachedTypeFace(String name) {
        return FONT_CACHE.get(name).get();
    }

    private Typeface createTypeFace(String name) {
        return Typeface.createFromAsset(assets, "fonts/" + name + ".ttf");
    }

    private void saveFontToCache(String name, Typeface typeface) {
        FONT_CACHE.put(name, new SoftReference<Typeface>(typeface));
    }
}