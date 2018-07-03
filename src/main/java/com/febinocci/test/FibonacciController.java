package com.febinocci.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {
	
	@RequestMapping("/api/FibonacciHelthCheck")	
	public String healthCheck() {
		return "OK Febinocci Initiated";
	}
	
	@RequestMapping("/api/Fibonacci")
	public ResponseEntity<Integer> getFebinocciByIndex(@RequestParam(value="n", required=true, defaultValue="0")int index){

		//Should be written as a seperate method
		int initValue = 0 ;
		int initValue2 = 1;
		for(int i=2; i<=index;i++){
			int temp= initValue;
			initValue= initValue+initValue2;
			initValue2= temp;
		}
		
		//Fill response headers in seperate method
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("pragma", "no-cache");
        responseHeaders.set("content-type", "application/json; charset=utf-8");
   	    responseHeaders.set("cache-control", "no-cache");
  	    responseHeaders.setContentLength(122);
	    responseHeaders.set("expires", "-1");
		return new ResponseEntity<Integer>(initValue,responseHeaders, HttpStatus.OK);
	}
}	