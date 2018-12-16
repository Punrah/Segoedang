package com.tripper.nfctripper.aqua.navigation_manager;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tripper.nfctripper.aqua.BuildConfig;
import com.tripper.nfctripper.aqua.FragmentProduct;
import com.tripper.nfctripper.aqua.NavDrawerActivity;
import com.tripper.nfctripper.aqua.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private NavDrawerActivity mActivity;

    public static FragmentNavigationManager obtain(NavDrawerActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(NavDrawerActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }






    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
                .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }

    @Override
    public void showFragmentProduct(String title) {
        showFragment(FragmentProduct.newInstance(title), false);
    }


}
