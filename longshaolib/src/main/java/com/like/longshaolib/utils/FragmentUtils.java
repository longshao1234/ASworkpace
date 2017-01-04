/*******************************************************************************
 *
 * Copyright (c) Baina Info Tech Co. Ltd
 *
 * FragmentUtils
 *
 * app.util.FragmentUtils.java
 * TODO: File description or class description.
 *
 * @author: qixiao
 * @since:  Feb 9, 2014
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.like.longshaolib.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 主要用于管理Fragment之间的操作
 *
 * @author longshao
 */
public class FragmentUtils {

    private FragmentUtils() {

    }

    public static void replaceFragment(FragmentManager fragmentManager, int container,
            Class<? extends Fragment> newFragment, Bundle args) {
        replaceFragment(fragmentManager, container, newFragment, args, false);
    }

    public static void replaceFragment(FragmentManager fragmentManager, int container,
            Fragment newFragment) {
        replaceFragment(fragmentManager, container, newFragment, false);
    }

    /**
     * replace fragment
     * @param fragmentManager
     * @param container
     * @param newFragment
     * @param args
     * @param addToBackStack
     * @return
     */
    public static void replaceFragment(FragmentManager fragmentManager, int container,
            Class<? extends Fragment> newFragment, Bundle args, boolean addToBackStack) {

        Fragment fragment = null;

        // 构造新的Fragment
        try {
            fragment = newFragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            // 设置参数
            if (args != null && !args.isEmpty()) {
                final Bundle bundle = fragment.getArguments();
                if (bundle != null) {
                    bundle.putAll(args);
                } else {
                    fragment.setArguments(args);
                }
            }
            // 替换
            replaceFragment(fragmentManager, container, fragment, addToBackStack);
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, int container,
            Fragment newFragment, boolean addToBackStack) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        final String tag = newFragment.getClass().getSimpleName();

        if (newFragment != null) {
            transaction.replace(container, newFragment, tag);
        }

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 添加fragment
     * @param manager fragment管理栈 必填项
     * @param container fragmentLayoutID 必填项
     * @param newFragment 新的fragment 必填项
     * @param args
     */
    public static void addFragment(FragmentManager manager,int container,Class<? extends Fragment> newFragment,Bundle args){
        addFragment(manager,container,null,newFragment,args,false);
    }
    /**
     * 添加fragment
     * @param manager fragment管理栈 必填项
     * @param container fragmentLayoutID 必填项
     * @param currentFragment 当前fragment
     * @param newFragment 新的fragment 必填项
     * @param args 参数
     * @param addToBackStack 是否加入堆栈
     */
    public static void addFragment(FragmentManager manager,int container,Class<? extends Fragment> currentFragment,Class<? extends Fragment> newFragment,Bundle args,boolean addToBackStack){
        final FragmentTransaction transaction = manager.beginTransaction();
        final String tag = newFragment.getSimpleName();
        Fragment fragment = manager.findFragmentByTag(tag);

        //在堆栈里找到有次fragment没有
        if(fragment!=null){
            if(newFragment!=currentFragment){
                //是否隐藏当前的fragment
                if (currentFragment!=null){
                    Fragment _currentFragment=manager.findFragmentByTag(currentFragment.getSimpleName());
                    if(_currentFragment!=null){
                        transaction.hide(_currentFragment);
                    }
                }
                //显示新的fragment
                transaction.show(fragment);
                if (addToBackStack) {
                    transaction.addToBackStack(null);
                }
                if(args!=null&& !args.isEmpty()){
                    fragment.getArguments().putAll(args);
                }
                transaction.commitAllowingStateLoss();
            }else {
                //相等于的话 什么都不做
            }
        }else {
            try {
                fragment = newFragment.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // 为新的Fragment添加参数
            if (fragment != null) {
                if (args != null && !args.isEmpty()) {
                    final Bundle bundle = fragment.getArguments();
                    if (bundle != null) {
                        bundle.putAll(args);
                    } else {
                        fragment.setArguments(args);
                    }
                }
            }

            // 隐藏当前的Fragment
            if (currentFragment!=null){
                Fragment _currentFragment=manager.findFragmentByTag(currentFragment.getSimpleName());
                if(_currentFragment!=null){
                    transaction.hide(_currentFragment);
                }
            }

            transaction.add(container, fragment, tag);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public static void replce(){

    }

    /**
     * 切换fragment
     * @param manager fragment管理器 必填项
     * @param container fragmentLayoutID 必填项
     * @param currentfragment 当前fragment
     * @param newFragment 新的fragment
     * @param args 传入的参数
     * @param addToBackStack 是否加入回退栈
     * @return
     */
    public static Fragment switchFragnent(FragmentManager manager,int container,Fragment currentfragment,Class<? extends Fragment> newFragment,Bundle args,boolean addToBackStack){
        final FragmentTransaction transaction = manager.beginTransaction();
        final String tag = newFragment.getSimpleName();
        Fragment fragment = manager.findFragmentByTag(tag);

        // 如果在栈中找到相应的Fragment，则显示，否则重新生成一个
        if (fragment != null) {
            if (fragment != currentfragment) {
                if (currentfragment != null) {
                    transaction.hide(currentfragment);
                }
                transaction.show(fragment);
                if (addToBackStack) {
                    transaction.addToBackStack(null);
                }
                if(args!=null&& !args.isEmpty()){
                    fragment.getArguments().putAll(args);
                }
                transaction.commitAllowingStateLoss();
            }
            return fragment;
        } else {
            try {
                fragment = newFragment.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 为新的Fragment添加参数
        if (fragment != null) {
            if (args != null && !args.isEmpty()) {
                final Bundle bundle = fragment.getArguments();
                if (bundle != null) {
                    bundle.putAll(args);
                } else {
                    fragment.setArguments(args);
                }
            }
        }

        // 显示新的Fragment
        if (currentfragment != null) {
            transaction.hide(currentfragment);
        }
        transaction.add(container, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
        return fragment;
    }
}
