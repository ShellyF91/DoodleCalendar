package ajbc.doodle.calendar;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import ajbc.doodle.calendar.daos.DaoException;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.services.UserService;
@EnableScheduling
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Application {
	public final static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) throws DaoException {
		SpringApplication.run(Application.class, args);
	}
}
