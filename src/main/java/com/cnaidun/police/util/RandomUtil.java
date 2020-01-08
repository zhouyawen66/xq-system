
package com.cnaidun.police.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 获取随机数util
 * @author dongyin
 */
public class RandomUtil {
	/**
	 * 获取2个数之间的随机整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static String getRandomNumBteween(int min, int max) {
		Random random = new Random();
		return String.valueOf(random.nextInt((max - min) + 1) + min);
	}

	/**
	 * 六位随机数
	 * @return
	 */
	public static String getRandomSixNum() {
		int rannum = (int)(Math.random()*(999999-100000+1))+100000;
		return ""+rannum;
	}

	/**
	 * 2019071614121255186275
	 * @return
	 */
	public static String getRandomNum() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		int rannum = (int)(Math.random()*(999999-100000+1))+100000;
		return str+rannum;
	}

	/**
	 * 生成指定位数的随机数
	 * String
	 * @author xubin
	 * @date 2019年7月16日 上午10:35:36
	 */
	public static String getRandom(int length) {
		StringBuffer val = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val.append(String.valueOf(random.nextInt(10)));
		}
		return val.toString();
	}

	public static void main(String[] args) {
		String random = RandomUtil.getRandom(16);
		System.out.println(random);
	}
}

