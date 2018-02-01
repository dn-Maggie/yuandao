package com.dongnao.workbench.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * MD5码操作，针对传入字符串进行加密
 * @author yang.bin
 * @version v1.0
 */
public class MD5Encryption {

	/**
	 * MD5 32位加密
	 * @param inStr 需要加密字符串
	 * @return 加密后的字符串
	 */
	public static String MD5(String inStr) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inStr.getBytes());
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0){
					i += 256;
				}
				if (i < 16){
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			re_md5 = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

}
