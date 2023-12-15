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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.solent.spring.map.user.model.dto.Address;
import org.solent.spring.map.user.model.dto.Address;

public class AddressTest {

    @Test
    public void testAddressGetterAndSetters() {
        Address address = new Address();

        // Set values using setters
        address.setHouseNumber("123");
        address.setAddressLine1("Main Street");
        address.setAddressLine2("Apt 456");
        address.setCounty("County");
        address.setCity("City");
        address.setPostcode("12345");
        address.setMobile("1234567890");
        address.setTelephone("0987654321");
        address.setCountry("Country");

        // Test getters to check if values are set correctly
        assertEquals("123", address.getHouseNumber());
        assertEquals("Main Street", address.getAddressLine1());
        assertEquals("Apt 456", address.getAddressLine2());
        assertEquals("County", address.getCounty());
        assertEquals("City", address.getCity());
        assertEquals("12345", address.getPostcode());
        assertEquals("1234567890", address.getMobile());
        assertEquals("0987654321", address.getTelephone());
        assertEquals("Country", address.getCountry());
    }

    @Test
    public void testAddressToString() {
        Address address = new Address();
        address.setHouseNumber("123");
        address.setAddressLine1("Main Street");
        address.setAddressLine2("Apt 456");
        address.setCounty("County");
        address.setCity("City");
        address.setPostcode("12345");
        address.setMobile("1234567890");
        address.setTelephone("0987654321");
        address.setCountry("Country");

        // Test the toString method
        assertEquals("Address{houseNumber=123, addressLine1=Main Street, addressLine2=Apt 456, county=County, city=City, postcode=12345, mobile=1234567890, telephone=0987654321, country=Country}", address.toString());
    }
}
