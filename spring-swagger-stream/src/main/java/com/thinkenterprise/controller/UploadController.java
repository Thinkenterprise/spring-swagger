package com.thinkenterprise.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@RestController
@Api("upload")
public class UploadController {

	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
		
		// Get name of uploaded file.
		String fileName = uploadedFileRef.getOriginalFilename();
		long filesize = uploadedFileRef.getSize();

		// Path where the uploaded file will be stored.
		String path = "C:/temp/" + fileName;

		// This buffer will store the data read from 'uploadedFileRef'
		byte[] buffer = new byte[8*1024];

		// Now create the output file on the server.
		File outputFile = new File(path);

		FileInputStream reader = null;
		FileOutputStream writer = null;
		
		try {
		    outputFile.createNewFile();

		    // Create the input stream to uploaded file to read data from it.
		    reader = (FileInputStream) uploadedFileRef.getInputStream();

		    // Create writer for 'outputFile' to write data read from
		    // 'uploadedFileRef'
		    writer = new FileOutputStream(outputFile);

		    // Iteratively read data from 'uploadedFileRef' and write to
		    // 'outputFile';            
		    int bytesRead = 0;
		    while ((bytesRead = reader.read(buffer)) != -1) {
		        writer.write(buffer);
		    }
		    
		    return ResponseEntity.ok("File saved to "+path+" ["+filesize+" bytes]");
		    
		} catch (Exception e) {
		    e.printStackTrace();
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: "+e.getMessage());
		
		} finally{
		    try {
		        reader.close();
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
}
