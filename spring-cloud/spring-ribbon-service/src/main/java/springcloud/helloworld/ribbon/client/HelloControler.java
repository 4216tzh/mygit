package springcloud.helloworld.ribbon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
    @Autowired HelloService helloService;
    @RequestMapping(value = "/")
    public String hello(){
        return helloService.getHelloContent();
    }
}