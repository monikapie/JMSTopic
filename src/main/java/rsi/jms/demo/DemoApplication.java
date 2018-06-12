package rsi.jms.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private OrderSender orderSender;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Spring Publisher/Receiver test");

		for( int i = 0; i < 5; i++){
			Order order = new Order("ja", "ty", new BigDecimal(i), LocalDateTime.now());
			orderSender.sendTopic(order);
		}

		logger.info("Waiting for all ActiveMQ to be consumed.");
		TimeUnit.SECONDS.sleep(3);
		System.exit(-1);
	}
}
