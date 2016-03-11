package edu.fjnu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	
	@RequestMapping(value = "/testRest", method=RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest PUT:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}", method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest DELETE:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest", method=RequestMethod.POST)
	public String testRest() {
		System.out.println("testRest POST");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}", method=RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest GET:" + id);
		return SUCCESS;
	}

	/**
	 * @PathVariable 可以来映射URL中的占位符到目标方法的参数中
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable(value="id") Integer id) {
		System.out.println("testPathVariable:" + id);
		return SUCCESS;
	}
	
	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * 了解:可以使用params和headers来更加精确的映射请求，params和headers支持简单的表达式
	 * @return
	 */
	@RequestMapping(value = "testParamsAndHeaders",
			params = { "username","age!=10" }, 
			headers = { "Accept-Language=en-US,zh;q=0.8" })
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	/**
	 * 使用method属性来制定请求方式
	 * @return
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	/**
	 * 1.@RequestMapping除了修饰方法，还可以修饰类
	 * 2.类定义处：提供初步的请求映射信息，相对于WEB应用的根目录
	 * 3.方法处：提供进一步的戏份映射信息，相对于类定义处的URL，若类定义处未标注@RequestMapping，
	 * 则方法处标记的URL相对于WEB应用根目录
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}

}
