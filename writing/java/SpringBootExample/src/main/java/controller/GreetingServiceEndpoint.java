package controller;


import javax.jws.WebMethod;
import javax.jws.WebService;
 
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
 
@WebService(serviceName = "GreetingService")
public class GreetingServiceEndpoint extends SpringBeanAutowiringSupport {

	 
	@WebMethod
	public String sayHello() {
		return "hell";
	}

}
 