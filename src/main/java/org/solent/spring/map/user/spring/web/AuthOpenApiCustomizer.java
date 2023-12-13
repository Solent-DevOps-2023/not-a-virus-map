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

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hamza
 */
@Component
public class AuthOpenApiCustomizer implements OpenApiCustomiser {
	@Override
	public void customise(OpenAPI openApi) {
		String securitySchemeName = "basicAuth";
		openApi.getComponents().addSecuritySchemes(securitySchemeName,
				new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"));
		openApi.addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
	}
}
