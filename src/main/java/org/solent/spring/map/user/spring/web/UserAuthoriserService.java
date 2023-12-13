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
import org.solent.spring.map.user.dao.impl.UserRepository;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;

import org.springframework.stereotype.Component;
/**
 *
 * @author Hamza
 */
@Component
public class UserAuthoriserService {

	final static Logger LOG = LogManager.getLogger(UserAuthoriserService.class);

	@Autowired
	UserRepository userRepository;

	public UserRole authorisedUserRole(HttpServletRequest httpRequest) {

		UserRole userRole = UserRole.ANONYMOUS;

		try {

			final String authorization = httpRequest.getHeader("Authorization");
			if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
				// Authorization: Basic base64credentials
				String base64Credentials = authorization.substring("Basic".length()).trim();
				byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
				String credentials = new String(credDecoded, StandardCharsets.UTF_8);
				// credentials = username:password
				final String[] values = credentials.split(":", 2);
				String username = values[0];
				String password = values[1];
				
				List<User> users = userRepository.findByUsername(username);
				if (users.isEmpty() || ! username.equals(users.get(0).getUsername())) {
					throw new RuntimeException("unknown authenticated username "+username);
				}
				if (! users.get(0).isValidPassword(password) ) throw new RuntimeException("incorrect password for username "+username) ;
				return users.get(0).getUserRole();
			}

		} catch (Exception e) {
			throw new RuntimeException("problem decoding credentials: ", e);
		}

		return userRole;
	}

}
