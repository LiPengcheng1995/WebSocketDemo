package com.myWebApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Author: lpc
 * @Description:
 * @Date: create in 14:30 2017/10/13
 * @Modified By:
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.myWebApp.web")
@EnableWebSocket
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {


    @Autowired
    LogWebSocketHandler handler;
    @Autowired
    HandlerShakerInceptor handlerShakerInceptor;

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
//
//    @Bean
//    public LogWebSocketHandler getHandler(){
//        return new LogWebSocketHandler();
//    }
//    @Bean
//    public HandlerShakerInceptor getHandlerShakerInceptor(){
//        return new HandlerShakerInceptor();
//    }


    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(handler,"/aWebSocket")//添加一个处理器还有定义处理器的处理路径
                .addInterceptors(handlerShakerInceptor)//添加一个过滤器
                .setAllowedOrigins("*");//允许跨域

    }
}
