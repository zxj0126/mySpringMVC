package com.myspringmvc.interceptor;
/**
 * @author Tricky
 * @version 2017年8月15日
 */
public class InterceptorFilter {
	public static boolean filterURI(String URI){
		if(URI.contains("swagger-resources")){
			return false;
		}
		return true;
	}
}
