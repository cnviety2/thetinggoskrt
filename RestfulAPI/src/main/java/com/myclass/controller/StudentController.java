package com.myclass.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclass.entity.Student;

@Controller
public class StudentController {
	private List<Student> list;
	/*@RequestMapping(value = "student", method = RequestMethod.GET)
	public String index(
			@RequestParam(value = "username",defaultValue = "duy") String username,
			@RequestParam(value = "age",defaultValue = "16") int age,
			Model model
			) {
		model.addAttribute("message","Name:"+username+"Age:"+age);
		return "home/index";
	}*/
	
	@PostConstruct
	public void init() {
		list=new ArrayList<Student>();
	}
	//khác nhau:PathVari nếu thiếu 1 tham số sẽ lỗi,RequestPa có thể đặt đc default value nếu ko có dữ liệu truyền lên
	@RequestMapping(value = "student/{username}/{age}",method = RequestMethod.GET)
	@ResponseBody
	public List<Student> index2(@PathVariable("username") String username,
			@PathVariable("age") int age
			) {
		list.add(new Student(username, age));
		return list;
	}

	
	@RequestMapping(value = "student", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> index3(
			@RequestParam(value = "username",defaultValue = "duy") String username,
			@RequestParam(value = "age",defaultValue = "16") int age
			) {
		list.add(new Student(username, age));
		return list;
	}
	
	@RequestMapping(value = "student", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> index4(@RequestBody Student stdent) {
		list.add(new Student(stdent.getName(),stdent.getAge()));
		return list;
	}
	
	@RequestMapping(value = "student/home",method = RequestMethod.GET)
	@ResponseBody
	public List<Student> index5(){
		return list;
	}
}
