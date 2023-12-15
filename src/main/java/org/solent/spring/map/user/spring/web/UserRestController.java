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

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;
import static org.solent.spring.map.user.spring.web.UserAndLoginController.LOG;
import org.solent.spring.map.user.spring.web.UserAuthoriserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class UserRestController {

	final static Logger LOG = LogManager.getLogger(UserRestController.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthoriserService userAuthorisedService;

	@Operation(summary = "Get a list of all users")

	@RequestMapping("/getUserList")
	public Iterable<User> listUsers(HttpServletRequest httpRequest) {
		
		if (UserRole.ADMINISTRATOR != userAuthorisedService.authorisedUserRole(httpRequest)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "unauthorised request");
		};
		

		return userRepository.findAll();
	}
	
	
	@Operation(summary = "Get particular user details")
	@RequestMapping("/getUser")
	public User findUser(@RequestParam String username, HttpServletRequest httpRequest) {
		
		if (UserRole.ADMINISTRATOR != userAuthorisedService.authorisedUserRole(httpRequest)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "unauthorised request");
		};
		

		List<User> users = userRepository.findByUsername(username);
		if (users.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unknown username "+username);
		
		return users.get(0);
		
	}
        
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    @Transactional
    @ResponseBody
    public User register(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "password2", required = false) String password2,
            HttpServletRequest httpRequest) {
        
        
		if (UserRole.ADMINISTRATOR != userAuthorisedService.authorisedUserRole(httpRequest)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "unauthorised request");
		};
        

       

            User modifyUser = new User();
            modifyUser.setUserRole(UserRole.CUSTOMER);
            modifyUser.setUsername(username);
            modifyUser.setFirstName(username);
            modifyUser.setPassword(password);
            modifyUser = userRepository.save(modifyUser);
            
            return modifyUser;
    }

// Senju's API-methods
@RequestMapping(value = "/getUserList/android", method = RequestMethod.GET)
@Transactional
@ResponseBody
public List<User> listUsers(Model model, HttpSession session) {
	return userRepository.findAll();
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

	List<User> userList = userRepository.findByUsername(username);

	if ("login".equals(action)) {
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


}
