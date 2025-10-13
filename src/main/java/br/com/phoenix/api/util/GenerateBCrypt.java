package br.com.phoenix.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBCrypt {

    public static void main(String[] args) {
        String rawPassword = "admin123";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Senha original: " + rawPassword);
        System.out.println("Hash gerado: " + hashedPassword);
    }

}
