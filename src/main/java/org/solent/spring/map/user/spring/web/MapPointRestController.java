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

package org.solent.spring.map.user.spring.web;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.repository.MapPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.solent.spring.map.user.model.dto.User;
import org.solent.spring.map.user.model.dto.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class MapPointRestController {

    final static Logger LOG = LogManager.getLogger(MapPointRestController.class);

@Autowired
private MapPointRepository mapPointRepository;


    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    @Transactional
    @ResponseBody
    public List<MapPoint> listMapPoints(Model model, HttpSession session) {
        return mapPointRepository.findAll();
    }
    

@RequestMapping(value = "/addMapPoint", method = {RequestMethod.POST})
@Transactional
@ResponseBody
public MapPoint addMapPoint(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "description", required = false) String description,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "lat", required = true) double lat,
                          @RequestParam(value = "lng", required = true) double lng
                         ) {
   
        
        
        // Create a new MapPoint entity
        MapPoint newMapPoint = new MapPoint(name, description, category, lat, lng);
  
        

        // Save the new MapPoint to the repository
        mapPointRepository.save(newMapPoint);
        return newMapPoint;

       
}
    @Operation(summary = "Get particular map point details")
	@RequestMapping("/findMapPointByName")
    
	public List<MapPoint> findMapPoint(@RequestParam String name) {
		
	
		List<MapPoint> mapPoints = mapPointRepository.findByName(name);
                
                return mapPoints;
	}

// Senju's API-methods
@RequestMapping(value = "/deletePoint/android", method = RequestMethod.POST)
public String deletePoint(@RequestParam(value = "pointId", required = true) Long pointId,
		  Model model,
		  HttpSession session) {

// Retrieve the MapPoint to be deleted
Optional<MapPoint> optionalMapPoint = mapPointRepository.findById(pointId);

MapPoint mapPointToDelete = optionalMapPoint.get();

// Delete the MapPoint
mapPointRepository.delete(mapPointToDelete);

// Redirect to the map points list or another appropriate page
return "redirect:/poiList";
}
}
