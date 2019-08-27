/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rhte.camel.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */

@Component
public class CamelRouter extends RouteBuilder {
	

    @Override
    public void configure() throws Exception {
      restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .apiContextPath("api")
            .apiProperty("api.title", "Test Data")
            .apiProperty("api.version", "1.0.0");
    
        rest().description("Parking Meter Data")
	    .consumes("application/json")
            .get("/parking/meters").description("Get All Meters or Meters with a specific status")
            //.produces("text/plain")
            	//could have query param of status or not 
                //.responseMessage().code(200).message("OK").endResponseMessage()
                .route().routeId("parking-meters")
                .to("direct:parking-meters")
                .endRest()
            .get("/parking/meters/{meter-id}").produces("text/plain")
                .responseMessage().code(200).message("OK").endResponseMessage()
                .route().routeId("parking-meters-id")
                .to("direct:parking-meters-id")
                .endRest()
            .get("/traffic/junctions/{junction-id}").produces("text/plain")
                .responseMessage().code(200).message("OK").endResponseMessage()
                .route().routeId("traffic-junctions-id")
                .to("direct:traffic-junctions-id")
                .endRest()
            .get("/traffic/junctions").produces("text/plain")
                .responseMessage().code(200).message("OK").endResponseMessage()
                .route().routeId("traffic-junctions")
                .to("direct:traffic-junctions")
                .endRest();
        
       from("direct:parking-meters")
       		.log(LoggingLevel.INFO, "Parking Meters with status ${in.header.status}")
       		.to("sql:select * from meter_info;");
       
       from("direct:parking-meters-id")
       		.log(LoggingLevel.INFO, "Parking Meters ID ${header.meter-id}");
       
       from("direct:traffic-junctions-id")
       		.log(LoggingLevel.INFO, "Traffic junctions ID ${header.junction-id}");
       
       from("direct:traffic-junctions")
       		.log(LoggingLevel.INFO, "Traffic junctions");

    }

}
