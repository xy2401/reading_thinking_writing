package v2ch01;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.*;
import java.nio.file.Path;

/**
 * Created by xy on 2016/4/11.
 */
public class Base {
    public static void main(String[] args) throws IOException {

        //user.dir 	User's current working director//用户当前工作目录 这个解释才对啊
        System.out.println("user.dir:"+System.getProperty("user.dir"));
        //File separator ("/" on UNIX) "\" on windows
        System.out.println("file.separator:"+System.getProperty("file.separator"));
        //Path separator (":" on UNIX) 这个windows unix一样啊
        System.out.println("path.separator:"+System.getProperty("path.separator"));
        //Line separator ("\n" on UNIX) 这个怎么输出啊？
        System.out.println("line.separator:"+System.getProperty("line.separator")+":");
        //这个静态变量和属性输出是一样啊 Path line并没有静态变量
        System.out.println("File.separator:"+File.separator);
        System.out.println("Path.separator:"+ Path.class);

        InputStreamReader in = new InputStreamReader(System.in);

        PrintWriter out = new PrintWriter("employee.txt");
        PrintWriter out2 = new PrintWriter(new FileWriter("employee.txt"));

        out.checkError();

    }
}
