package com.example.brayo.mealorderingmobileapplication.Count;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import com.example.brayo.mealorderingmobileapplication.R;

/**
 * Created by Brayo on 17/04/2018.
 */

public class Utils {
    public static void setBadgeCount(Context context, LayerDrawable icon, int count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.drawable.ic_shopping_cart);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.drawable.ic_shopping_cart, badge);
    }
}
