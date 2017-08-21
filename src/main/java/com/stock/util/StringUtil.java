package com.stock.util;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String EMPTY = "";
	public static final String COMMA = ",";
	public final static String UNDERLINE = "_";
	public final static String t = "t";
	public final static String T = "T";
	public final static String NULL = "null";
	public final static String COLON = ":";
	protected final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	protected final static String HEX_CHARS = "0123456789abcdefABCDEF";
	protected final static byte[] HEX_VALUES = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 10, 11, 12, 13,
			14, 15 };

	protected final static Pattern DEC_PATTERN = Pattern.compile("^[0-9]*$");
	protected final static Pattern HEX_PATTERN = Pattern.compile("^[0-9,A-F,a-f]*$");

	public static boolean isDecString(String string) {
		// TODO: Optimize
		return DEC_PATTERN.matcher(string).matches();
	}

	public static boolean isHexString(String string) {
		// TODO: Optimize
		return HEX_PATTERN.matcher(string).matches();
	}

	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static String LongToHexString(long value) {
		int len = 16;
		char[] hexChars = new char[len];
		for (int j = 0; j < 8; j++) {
			int v = (int) value & 0xFF;
			hexChars[(7 - j) << 1] = HEX_ARRAY[v >>> 4];
			hexChars[((7 - j) << 1) + 1] = HEX_ARRAY[v & 0x0F];
			value = (value >>> 8);
		}
		return new String(hexChars);
	}

	public static String LongToHexString(long high, long low) {
		int len = 32;
		char[] hexChars = new char[len];

		for (int j = 0; j < 8; j++) {
			int hv = (int) high & 0xFF;
			int lv = (int) low & 0xFF;
			hexChars[(7 - j) << 1] = HEX_ARRAY[hv >>> 4];
			hexChars[((7 - j) << 1) + 1] = HEX_ARRAY[hv & 0x0F];
			hexChars[((7 - j) << 1) + 16] = HEX_ARRAY[lv >>> 4];
			hexChars[((7 - j) << 1) + 1 + 16] = HEX_ARRAY[lv & 0x0F];
			high = (high >>> 8);
			low = (low >>> 8);
		}
		return new String(hexChars);
	}

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null) {
			return null;
		}

		if (hexString.length() % 2 != 0) {
			return null;
		}
		try {
			byte[] bytes = new byte[hexString.length() / 2];
			for (int i = 0; i < hexString.length() / 2; i++) {
				char ch = hexString.charAt(i * 2);
				bytes[i] = (byte) (HEX_VALUES[HEX_CHARS.indexOf(ch)] << 4);

				ch = hexString.charAt(i * 2 + 1);
				bytes[i] |= (byte) (HEX_VALUES[HEX_CHARS.indexOf(ch)]);
			}

			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description 鎶奡tring绫诲瀷鐨勫�艰浆鍖栦负int绫诲瀷
	 * @param str
	 * @return 濡傛灉杞崲澶辫触锛岃繑鍥�0锛�
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static int valueOfInt(String str) {
		int result = 0;
		if (!isNullEmpty(str)) {
			result = Integer.parseInt(str);
		}
		return result;
	}

	public static Integer valueOfInteger(String str) {
		Integer result = null;
		if (!isNullEmpty(str)) {
			result = Integer.parseInt(str);
		}
		return result;
	}

	/**
	 * @Description 鍒ゆ柇瀛楃涓叉槸鍚︿负null鎴杄mpty
	 * @param str
	 * @return
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static boolean isNullEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 
	 * 姝ゆ柟娉曟槸灏嗗瓧绗︿覆杞寲涓簂ong绫诲瀷鐨勬暟
	 *
	 * @return 濡傛灉杞崲澶辫触锛岃繑鍥�0锛�
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static long valueOfLong(String str) {
		long result = 0;
		if (!isNullEmpty(str)) {
			try {
				result = Long.parseLong(str);
			} catch (Exception e) {
				result = 0;
			}
		}
		return result;
	}

	public static Short valueOfShort(String str) {
		Short result = 0;
		if (!isNullEmpty(str)) {
			result = Short.parseShort(str);
		}
		return result;
	}

	/**
	 * 
	 * 姝ゆ柟娉曟槸鏍规嵁瀛楃涓插垽鏂槸false寮�杩樻槸true,浼犲叆鐨勫瓧绗︿覆濡傛灉鏄互t,T锛屽紑澶存垨绛変簬 1锛岄兘瑙嗕负true
	 *
	 * @param str
	 * @return
	 * @author Terry
	 * @time 2016骞�5鏈�11鏃�
	 */
	public static boolean valueOfBoolean(String str) {
		boolean flag = false;
		if (!isNullEmpty(str)) {
			if (str.startsWith(t) || str.startsWith(T) || str.equals("1")) {
				flag = true;
			}
		}
		return flag;
	}

	public static byte valueOfByte(String str) {
		byte result = 0;
		if (!isNullEmpty(str)) {
			result = Byte.parseByte(str);
		}
		return result;
	}

	public static double valueOfDouble(String str) {
		double result = 0;
		if (!isNullEmpty(str)) {
			result = Double.parseDouble(str);
		}
		return result;
	}

	/**
	 *
	 * <p>
	 * Title: firstToUpper
	 * </p>
	 * <p>
	 * Description:鎶婂瓧绗︿覆杞寲涓洪瀛楁瘝澶у啓鐨勫瓧绗︿覆
	 * </p>
	 *
	 * @param str
	 * @return
	 * @author guangshuai.wang
	 */
	public static String firstToUpper(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

	public static String firstToLower(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
	}

	/**
	 * 
	 * 姝ゆ柟娉曟槸鑾峰彇涓�涓敮涓�鐨剈uid
	 *
	 * @return
	 * @author Terry
	 * @time 2016骞�6鏈�6鏃�
	 */
	public static String getUUid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * 鎻忚堪:鑾峰彇涓�涓ぇ鍐欑殑娌℃湁鐭嚎鐨�32浣嶅彲瑙嗗寲UUID
	 *
	 * @return
	 * @author Cai
	 * @time 2016骞�9鏈�8鏃�-涓嬪崍5:36:21
	 */
	public static String getUuidUpper() {
		return getUUid().replaceAll("-", "").toUpperCase();
	}

	/**
	 * 鎶婂浐瀹氭牸寮忕殑瀛楃鐨勬暟缁勪覆杞寲涓簃ap锛歬ey:value key:value銆傘�傘�傘��
	 * 杩欎釜鏂规硶鎶婂弬鏁拌浆鍖栦负key-value瀵瑰簲鐨刴ap闆嗗悎
	 */
	public static Map<String, String> splitStrToMap(String[] args) {
		Map<String, String> map = CollectionUtil.createHashMap();
		if (args != null && args.length > 0) {
			for (String arg : args) {
				String[] keyValues = arg.split(":");
				if (keyValues.length == 2) {
					map.put(keyValues[0], keyValues[1]);
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * 鎻忚堪:杩欎釜鏂规硶鏄皢涓�涓笅鍒掔嚎鍒嗛殧鐨勫瓧绗︿覆杞寲涓洪瀛楁瘝澶у啓鐨勯┘宄板懡鍚嶅瓧绗︿覆
	 *
	 * @param tableName
	 * @return
	 * @author Terry
	 * @time 2016骞�8鏈�6鏃�-涓嬪崍4:46:53
	 */
	public static String toCamelCase(String field) {
		field = field.toLowerCase();
		String[] strs = field.split(UNDERLINE);
		StringBuilder className = new StringBuilder();
		if (strs.length >= 2) {

			for (String str : strs) {

				className.append(StringUtil.firstToUpper(str));
			}
		} else {

			className.append(StringUtil.firstToUpper(field));
		}
		return className.toString();
	}

	public static byte[] toBytes(String str) {
		return str.getBytes();
	}

	public static String fromBytes(byte[] str) {
		return new String(str);
	}

	/**
	 * 
	 * @Desc 描述：检测某个字符串是否都是数字
	 * @param value
	 * @return
	 * @author 王广帅
	 * @Date 2017年6月8日 下午7:49:27
	 *
	 */
	public static boolean checkNumber(String value) {
		if (isNullEmpty(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(value).matches();
	}

}
