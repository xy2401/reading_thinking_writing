package com.xy2401.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 来源 Java字符串之性能优化 http://www.importnew.com/14595.html
 * 
 * @author xy2401
 *
 */
public class StringOptimize {

	public static void main(String[] args) {

		// 你需要显式的将第一个值转化成String（不然的话像System.out.println(1+’a')会输出98，而不是”1a”）。
		// 嘛，String 和 char的问题 char 直接与数字运算是数字
		System.out.println(1 + "a");// 1a
		System.out.println(1 + 'a');// 98

		// string_exp + any_exp
		// 如果表达式里有多个+号的话，后面相应也会多多几个StringBuilder.append的调用，最后才是toString方法。
		// new StringBuilder().append( string_exp ).append( any_exp ).toString()
		System.out.println(" " + 1 + 'a');// 1a

		// 不幸的是，这是最糟糕的实现方法了
		// xy:为什么？不是从来说使用 StringBuilder().append 效率好吗？
		// 话说其实从 起码从jdk1.5开始 字符串连加运算就会 自动调用 StringBuilder.append 还是有人守着老旧的优化方法
		// 写丑陋的append 不是指for循环的，特指就是一条拼接这种

	}

	private static String m_separator = ".", m_separatorChar = ".";// 分割符
																	// （译注：能用这个变量名的，应该不是Java开发出身的吧。。）
																	// xy 为什么？

	@SuppressWarnings("unused")
	private static List<String> split(final String str) {
		final List<String> res = new ArrayList<String>(10);
		int pos, prev = 0;
		while ((pos = str.indexOf(m_separator, prev)) != -1) {
			res.add(str.substring(prev, pos));
			prev = pos + m_separator.length(); // start from next char after
												// separator
		}
		res.add(str.substring(prev));
		return res;
	}

	@SuppressWarnings("unused")
	private static List<String> split2(final String str) {
		final List<String> res = new ArrayList<String>(10);
		int pos, prev = 0;
		while ((pos = str.indexOf("" + m_separatorChar, prev)) != -1) {
			res.add(str.substring(prev, pos));
			prev = pos + 1; // start from next char after separator
		}
		res.add(str.substring(prev));
		return res;
	}

}
