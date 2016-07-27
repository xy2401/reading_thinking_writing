package com.xy2401.example.commons;

public class CharacterEg {

	public static void main(String[] args) {
		
	      char c1=' '; //字符，可以是汉字，因为是Unicode编码
		  char c2=32;// 十进制数，八进制数，十六进制数等等; //可以用整数赋值
		  char c3='\u0020'; //用字符的编码值来初始化 四位16进制
		  
		  //API说：通常应该优先采用此方法，而不是构造方法 Character(char)
		  //因为该方法很可能通过缓存经常请求的值来显著提高空间和时间性能。 
		  Character charSpace1 = Character.valueOf(c1);
		  Character charSpace2 =  Character.valueOf(c2);
		  Character charSpace3 =  Character.valueOf(c3);
		  
		  System.out.println("charSpace1:"+charSpace1.charValue());
		  System.out.println("charSpace2:"+charSpace2.charValue());
		  System.out.println("(charSpace1==charSpace2):"+(charSpace1==charSpace2));
		  System.out.println("charSpace1.equals(charSpace2):"+charSpace1.equals(charSpace2));
		  System.out.println("charSpace2.equals(charSpace3):"+charSpace2.equals(charSpace3));
		  
		  System.out.println("Character.MIN_RADIX:"+Character.MIN_RADIX);  //int 2
		  System.out.println("Character.MAX_RADIX:"+Character.MAX_RADIX);  //int 32
 
		  System.out.println("Character.SPACE_SEPARATOR:"+Character.SPACE_SEPARATOR);  //byte 12
		  System.out.println("Character.LINE_SEPARATOR:"+Character.LINE_SEPARATOR);  //byte 13
		  System.out.println("Character.PARAGRAPH_SEPARATOR:"+Character.PARAGRAPH_SEPARATOR);  //byte 124

	}

}
