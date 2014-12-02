package com.skpw.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc异常捕获类
 * 
 */
//@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = Logger
			.getLogger(MyExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
				
		if (ex instanceof IOException) {
			
			System.out.println("Hello——————————");
			
			return new ModelAndView("");
		}else if (ex instanceof SQLException) {
			
			System.out.println("数据库连接异常————————————————————");
			return new ModelAndView("common/dbError");
		}else if (ex instanceof  ArithmeticException) {
			System.out.println("算术异常");
			return new ModelAndView("common/arithmeticexception");
		}
		
		
		
		return null;
		
		
		
//		if(ex instanceof IoException){
//			 return new ModelAndView("ioexp");
//			}else if(ex instanceof SQLException){
//				return new ModelAndView("sqlexp");
//	      }   
//		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
//		logger.info(exceptionMessage);
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("exceptionMessage", exceptionMessage);
//		model.put("ex", ex);
//		return new ModelAndView("common/error", model);
	}
}
