package com.xy2401.example.apacheCommons;

import org.apache.commons.lang3.StringUtils;

public class Commons_lang {

	public static void main(String[] args) {
		
		 System.out.println("编辑距离："+StringUtils.getLevenshteinDistance("hello", "￥hello￥"));// = -1
		 
		 StringUtils.abbreviate("abcdefghijklmno", 4, 10);//  = "abcdefg..."
		
		Commons_lang c = new Commons_lang();

		c.base();
		c.StringUtilsEg();// String Utils
	}

	public void base() {

		String strNull = null;// null - null
		String strEmpty = "";// empty - a zero-length string ("")

		System.out.println("strNull:" + strNull);
		System.out.println("strEmpty:" + strEmpty);

		// space - the space character (' ', char 32)
		char c1 = ' '; // 字符，可以是汉字，因为是Unicode编码
		char c2 = 32;// 十进制数，八进制数，十六进制数等等; //可以用整数赋值
		char c3 = '\u0020'; // 用字符的编码值来初始 四位十六进制

		System.out.println("c1+c2+c3:" + c1 + c2 + c3);

		// whitespace - the characters defined by Character.isWhitespace(char)
		// It is a Unicode space character (SPACE_SEPARATOR, LINE_SEPARATOR, or
		// PARAGRAPH_SEPARATOR)
		// but is not also a non-breaking space ('\u00A0', '\u2007', '\u202F').
		System.out.println("HORIZONTAL TABULATION 水平制表符：" + '\u0009');
		// LINE FEED 换行

		System.out.println("VERTICAL TABULATION 纵向列表：" + '\u000B');
		System.out.println("FORM FEED 换：" + '\u000C');
		// CARRIAGE RETURN 回车

		System.out.println("FILE SEPARATOR 文件分隔符：" + '\u001C');
		System.out.println("GROUP SEPARATOR 组分隔符：" + '\u001D');
		System.out.println("RECORD SEPARATOR 记录分隔符：" + '\u001E');
		System.out.println("UNIT SEPARATOR 单位分隔符：" + '\u001F');

		// trim - the characters <= 32 as in String.trim()
		// String.trim源代码可以看到 val[st] <= ' ' 即前后的字符小于' '、32、 /u0020的被舍弃

	}

	/**
	 * Operations on String that are null safe. IsEmpty/IsBlank - checks if a
	 * String contains text Trim/Strip - removes leading and trailing whitespace
	 * Equals - compares two strings null-safe startsWith - check if a String
	 * starts with a prefix null-safe endsWith - check if a String ends with a
	 * suffix null-safe IndexOf/LastIndexOf/Contains - null-safe index-of checks
	 * IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut - index-of any
	 * of a set of Strings ContainsOnly/ContainsNone/ContainsAny - does String
	 * contains only/none/any of these characters Substring/Left/Right/Mid -
	 * null-safe substring extractions
	 * SubstringBefore/SubstringAfter/SubstringBetween - substring extraction
	 * relative to other strings Split/Join - splits a String into an array of
	 * substrings and vice versa Remove/Delete - removes part of a String
	 * Replace/Overlay - Searches a String and replaces one String with another
	 * Chomp/Chop - removes the last part of a String AppendIfMissing - appends
	 * a suffix to the end of the String if not present PrependIfMissing -
	 * prepends a prefix to the start of the String if not present
	 * LeftPad/RightPad/Center/Repeat - pads a String
	 * UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize - changes the case
	 * of a String CountMatches - counts the number of occurrences of one String
	 * in another IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable - checks the
	 * characters in a String DefaultString - protects against a null input
	 * String Reverse/ReverseDelimited - reverses a String Abbreviate -
	 * abbreviates a string using ellipsis Difference - compares Strings and
	 * reports on their differences LevenshteinDistance - the number of changes
	 * needed to change one String into another
	 * 
	 * The StringUtils class defines certain words related to String handling.
	 * 
	 * null - null empty - a zero-length string ("") space - the space character
	 * (' ', char 32) whitespace - the characters defined by
	 * Character.isWhitespace(char) trim - the characters <= 32 as in
	 * String.trim()
	 * **/
	public void StringUtilsEg(){
	  
	  System.out.println("IsEmpty/IsBlank - checks if a String contains text");
	  System.out.println("==isEmpty 是否为空(不包括全空白字符whitespace的字符串)==");
      //Checks if a CharSequence is empty ("") or null.
	  System.out.println("isEmpty(null):"+StringUtils.isEmpty(null));      // true
	  System.out.println("isEmpty(\"\"):"+StringUtils.isEmpty(""));        // true
	  System.out.println("isEmpty(\" \"):"+StringUtils.isEmpty(" "));       // false
	  System.out.println("isEmpty(\"bob\"):"+StringUtils.isEmpty("bob"));     // false
	  System.out.println("isEmpty(\"  bob  \"):"+StringUtils.isEmpty("  bob  ")); // false

	  //Checks if a CharSequence is not empty ("") and not null.
	  StringUtils.isNotEmpty(null);//      = false
	  StringUtils.isNotEmpty("");//        = false
	  StringUtils.isNotEmpty(" ");//       = true
	  StringUtils.isNotEmpty("bob");//     = true
	  StringUtils.isNotEmpty("  bob  ");// = true
	  
	  //Checks if any one of the CharSequences are empty ("") or null.
	  //  多个参数 isEmpty(p1)||isEmpty(p2)....||isEmpty(pn)
	  
	  StringUtils.isAnyEmpty(null);//             = true
	  StringUtils.isAnyEmpty(null, "foo");//      = true
	  StringUtils.isAnyEmpty("", "bar");//        = true
	  StringUtils.isAnyEmpty("bob", "");//        = true
	  StringUtils.isAnyEmpty("  bob  ", null);//  = true
	  StringUtils.isAnyEmpty(" ", "bar");//       = false
	  StringUtils.isAnyEmpty("foo", "bar");//     = false
	  //true 多参数有一个为 isEmpty则为true
	  System.out.println("isAnyEmpty(\"p1\", \"p2\", \"p3\",\"\",\"p5\")"+StringUtils.isAnyEmpty("p1", "p2", "p3","","p5"));
	  
	  //Checks if none of the CharSequences are empty ("") or null 
	  //多参数 !isEmpty(p1)&&!isEmpty(p1)....!isEmpty(pn)
	  StringUtils.isNoneEmpty(null);//             = false
	  StringUtils.isNoneEmpty(null, "foo");//      = false
	  StringUtils.isNoneEmpty("", "bar");//        = false
	  StringUtils.isNoneEmpty("bob", "");//        = false
	  StringUtils.isNoneEmpty("  bob  ", null);//  = false
	  StringUtils.isNoneEmpty(" ", "bar");//       = true
	  StringUtils.isNoneEmpty("foo", "bar");//     = true
	  
	  
	  System.out.println("==isBlank是否为空(包括全空白字符whitespace的字符串)=="); 
	  //Checks if a CharSequence is whitespace, empty ("") or null.
	  System.out.println("isBlank(null):"+StringUtils.isBlank(null));      // true
	  System.out.println("isBlank(\"\"):"+StringUtils.isBlank(""));        // true
	  System.out.println("isBlank(\" \"):"+StringUtils.isBlank(" 	"));       // true
	  System.out.println("isBlank(\"bob\"):"+StringUtils.isBlank("bob"));     // false
	  System.out.println("isBlank(\"  bob  \"):"+StringUtils.isBlank("  bob  ")); // false

	  //Checks if a CharSequence is not empty (""), not null and not whitespace only.
	  StringUtils.isNotBlank(null);//      = false
	  StringUtils.isNotBlank("");//        = false
	  StringUtils.isNotBlank(" ");//       = false
	  StringUtils.isNotBlank("bob");//     = true
	  StringUtils.isNotBlank("  bob  ");// = true
	  
	  //Checks if any one of the CharSequences are blank ("") or null and not whitespace only..
	  StringUtils.isAnyBlank(null);//             = true
	  StringUtils.isAnyBlank(null, "foo");//      = true
	  StringUtils.isAnyBlank(null, null);//       = true
	  StringUtils.isAnyBlank("", "bar");//        = true
	  StringUtils.isAnyBlank("bob", "");//        = true
	  StringUtils.isAnyBlank("  bob  ", null);//  = true
	  StringUtils.isAnyBlank(" ", "bar");//       = true
	  StringUtils.isAnyBlank("foo", "bar");//     = false
	  
	  //Checks if none of the CharSequences are blank ("") or null and whitespace only..
	  StringUtils.isNoneBlank(null);//             = false
	  StringUtils.isNoneBlank(null, "foo");//      = false
	  StringUtils.isNoneBlank(null, null);//       = false
	  StringUtils.isNoneBlank("", "bar");//        = false
	  StringUtils.isNoneBlank("bob", "");//        = false
	  StringUtils.isNoneBlank("  bob  ", null);//  = false
	  StringUtils.isNoneBlank(" ", "bar");//       = false
	  StringUtils.isNoneBlank("foo", "bar");//     = true
	  
	  //Checks if the CharSequence contains only Unicode digits. A decimal point is not a Unicode digit and returns false.
      //null will return false. An empty CharSequence (length()=0) will return false.
	  //Note that the method does not allow for a leading sign, either positive or negative. 
	  //Also, if a String passes the numeric test, it may still generate a NumberFormatException 
	  //when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range for int or long respectively.
      //检查每个字符是不是都是数字  + - . null empty 均不算  Character.isDigit(cs.charAt(i))
	  StringUtils.isNumeric(null);//   = false
      StringUtils.isNumeric("");//     = false
      StringUtils.isNumeric("  ");//   = false
      StringUtils.isNumeric("123");//  = true
      StringUtils.isNumeric("12 3");// = false
      StringUtils.isNumeric("ab2c");// = false
      StringUtils.isNumeric("12-3");// = false
      StringUtils.isNumeric("12.3");// = false
      StringUtils.isNumeric("-123");// = false
      StringUtils.isNumeric("+123");// = false
 
	  
      //Checks if the CharSequence contains only Unicode digits or space (' '). A decimal point is not a Unicode digit and returns false.
      //null will return false. An empty CharSequence (length()=0) will return true.
      //检查字符串是不是Empty或者''和数字构成 Character.isDigit(cs.charAt(i)) == false && cs.charAt(i) != ' '
      StringUtils.isNumericSpace(null);//   = false
      StringUtils.isNumericSpace("");//     = true
      StringUtils.isNumericSpace("  ");//   = true
      StringUtils.isNumericSpace("123");//  = true
      StringUtils.isNumericSpace("12 3");// = true
      StringUtils.isNumericSpace("ab2c");// = false
      StringUtils.isNumericSpace("12-3");// = false
      StringUtils.isNumericSpace("12.3");// = false
       
      //public static boolean isWhitespace(CharSequence cs)
      //Checks if the CharSequence contains only whitespace.
      //null will return false. An empty CharSequence (length()=0) will return true.
      //检查字符串是Empty或者只有whitespace构成 Character.isWhitespace(cs.charAt(i))
      StringUtils.isWhitespace(null);//   = false
      StringUtils.isWhitespace("");//     = true
      StringUtils.isWhitespace("  ");//   = true
      StringUtils.isWhitespace("abc");//  = false
      StringUtils.isWhitespace("ab2c");// = false
      StringUtils.isWhitespace("ab-c");// = false
       
      //Checks if the CharSequence contains only lowercase characters.
      //null will return false. An empty CharSequence (length()=0) will return false.
      //检查字符串是否只有小写字母构成 Character.isLowerCase(cs.charAt(i))
      StringUtils.isAllLowerCase(null);//   = false
      StringUtils.isAllLowerCase("");//     = false
      StringUtils.isAllLowerCase("  ");//   = false
      StringUtils.isAllLowerCase("abc");//  = true
      StringUtils.isAllLowerCase("abC");// = false
       
      //Checks if the CharSequence contains only uppercase characters.
      //null will return false. An empty String (length()=0) will return false.
      //检查字符串是否只由大写字母构成 Character.isUpperCase(cs.charAt(i))
      StringUtils.isAllUpperCase(null);//   = false
      StringUtils.isAllUpperCase("");//     = false
      StringUtils.isAllUpperCase("  ");//   = false
      StringUtils.isAllUpperCase("ABC");//  = true
      StringUtils.isAllUpperCase("aBC");// = false
       
      //Returns either the passed in String, or if the String is null, an empty String ("").
      //直接返回传入的String 如果传的是null就返回Empty "" = =||这方法 源码也好简单 return str == null ? EMPTY : str;
      StringUtils.defaultString(null);//  = ""
      StringUtils.defaultString("");//    = ""
      StringUtils.defaultString("bat");// = "bat"


      //public static String defaultString(String str, String defaultStr)
      //Returns either the passed in String, or if the String is null, the value of defaultStr.
      //直接返回传入的String 如果传入的是null 就返回默认值  源码同样简单的return str == null ? defaultStr : str;
      StringUtils.defaultString(null, "NULL");//  = "NULL"
      StringUtils.defaultString("", "NULL");//    = ""
      StringUtils.defaultString("bat", "NULL");// = "bat"
 
      //public static <T extends CharSequence> T defaultIfBlank(T str,T defaultStr)
      //Returns either the passed in CharSequence, or if the CharSequence is whitespace, empty ("") or null, the value of defaultStr.
      //返回字符串,如果是null bank则返回默认值   源码也挺简单的 return StringUtils.isBlank(str) ? defaultStr : str;
      StringUtils.defaultIfBlank(null, "NULL");//  = "NULL"
      StringUtils.defaultIfBlank("", "NULL");//    = "NULL"
      StringUtils.defaultIfBlank(" ", "NULL");//   = "NULL"
      StringUtils.defaultIfBlank("bat", "NULL");// = "bat"
      StringUtils.defaultIfBlank("", null);//      = null

      //public static <T extends CharSequence> T defaultIfEmpty(T str,T defaultStr)
      //Returns either the passed in CharSequence, or if the CharSequence is empty or null, the value of defaultStr.
      //入会字符串，如果是Empty 或者null 返回默认值  一行源代码系列吗 return StringUtils.isEmpty(str) ? defaultStr : str;
      StringUtils.defaultIfEmpty(null, "NULL");//  = "NULL"
      StringUtils.defaultIfEmpty("", "NULL");//    = "NULL"
      StringUtils.defaultIfEmpty(" ", "NULL");//   = " "
      StringUtils.defaultIfEmpty("bat", "NULL");// = "bat"
      StringUtils.defaultIfEmpty("", null);//      = null

      //public static String reverse(String str)
      //Reverses a String as per StringBuilder.reverse().A null String returns null.
      //反转字符串  return new StringBuilder(str).reverse().toString();
      StringUtils.reverse(null);//  = null
      StringUtils.reverse("");//    = ""
      StringUtils.reverse("bat");// = "tab"
 
      //public static String reverseDelimited(String str,char separatorChar)
      //Reverses a String that is delimited by a specific character.
      //The Strings between the delimiters are not reversed. Thus java.lang.String becomes String.lang.java (if the delimiter is '.').
      //根据分隔符反转字符串 分隔符 中间的字符不反转 ，java.lang.String 就变成了 String.lang.java(分隔符为'.') 
      StringUtils.reverseDelimited(null, ' ');//      = null
      StringUtils.reverseDelimited("", ' ');//        = ""
      StringUtils.reverseDelimited("a.b.c", 'x');// = "a.b.c"
      StringUtils.reverseDelimited("a.b.c", '.');// = "c.b.a"

      //public static String abbreviate(String str, int maxWidth)
      //Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into "Now is the time for..." Specifically:
      //If str is less than maxWidth characters long, return it.
      //Else abbreviate it to (substring(str, 0, max-3) + "...").
      //If maxWidth is less than 4, throw an IllegalArgumentException.
      //In no case will it return a String of length greater than maxWidth.
      //缩写字符串  就像 "Now is the time for all good men" 缩写 "Now is the time for..." 
      //如果 参数 maxWidth大于参数str.length ,则返回这个字符串
      //不然 就将返回maxWidth长度字符串，并且最后三个字符转换为.  (substring(str, 0, max-3) + "...")
      //所以参数maxWidth小于3就没有意义了，难道返回 ... .. .这样吗?所以抛出异常
      //if (maxWidth < 4) { throw new IllegalArgumentException("Minimum abbreviation width is 4");}
      StringUtils.abbreviate(null, 0);//      = null
      StringUtils.abbreviate("", 4);//        = ""
      StringUtils.abbreviate("abcdefg", 6);// = "abc..."
      StringUtils.abbreviate("abcdefg", 7);// = "abcdefg"
      StringUtils.abbreviate("abcdefg", 8);// = "abcdefg"
      StringUtils.abbreviate("abcdefg", 4);// = "a..."
      StringUtils.abbreviate("abcdefg", 3);// = IllegalArgumentException

      // public static String abbreviate(String str,int offset,int maxWidth)
      //Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into "...is the time for..."
      //Works like abbreviate(String, int), but allows you to specify a "left edge" offset. 
      //Note that this left edge is not necessarily going to be the leftmost character in the result, 
      //or the first character following the ellipses, but it will appear somewhere in the result.
      //In no case will it return a String of length greater than maxWidth.
      //把字符串str offset前面的字符变成...,然后maxwidth后面的字符舍弃，maxwidth前三个字符变成...
      //参数offset小于等于4的都会只省略后面的 文字,好奇怪，不应该是3吗 囧 if (offset <= 4) {return str.substring(0, maxWidth - 3) + abrevMarker;}
      //当offset>4 参数maxWidth要大于等于7 不然会抛出异常 不然还输出 ... ...吗~
      StringUtils.abbreviate(null, 1, 1);//                = null
      StringUtils.abbreviate("", 0, 4);//                  = ""
      StringUtils.abbreviate("abcdefghijklmno", -1, 10);// = "abcdefg..."
      StringUtils.abbreviate("abcdefghijklmno", 0, 10);//  = "abcdefg..."
      StringUtils.abbreviate("abcdefghijklmno", 1, 10);//  = "abcdefg..."
      StringUtils.abbreviate("abcdefghijklmno", 3, 10);//  = "abcdefg..."
      StringUtils.abbreviate("abcdefghijklmno", 4, 10);//  = "abcdefg..."
      StringUtils.abbreviate("abcdefghijklmno", 5, 10);//  = "...fghi..."
      StringUtils.abbreviate("abcdefghijklmno", 6, 10);//  = "...ghij..."
      StringUtils.abbreviate("abcdefghijklmno", 8, 10);//  = "...ijklmno"
      StringUtils.abbreviate("abcdefghijklmno", 10, 10);// = "...ijklmno"
      StringUtils.abbreviate("abcdefghijklmno", 12, 10);// = "...ijklmno"
      StringUtils.abbreviate("abcdefghij", 0, 3);//        = IllegalArgumentException
      StringUtils.abbreviate("abcdefghij", 5, 6);//        = IllegalArgumentException

      //public static String abbreviateMiddle(String str,String middle,int length)
      //Abbreviates a String to the length passed, replacing the middle characters with the supplied replacement String.
      //This abbreviation only occurs if the following criteria is met:
      //Neither the String for abbreviation nor the replacement String are null or empty
      //The length to truncate to is less than the length of the supplied String
      //The length to truncate to is greater than 0
      //The abbreviated String will have enough room for the length supplied replacement String and the first and last characters of the supplied String for abbreviation
      //Otherwise, the returned String will be the same as the supplied String for abbreviation.
      //将字符串中间的文字缩写 ,知道字符串长度为 length
      //缩写的字符串将有足够的空间供长度替换字符串和所提供的字符串的缩写的第一个和最后一个字符 否则，返回的字符串将是一样的提供的字符串的缩写。 
      StringUtils.abbreviateMiddle(null, null, 0);//      = null
      StringUtils.abbreviateMiddle("abc", null, 0);//      = "abc"
      StringUtils.abbreviateMiddle("abc", ".", 0);//      = "abc"
      StringUtils.abbreviateMiddle("abc", ".", 3);//      = "abc"
      StringUtils.abbreviateMiddle("abcdef", ".", 4);//     = "ab.f"

      // public static String difference(String str1, String str2)
      //Compares two Strings, and returns the portion where they differ. More precisely, 
      //return the remainder of the second String, starting from where it's different from the first. 
      //This means that the difference between "abc" and "ab" is the empty String and not "c".
      //For example, difference("i am a machine", "i am a robot") -> "robot".
      //返回str2相对str1的不同部分
      StringUtils.difference(null, null);// = null
      StringUtils.difference("", "");// = ""
      StringUtils.difference("", "abc");// = "abc"
      StringUtils.difference("abc", "");// = ""
      StringUtils.difference("abc", "abc");// = ""
      StringUtils.difference("abc", "ab");// = ""
      StringUtils.difference("ab", "abxyz");// = "xyz"
      StringUtils.difference("abcde", "abxyz");// = "xyz"
      StringUtils.difference("abcde", "xyz");// = "xyz"

      //public static int indexOfDifference(CharSequence cs1, CharSequence cs2)
      //Compares two CharSequences, and returns the index at which the CharSequences begin to differ.
      //For example, indexOfDifference("i am a machine", "i am a robot") -> 7
      //返回str2相对str1的不同部分的起始index
      StringUtils.indexOfDifference(null, null);// = -1
      StringUtils.indexOfDifference("", "");// = -1
      StringUtils.indexOfDifference("", "abc");// = 0
      StringUtils.indexOfDifference("abc", "");// = 0
      StringUtils.indexOfDifference("abc", "abc");// = -1
      StringUtils.indexOfDifference("ab", "abxyz");// = 2
      StringUtils.indexOfDifference("abcde", "abxyz");// = 2
      StringUtils.indexOfDifference("abcde", "xyz");// = 0

      //public static int indexOfDifference(CharSequence... css)
      //Compares all CharSequences in an array and returns the index at which the CharSequences begin to differ.
      //For example, indexOfDifference(new String[] {"i am a machine", "i am a robot"}) -> 7
      //对比全部字符串,返回第一个不同的部分的index
      StringUtils.indexOfDifference(null);// = -1
      StringUtils.indexOfDifference(new String[] {});// = -1
      StringUtils.indexOfDifference(new String[] {"abc"});// = -1
      StringUtils.indexOfDifference(new String[] {null, null});// = -1
      StringUtils.indexOfDifference(new String[] {"", ""});// = -1
      StringUtils.indexOfDifference(new String[] {"", null});// = 0
      StringUtils.indexOfDifference(new String[] {"abc", null, null});// = 0
      StringUtils.indexOfDifference(new String[] {null, null, "abc"});// = 0
      StringUtils.indexOfDifference(new String[] {"", "abc"});// = 0
      StringUtils.indexOfDifference(new String[] {"abc", ""});// = 0
      StringUtils.indexOfDifference(new String[] {"abc", "abc"});// = -1
      StringUtils.indexOfDifference(new String[] {"abc", "a"});// = 1
      StringUtils.indexOfDifference(new String[] {"ab", "abxyz"});// = 2
      StringUtils.indexOfDifference(new String[] {"abcde", "abxyz"});// = 2
      StringUtils.indexOfDifference(new String[] {"abcde", "xyz"});// = 0
      StringUtils.indexOfDifference(new String[] {"xyz", "abcde"});// = 0
      StringUtils.indexOfDifference(new String[] {"i am a machine", "i am a robot"});// = 7
       
      //public static String getCommonPrefix(String... strs)
      //Compares all Strings in an array and returns the initial sequence of characters that is common to all of them.
      //For example, getCommonPrefix(new String[] {"i am a machine", "i am a robot"}) -> "i am a "
      //getCommonPrefix 顾名思义 返回共同的前缀
      StringUtils.getCommonPrefix(null);// = ""
      StringUtils.getCommonPrefix(new String[] {});// = ""
      StringUtils.getCommonPrefix(new String[] {"abc"});// = "abc"
      StringUtils.getCommonPrefix(new String[] {null, null});// = ""
      StringUtils.getCommonPrefix(new String[] {"", ""});// = ""
      StringUtils.getCommonPrefix(new String[] {"", null});// = ""
      StringUtils.getCommonPrefix(new String[] {"abc", null, null});// = ""
      StringUtils.getCommonPrefix(new String[] {null, null, "abc"});// = ""
      StringUtils.getCommonPrefix(new String[] {"", "abc"});// = ""
      StringUtils.getCommonPrefix(new String[] {"abc", ""});// = ""
      StringUtils.getCommonPrefix(new String[] {"abc", "abc"});// = "abc"
      StringUtils.getCommonPrefix(new String[] {"abc", "a"});// = "a"
      StringUtils.getCommonPrefix(new String[] {"ab", "abxyz"});// = "ab"
      StringUtils.getCommonPrefix(new String[] {"abcde", "abxyz"});// = "ab"
      StringUtils.getCommonPrefix(new String[] {"abcde", "xyz"});// = ""
      StringUtils.getCommonPrefix(new String[] {"xyz", "abcde"});// = ""
      StringUtils.getCommonPrefix(new String[] {"i am a machine", "i am a robot"});// = "i am a "
       
      //public static int getLevenshteinDistance(CharSequence s,CharSequence t)
      //Find the Levenshtein distance between two Strings.
      //This is the number of changes needed to change one String into another, where each change is a single character modification 
      //(deletion, insertion or substitution).
      //The previous implementation of the Levenshtein distance algorithm was from http://www.merriampark.com/ld.htm
      //Chas Emerick has written an implementation in Java, 
      //which avoids an OutOfMemoryError which can occur when my Java implementation is used with very large strings.
      //This implementation of the Levenshtein distance algorithm is from http://www.merriampark.com/ldjava.htm
      //Levenshtein  distance(编辑距离) ,由t变为s 的操作步骤数目(删除 插入 或者替换) ,以前的算法会照成内存泄漏，所以我们换了一个更好的算法
      //StringUtils.getLevenshteinDistance(null, *);//             = IllegalArgumentException
      //StringUtils.getLevenshteinDistance(*, null);//             = IllegalArgumentException
      StringUtils.getLevenshteinDistance("","");//               = 0
      StringUtils.getLevenshteinDistance("","a");//              = 1
      StringUtils.getLevenshteinDistance("aaapppp", "");//       = 7
      StringUtils.getLevenshteinDistance("frog", "fog");//       = 1
      StringUtils.getLevenshteinDistance("fly", "ant");//        = 3
      StringUtils.getLevenshteinDistance("elephant", "hippo");// = 7
      StringUtils.getLevenshteinDistance("hippo", "elephant");// = 7
      StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz");// = 8
      StringUtils.getLevenshteinDistance("hello", "hallo");//    = 1
      
      //public static int getLevenshteinDistance(CharSequence s, CharSequence t,int threshold)
      //Find the Levenshtein distance between two Strings if it's less than or equal to a given threshold.
      //This is the number of changes needed to change one String into another, 
      //where each change is a single character modification (deletion, insertion or substitution).
      //This implementation follows from Algorithms on Strings, Trees and Sequences by Dan Gusfield and Chas Emerick's implementation 
      //of the Levenshtein distance algorithm from http://www.merriampark.com/ld.htm
      //返回编辑距离，如果 编辑距离大于 threshold就返回-1 性能考虑吗？
//      StringUtils.getLevenshteinDistance(null, *, *);//             = IllegalArgumentException
//      StringUtils.getLevenshteinDistance(*, null, *);//             = IllegalArgumentException
//      StringUtils.getLevenshteinDistance(*, *, -1);//               = IllegalArgumentException
      StringUtils.getLevenshteinDistance("","", 0);//               = 0
      StringUtils.getLevenshteinDistance("aaapppp", "", 8);//       = 7
      StringUtils.getLevenshteinDistance("aaapppp", "", 7);//       = 7
      StringUtils.getLevenshteinDistance("aaapppp", "", 6);//       = -1
      StringUtils.getLevenshteinDistance("elephant", "hippo", 7);// = 7  
      StringUtils.getLevenshteinDistance("elephant", "hippo", 6);// = -1
      StringUtils.getLevenshteinDistance("hippo", "elephant", 7);// = 7
      StringUtils.getLevenshteinDistance("hippo", "elephant", 6);// = -1

      
      //public static double getJaroWinklerDistance(CharSequence first, CharSequence second)
      //Find the Jaro Winkler Distance which indicates the similarity score between two Strings.
      //The Jaro measure is the weighted sum of percentage of matched characters from each file and transposed characters. 
      //Winkler increased this measure for matching initial characters.
      //This implementation is based on the Jaro Winkler similarity algorithm from http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance.
      //总而言之计算两个字符串的相似度算法 返回double
      StringUtils.getJaroWinklerDistance(null, null);//          = IllegalArgumentException
      StringUtils.getJaroWinklerDistance("","");//               = 0.0
      StringUtils.getJaroWinklerDistance("","a");//              = 0.0
      StringUtils.getJaroWinklerDistance("aaapppp", "");//       = 0.0
      StringUtils.getJaroWinklerDistance("frog", "fog");//       = 0.93
      StringUtils.getJaroWinklerDistance("fly", "ant");//        = 0.0
      StringUtils.getJaroWinklerDistance("elephant", "hippo");// = 0.44
      StringUtils.getJaroWinklerDistance("hippo", "elephant");// = 0.44
      StringUtils.getJaroWinklerDistance("hippo", "zzzzzzzz");// = 0.0
      StringUtils.getJaroWinklerDistance("hello", "hallo");//    = 0.88
      StringUtils.getJaroWinklerDistance("ABC Corporation", "ABC Corp");// = 0.91
      StringUtils.getJaroWinklerDistance("D N H Enterprises Inc", "D & H Enterprises, Inc.");// = 0.93
      StringUtils.getJaroWinklerDistance("My Gym Children's Fitness Center", "My Gym. Childrens Fitness");// = 0.94
      StringUtils.getJaroWinklerDistance("PENNSYLVANIA", "PENNCISYLVNIA");//    = 0.9

      //public static boolean startsWith(CharSequence str, CharSequence prefix)
      //Check if a CharSequence starts with a specified prefix.
      //nulls are handled without exceptions. Two null references are considered to be equal. The comparison is case sensitive.
      //检查prefix是否是str的前缀
      StringUtils.startsWith(null, null);//      = true
      StringUtils.startsWith(null, "abc");//     = false
      StringUtils.startsWith("abcdef", null);//  = false
      StringUtils.startsWith("abcdef", "abc");// = true
      StringUtils.startsWith("ABCDEF", "abc");// = false

      
      
  }
}
