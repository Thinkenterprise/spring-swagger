package com.thinkenterprise.controller.mapper;

import org.springframework.stereotype.Component;

import com.thinkenterprise.domain.route.Route;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class RouteMapping implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Route.class, RouteDTO.class)
                .field("flightNumber", "flight")
                .byDefault()
                .register();
    }
}