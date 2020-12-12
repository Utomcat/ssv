package com.ranyk.ssv.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ranyi
 */
@SpringBootApplication(scanBasePackages = {"com.ranyk.ssv"})
public class BackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackupApplication.class, args);
	}

}
