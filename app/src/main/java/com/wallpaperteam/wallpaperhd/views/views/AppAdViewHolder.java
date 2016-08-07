package com.wallpaperteam.wallpaperhd.views.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wallpaperteam.wallpaperhd.R;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;

import java.util.List;

/**
 * Created by sev_user on 6/14/2016.
 */
public class AppAdViewHolder extends RecyclerView.ViewHolder{

    public NativeAppInstallAdView mAdView;

    public AppAdViewHolder(NativeAppInstallAdView adView) {
        super(adView);

        mAdView = adView;

        mAdView.setHeadlineView(mAdView.findViewById(R.id.appinstall_headline));
        mAdView.setImageView(mAdView.findViewById(R.id.appinstall_image));
        mAdView.setCallToActionView(mAdView.findViewById(R.id.appinstall_call_to_action));
        mAdView.setIconView(mAdView.findViewById(R.id.appinstall_app_icon));
        mAdView.setStarRatingView(mAdView.findViewById(R.id.appinstall_stars));

    }

    public void populateView(NativeAppInstallAd appInstallAd) {
        ((TextView) mAdView.getHeadlineView()).setText(appInstallAd.getHeadline());

        ((Button) mAdView.getCallToActionView()).setText(appInstallAd.getCallToAction());
        ((ImageView) mAdView.getIconView()).setImageDrawable(appInstallAd.getIcon().getDrawable());
        ((RatingBar) mAdView.getStarRatingView())
                .setRating(appInstallAd.getStarRating().floatValue());

        List<NativeAd.Image> images = appInstallAd.getImages();

        if (images.size() > 0) {
            ((ImageView) mAdView.getImageView())
                    .setImageDrawable(images.get(0).getDrawable());
        }

        // assign native ad object to the native view and make visible
        mAdView.setNativeAd(appInstallAd);
        mAdView.setVisibility(View.VISIBLE);
    }

    public void hideView() {
        mAdView.setVisibility(View.GONE);
    }
}
