package springcloud.helloworld.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired HelloWorldService helloWorldFeignService;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return helloWorldFeignService.sayHello();
    }
}
