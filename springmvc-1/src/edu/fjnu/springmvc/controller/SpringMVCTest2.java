package edu.fjnu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fjnu.springmvc.domain.User;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest2 {
	private static final String SUCCESS = "success";
	
	/**
	 * SpringMVC会按请求参数名和POJO属性名进行自动匹配，
	 * 自动为该对象填充属性值，支持级联属性。
	 * @param user
	 * @return
	 */
	@RequestMapping("/testPojo")
	private String testPojo(User user) {
		System.out.println("testPojs:" + user);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue 映射一个Cookie值
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
		System.out.println("testCookieValue: " + sessionId);
		return SUCCESS;
	}
	
	/**
	 * 映射请求头信息
	 * 用法同@RequestParam
	 * @param language
	 * @return
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String language) {
		System.out.println("testRequestHeader, Accept-Language:" + language);
		return SUCCESS;
	}
	
	/**
	 * @RequestParam 来映射请求参数
	 * value值即为请求参数的参数名
	 * required 该参数是否必须
	 * defaultValue 请求参数的默认值
	 * @param username
	 * @param age
	 * @return
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String username, 
			@RequestParam(value="age", required=false, defaultValue="0") int age) {
		System.out.println("testRequestParam: username=" + username + ", age=" + age);
		return SUCCESS;
	}
}
