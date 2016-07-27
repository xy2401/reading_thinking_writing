package com.xy2401.example.suggestions151;

import java.math.BigDecimal;

public class S_020 {

	public static void main(String[] args) {

	 
		/** 
		 * src:http://blog.iamzsx.me/show.html?id=153003
		 * 分析一下上面代码的问题（注释的内容表示此语句的输出）
		 * 第一行：事实上，由于二进制无法精确地表示十进制小数0.1，但是编译器读到字符串"0.1"
		 *  之后，必须把它转成8个字节的double值，因此，编译器只能用一个最接近的值来代替0.1了，
		 *  即0.1000000000000000055511151231257827021181583404541015625
		 *  因此，在运行时，传给BigDecimal构造函数的真正的数值是
		 *  0.1000000000000000055511151231257827021181583404541015625。
		 * 第二行：BigDecimal能够正确地把字符串转化成真正精确的浮点数。
		 * 第三行：问题在于Double.toString会使用一定的精度来四舍五入double，然后再输出。会。
		 *  Double.toString(0.1000000000000000055511151231257827021181583404541015625)
		 *  输出的事实上是"0.1"，因此生成的BigDecimal表示的数也是0.1。 
		 * 第四行：基于前面的分析，事实上这一行代码等价于第三行
		 * 
		 * 结论：
		 * 1.如果你希望BigDecimal能够精确地表示你希望的数值，那么一定要使用字符串来表示小数，并传递给BigDecimal的构造函数。
		 * 2.如果你使用Double.toString来把double转化字符串，然后调用BigDecimal(String)，这个也是不靠谱的，
		 * 它不一定按你的想法工作。
		 * 3.如果你不是很在乎是否完全精确地表示，并且使用了BigDecimal(double)，那么要注意double本身的特例
		 * ，double的规范本身定义了几个特殊的double值
		 * (Infinite，-Infinite，NaN)，不要把这些值传给BigDecimal，否则会抛出异常
		 * 
		 * ps: String valueOf(double d) {return Double.toString(d)}
		 * **/
	   System.out.println("BigDecimal Facts:");
	   System.out.println(new BigDecimal(0.1).toString()); // 0.1000000000000000055511151231257827021181583404541015625
	   System.out.println(new BigDecimal("0.1").toString()); // 0.1
	   System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625)).toString()); // 0.1
	   System.out.println(new BigDecimal(Double.toString(0.1)).toString()); // 0.1
		
	   System.out.println("BigDecimal equals");
	   BigDecimal bd_t1 = new BigDecimal(0.1);
	   BigDecimal bd_t2 = new BigDecimal("0.1");
	   BigDecimal bd_t3 = new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625));
	   BigDecimal bd_t4 = new BigDecimal(Double.toString(0.1));
	   BigDecimal bd_t5 = new BigDecimal(0.1000000000000000055511151231257827021181583404541015625);
	   
	   System.out.println(bd_t1+" equals "+bd_t2+" "+bd_t1.equals(bd_t2));
	   System.out.println(bd_t2+" equals "+bd_t2+" "+bd_t2.equals(bd_t2));
	   System.out.println(bd_t3+" equals "+bd_t2+" "+bd_t3.equals(bd_t2));
	   System.out.println(bd_t4+" equals "+bd_t2+" "+bd_t4.equals(bd_t2));
	   System.out.println(bd_t5+" equals "+bd_t2+" "+bd_t5.equals(bd_t2));
	   
	   System.out.println(bd_t1+" equals "+bd_t5+" "+bd_t1.equals(bd_t5));
	    
		
	   System.out.println("double Facts:");
	   System.out.println("0.05 + 0.01 = " + (0.05 + 0.01) );  
	   System.out.println("1.0 - 0.42 = " + (1.0 - 0.42) );  
	   System.out.println("4.015 * 100 = " + (4.015 * 100) );  
	   System.out.println("123.3 / 100 = " + (123.3 / 100) );  
	   
	   System.out.println("BigDecimal Arithmetic");
	   BigDecimal bd_0 = new BigDecimal("100");
	   BigDecimal bd_1 = new BigDecimal("0.05");
	   BigDecimal bd_2 = new BigDecimal("0.01");
	   BigDecimal bd_3 = new BigDecimal("1.0");
	   BigDecimal bd_4 = new BigDecimal("0.42");
	   BigDecimal bd_5 = new BigDecimal("4.015");
	   BigDecimal bd_6 = new BigDecimal("123.3");
	 
	   BigDecimal bd_result = null;
	   
	   System.out.println("bd_0:"+bd_0 + " && bd_1:"+bd_1); 
	   
	   bd_result = bd_0.add(bd_1);//相加不改变 a b 而是把新值作为新的对象返回
	   System.out.println("bd_0:"+bd_0 + " && bd_1:"+bd_1); // bd_0 bd_1 值未变
	   System.out.println("bd_0+bd_1="+bd_result);
	   
	   System.out.println(bd_1 + " + " + bd_2 + " = " + (bd_1.add(bd_2)) );
	   System.out.println(bd_3 + " - " + bd_4 + " = " + (bd_3.subtract(bd_4)) );
	   System.out.println(bd_5 + " * " + bd_0 + " = " + (bd_5.multiply(bd_0)) );
	   System.out.println(bd_6 + " / " + bd_0 + " = " + (bd_6.divide(bd_0)) );
	   
	   
	   System.out.println("(4.015 * 100.0)="+(4.015 * 100));
	   System.out.println("Math.round(4.015 * 100.0)="+Math.round(4.015 * 100));
	   System.out.println("Math.round(4.015 * 100.0)/100="+Math.round(4.015 * 100)/100.0);
	     
	}

}
