package com.app.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Student;
import com.app.service.IStudentService;
import com.app.servicesimpl.StudentServiceImpl;

@Controller
public class StudentController {

	@Autowired
	private IStudentService serviceInvoke = null;
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public String studentRegistration(Student std, Model model) throws ClassNotFoundException, SQLException {
		/*StudentServiceImpl registerService = new StudentServiceImpl();*/
		String stdReg = serviceInvoke.studentRegistration(std,model);
		//userDao.studentRegistration(std);
		//model.addAttribute("succMsg", "You are successfully registered,Please login");
		//model.addAttribute("username", std.getEmail());
		System.out.println("register entered");
		return stdReg;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginStudent(Student std, Model model,HttpServletRequest req) throws ClassNotFoundException, SQLException {
		//StudentServiceImpl loginService = new StudentServiceImpl();
		String loginStudent = serviceInvoke.loginStudent(std, model, req);
		return loginStudent;
	}
	
	@RequestMapping(value="/deletestudent/{email:.+}", method=RequestMethod.GET)
	public String deleteStudentWIthHyperLink(@PathVariable("email") String email, Model model ){
		//StudentServiceImpl deleteService = new StudentServiceImpl();
		String deleteStudentWIthHyperLink = serviceInvoke.deleteStudentWIthHyperLink(email, model);
		return deleteStudentWIthHyperLink;
	}
	
	@RequestMapping(value="/deletemultiple")
	public String deleteMultipleRecords(Model model, @RequestParam("checkDeleteAll") List<String> checkDeleteAll){
		System.out.println("This Method for Delete Multiple Records :: Guna");
		System.out.println("this method for sagar..........");
		//StudentServiceImpl deleteMultipleService = new StudentServiceImpl();
		String deleteMultipleRecords = serviceInvoke.deleteMultipleRecords(model, checkDeleteAll);
		return deleteMultipleRecords;
	}
	
	@RequestMapping(value="/editStudent")
	public String editStudent(@RequestParam("email") String email,Model model){
		//StudentServiceImpl editViewService = new StudentServiceImpl();
		String editStudent = serviceInvoke.editStudent(email, model);
		return editStudent;
	}
	
	@RequestMapping(value="/updateStudent")
	public String updateStudent(Student std, @RequestParam("email") String email,Model model){
     System.out.println("Dinesh Updating profile");
		System.out.println("Guna updating student");
		//StudentServiceImpl updateStudentService = new StudentServiceImpl();
		String updateStudent = serviceInvoke.updateStudent(std, email, model);
		return updateStudent;
	}
	
	@RequestMapping(value="/viewstudent", method=RequestMethod.GET)
	public String viewStudent(@RequestParam("email") String email, Model model){
		//StudentServiceImpl viewStudentService = new StudentServiceImpl();
		String viewStudent = serviceInvoke.viewStudent(email, model);
		return viewStudent;
	}
	
	@RequestMapping(value = "/getuserinfo", method = RequestMethod.POST)
	public String searchStudent(@RequestParam("email") String email, Model model) {
		//StudentServiceImpl searchStudent = new StudentServiceImpl();
		String searchStd = serviceInvoke.searchStudent(email, model);
		return searchStd;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,Model model){
		 HttpSession session=request.getSession();  
         session.invalidate();
         model.addAttribute("logoutMsg", "You Are Logout !!! ,Please login again");
		return "home";
	}
}
