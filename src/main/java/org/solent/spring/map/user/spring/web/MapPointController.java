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
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;
import static org.solent.spring.map.user.spring.web.MVCController.LOG;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
    
    @Autowired
    UserRepository userRepository;

    private User getSessionUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new User();
            sessionUser.setUsername("anonymous");
            sessionUser.setUserRole(UserRole.ANONYMOUS);
            session.setAttribute("sessionUser", sessionUser);
        }
        return sessionUser;
    }

    
    // Add more methods for handling MapPoints functionality as needed

@RequestMapping(value = {"/add"}, method = RequestMethod.POST)
@Transactional
public String addMapPoint(@RequestParam(value = "name", required = true) String name,
                                      @RequestParam(value = "description", required = false) String description,
                                      @RequestParam(value = "category", required = false) String category,
                                      @RequestParam(value = "lat", required = true) double lat,
                                      @RequestParam(value = "lng", required = true) double lng) {
        // Your logic to add a new MapPoint to the repository
        MapPoint newMapPoint = new MapPoint(name, description, category, lat, lng);
        mapPointRepository.save(newMapPoint);


    // Redirect to the home page
    return "redirect:/home";
}

@RequestMapping(value = {"/poiList"}, method = RequestMethod.GET)
@Transactional
public String poiList(Model model,
            HttpSession session) {
        String message = "";
        String errorMessage = "";

        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            return "home";
        }
        
        LOG.debug("/pointList called");

        List<MapPoint> mapPoints = mapPointRepository.findAll();

        model.addAttribute("mapPoints", mapPoints);

        return "poiList";
}

    // You can continue to add more methods for other MapPoints functionalities

    // ...


}

  
