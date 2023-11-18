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

import java.util.Optional;

import org.solent.spring.map.model.MapPoint;
import org.solent.spring.map.repository.MapPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * Created by pingwin on 25.10.16.
 */
@RestController
public class MapPointRestController {
	
	
    @Autowired
    MapPointRepository mapPointRepository;

    @Operation(summary = "Get a list of map points")
    @RequestMapping("/get")
    public Iterable<MapPoint> list() {
        return mapPointRepository.findAll();
    }

    @Operation(summary = "Get a specific map point by id")
    @RequestMapping("/get/{id}")
    public MapPoint getById(@Parameter(description = "id if point to be retreived") @PathVariable(value = "id") long id){
        Optional<MapPoint> mpo = mapPointRepository.findById(id);
        return (mpo.isEmpty()) ? null : mpo.get();
    }


}
