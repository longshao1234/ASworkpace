package com.like.longshaolib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author longshao
 * 数据的分享 保存到数据下的东西 保存到本地目录下
 */
public class PreferenceUtils {

	private SharedPreferences mPerferences;

	/**
	 * 初始化数据
	 * 
	 * @param context
	 */
	public PreferenceUtils(Context context) {
		mPerferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * 根据键值 获取对应的数据 如果没有 就默认值为空
	 * 
	 * @param Key
	 * @return
	 */
	public String getString(String Key) {
		return mPerferences.getString(Key, "");
	}

	/**
	 * 保存 数据
	 * 
	 * @param Key
	 * @param Values
	 */
	public void setString(String Key, String Values) {
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.putString(Key, Values);
		Editor.commit();
	}

	/**
	 * 保存 boolean类型的数据
	 * 
	 * @param Key
	 * @param value
	 */
	public void setBoolean(String Key, Boolean value) {
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.putBoolean(Key, value);
		Editor.commit();
	}

	/**
	 * 根据键值返回boolean类型数据 默认值为true
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {
		return mPerferences.getBoolean(key, false);
	}

	/**
	 * 移除数据
	 */
	public void removePreference() {
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.clear();
		Editor.commit();
	}
}
