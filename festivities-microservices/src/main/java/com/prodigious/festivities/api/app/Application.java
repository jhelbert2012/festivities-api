package com.prodigious.festivities.api.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.prodigious.festivities.api.repository.FestivityRepository;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.prodigious.festivities.api.repository")
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private FestivityRepository festivityRepository;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    public void run(String... strings) throws Exception {
    }

}
