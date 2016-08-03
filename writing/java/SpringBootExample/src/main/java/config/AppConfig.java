package config;
 
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
 
//并不能设置扫描根目录所有文件夹。除非放一个一个包下
//原来来可以通配符啊
//通配符形式  全路径  AspectJ語法 正则表达式语法
//configuration does not rely on that class. This can also happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)
//wqnmlgb,管不得不能用通配符,原来会和其他包冲突，一不小心就启动了不知道的什么东西
@SpringBootApplication(scanBasePackages="bean,config,controller,service,task,webservice") 
public class AppConfig  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
 
	public static final int port = 8081; 
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
    @Override  
    public void customize(ConfigurableEmbeddedServletContainer container) {  
        container.setPort(port);  
    }  
}
