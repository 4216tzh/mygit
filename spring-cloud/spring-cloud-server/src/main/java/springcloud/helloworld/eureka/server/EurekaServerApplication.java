package springcloud.helloworld.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 注册中心，其他的spring-cloud服务只要在配置文件配置
    eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
 * @author tzh
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}