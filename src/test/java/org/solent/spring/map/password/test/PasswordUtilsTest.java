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
package org.solent.spring.map.password.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.solent.spring.map.password.PasswordUtils;

import static org.junit.Assert.*;

/**
 *
 * @author gallenc
 */
public class PasswordUtilsTest {

    final static Logger LOG = LogManager.getLogger(PasswordUtilsTest.class);

    @Test
    public void testPasswordUtils() {

        String TEST_PASSWORD = "1234567890";
        String WRONG_PASSWORD = "abcd";

        String hashed = PasswordUtils.hashPassword(TEST_PASSWORD);
        LOG.debug("password=" + TEST_PASSWORD + " hashedpw=" + hashed);

        // Check that an unencrypted password matches one that has
        // previously been hashed
        if (PasswordUtils.checkPassword(TEST_PASSWORD, hashed)) {
            LOG.debug("password " + TEST_PASSWORD + " matches");
        } else {
            LOG.debug("password " + TEST_PASSWORD + " does not match");
        }
        assertTrue(PasswordUtils.checkPassword(TEST_PASSWORD, hashed));

        if (PasswordUtils.checkPassword(WRONG_PASSWORD, hashed)) {
            LOG.debug("password " + TEST_PASSWORD + " matches");
        } else {
            LOG.debug("password " + TEST_PASSWORD + " does not match");
        }
        assertFalse(PasswordUtils.checkPassword(WRONG_PASSWORD, hashed));

    }

}
