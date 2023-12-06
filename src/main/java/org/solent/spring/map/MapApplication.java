/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 
package org.solent.spring.map;

import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.repository.MapPointRepository;
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MapApplication extends SpringBootServletInitializer {

	@Bean
	CommandLineRunner runner(MapPointRepository mapPointRepository) {
		return args -> {

			MapPoint point1 = new MapPoint("Point 1", "Some point", "POI", 54, 17);
			MapPoint point2 = new MapPoint("Point 2", "Some point", "Nature", 55, 17);
			MapPoint point3 = new MapPoint("Point 3", "Some point", "Shop", 56, 17);
			MapPoint point4 = new MapPoint("Point 4", "Some point", "POI", 57, 17);
			mapPointRepository.save(point1);
			mapPointRepository.save(point2);
			mapPointRepository.save(point3);
			mapPointRepository.save(point4);
                        
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MapApplication.class, args);
	}
}
*/