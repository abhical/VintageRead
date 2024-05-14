package com.example.ElectronicStore.config;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration

public class UniqueIdGenerator {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 10;
    
    @Bean
    public static String generateUniqueId() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < ID_LENGTH) { // While loop to ensure the ID has exactly 10 characters
            int index = (int) (random.nextFloat() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return builder.toString();
    }
}
