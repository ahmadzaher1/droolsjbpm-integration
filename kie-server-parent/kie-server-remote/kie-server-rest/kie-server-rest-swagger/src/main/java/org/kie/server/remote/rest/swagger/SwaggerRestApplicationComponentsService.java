/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.kie.server.remote.rest.swagger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.kie.server.services.api.KieServerApplicationComponentsService;
import org.kie.server.services.api.SupportedTransports;
import org.kie.server.services.swagger.SwaggerKieServerExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.jaxrs.listing.SwaggerSerializers;

public class SwaggerRestApplicationComponentsService implements KieServerApplicationComponentsService {

	private static final String OWNER_EXTENSION = SwaggerKieServerExtension.EXTENSION_NAME;

	@Override
	public Collection<Object> getAppComponents(String extension, SupportedTransports type, Object... services) {
		// skip calls from other than owning extension
		if (!OWNER_EXTENSION.equals(extension)) {
			return Collections.emptyList();
		}
				
		List<Object> components = new ArrayList<Object>(2);
		// Swagger Resources
		components.add(new KieApiListingResource());
		components.add(new SwaggerSerializers());

		return components;
	}

}
