package util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JLogger {
	Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

	@Test
	public void print() {
		
		System.out.println("Hello World");
		logger.info("Hello World");
		logger.warn("Hello warning");
	}
	
}
