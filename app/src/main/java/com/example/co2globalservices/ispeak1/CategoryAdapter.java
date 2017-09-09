package com.example.co2globalservices.ispeak1;


import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new IgboFragment();
        } else if (position == 1) {
            return new YorubaFragment();
        } else if (position == 2) {
            return new HausaFragment();
        } else if (position == 3) {
            return new UrhoboFragment();
        } else if (position == 4) {
            return new IbibioFragment();
        } else if (position == 5) {
            return new IsokoFragment();
        } else if (position == 6) {
            return new EsanFragment();
        } else if (position == 7) {
            return new PidginFragment();
        } else if (position == 8) {
            return new OboloFragment();
        } else {
            return new idomaFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {

            return mContext.getString(R.string.category_igbo);
        } else if (position == 1) {
            return mContext.getString(R.string.category_yoruba);
        } else if (position == 2) {
            return mContext.getString(R.string.category_hausa);
        } else if (position == 3) {
            return mContext.getString(R.string.category_urhobo);
        } else if (position == 4) {
            return mContext.getString(R.string.category_ibibio);
        } else if (position == 5) {
            return mContext.getString(R.string.category_isoko);
        } else if (position == 6) {
            return mContext.getString(R.string.category_esan);
        } else if (position == 7) {
            return "Pidgin";
        } else if (position == 8) {
            return "Obolo";
        } else {
            return "Idoma";
        }
    }
}