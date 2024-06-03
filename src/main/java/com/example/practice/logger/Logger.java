package com.example.practice.logger;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Logger {
    public void createNotFoundLog(){
        log.error("User with this id not found");
    }
}
