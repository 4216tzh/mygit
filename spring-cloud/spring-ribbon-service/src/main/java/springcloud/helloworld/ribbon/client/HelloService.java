package springcloud.helloworld.ribbon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
    @Autowired RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "serviceFailure")
    public String getHelloContent() {
    	System.out.println("call SERVICE-HELLOWORLD");
        return restTemplate.getForObject("http://SERVICE-HELLOWORLD/",String.class);
    }

    public String serviceFailure() {
        return "hello world service is not available !";
    }
}