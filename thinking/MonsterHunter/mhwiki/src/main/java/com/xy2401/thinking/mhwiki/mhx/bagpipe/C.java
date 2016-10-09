package com.xy2401.thinking.mhwiki.mhx.bagpipe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xy on 2016/4/2
 * 读取文件转换
 */
public class C {


    Charset charset = Charset.forName("UTF-8");
    String melody_effects_path = "melody_effects.csv";//演奏旋律效果
    String melody_effects_translate_path = "melody_effects_translate.csv";//旋律效果翻译
    Map<String,String> translateMap = null;//翻译效果说明

    Boolean translateFlag = true;//是否翻译

    //白紫赤黄青空绿橙
    //c1c2c3c4c5c6c7c8
    String colors = "白紫赤黄青空绿橙";

    public static void main(String[] args) throws IOException, URISyntaxException {

        C c = new C();

        c.generate();
        //c.translateFlag=false;
       // c.generate();

    }

    public void transition() throws IOException, URISyntaxException {
        String _path = this.getClass().getResource("").getPath();
        System.out.println(_path);
        Path path = Paths.get(this.getClass().getResource("").toURI());
        System.out.println(path);
    }

    public void generate() throws IOException, URISyntaxException {

        Path _path = Paths.get(this.getClass().getResource("").toURI());//获取当前class所在路径

        Path effectsPath = _path.resolve(melody_effects_path);
        Path effectsTranslatePath = _path.resolve(melody_effects_translate_path);

        List<String> effectsLines = Files.readAllLines(effectsPath,charset);
        List<String> effectsTranslateLines = Files.readAllLines(effectsTranslatePath,charset);

        translateMap = new HashMap<String, String>();//翻译效果说明


        String separator =",";//间隔符号
        String[] strings = null;

        String htmlStr="";//最后生成的html


        for(String line:effectsTranslateLines){
            strings = line.split(separator);//
            if(strings.length>1){
                translateMap.put(strings[0],strings[1]);
            }
        }

        //青青赤,風圧無効,180(210),風圧完全無効
        String trClass = null,cStr1=null,cStr2=null;
        for(String line:effectsLines){
            strings = line.split(separator);//
            if(strings.length>1){//至少有一个逗号

                trClass = "";
                cStr1="";
                cStr2="";

                for(int i=0;i<strings[0].length();i++){
                    char c = strings[0].charAt(i);
                    int colorIndex = colors.indexOf(c) +1;
                    if(colorIndex>0){
                        trClass += " c"+ colorIndex+"t ";

                        cStr1 += "<span class='c"+colorIndex+"'>"+c+"</span>";
                        cStr2 += "<span class='c"+colorIndex+"'>"+"♪"+"</span>";
                    }
                }

                if(trClass.length()>0){
                    htmlStr += "<tr class='"+trClass+"'>";
                }else {
                    htmlStr += "<tr>";
                }


                htmlStr += "\n    <td> ";

                if(cStr1.length()>0){
                    htmlStr += cStr1 +"<br>" +cStr2;
                }else {
                    htmlStr += strings[0];
                }

                htmlStr += "</td>\n    <td>";
                htmlStr += translate(strings[1]);
                htmlStr += "</td>";
                if(strings.length>=3){
                    htmlStr += "<td>";
                    htmlStr += translate(strings[2]);
                    htmlStr += "</td>";
                }
                if(strings.length>=4){
                    htmlStr += "<td>";
                    htmlStr += translate(strings[3]);
                    htmlStr += "</td>";
                }
                htmlStr += "\n</tr> \n";

            }

        }

        System.out.println("生成table");
        System.out.println(htmlStr);

    }
    public String translate(String src){
        String result = null;

        if(translateFlag){
            result = translateMap.get(src.trim());
        }

        if( result == null){
            result = src;
        }
        return result;
    }
}
