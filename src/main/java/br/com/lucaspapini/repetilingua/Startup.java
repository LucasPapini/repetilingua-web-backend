package br.com.lucaspapini.repetilingua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Startup implements CommandLineRunner {
@Autowired
private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("ENCODE = " + passwordEncoder.encode("123456"));
		boolean result = passwordEncoder.matches("123456", "$2a$10$w9y9yAyQ1j9TBKE19RpVZ.6V3k7h.cYl5xHibIe0ZL57e1tUZW.A6");
		System.out.println("MATCH = " + result);
	}
}
