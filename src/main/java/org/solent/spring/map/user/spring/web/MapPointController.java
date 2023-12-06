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
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.spring.map.user.spring.web;

/**
 *
 * @author Dário
 */

import org.solent.spring.map.repository.MapPointRepository;
import org.solent.spring.map.model.MapPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/")
public class MapPointController {

    @Autowired
    MapPointRepository mapPointRepository;

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @ResponseBody
    public List<MapPoint> listMapPoints(Model model, HttpSession session) {
        return mapPointRepository.findAll();
    }

    // Add more methods for handling MapPoints functionality as needed

    // Example:
    /*
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    @Transactional
    public String addMapPoint(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "latitude", required = true) String latitude,
                              @RequestParam(value = "longitude", required = true) String longitude,
                              Model model, HttpSession session) {
        // Your logic to add a new MapPoint to the repository
        MapPoint newMapPoint = new MapPoint();
        newMapPoint.setName(name);
        newMapPoint.setLatitude(latitude);
        newMapPoint.setLongitude(longitude);
        mapPointsRepository.save(newMapPoint);

        // Redirect to the list endpoint or show a success message
        return "redirect:/mappoints/list";
    }
    */

    // You can continue to add more methods for other MapPoints functionalities

    // ...

}
