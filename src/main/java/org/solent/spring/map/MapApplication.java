package org.solent.spring.map;

import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.model.User;
import org.solent.spring.map.repository.MapPointRepository;
import org.solent.spring.map.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MapApplication extends SpringBootServletInitializer {

	@Bean
	CommandLineRunner runner(MapPointRepository mapPointRepository, UserRepository userRepository) {
		return args -> {

			MapPoint point1 = new MapPoint("Point 1", "Some point", "POI", 54, 17);
			MapPoint point2 = new MapPoint("Point 2", "Some point", "Nature", 55, 17);
			MapPoint point3 = new MapPoint("Point 3", "Some point", "Shop", 56, 17);
			MapPoint point4 = new MapPoint("Point 4", "Some point", "POI", 57, 17);
			mapPointRepository.save(point1);
			mapPointRepository.save(point2);
			mapPointRepository.save(point3);
			mapPointRepository.save(point4);
                        
                        User user1 = new User("Dario", "Admin", "admin");
                        User user2 = new User("Igor", "User", "user");

                        userRepository.save(user1);
                        userRepository.save(user2);


		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MapApplication.class, args);
	}
}
