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

import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
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
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;
import static org.solent.spring.map.user.spring.web.MVCController.LOG;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class MapPointController {

    @Autowired
    MapPointRepository mapPointRepository;
 

    
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

@RequestMapping(value = "/add", method = RequestMethod.POST)
@Transactional
public String addMapPoint(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "description", required = false) String description,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "lat", required = true) double lat,
                          @RequestParam(value = "lng", required = true) double lng,
                          @RequestParam("image") MultipartFile image,
                          RedirectAttributes redirectAttributes) {
    try {
        // Validate and process the image file
        

        
        
        // Create a new MapPoint entity
        MapPoint newMapPoint = new MapPoint(name, description, category, lat, lng);
  
        
        if (!image.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            
                  
        if (!image.getContentType().startsWith("image/")) {
            redirectAttributes.addFlashAttribute("error", "Please upload a valid image file.");
            return "redirect:/home";
        }
        // Set the image data to the entity
            newMapPoint.setImage(image.getBytes());
        }
        

        

        // Save the new MapPoint to the repository
        mapPointRepository.save(newMapPoint);

        // Redirect to the home page with a success message
        redirectAttributes.addFlashAttribute("message", "Map Point added successfully.");
        return "redirect:/home";
    } catch (IOException e) {
        // Handle IO exception (e.g., reading image bytes)
        redirectAttributes.addFlashAttribute("error", "An error occurred while processing the image.");
        return "redirect:/home";
    }
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

@GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<MapPoint> mapPointOptional = mapPointRepository.findById(id);

        if (mapPointOptional.isPresent()) {
            MapPoint mapPoint = mapPointOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Change this based on your image format

            // Return the image data with the correct headers
            return new ResponseEntity<>(mapPoint.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // You can continue to add more methods for other MapPoints functionalities

    // ...
    
	@RequestMapping("/findMapPoint")
    
	public String findMapPoint(@RequestParam String name, Model model) {
		
	
		List<MapPoint> mapPoints = mapPointRepository.findByName(name);
                if (mapPoints.isEmpty()) {
                        model.addAttribute("errorMessage", "No map points found with the name: " + name);
                    } else {
                        model.addAttribute("mapPoints", mapPoints);
                    }		
                return "poiList";
	}
        
        
    @RequestMapping(value = {"/viewModifyPoi"}, method = RequestMethod.GET)
public String modifypoi(
    @RequestParam(value = "pointId", required = true) Long id,
    Model model,
    HttpSession session) {

    String errorMessage = "";

    model.addAttribute("selectedPage", "home");

    // Check secure access to modifyPoi profile
    User sessionUser = getSessionUser(session);
    model.addAttribute("sessionUser", sessionUser);

    if (UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
        errorMessage = "You must be logged in to access poi information.";
        model.addAttribute("errorMessage", errorMessage);
        return "home";
    }

    if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
        // If not an administrator, you can only access your own account info
        errorMessage = "Security: Non-admin viewModifyPoi called for user "
                + sessionUser.getUsername() + " which is not the logged-in user.";
        LOG.warn(errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "home";
    }

    // Correct repository method for finding a MapPoint by ID
    Optional<MapPoint> optionalMapPoint = mapPointRepository.findById(id);

    if (optionalMapPoint.isEmpty()) {
        errorMessage = "MapPoint with ID " + id + " not found.";
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage"; // Return an appropriate error page or redirect
    }

    MapPoint mapPoint = optionalMapPoint.get();
    model.addAttribute("mapPoint", mapPoint);

    return "viewModifyPoi";
}

@RequestMapping(value = {"/viewModifyPoi"}, method = RequestMethod.POST)
public String updatePoi(
        @RequestParam(value = "poiId", required = true) Long poiId,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "description", required = false) String description,
        @RequestParam(value = "category", required = false) String category,
        @RequestParam(value = "lat", required = false) String lat,
        @RequestParam(value = "lng", required = false) String lng,
        @RequestParam(value = "image", required = false) MultipartFile image,
        Model model,
        HttpSession session) {

    String message = "";
    String errorMessage = "";

    LOG.debug("post updatePoi called for poiId=" + poiId);

    // security check if the user is allowed to access or modify this MapPoint
    User sessionUser = getSessionUser(session);
    model.addAttribute("sessionUser", sessionUser);

    if (UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
        errorMessage = "You must be logged in to access Map Points information";
        model.addAttribute("errorMessage", errorMessage);
        return "home";
    }

    Optional<MapPoint> optionalMapPoint = mapPointRepository.findById(poiId);

    if (optionalMapPoint.isEmpty()) {
        errorMessage = "Update Map Point called for unknown poiId:" + poiId;
        LOG.warn(errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "home";
    }

    MapPoint modifyMapPoint = optionalMapPoint.get();

    // Update properties if provided
    if (name != null) {
        modifyMapPoint.setName(name);
    }
    if (description != null) {
        modifyMapPoint.setDescription(description);
    }
    if (category != null) {
        modifyMapPoint.setCategory(category);
    }
    if (lat != null) {
        modifyMapPoint.setLat(Double.parseDouble(lat));
    }
    if (lng != null) {
        modifyMapPoint.setLng(Double.parseDouble(lng));
    }

    // Update image if provided
    if (image != null && !image.isEmpty()) {
        try {
            modifyMapPoint.setImage(image.getBytes());
        } catch (IOException e) {
            errorMessage = "Error updating image for Map Point: " + e.getMessage();
            LOG.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "viewModifyPoi";
        }
    }

    modifyMapPoint = mapPointRepository.save(modifyMapPoint);

    model.addAttribute("modifyMapPoint", modifyMapPoint);
    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("message", "Map Point " + modifyMapPoint.getName() + " updated successfully");
    model.addAttribute("selectedPage", "home");

    return "viewModifyPoi";
}
@RequestMapping(value = "/deletePoint", method = RequestMethod.POST)
public String deletePoint(@RequestParam(value = "pointId", required = true) Long pointId,
                          Model model,
                          HttpSession session) {

    String message = "";
    String errorMessage = "";

    LOG.debug("post deletePoint called for pointId=" + pointId);

    // Security check if the user is allowed to delete this point
    User sessionUser = getSessionUser(session);
    model.addAttribute("sessionUser", sessionUser);

    if (UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
        errorMessage = "You must be logged in to delete a point.";
        model.addAttribute("errorMessage", errorMessage);
        return "home";
    }

    // Retrieve the MapPoint to be deleted
    Optional<MapPoint> optionalMapPoint = mapPointRepository.findById(pointId);

    if (optionalMapPoint.isEmpty()) {
        errorMessage = "MapPoint with ID " + pointId + " not found.";
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage"; // Return an appropriate error page or redirect
    }

    MapPoint mapPointToDelete = optionalMapPoint.get();

    // Additional security check (if needed), e.g., only administrators can delete
    if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
        // Add your specific security checks here
        errorMessage = "You do not have permission to delete a point.";
        model.addAttribute("errorMessage", errorMessage);
        return "home";
    }

    // Delete the MapPoint
    mapPointRepository.delete(mapPointToDelete);

    // Add success message
    message = "MapPoint with ID " + pointId + " has been deleted.";
    model.addAttribute("message", message);

    // Redirect to the map points list or another appropriate page
    return "redirect:/poiList";
}


}



