package com.wallpaperteam.wallpaperhd.views.views;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wallpaperteam.wallpaperhd.activities.OnItemClickListener;
import com.wallpaperteam.wallpaperhd.R;
import com.mikepenz.iconics.view.IconicsImageView;

/**
 * Created by sev_user on 6/14/2016.
 */
public class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public LinearLayout imageTextContainer;
    public ImageView imageView;
    public TextView imageAuthor;
    public RelativeLayout imageLoveContainer;
    public IconicsImageView imageLovedYes;
    public IconicsImageView imageLovedNo;
    public OnItemClickListener onItemClickListener;

    public ImagesViewHolder(final View itemView, OnItemClickListener onItemClickListener) {

        super(itemView);
        this.onItemClickListener = onItemClickListener;

        imageTextContainer = (LinearLayout) itemView.findViewById(R.id.item_image_text_container);
        imageView = (ImageView) itemView.findViewById(R.id.item_image_img);
        imageAuthor = (TextView) itemView.findViewById(R.id.item_image_author);
        imageLoveContainer = (RelativeLayout) itemView.findViewById(R.id.item_image_loved_container);
        imageLovedYes = (IconicsImageView) itemView.findViewById(R.id.item_image_loved_yes);
        imageLovedNo = (IconicsImageView) itemView.findViewById(R.id.item_image_loved_no);

        imageView.setOnClickListener(this);
    }

    public void animateHeart(View imageLovedOn, View imageLovedOff, boolean on) {
        imageLovedOn.setVisibility(View.VISIBLE);
        imageLovedOff.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            viewPropertyStartCompat(imageLovedOff.animate().scaleX(on ? 0 : 1).scaleY(on ? 0 : 1).alpha(on ? 0 : 1));
            viewPropertyStartCompat(imageLovedOn.animate().scaleX(on ? 1 : 0).scaleY(on ? 1 : 0).alpha(on ? 1 : 0));
        }
    }

    public static void viewPropertyStartCompat(ViewPropertyAnimator animator) {
        if (Build.VERSION.SDK_INT >= 14) {
            animator.start();
        }
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onClick(v, getPosition());
    }



}

