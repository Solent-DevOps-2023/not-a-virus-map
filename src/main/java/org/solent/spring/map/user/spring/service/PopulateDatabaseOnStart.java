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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.spring.map.user.spring.service;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.repository.MapPointRepository;
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class PopulateDatabaseOnStart {

    private static final Logger LOG = LogManager.getLogger(PopulateDatabaseOnStart.class);

    private static final String DEFAULT_ADMIN_USERNAME = "globaladmin";
    private static final String DEFAULT_ADMIN_PASSWORD = "globaladmin";

    private static final String DEFAULT_USER_PASSWORD = "user1234";
    private static final String DEFAULT_USER_USERNAME = "user1234";
    
    
    

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MapPointRepository mapPointRepository;

    @PostConstruct
    public void initDatabase() {
        LOG.debug("initialising database with startup values");

        // initialising admin and normal user if dont exist
        User adminUser = new User();
        adminUser.setUsername(DEFAULT_ADMIN_USERNAME);
        adminUser.setFirstName("default administrator");
        adminUser.setPassword(DEFAULT_ADMIN_PASSWORD);
        adminUser.setUserRole(UserRole.ADMINISTRATOR);

        List<User> users = userRepository.findByUsername(DEFAULT_ADMIN_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(adminUser);
            LOG.info("creating new default admin user:" + adminUser);
        } else {
            LOG.info("default admin user already exists. Not creating new :" + adminUser);
        }

        User defaultUser = new User();
        defaultUser.setUsername(DEFAULT_USER_USERNAME);
        defaultUser.setFirstName("default user");
        defaultUser.setPassword(DEFAULT_USER_PASSWORD);
        defaultUser.setUserRole(UserRole.CUSTOMER);

        users = userRepository.findByUsername(DEFAULT_USER_USERNAME);
        if (users.isEmpty()) {
            userRepository.save(defaultUser);
            LOG.info("creating new default user:" + defaultUser);
        } else {
            LOG.info("defaultuser already exists. Not creating new :" + defaultUser);
        }
        // Creating a MapPoint
        MapPoint point1 = new MapPoint("Point 1", "Some point", "POI", 54, 17);
        MapPoint point2 = new MapPoint("Point 2", "Some point", "Nature", 55, 17);
        MapPoint point3 = new MapPoint("Point 3", "Some point", "Shop", 56, 17);
        MapPoint point4 = new MapPoint("Point 4", "Some point", "POI", 57, 17);
        MapPoint point5 = new MapPoint("Point 5", "Some point", "POI", 50.919047, -1.403267);

        mapPointRepository.save(point1);
        mapPointRepository.save(point2);
        mapPointRepository.save(point3);
        mapPointRepository.save(point4);
        mapPointRepository.save(point5);
        LOG.info("creating new map points:" + point1 + point2 + point3 + point4 + point5);

        LOG.debug("database initialised");
    }
}
