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

@RestController
@RequestMapping("routes")
public class RouteController {
	
	@Autowired
	RouteRepository routeRepository;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Route>> getAll() {
		if(routeRepository.count()>0) return new ResponseEntity<Iterable<Route>>(routeRepository.findAll(),HttpStatus.OK);
		else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Route> get(@PathVariable(value = "id") Long id) {
		if(routeRepository.exists(id)) return new ResponseEntity<Route>(routeRepository.findOne(id),HttpStatus.OK);
		else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);	 
	 }
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
   		routeRepository.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@Validated @RequestBody Route entity) {	
	    routeRepository.save(entity);
	}	
	
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Route> post(@Validated @RequestBody Route entity) {	
	    return new ResponseEntity<Route>(routeRepository. save(entity),HttpStatus.CREATED);
	}	
	
	 @RequestMapping(value="search",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Iterable<Route>> findByDeparture(@RequestParam(value = "departure") String departure) {
		 if(routeRepository.findByDeparture(departure).iterator().hasNext()) return new ResponseEntity<Iterable<Route>>(routeRepository.findAll(),HttpStatus.OK);
		 else return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	 }
	
	
}
