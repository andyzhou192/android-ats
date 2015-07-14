package com.cmcc.android.ats.handler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.cmcc.android.ats.annotation.Test;
import com.cmcc.android.ats.tools.StringTools;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * android 扫描某个包下的所有class文件
 * 
 * @author zhouyelin
 * 
 */
public class ObtainClassHandler {
	private final static String TAG = "ObtainClassHandler";

	/**
	 * android 扫描某个包下的所有class文件
	 * 
	 * @param ctx
	 * @param entityPackage
	 *            ,包名（如：com.cmcc.test）
	 * @return
	 */
	public static List<Class<?>> scan(Context ctx, String entityPackage) {
		if (StringTools.isEmpty(entityPackage)) {
			entityPackage = ctx.getPackageName() + ".testscript";
		}
		Log.i("", "entityPackage=====>" + entityPackage);
		List<Class<?>> classes = new ArrayList<Class<?>>();
		try {
			PathClassLoader classLoader = (PathClassLoader) Thread
					.currentThread().getContextClassLoader();

			DexFile dex = new DexFile(ctx.getPackageResourcePath());
			Enumeration<String> entries = dex.entries();
			while (entries.hasMoreElements()) {
				String entryName = entries.nextElement();
				if (entryName.contains(entityPackage)) {
					Log.d("", "entryName------>" + entryName);
					Class<?> entryClass = Class.forName(entryName, true,
							classLoader);
					// Test annotation = entryClass.getAnnotation(Test.class);
					// if (annotation != null) {
					if (entryClass.isAnnotationPresent(Test.class)) {
						classes.add(entryClass);
						Log.d("", "entryClass------>" + entryClass);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == classes || !(classes.size() > 0)) {
			Log.e(TAG,
					"Test class is null，please make sure the test class is put in \"*.testscript\" package（* is package in manifest）, and class added @Test.");
		}
		return classes;
	}
}
