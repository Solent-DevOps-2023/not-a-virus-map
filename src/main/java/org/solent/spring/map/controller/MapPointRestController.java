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
package org.solent.spring.map.controller;

import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.repository.MapPointRepository;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@RestController
@RequestMapping("/")
public class MapPointRestController {

    final static Logger LOG = LogManager.getLogger(MapPointRestController.class);

    @Autowired
    @Qualifier("serviceEntityManagerFactory")
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    private MapPointRepository mapPointRepository;


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

    @Operation(summary = "Get a list of map points")
    @RequestMapping("/get")
    public Iterable<MapPoint> list() {
        return mapPointRepository.findAll();
    }

    @Operation(summary = "Get a specific map point by id")
    @RequestMapping("/get/{id}")
    public MapPoint getById(@Parameter(description = "id if point to be retreived") @PathVariable(value = "id") long id) {
        Optional<MapPoint> mpo = mapPointRepository.findById(id);
        return mpo.orElse(null);
    }
}
