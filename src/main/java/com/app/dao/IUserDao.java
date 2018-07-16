package com.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.app.model.Student;

public interface IUserDao {
	void studentRegistration(Student std) throws ClassNotFoundException, SQLException;
	List<Student> loginStudent(Student std) throws ClassNotFoundException, SQLException;
	List<Student> getAllStudents();
	void  deleteStudentWIthHyperLink(String email);
	void  deleteMultipleRecords(List<String> checkDeleteAll);
	Student editViewStudent(String email);
	void updateStudent(Student std,String email);
	Student viewStudent(String email);
	Student searchStudent(String email);
}
