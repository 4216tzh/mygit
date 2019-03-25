package springcloud.helloworld.feign.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HelloWorldServiceFailure implements HelloWorldService {
	@Override
	public String sayHello() {
		System.out.println("hello world service is not available !");
		return "hello world service is not available !";
	}
}
