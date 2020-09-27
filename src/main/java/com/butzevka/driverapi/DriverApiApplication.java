package com.butzevka.driverapi;

import com.butzevka.driverapi.service.FilesStorageServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@SpringBootApplication
@EnableSwagger2
public class DriverApiApplication implements CommandLineRunner {

    @Resource
    FilesStorageServiceImpl filesStorageService;

    public static void main(String[] args) {
        SpringApplication.run(DriverApiApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        filesStorageService.deleteAll();
        filesStorageService.init();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
