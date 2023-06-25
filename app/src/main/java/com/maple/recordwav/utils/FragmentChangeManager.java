package com.maple.recordwav.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * Fragment manager
 *
 */
public class FragmentChangeManager {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;
    private List<Fragment> mFragments;
    private int currentTab;

    public FragmentChangeManager(FragmentManager fragmentManager, int containerViewId, List<Fragment> fragments, int currentTab) {
        mFragmentManager = fragmentManager;
        mContainerViewId = containerViewId;
        mFragments = fragments;
        this.currentTab = currentTab;

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (int index = 0; index < mFragments.size(); index++) {
            Fragment fragment = mFragments.get(index);
            String tag = getFragmentTag(index);
            // mFragmentManager.findFragmentByTag(tag)?.let { remove(it) }
            if (!fragment.isAdded()) {
                transaction.add(mContainerViewId, fragment, tag).hide(fragment);
            }
        }
        transaction.commit();
        setCurrentFragment(currentTab);
    }

    // 获取指定索引 Fragment 的 Tag
    public static String getFragmentTag(int index) {
        return "tab_" + index;
    }

    public void setCurrentFragment(int index) {
        // tab相同 且 页面已添加，不再更新
        if (currentTab == index && mFragments.get(index).isVisible() && mFragmentManager.getFragments().size() > 0)
            return;

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            if (i == index) {
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
        currentTab = index;
    }

    public int currentTab() {
        return currentTab;
    }

    public Fragment currentFragment() {
        return mFragments.get(currentTab);
    }
}
