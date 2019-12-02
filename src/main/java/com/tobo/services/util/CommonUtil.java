/**
 * 
 */
package com.tobo.services.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author PRASADBolla 
 * 
 * Utility Class for common functions.
 */
public class CommonUtil {
	public static final String YES = "YES";
	public static final String NO = "NO";

	/**
	 * @param list
	 * @return
	 */
	public static boolean isEmptyList(Collection<?> list) {
		if (list == null || list.isEmpty())
			return true;

		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj != null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty())
			return true;

		return false;
	}
}
