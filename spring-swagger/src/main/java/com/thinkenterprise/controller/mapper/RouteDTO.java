/*
 * Copyright (C) 2016 Thinkenterprise
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.thinkenterprise.controller.mapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RouteDTO {
  
    private String flight;
   
    private String departure = "UNDEFINED";
    
    private String destination = "UNDEFINED";

    private Map<LocalDate, Double> prices = new HashMap<>();

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Map<LocalDate, Double> getPrices() {
		return prices;
	}

	public void setPrices(Map<LocalDate, Double> prices) {
		this.prices = prices;
	}
}
