package com.novoda.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings;
import com.novoda.imageloader.core.model.ImageTag;
import com.novoda.imageloader.core.model.ImageTagFactory;

public class NovodaImageLoader implements ImageLoader {
    private final ImageManager imageManager;
    private final ImageTagFactory tagFactory;
    private final int placeholderResourceId;

    private NovodaImageLoader(int resourceId, ImageTagFactory tagFactory, ImageManager imageManager) {
        this.placeholderResourceId = resourceId;
        this.tagFactory = tagFactory;
        this.imageManager = imageManager;
    }

    public static class Builder {
        private Context context;
        private LoaderSettings settings;
        private int placeholderResourceId;
        private int errorResourceId;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder settings(LoaderSettings settings) {
            this.settings = settings;
            return this;
        }

        public Builder placeholder(int resourceId) {
            this.placeholderResourceId = resourceId;
            return this;
        }

        public Builder error(int resourceId) {
            this.errorResourceId = resourceId;
            return this;
        }

        public ImageLoader build() {
            if (settings == null) {
                settings = new LoaderSettings.SettingsBuilder().build(context);
            }

            ImageTagFactory tagFactory = ImageTagFactory.newInstance(context, placeholderResourceId);
            tagFactory.setErrorImageId(errorResourceId);

            return new NovodaImageLoader(placeholderResourceId, tagFactory, new ImageManager(context, settings));
        }

    }

    @Override
    public void load(String url, ImageView view) {
        view.setImageResource(placeholderResourceId);
        ImageTag tag = tagFactory.build(url, view.getContext());
        view.setTag(tag);

        imageManager.getLoader().load(view);
    }

}