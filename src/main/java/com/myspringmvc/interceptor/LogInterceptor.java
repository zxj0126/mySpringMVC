package com.myspringmvc.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.cglib.transform.impl.InterceptFieldFilter;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(LogInterceptor.class);
	private static final ThreadLocal<Long> startTimeThreadLocal =
			new NamedThreadLocal<Long>("ThreadLocal StartTime");
	
	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object obj, Exception e)throws Exception {
		if(InterceptorFilter.filterURI(request.getRequestURI())){
			long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
			long endTime = System.currentTimeMillis(); 	//2、结束时间  
			StringBuffer sb = new StringBuffer();
			sb.append("计时结束：").append(new SimpleDateFormat("hh:mm:ss.SSS").format(endTime))
			.append(" 耗时：").append(formatDateTime(endTime - beginTime))
			.append(" URI：").append(request.getRequestURI())
			.append(" 最大内存：").append(Runtime.getRuntime().maxMemory()/1024/1024).append("m")
			.append(" 已分配内存：").append(Runtime.getRuntime().totalMemory()/1024/1024).append("m")
			.append(" 已分配内存中的剩余空间：").append(Runtime.getRuntime().freeMemory()/1024/1024).append("m")
			.append(" 最大可用内存：").append((Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024).append("m");
			logger.info(sb.toString());
	        //删除线程变量中的数据，防止内存泄漏
	        startTimeThreadLocal.remove();
	    }
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		if(InterceptorFilter.filterURI(request.getRequestURI())){
			long beginTime = System.currentTimeMillis();//1、开始时间  
	        startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见） 
	        logger.info("开始计时："+ new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime)+" URI:"+request.getRequestURI());
	    }
		return true;
	}
	
	public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
}
