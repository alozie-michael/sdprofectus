package com.solutionsdelivery;

import com.solutionsdelivery.OTP.dao.OtpCredentials;
import com.solutionsdelivery.RPG.dao.RpgCredentials;
import com.solutionsdelivery.directdebit.dao.DirectDebitCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({DirectDebitCredentials.class, OtpCredentials.class, RpgCredentials.class})
public class SolutionsDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionsDeliveryApplication.class, args);
	}

	@Bean
	public Executor executor(){
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("solutionsdelivery-");
		return threadPoolTaskExecutor;
	}
}
