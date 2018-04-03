package com.yunnex.boot.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * boot服务总体启动类，完成所有controller的启动
 * @author yuwenjun
 * @date 2017年12月1日 下午4:28:19
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.yunnex.boot.framework"})
@ImportResource(locations = "classpath*:/META-INF/spring/applicationContext-db.xml")

@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ApplicationStart  {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}
}
