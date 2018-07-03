package com.util;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HttpHeaderFiller {
	private HttpHeaders httpHeaders;
    public HttpHeaders fillHeaders(){

		//Fill response headers in seperate method
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("pragma", "no-cache");
        responseHeaders.set("content-type", "application/json; charset=utf-8");
   	    responseHeaders.set("cache-control", "no-cache");
  	    responseHeaders.setContentLength(122);
	    responseHeaders.set("expires", "-1");
	    
    	return responseHeaders;
    }
}
