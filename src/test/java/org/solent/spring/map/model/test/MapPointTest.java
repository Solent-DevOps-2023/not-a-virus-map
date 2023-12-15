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
package org.solent.spring.map.model.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.model.MapPoint;

public class MapPointTest {

    //Quick genreic test to ensure all getters and setters have expected behaviour
    @Test
    public void testMapPointConstructor() {
        MapPoint mapPoint = new MapPoint("TestName", "TestDescription", "TestCategory", 10.0, 20.0);

        assertEquals("TestName", mapPoint.getName());
        assertEquals("TestDescription", mapPoint.getDescription());
        assertEquals("TestCategory", mapPoint.getCategory());
        assertEquals(10.0, mapPoint.getLat());
        assertEquals(20.0, mapPoint.getLng());
    }

    //Tests max and min longitude values
    @Test
    public void testMapPointGetLon() {
	//Test min longitude
        MapPoint mapPoint = new MapPoint("TestName", "TestDescription", "TestCategory", 10.0, -180.0);
        assertEquals(-180.0, mapPoint.getLng());

	//Test max longitude
        mapPoint = new MapPoint("TestName", "TestDescription", "TestCategory", 10.0, 180.0);
        assertEquals(180.0, mapPoint.getLng());
    }

    @Test
    public void testMapPointGetLat() {
	//Test min latitude
        MapPoint mapPoint = new MapPoint("TestName", "TestDescription", "TestCategory", -90.0, 20.0);
        assertEquals(-90.0, mapPoint.getLat());

	//Test max latitude
        mapPoint = new MapPoint("TestName", "TestDescription", "TestCategory", 90.0, 20.0);
        assertEquals(90.0, mapPoint.getLat());
    }

    //Tests differant strings genreated from the class
    @Test
    public void testMapPointToString() {
        MapPoint mapPoint = new MapPoint("TestName", "TestDescription", "TestCategory", 10.0, 20.0);

        String expectedString = "Map point: {id=null, name='TestName', description='TestDescription', category='TestCategory', lat=10.0, lng=20.0}";
        assertEquals(expectedString, mapPoint.toString());

	//Ttest another string just in case
        mapPoint = new MapPoint("DifferantTestName", "DifferantTestDescription", "DifferantTestCategory", 100.0, -20.0);

        expectedString = "Map point: {id=null, name='DifferantTestName', description='DifferantTestDescription', category='DifferantTestCategory', lat=100.0, lng=-20.0}";
        assertEquals(expectedString, mapPoint.toString());

    }

}
