package com.animbus.music;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.afollestad.appthemeengine.ATE;
import com.afollestad.appthemeengine.Config;
import com.afollestad.materialdialogs.MaterialDialog;
import com.animbus.music.media.Library;
import com.animbus.music.media.PlaybackRemote;
import com.animbus.music.util.Options;

import static com.animbus.music.media.PlaybackRemote.LOCAL;


public class GEMApp extends Application {
    private static final int LAST_ATE_CONFIG_UPDATE = 16;
    @Override
    public void onCreate() {
        super.onCreate();
        if (!ATE.config(this, getATEKey()).isConfigured(BuildConfig.VERSION_CODE)) {
            ATE.config(this, getATEKey())
                    .activityTheme(R.style.AppTheme_Faithful)
                    .coloredActionBar(true)
                    .primaryColorRes(R.color.faithfulPrimaryDark)
                    .autoGeneratePrimaryDark(true)
                    .coloredStatusBar(true)
                    .accentColorRes(R.color.default_accent)
                    .navigationViewThemed(true)
                    .navigationViewSelectedIconRes(R.color.default_accent)
                    .navigationViewSelectedTextRes(R.color.default_accent)
                    .lightStatusBarMode(Config.LIGHT_STATUS_BAR_AUTO)
                    .lightToolbarMode(Config.LIGHT_TOOLBAR_AUTO)
                    .commit();
        }

        Options.init(this);
        Library.setContext(this);

        //Initiates the process of setting up all of the media objects to be triggered instantly
        PlaybackRemote.setUp(this);
        PlaybackRemote.inject(LOCAL);
    }

    public String getATEKey() {
        return null;
    }
}