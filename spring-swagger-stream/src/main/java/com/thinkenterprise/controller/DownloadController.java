package com.thinkenterprise.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@Api("download")
public class DownloadController {

	@ApiOperation(value = "Download BLOB")
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "File found", responseHeaders = @ResponseHeader(name = "Content-disposition", description = "attachment;filename=filename", response=String.class)),
			@ApiResponse(code = 404, message = "File not found") })
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response) {

		// Set the content type and attachment header.
		response.addHeader("Content-disposition", "attachment;filename=blob.jpg");
		response.setContentType("application/octet-stream");

		FileInputStream reader = null;
		OutputStream writer = null;
		byte[] buffer = new byte[8 * 1024];
		try {
			int bytesRead;
			reader = new FileInputStream("src/main/resources/blob.jpg");
			writer = response.getOutputStream();
			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer);
				response.flushBuffer();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
