package com.app.servicesimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.app.dao.IUserDao;
import com.app.daoimpl.UserDaoImplHibernate;
import com.app.model.Student;
import com.app.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
	
	private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
	@Autowired
	private IUserDao stdDao = null;
	
	public String studentRegistration(Student std, Model model) throws ClassNotFoundException, SQLException {
		logger.info("Entered Into Sudentregistration method :: StudentServiceImpl");
		logger.debug("StundetnRegistration entered");
		//UserDaoImplHibernate registerDao = new UserDaoImplHibernate();
		stdDao.studentRegistration(std);
		// UserDaoImplJDBC registerDaoJdbc = new UserDaoImplJDBC();

		// registerDaoJdbc.studentRegistration(std);
		model.addAttribute("succMsg", "Your Registration Is Done!!,Please Login...");
		model.addAttribute("username", std.getEmail());
		logger.debug("StundetnRegistration entered");
		logger.info("Exit Into Sudentregistration method :: StudentServiceImpl");
		return "home";

	}

	public String loginStudent(Student std, Model model, HttpServletRequest req) throws ClassNotFoundException, SQLException {
		//UserDaoImplHibernate userDaoHiber = new UserDaoImplHibernate();
		List<Student> loginStudent = stdDao.loginStudent(std);
		// UserDaoImplJDBC userDao = new UserDaoImplJDBC();
		// List<Student> loginStudent = userDao.loginStudent(std);
		if (loginStudent.isEmpty()) {
			model.addAttribute("message", "Inavalid Credentials!!");
			return "home";
		} else {
			Student student = loginStudent.get(0);
			if (student.getRole().equals("Admin")) {
				List<Student> allStudents = stdDao.getAllStudents();
				model.addAttribute("loginUserProfile", loginStudent.get(0));
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", std.getEmail());
				model.addAttribute("allStudents", allStudents);
				return "profile";
			} else {
				model.addAttribute("username", std.getEmail());
				model.addAttribute("loginUserProfile", loginStudent.get(0));
				return "user";
			}
		}

	}

	public String deleteStudentWIthHyperLink(String email, Model model) {
		//UserDaoImplHibernate userDao = new UserDaoImplHibernate();
		stdDao.deleteStudentWIthHyperLink(email);
		List<Student> allStudents = stdDao.getAllStudents();
		model.addAttribute("allStudents", allStudents);
		return "profile";
	}

	public String deleteMultipleRecords(Model model, List<String> checkDeleteAll) {
		//UserDaoImplHibernate userDao = new UserDaoImplHibernate();
		stdDao.deleteMultipleRecords(checkDeleteAll);
		List<Student> allStudents = stdDao.getAllStudents();
		model.addAttribute("allStudents", allStudents);
		return "profile";
	}

	public String editStudent(String email, Model model) {
		//UserDaoImplHibernate userDao = new UserDaoImplHibernate();
		Student editViewStudent = stdDao.editViewStudent(email);
		model.addAttribute("editRecrd", editViewStudent);
		return "editform";
	}

	public String updateStudent(Student std, String email, Model model) {
		//UserDaoImplHibernate userDao = new UserDaoImplHibernate();
		stdDao.updateStudent(std, email);
		List<Student> allStudents = stdDao.getAllStudents();
		model.addAttribute("allStudents", allStudents);
		return "profile";
	}

	public String viewStudent(String email, Model model) {
		//UserDaoImplHibernate userDao = new UserDaoImplHibernate();
		Student viewStudent = stdDao.viewStudent(email);
		model.addAttribute("getStdInfo", viewStudent);
		return "student-info";
	}

	public String searchStudent(String email, Model model) {
		//UserDaoImplHibernate userDao = new UserDaoImplHibernate();
		Student searchStudent = stdDao.searchStudent(email);
		if(searchStudent !=null){
			model.addAttribute("getStdInfo",searchStudent);
			return "student-info";
		}else{
			model.addAttribute("msg","Your entered email not found,Please enter Correct Email");
			List<Student> allStudents = stdDao.getAllStudents();
			model.addAttribute("allStudents", allStudents);
			return "profile";
		}
	}

}
