package com.wallpaperteam.wallpaperhd.other.ads;

import android.view.View;

import com.wallpaperteam.wallpaperhd.views.views.AppAdViewHolder;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;

/**
 * Created by sev_user on 6/14/2016.
 */
public class AppInstallAdPlacement {
    public AppInstallAdFetcher mFetcher;

    public AppInstallAdPlacement(AppInstallAdFetcher fetcher) {
        mFetcher = fetcher;
    }

    public AppAdViewHolder getView(View rowView) {
        AppAdViewHolder holder = new AppAdViewHolder((NativeAppInstallAdView)rowView);
        return holder;
    }

}
