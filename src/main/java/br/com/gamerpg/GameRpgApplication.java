package br.com.gamerpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
class GameRpgApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameRpgApplication.class, args);

    }

}
