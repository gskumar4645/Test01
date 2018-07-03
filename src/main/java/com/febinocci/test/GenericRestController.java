package com.febinocci.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CustomExceptions;
import com.to.IntAryVo;
import com.to.ReverseStringResponseVO;
import com.util.HttpHeaderFiller;

@RestController
public class GenericRestController {
	
	@Autowired
	private HttpHeaderFiller httpHeaderFiller;

	@Autowired
	private ReverseStringResponseVO reverseStringResponseVO;
	
	
	@RequestMapping("/api")	
	public String healthCheck() {
		return "OK Febinocci Initiated";
	}

	
	//Febinocci
	@RequestMapping("/api/Fibonacci")
	public ResponseEntity<Integer> getFebinocciByIndex(@RequestParam(value="n", required=true, defaultValue="0")String index){

		//Logic can be written as seperate service
		int nthFebinocci = 0;
		try{
			nthFebinocci = Integer.parseInt(index);
		}catch (NumberFormatException nfe) {
		    throw new CustomExceptions("Kindly request with valid input | input must be number in order to get febinocci");
		}
		
		
		int initValue = 0 ;
		int initValue2 = 1;
		for(int i=2; i<=nthFebinocci;i++){
			int temp= initValue;
			initValue= initValue+initValue2;
			initValue2= temp;
		}
		
		if(initValue2 <0){
			throw new CustomExceptions("Currently We are providing the values up to Integer Maximum range, feachure will be enabled soon !!!");
		}
		
		return new ResponseEntity<Integer>(initValue,httpHeaderFiller.fillHeaders(), HttpStatus.OK);
	}


	
	@RequestMapping("/api/ReverseWords")
	public ResponseEntity<String> reverseWordInMyString(@RequestParam(value="sentence", required=false)String reverseString){
	
		HttpHeaders headers = new HttpHeaders();
		if(reverseString == null || reverseString.length() <=0){
			throw new CustomExceptions("No message received from the request, please provide valid message !!!");
		}
		
		
		//Logic can be written as seperate service
		String[] words = reverseString.split(" ");
		String reversedString = "";
		for (int i = 0; i < words.length; i++)
	        {
	           String word = words[i]; 
	           String reverseWord = "";
	           for (int j = word.length()-1; j >= 0; j--) 
		   {
			/* The charAt() function returns the character
			 * at the given position in a string
			 */
			reverseWord = reverseWord + word.charAt(j);
		   }
		   reversedString = reversedString + reverseWord + " ";
		}
	
		//reverseStringResponseVO.setText(reversedString);
		headers= httpHeaderFiller.fillHeaders();
		headers.add("Content-Type" , "text/plain");
		return new ResponseEntity<String>(reversedString,headers, HttpStatus.OK);
	}

	

	@RequestMapping("/api/TriangleType")
	public ResponseEntity<String> getTriangleType(@RequestParam(value="a", required=false)String a,@RequestParam(value="b", required=false)String b, @RequestParam(value="c", required=false)String c ){

		HttpHeaders headers = new HttpHeaders();
	    String traingleType= "";
		int length, breadth, height = 0;
		try{
			length = Integer.parseInt(a);
			breadth = Integer.parseInt(b);
			height = Integer.parseInt(c);
		}catch (NumberFormatException nfe) {
		    throw new CustomExceptions("Kindly request with valid inputs | length, breadth and width must number in order to get triangle Type");
		}
		
        if(length==breadth && breadth==height)
            traingleType = "Equilateral";

        else if(length >= (breadth+height) || height >= (breadth+length) || breadth >= (length+height) )
        	traingleType = "Not a triangle";

        else if ((length==breadth && breadth!=height ) || (length!=breadth && height==length) || (height==breadth && height!=length))
        	traingleType =  "Isosceles";

        else if(length!=breadth && breadth!=height && height!=length)
        	traingleType =  "Scalene";

		
		
		headers= httpHeaderFiller.fillHeaders();
		headers.add("Content-Type" , "text/plain");
		return new ResponseEntity<String>(traingleType,headers, HttpStatus.OK);
	}
}	