package com.thinkenterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.RouteRepository;

@RestController
public class RouteController {
	
	 @Autowired
	 private RouteRepository repository;
	 
	
	 @RequestMapping(value = "routes", method = RequestMethod.GET)
	 public ResponseEntity<Iterable<Route>> findAll() {
	      return ResponseEntity.ok(repository.findAll());
	 }
	


}
