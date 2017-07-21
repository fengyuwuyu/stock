package com.stock.util;

import java.util.Random;

/**
 * 游戏随机工具类<br>
 */
public class RandomUtil {
	/**
	 * 在一个范围中随机一个整数(包括两端的值)
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt(int min, int max){
		if(min == max){
			return min;
		}
		Random r = new Random();
		int intValue = max - min;
		intValue = intValue < 0 ? 1 : intValue + 1;
		return min +r.nextInt(intValue);
	}
	
	public static long randomLong(){
		Random r = new Random();
		return r.nextLong();
	}
	
	public static float randomFloat(float min, float max){
		if(min == max){
			return min;
		}
		Random r = new Random();
		return min + (max - min) * r.nextFloat();
	}
	
	public static boolean successOrFailure(int percent){
		Random r = new Random();
		int rValue = r.nextInt(100);
		if(rValue < percent){
			return true;
		}
		return false;
	}
	/**
	 * n个整数随机一个<br>
	 * @author suyinglong
	 * @version 2015年5月15日下午3:51:06
	 * @param i 整数可变长参数
	 * @return 随机出的数
	 */
	public static int randomIntByParamList(int... i){
		if(i != null && i.length > 0){
			return i[randomInt(0, i.length - 1)];
		}
		return 0;
	}
	
	/**
	 * 通过百分比随机，看是否中
	 * @author suyinglong
	 * @version 2015年4月8日下午4:04:03
	 * @param percent
	 * @return 返回“中不中”
	 */
	public static boolean randomByPercent(float percent){
		int num = new Float(percent * 100).intValue();
		int r = randomInt(1, 100);
		return r <= num;
	}
	
	public static String randomString(int length) {
		
		final char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
		    char c = chars[randomInt(0, chars.length - 1)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
}
