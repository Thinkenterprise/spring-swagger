package com.thinkenterprise.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BinarySampleControllers {
	
	@RequestMapping(value = "/image-byte-array", method = RequestMethod.GET)
	public @ResponseBody byte[] getImageAsByteArray() throws IOException {
		InputStream in = new FileInputStream("src/main/resources/blob.jpg");
	     return IOUtils.toByteArray(in);
	}
	
	
	@RequestMapping(value = "/image-response-entity-byte-array", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
	    HttpHeaders headers = new HttpHeaders();
	    InputStream in = new FileInputStream("src/main/resources/blob.jpg");
	    byte[] media = IOUtils.toByteArray(in);
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}


}
