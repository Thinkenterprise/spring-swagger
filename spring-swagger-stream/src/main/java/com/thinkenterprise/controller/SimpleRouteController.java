package com.thinkenterprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkenterprise.domain.route.SimpleRoute;

@Controller
@RequestMapping("SimpleRoute")
public class SimpleRouteController {

	@RequestMapping(method=RequestMethod.GET, produces={"application/x-kryo"})
	@ResponseBody
	public SimpleRoute get() {
		return new SimpleRoute(1,"Foo Name");
	}
	
}
