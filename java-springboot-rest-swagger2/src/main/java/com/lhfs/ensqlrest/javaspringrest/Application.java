package com.lhfs.ensqlrest.javaspringrest;

import org.springframework.boot.SpringApplication;

public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication app = new SpringApplication(Application.class);
        //app.setShowBanner(false);
        app.run(args);
    }

}
