package com.like.longshaolib.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.like.longshaolib.utils.helper.GsonType;

/**
 * gson数据解析类
 * @author longshao
 */
public class GSONUtils {

	/**
	 * json数据转换为实体
	 * 
	 * @param json
	 *            数据源
	 * @param entity
	 *            实体
	 * @return
	 */
	public static <T> T getEntity(String json, Class<T> entity) {
		Gson gson = new Gson();
		T jsonEntity = null;
		try {
			jsonEntity = gson.fromJson(json, entity);
		} catch (Exception e) {
			return null;
		}
		return jsonEntity;
	}

	/**
	 * json数据转换为实体集合
	 * 
	 * @param jsonString
	 * @return
	 */
	public static <T> List<T> getEntityList(String jsonString, Class<T> entity) {
		Gson gson = new Gson();
		List<T> list = new ArrayList<T>();
		try {
			list = gson.fromJson(jsonString, new GsonType<T>(entity));
		} catch (Exception e) {
			return new ArrayList<T>();
		}
		return list;
	}

	/**
	 * json转换为map对象集合
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> getEntityMap(String jsonString) {
		Gson gson = new Gson();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = gson.fromJson(jsonString,
					new TypeToken<List<Map<String, Object>>>() {
					}.getType());
		} catch (Exception e) {
			return new ArrayList<Map<String, Object>>();
		}
		return list;
	}
}
