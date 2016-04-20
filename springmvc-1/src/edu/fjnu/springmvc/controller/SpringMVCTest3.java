package edu.fjnu.springmvc.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest3 {
	private static final String SUCCESS = "success";
	
	/**
	 * 目标方法可以添加Map类型(实际上也可以是Model类型或ModelMap类型)的参数
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
		return SUCCESS;
	}
	
	/**
	 * 目标方法的返回值可以是ModelAndView类型
	 * 其中可以包含视图和模型信息
	 * SpringMVC会吧ModelAndView的model中数据放到request域对象中
	 * @return
	 */
	@RequestMapping("testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		
		//添加模型数据到ModelAndView中
		modelAndView.addObject("time", new Date());
		
		return modelAndView;
	}
	
	/**
	 * 可以使用Servlet原生的API作为目标方法的参数
	 * 具体支持以下类型：HttpServletRequest，HttpServletResponse，java.security.Principal
	 * Locale，InputStream，OutputStream，Reader，Writer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("testServletAPI:" + request + ", " + response);
		return SUCCESS;
	}
}
