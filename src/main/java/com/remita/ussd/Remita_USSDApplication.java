package com.remita.ussd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class Remita_USSDApplication {

	public static void main(String[] args) {
		SpringApplication.run(Remita_USSDApplication.class, args);
		
	}

	@Bean
	public Executor executor(){
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("ussd-");
		return threadPoolTaskExecutor;
	}
}
