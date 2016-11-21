package com.jun.xsutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jun.bean.Automata;


public class XML2xmlbeanUtil {
	// 根节点类型
	private static Automata mAutomata;
	
	public static void read(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] buf = new byte[is.available()];
			is.read(buf);
			// 在这里修改根节点类
			mAutomata = XStreamTransformUtil.toBean(Automata.class, buf);
		} catch (Exception e) {
			System.out.println("读取异常:" + e);
		} finally {
			try{
				is.close();
			} catch (Exception e) {
				System.out.println("关闭异常");
			}
		}
	}
	
	public static Automata getAutomata(File file) {
		read(file);
		return mAutomata;
	}
}
