package com.lvwang.osf;

import com.lvwang.osf.util.CipherUtil;

public class CipherTest {
	public static void main(String[] args) {
		String pwd1 = CipherUtil.generatePassword("hello2");
		System.out.println(CipherUtil.validatePassword(pwd1, "hello")?"equal":"not equal");
	}
}
