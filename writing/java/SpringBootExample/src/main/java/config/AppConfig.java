package config;
 
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
 
@SpringBootApplication 
public class AppConfig  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
    @Override  
    public void customize(ConfigurableEmbeddedServletContainer container) {  
        container.setPort(8081);  
    }  
}
