package com.myspringmvc.web;

import javax.xml.ws.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tricky
 * @version 2017��8��11��
 */
@Controller
public class TestControl {
	
	@RequestMapping(value="/first/test/",method=RequestMethod.GET)
	@ApiOperation(value="测试")
	@ApiResponses(value={@ApiResponse(code=201,message="success"),
			@ApiResponse(code=300,message="faild")})
	public @ResponseBody String firstTest(){
		return "ok";
	}
	
	@RequestMapping(value="/second/test/",method=RequestMethod.POST)
	@ApiOperation(value="测试")
	@ApiResponses(value={@ApiResponse(code=201,message="success"),
			@ApiResponse(code=300,message="faild")})
	public @ResponseBody String secondTest(){
		return "ok";
	}
	
	@RequestMapping(value="/third/test/",method=RequestMethod.PUT)
	@ApiOperation(value="测试")
	@ApiResponses(value={@ApiResponse(code=201,message="success"),
			@ApiResponse(code=300,message="faild")})
	public @ResponseBody String thirdTest(){
		return "ok";
	}
	
	@RequestMapping(value="/fouth/test/",method=RequestMethod.DELETE)
	@ApiOperation(value="测试")
	@ApiResponses(value={@ApiResponse(code=201,message="success"),
			@ApiResponse(code=300,message="faild")})
	public @ResponseBody String fouthTest(){
		return "ok";
	}
}
