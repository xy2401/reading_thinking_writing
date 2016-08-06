package config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

import service.AccountService;

@Configuration
public class WebServiceConfig {

//	@Bean 
//	public SimpleJaxWsServiceExporter getSimpleJaxWsServiceExporter() { 
//		SimpleJaxWsServiceExporter  exporter= new SimpleJaxWsServiceExporter();
//		exporter.setBaseAddress("http://localhost:8081/");
//		 
//		return exporter;
//	}
	
//	
//	<bean id="accountWebService" class="org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean">
//    <property name="serviceInterface" value="example.AccountService"/>
//    <property name="wsdlDocumentUrl" value="http://localhost:8888/AccountServiceEndpoint?WSDL"/>
//    <property name="namespaceUri" value="http://example/"/>
//    <property name="serviceName" value="AccountService"/>
//    <property name="portName" value="AccountServiceEndpointPort"/>
//    </bean>
//	
	
	//@Bean 
	public JaxWsPortProxyFactoryBean getJaxWsPortProxyFactoryBean()  {
		
		JaxWsPortProxyFactoryBean bean = new JaxWsPortProxyFactoryBean();
		
		bean.setServiceInterface(AccountService.class);
		try {
			bean.setWsdlDocumentUrl( new URL("http://localhost:8081/AccountServiceEndpoint?WSDL") );
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		bean.setNamespaceUri("http://example/");
		bean.setServiceName("AccountService");
		bean.setPortName("AccountServiceEndpointPort");
			 
		return bean;
	}
	
	
}
