package controller;
 
  
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller  
@RequestMapping("/concurrency")
public class ConcurrencyController  {

    @RequestMapping("/threadPool_1")
    @ResponseBody
    String threadPool_1() {
        return "Hello Concurrency World!!!";
    }

    @RequestMapping("/threadPool_2")
    @ResponseBody
    String threadPool_2() {
        return "Hello Concurrency World!!!";
    }
    
 
}
