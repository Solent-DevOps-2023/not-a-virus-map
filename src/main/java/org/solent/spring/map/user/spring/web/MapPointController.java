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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class MapPointController {

    @Autowired
    MapPointRepository mapPointRepository;

    @RequestMapping(value = {"/mappoints/get"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
        if (image.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            return "redirect:/home";
        }

        if (!image.getContentType().startsWith("image/")) {
            redirectAttributes.addFlashAttribute("error", "Please upload a valid image file.");
            return "redirect:/home";
        }

        // Create a new MapPoint entity
        MapPoint newMapPoint = new MapPoint(name, description, category, lat, lng);

        // Set the image data to the entity
        newMapPoint.setImage(image.getBytes());

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
            headers.setContentType(MediaType.IMAGE_JPEG); // Change this based on your image format

            // Return the image data with the correct headers
            return new ResponseEntity<>(mapPoint.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // You can continue to add more methods for other MapPoints functionalities

    // ...


}

  
@RequestMapping(value = "/login/android", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public String login(@RequestParam(value = "action", required = false) String action,
		@RequestParam(value = "username", required = false) String username,
		@RequestParam(value = "password", required = false) String password,
		@RequestParam(value = "password2", required = false) String password2,
		Model model,
		HttpSession session) {
	String message = "";
	String errorMessage = "";

	//LOG.debug("login for username=" + username);

	// get current session modifyUser 
	//User sessionUser = getSessionUser(session);
	//model.addAttribute("sessionUser", sessionUser);

	/*
	if (!UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
		errorMessage = "user " + sessionUser.getUsername() + " already logged in";
		LOG.warn(errorMessage);
		model.addAttribute("errorMessage", errorMessage);
		return "home";
	};

	if (username == null || username.trim().isEmpty()) {
		errorMessage = "you must enter a username";
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}
	*/

	List<User> userList = userRepository.findByUsername(username);

	if ("login".equals(action)) {
		//todo find and add modifyUser and test password
		LOG.debug("logging in user username=" + username);
		if (userList.isEmpty()) {
			errorMessage = "cannot find user for username :" + username;
			LOG.warn(errorMessage);
			model.addAttribute("errorMessage", errorMessage);
			return "Unknown username";
		}
		/*
		if (password == null) {
			errorMessage = "you must enter a password";
			LOG.warn(errorMessage);
			model.addAttribute("errorMessage", errorMessage);
			return "login";
		}
		*/
		User loginUser = userList.get(0);
		if (!loginUser.isValidPassword(password)) {
			model.addAttribute("errorMessage", "invalid username or password");
			return "Invalid username or password";
		}

		if (!loginUser.getEnabled()) {
			model.addAttribute("errorMessage", "user account "+username
					+ " is disabled in this system");
			return "User account is disabled";
		}

		message = "successfully logged in user:" + username;
		session.setAttribute("sessionUser", loginUser);

		model.addAttribute("sessionUser", loginUser);

		model.addAttribute("message", message);
		model.addAttribute("errorMessage", errorMessage);
		// used to set tab selected
		model.addAttribute("selectedPage", "home");
		return "successful";
	} else {
		model.addAttribute("errorMessage", "unknown action requested:" + action);
		LOG.error("login page unknown action requested:" + action);
		model.addAttribute("errorMessage", errorMessage);
		// used to set tab selected
		model.addAttribute("selectedPage", "home");
		return "error";
	}
}
