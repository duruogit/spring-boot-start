package com.yunnex.boot.framework.service.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class BootFeignFacadeImpl implements BootFeignFacade {
	
	@Value("${server.port}")
	private String port;

	@Override
	@HystrixCommand(fallbackMethod = "defaultMessage", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
	public String getMessage() {
		return "获取到正确信息!, 端口:"+port;
	}

	@Override
	public String defaultMessage() {
		return "服务降级，获取到默认信息!,端口:"+port;
	}

}
