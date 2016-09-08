package com.xy2401.example;

import java.util.ArrayList;
import java.util.List;

public class TestGet {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestGet t = new TestGet();
		for(String s:t.getList()){
			System.out.println("s :"+s);
		}

	}
	
	public List<String> getList(){
		List<String> sString = new ArrayList<String>();
		
		sString.add("a");
		sString.add("b");
		sString.add("c");
		sString.add("d");
		sString.add("e");
		sString.add("f");
		
		System.out.println("sString size:"+sString.size());
		
		return sString ;
	}

}
