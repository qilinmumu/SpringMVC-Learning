package edu.fjnu.springmvc.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.fjnu.springmvc.domain.User;

//@SessionAttributes(types={User.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest3 {
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/testView")
	public String testView() {
		System.out.println("testView");
		return "helloView";
	}
	
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver() {
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	
	/**
	 * 1.有@ModelAttribute标记的方法，会在每个目标方法执行之前被SpringMVC调用
	 * 2.@ModelAttribute注解也可以来继续试目标方法POJO类型的入参，其value属性值有如下的作用：
	 * 1).SpringMVC会使用value属性值在implicitModel中查找对应的对象，若存在则会直接传入到目标方法的入参中，
	 * 2).SpringMVC会 value为key，POJO类型的对象为value，存入request中
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id", required=false) Integer id,
			Map<String, Object> map) {
		if(id != null) {
			//模拟从数据库中获取对象
			User user = new User(1, "Tom", "123456", "tom@qq.com", 12);
			System.out.println("从数据库中获取一个对象：" + user);
			map.put("user", user);
		}
	}
	
	/**
	 * 运行流程：
	 * 1.执行@ModelAttribute注解修饰的方法:从数据库中取出对象，把对象放到Map中，键为user
	 * 2.SpringMVC从Map中取出User对象，并把表单的请求参数赋给该User对象的对应属性
	 * 3.SpringMVC把上述对象传入目标方法的参数 
	 * 
	 * 注意：在@ModelAttribute修饰的方法汇总，放入到map时的键需要和目标方法入参类型的第一个字母小写的字符串一致
	 * 
	 * SpringMVC确定目标方法POJO类型入参的过程
	 * 1.确定一个key：
	 * 1).若目标方法的POJO类型的参数没有使用@ModelAttribute作为修饰，则key为POJO类名第一个字母的小写
	 * 2).若使用了@ModelAttribute来修饰，则key为@ModelAttribute注解的value属性值
	 * 2.在implicitModel中查找key对应的对象，若存在，则作为入参传入
	 * 1).若在@ModelAttribute标记的方法中在Map中保存过，且key 和1确定的key一直，则会获取到
	 * 3.若implicitModel中不存在key对应的对象，则检查当前的Handler是否使用@SessionAttributes注解修饰，
	 * 若使用了注解，且@SessionAttributes注解的value属性值中包含了key，则会从HttpSession中来获取key所
	 * 对应的value值，若存在则直接传入到目标方法的入参中，若不存在则将抛出异常
	 * 4.若Handler没有表示@SessionAttributes注解，或@SessionAttributes注解的value值中不包含key，
	 * 则会通过反射来创建POJO类型的参数，传入到目标方法的入参中
	 * 5.SpringMVC会把key和value保存到implicitModel中，进而会保存到request中
	 * 
	 * 源代码分析的流程
	 * 1.调用@ModelAttribute注解修饰的方法。 实际上把@ModelAttribute方法中Map中的数据放在了implicitModel中
	 * 2.解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
	 * 1).创建WebDataBinder对象：
	 * ①.确定objectName属性:若传入的attrName属性值为""，则objectName为类名第一个字母小写
	 * *注意 ：attrName，若目标方法的POJO属性使用@ModelAttribute来修饰，则attrName值即为@ModelAttribute的value属性
	 * ②.确定target属性:
	 *  >在implicitModel中查找attrName对应的属性值。若存在，OK
	 *  >*若不存在：则验证当前Handler是否使用了@SessionAttributes进行修饰，若使用了，则尝试从Session中后去attrName所对应的属性值
	 *  若session中没有对应的属性值，则抛出异常
	 *  >若Handler没有使用SessionAttributes进行修饰，或者SessionAttributes中没有使用value值指定的key和attrName相匹配，则通过
	 *  反射创建了POJO对象
	 *  2).SpringMVC把表单的请求参数赋给了WebDataBinder的target对应的属性
	 *  3).*SpringMVC会把WebDataBinder的attrName和target给到implicitModel,进而传到request域对象中
	 *  4).把WebDataBinder的target作为参数传递给目标方法的入参
	 * @param user
	 * @return
	 */
	@RequestMapping("testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("修改：" + user);
		return SUCCESS;
	}
	
	/**
	 * SessionAttributes除了可以通过属性名指定需要放到会话中的属性外（使用value属性值），
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（使用types属性值）
	 * 注意:该注解只能放在类上面，不能放在方法上面
	 * @param map
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		User user = new User("Tom", "123456", "qlmm@qq.com", 16);
		map.put("user", user);
		return SUCCESS;
	}
	
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
