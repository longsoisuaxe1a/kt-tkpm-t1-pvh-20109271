package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService service;
	@GetMapping("/index")
	public String search(Model model) {
		List<Student> students =service.findAll();
		model.addAttribute("students", students);
		return "index";
	}
	@GetMapping("/create")
	public String showCreate(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create";
	}
	@PostMapping("/submit")
	public String add(@ModelAttribute("student") Student student) {
		service.save(student);
		return "redirect:index";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		service.deleteById(id);
		return "redirect:index";
	}
	@GetMapping("/update")
	public String showUpdate(@RequestParam("id") int id, Model model) {
		Student student = service.getById(id);
		model.addAttribute("student", student);
		return "update";
	}
	@PostMapping("/update-submit")
	public String update(@ModelAttribute("student") Student student) {
		service.save(student);
		return "redirect:index";
	}
}
