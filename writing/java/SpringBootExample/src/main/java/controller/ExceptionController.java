package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

	private static ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("")
	@ResponseBody
	String home() {
		return "Hello Exception!";
	}

	/**
	 * 正常转换json
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/1")
	@ResponseBody
	String home1() throws JsonProcessingException {

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");

		return mapper.writeValueAsString(map);
	}

	/**
	 * 抛出异常异常转换
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/2")
	@ResponseBody
	String home2() throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");

		if (true) {
			throw new Exception("没什么我就是想抛个异常");
		}

		return mapper.writeValueAsString(map);
	}

	/**
	 * 抛出异常异常转换
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/3")
	@ResponseBody
	String home3() {

		((Integer) ((1 + 2) / 0)).toString();

		return "123";
	}

	/**
	 * 抛出异常异常转换
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	@RequestMapping("/4")
	@ResponseBody
	String home4() {

		String s = "123";
		if (true) {
			s = null;
		}
		s.length();

		return "123";
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String exp(HttpServletRequest request, Exception ex) throws JsonProcessingException {

		ex.printStackTrace();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("Exception", "RuntimeException");
		map.put("result", false);
		map.put("msg", ex.getMessage());
		map.put("RequestURL", request.getRequestURL());

		if (ex instanceof NullPointerException) {
			map.put("null", "空指针啊");
		}
		
		return mapper.writeValueAsString(map);

	}

	@ExceptionHandler
	@ResponseBody
	public String exp2(HttpServletRequest request, Exception ex) throws JsonProcessingException {

		ex.printStackTrace();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Exception", "Exception");
		map.put("result", false);
		map.put("msg", ex.getMessage());
		map.put("RequestURL", request.getRequestURL());



		return mapper.writeValueAsString(map);

	}

}
