package org.solent.spring.map.user.model.dto.test;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.User;

public class UserTest {

    private User user;

    //Create standard object for each test
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setSecondName("Doe");
        user.setUsername("johndoe");
        user.setPassword("password123"); 
        user.setEnabled(true);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getSecondName());
        assertEquals("johndoe", user.getUsername());
        assertTrue(user.getEnabled());
    }

    @Test
    public void testPasswordHashing() {
        String password = "password123";
        user.setPassword(password);

        assertNotNull(user.getHashedPassword());
        assertNotEquals(password, user.getHashedPassword());
        assertTrue(user.isValidPassword(password));
        assertFalse(user.isValidPassword("wrongpassword"));
    }

    @Test
    public void testToString() {
        String expectedString = "User{id=1, firstName=John, secondName=Doe, username=johndoe, password=NOT SHOWN, address=null, userRole=null}";
        assertEquals(expectedString, user.toString());
    }

}
