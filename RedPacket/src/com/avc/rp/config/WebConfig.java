package com.avc.rp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//定义Spring MVC扫描的包
@ComponentScan(value="com.*", includeFilters= {@Filter(type = FilterType.ANNOTATION, value = Controller.class)})
//启动Spring MVC配置
@EnableWebMvc
//@EnableAsync
public class WebConfig extends AsyncConfigurerSupport{
	
	/**
	 * 通过注解@Bean初始化视图解析器
	 * @return ViewResolver 视图解析器
	 * */
	@Bean(name="internalResourceViewResolver")
	public ViewResolver initViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * 初始化RequsetMapperingHandlerAdpater,并且加载Http的Json转换器
	 * @return RequestMappingHandlerAdpater对象
	 * */
	@Bean(name="requestMappingHandlerAdapter")
	public HandlerAdapter initRequsetMappingHandlerAdapter() {
		//创建RequestMappingHandlerAdapter适配器
		RequestMappingHandlerAdapter rmhd = new RequestMappingHandlerAdapter();

		//Http Json转换器
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		//MappingJackson2HttpMessageConverter接收Json类型消息的转换
		MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(mediaType);
		//加入转换器的支持累类型
		converter.setSupportedMediaTypes(mediaTypes);
		//往适配器加入Json转换器
		rmhd.getMessageConverters().add(converter);
		return rmhd;
	}
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(200);
		taskExecutor.initialize();
		return taskExecutor;
	}

}
