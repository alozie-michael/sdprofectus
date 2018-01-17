package com.remita.directdebit;

import com.remita.directdebit.dao.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({Credentials.class})
public class SDDirectDebitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SDDirectDebitApplication.class, args);
	}

	@Bean
	public Executor executor(){
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("directdebit-");
		return threadPoolTaskExecutor;
	}
}
