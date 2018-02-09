package com.thinkenterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.RouteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("routes")
public class RouteController {

	@Autowired
	private RouteRepository repository;

	@Autowired
	RouteRepository routeRepository;
	
	@ApiOperation(value="Get all Routes", notes= "Read the list of routes from the repository", nickname="getAll", response=Route.class, responseContainer="List", code=200, tags={"Routes"}, produces="application/json")
	@ApiResponses(value = {@ApiResponse(code=204, message="empty list"), @ApiResponse(code=400, message="can't access routes ",response=Error.class)})
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Route>> getAll() {
		if(routeRepository.count()>0) return new ResponseEntity<Iterable<Route>>(routeRepository.findAll(),HttpStatus.OK);
		else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="Delete one route", notes="Delete one route from the repository", nickname="getAll", response=Void.class)
	@ApiResponses(value= {@ApiResponse(code=204, message="no content"), @ApiResponse(code=400, message="can't access routes ",response=Error.class) })
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(name="id", value="route identifier", required=true, type="Long") @PathVariable(value = "id") Long id) {
		routeRepository.delete(id);
	}
			
	@ApiOperation(value="Get one route", notes="Get one Route from the repository", nickname="get", response=Route.class, code=200, tags={"Routes"}, produces="application/json")
	@ApiResponses(value = {@ApiResponse(code=400, message="Cant read route from repository",response=Error.class)})
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Route> get(@ApiParam(name="id", value="route itentifier", required=true, type="Long")@PathVariable(value = "id") Long id) {
		if(routeRepository.exists(id)) return new ResponseEntity<Route>(routeRepository.findOne(id),HttpStatus.OK);
		else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);	 
	 }
	
	@ApiOperation(value="Update one route", notes="Update Route form repository", nickname="put", code=200, tags={"Routes"}, consumes="application/json")
	@ApiResponses(value={@ApiResponse(code=400, message="can't update routes ",response=Error.class)})
	@RequestMapping(method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@ApiParam(name="Route", value="new route entity", required=true )@Validated @RequestBody Route entity) {	
	    routeRepository.save(entity);
	}	
	
	@ApiOperation(value="Create one route", notes="Create Route in repository", nickname="post", code=203, response=Route.class, tags={"Routes"}, consumes="application/json")
	@ApiResponses(value={@ApiResponse(code=400, message="can't update routes ",response=Error.class)})
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Route> post(@ApiParam(name="Route", value="new route entity", required=true ) @Validated @RequestBody Route entity) {	
	    return new ResponseEntity<Route>(routeRepository. save(entity),HttpStatus.CREATED);
	}	
	
	@ApiOperation(value="Update one route", notes="Update Route form repository", nickname="put", code=203, response=Route.class, responseContainer="List", tags={"Routes"}, produces="application/json")
	@ApiResponses(value = {@ApiResponse(code=204, message="empty list"), @ApiResponse(code=400, message="can't access routes ",response=Error.class)})
	@RequestMapping(value="search",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Iterable<Route>> findByDeparture(@ApiParam(name="depature", value="search criteria",type="String") @RequestParam(value = "departure") String departure) {
		 if(routeRepository.findByDeparture(departure).iterator().hasNext()) return new ResponseEntity<Iterable<Route>>(routeRepository.findAll(),HttpStatus.OK);
		 else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
