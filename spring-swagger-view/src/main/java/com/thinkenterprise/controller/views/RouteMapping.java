package com.thinkenterprise.controller.views;

import org.springframework.stereotype.Component;

import com.thinkenterprise.domain.route.Route;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class RouteMapping extends ConfigurableMapper {
	
    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Route.class, RouteView.class)
        		// Map nulls on class level
        		.mapNulls(true)
        		// Map Route.flightNumber to Route.flight
                .field("flightNumber", "flight")
                // Do not map null value from Route.total to RouteDTO.total but vice versa
                .fieldMap("departure").mapNulls(false).mapNullsInReverse(true).add()
                .fieldMap("destination").mapNulls(false).mapNullsInReverse(true).add()
                // Map list of flights to a map with dateOfFlight and priceOfFlight
                .field("flights{date}", "prices{key}")
                .field("flights{price}", "prices{value}")
                // In the case where one or more fields in the two types being mapped have matching names, the byDefault method can be used, like so:
                .byDefault()
                // Always the last statement
                .register();
    }
}