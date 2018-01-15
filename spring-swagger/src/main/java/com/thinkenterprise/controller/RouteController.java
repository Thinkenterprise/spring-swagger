package com.thinkenterprise.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.controller.mapper.RouteDTO;
import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.RouteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ma.glasnost.orika.MapperFacade;

@RestController
@Api("routes")
public class RouteController {

	@Autowired
	private RouteRepository repository;

	@Autowired
	private MapperFacade orikaMapperFacade;

	@RequestMapping(value = "routes", method = RequestMethod.GET)
	@ApiOperation(value = "List all routes", response = Route.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Routes found"),
			@ApiResponse(code = 404, message = "Route not found") })
	public ResponseEntity<Iterable<Route>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@RequestMapping(value = "routes-dto", method = RequestMethod.GET)
	@ApiOperation(value = "List all routes", response = Route.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Routes found"),
			@ApiResponse(code = 404, message = "Route not found") })
	public ResponseEntity<Iterable<RouteDTO>> findAllAsDtos() {
		Collection<RouteDTO> responseEntity = new ArrayList<RouteDTO>();
		orikaMapperFacade.mapAsCollection(repository.findAll(), responseEntity, RouteDTO.class);
		return ResponseEntity.ok(responseEntity);
	}
}
