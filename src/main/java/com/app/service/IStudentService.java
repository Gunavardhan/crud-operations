package com.app.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Student;

public interface IStudentService {
	public String studentRegistration(Student std, Model model) throws ClassNotFoundException, SQLException;
	public String loginStudent(Student std, Model model, HttpServletRequest req) throws ClassNotFoundException, SQLException;
	public String deleteStudentWIthHyperLink(String email, Model model );
	String deleteMultipleRecords(Model model, List<String> checkDeleteAll);
	public String editStudent(@RequestParam("email") String email,Model model);
	public String updateStudent(Student std,String email,Model model);
	String viewStudent(String email, Model model);
	public String searchStudent(String email,Model model);
}