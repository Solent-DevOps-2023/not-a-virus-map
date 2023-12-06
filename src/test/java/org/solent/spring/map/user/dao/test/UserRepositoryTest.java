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
package org.solent.spring.map.user.dao.test;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.solent.spring.map.user.model.dto.User;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserRepositoryTest {

    private static final Logger LOG = LogManager.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testUser() {
        LOG.debug("****************** starting test");

        userRepository.deleteAll();

        User user1 = new User();
        user1.setUsername("cg101");
        user1.setFirstName("craig");
        user1.setSecondName("gallen");
        user1 = userRepository.save(user1);

        assertEquals(1, userRepository.count());

        Optional<User> optional = userRepository.findById(user1.getId());
        User foundUser = optional.get();

        LOG.debug("found user: " + foundUser);
        
        List<User> foundUsers1 = userRepository.findByUsername("cg101");
        LOG.debug("found user21: " + foundUsers1);
        
        List<User> foundUsers = userRepository.findByNames("craig", "gallen");
        LOG.debug("found user3: " + foundUsers);
        

        LOG.debug("****************** test complete");
    }

}
