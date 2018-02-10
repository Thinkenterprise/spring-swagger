package com.thinkenterprise.domain.route;

public class SimpleRoute {
	
	    private long id;
	    private String name;

	    public SimpleRoute() {
	        super();
	    }

	    public SimpleRoute(final String name) {
	        super();

	        this.name = name;
	    }

	    public SimpleRoute(final long id, final String name) {
	        super();

	        this.id = id;
	        this.name = name;
	    }

	    // API

	    public long getId() {
	        return id;
	    }

	    public void setId(final long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(final String name) {
	        this.name = name;
	    }
	
}
