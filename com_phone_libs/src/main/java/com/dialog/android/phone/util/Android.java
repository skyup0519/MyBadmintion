package com.dialog.android.phone.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Jason
 */
public class Android {

	// ////////////////////////////////////////////////////////////
	// 
	// System.
	// 
	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	private static int mAndroidVersion = 0;

	/**
	 * Get Android OS Version.
	 * 
	 * @return version
	 */
	public static int computeAndroidVersion() {
		if (mAndroidVersion == 0) {
			try {
				mAndroidVersion = Integer.parseInt(android.os.Build.VERSION.SDK);
			} catch (Exception e) {
				mAndroidVersion = 3;
			}
		}
		return mAndroidVersion;
	}

	/**
	 * 
	 */
	private static int mViewRootDoTraversalCode = -1;

	/**
	 * DoTraversal Message what of ViewRoot.
	 * 
	 * @return view root message what
	 * @see Message#what
	 */
	public static int computeViewRootDoTraversalCode() {
		if (mViewRootDoTraversalCode == -1) {
			try {
				Class<?> root = Class.forName("android.view.ViewRoot");

				Field f = root.getField("DO_TRAVERSAL");
				mViewRootDoTraversalCode = f.getInt(root);
			} catch (Exception e) {
				// Come from Android Source.

				mViewRootDoTraversalCode = 1000;
			}
		}
		return mViewRootDoTraversalCode;
	}

	/**
	 * @param context
	 * @return version code
	 */
	public static int getAppVersionCode(Context context) {
		int mVersionNumber = -1;
		try {
			String pkg = context.getPackageName();
			mVersionNumber = context.getPackageManager().getPackageInfo(pkg, 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return mVersionNumber;
	}

	/**
	 * @param context
	 * @return version name
	 */
	public static String getAppVersionName(Context context) {
		String mVersionNumber = null;
		try {
			String pkg = context.getPackageName();
			mVersionNumber = context.getPackageManager().getPackageInfo(pkg, 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return mVersionNumber;
	}

	// ////////////////////////////////////////////////////////////
	// 
	// Reflection.
	// 
	// ////////////////////////////////////////////////////////////

	/**
	 * Get Field including public and private.
	 * 
	 * @param object
	 * @param field
	 * @return result
	 * @throws Exception
	 */
	public static Object getField(Object object, String field) throws Exception {
		Class<?> clazz = object.getClass();
		Field find = findField(clazz, field);
		find.setAccessible(true);
		Object o = find.get(object);
		return o;
	}

	/**
	 * Set Field including public and private.
	 * 
	 * @param object
	 * @param field
	 * @param value
	 * @return result
	 * @throws Exception
	 */
	public static Object setField(Object object, String field, Object value) throws Exception {
		Class<?> clazz = object.getClass();
		Field find = findField(clazz, field);
		find.setAccessible(true);
		find.set(object, value);
		Object o = find.get(object);
		return o;
	}

	/**
	 * @param clazz
	 * @param field
	 * @return result
	 * @throws Exception
	 */
	private static Field findField(Class<?> clazz, String field) throws Exception {
		// Result.
		Field result = null;
		// Find Public.
		try {
			result = clazz.getField(field);
		} catch (Exception e) {

		}
		// Find Private.
		while (result == null) {
			try {
				result = clazz.getDeclaredField(field);
				if (result == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				if ((clazz = clazz.getSuperclass()) == null) {
					throw e;
				}
			}
		}
		return result;
	}

	/**
	 * Invoke Method including public and private.
	 * 
	 * @param receiver
	 * @param method
	 * @param paramsTypes
	 * @param params
	 * @return result
	 * @throws Exception
	 */
	public static Object invokeMethod(Object receiver, String method, Class<?>[] paramsTypes, Object[] params) throws Exception {
		if (paramsTypes == null) {
			paramsTypes = new Class[0];
		}
		if (params == null) {
			params = new Object[0];
		}
		Class<?> clazz = receiver.getClass();
		Method find = findMethod(clazz, method, paramsTypes);
		find.setAccessible(true);
		Object object = find.invoke(receiver, params);
		return object;
	}

	/**
	 * @param clazz
	 * @param method
	 * @param paramsTypes
	 * @return result
	 * @throws Exception
	 */
	private static Method findMethod(Class<?> clazz, String method, Class<?>[] paramsTypes) throws Exception {
		// Result.
		Method result = null;
		// Find Public.
		try {
			result = clazz.getMethod(method, paramsTypes);
		} catch (Exception e) {

		}
		// Find Private.
		while (result == null) {
			try {
				result = clazz.getDeclaredMethod(method, paramsTypes);
				if (result == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				if ((clazz = clazz.getSuperclass()) == null) {
					throw e;
				}
			}
		}
		return result;
	}

	// ////////////////////////////////////////////////////////////
	// 
	// User Interface.
	// 
	// ////////////////////////////////////////////////////////////

	/**
	 * @param context
	 * @param dip
	 * @return px
	 */
	public static float computeDIPtoPixel(Context context, float dip) {
		Resources resources = context == null ? Resources.getSystem() : context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = metrics.density * dip;
		return px;
	}

	/**
	 * @param dynamic
	 * @param maxWidth
	 * @return size
	 */
	public static float computeDynamicWidth(TextView dynamic, int maxWidth) {
		Rect rect = new Rect();
		Paint paint = new Paint();
		return computeDynamicWidth(dynamic, maxWidth, paint, rect);
	}

	/**
	 * @param dynamic
	 * @param maxWidth
	 * @param paint
	 * @param rect
	 * @return size
	 */
	public static float computeDynamicWidth(TextView dynamic, int maxWidth, Paint paint, Rect rect) {
		String string = (String) dynamic.getText();
		float size = dynamic.getTextSize();
		while (true) {
			paint.setTextSize(size);
			paint.getTextBounds(string, 0, string.length(), rect);
			if (rect.width() > maxWidth) {
				size = size - 1;
			} else {
				break;
			}
		}
		return size;
	}

	/**
	 * @param string
	 * @param size
	 * @return width of text
	 */
	public static int computeStringWidth(String string, float size) {
		Rect rect = new Rect();
		Paint paint = new Paint();
		paint.setTextSize(size);
		paint.getTextBounds(string, 0, string.length(), rect);
		return rect.width();
	}
}
