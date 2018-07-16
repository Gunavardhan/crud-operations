package com.app.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.IUserDao;
import com.app.dbutil.DBUtilJDBC;
import com.app.model.Student;

public class UserDaoImplJDBC implements IUserDao {

	public void studentRegistration(Student std) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try { 
			conn = DBUtilJDBC.getDBConnection();
			String sql = "INSERT INTO studentsinfo values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,std.getEmail());
			ps.setString(2,std.getStdName());
			ps.setString(3, std.getPwd());
			ps.setString(4, std.getMobile());
			ps.setString(5, std.getRole());
			int count = ps.executeUpdate();
			System.out.println("Record Inseted Count : " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public List<Student> loginStudent(Student std) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Student> loginUser = new ArrayList<Student>();
		try {
			conn = DBUtilJDBC.getDBConnection();
			String sql = "SELECT * FROM studentsinfo WHERE email = ? AND pwd = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, std.getEmail());
			ps.setString(2, std.getPwd());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student s = new Student();
				s.setStdName(rs.getString("stdName"));
				s.setEmail(rs.getString("email"));
				s.setMobile(rs.getString("mobile"));
				s.setPwd(rs.getString("pwd"));
				s.setRole(rs.getString("role"));
				loginUser.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return loginUser;
	}

	public List<Student> getAllStudents() {
		return null;
	}

	public void deleteStudentWIthHyperLink(String email) {

	}

	public void deleteMultipleRecords(List<String> checkDeleteAll) {

	}

	public Student editViewStudent(String email) {
		return null;
	}

	public void updateStudent(Student std, String email) {

	}

	public Student viewStudent(String email) {
		return null;
	}

	public Student searchStudent(String email) {
		return null;
	}

}
