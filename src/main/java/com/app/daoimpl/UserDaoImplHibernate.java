package com.app.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.app.dao.IUserDao;
import com.app.dbutil.DBUtilHibernate;
import com.app.model.Student;

public class UserDaoImplHibernate implements IUserDao{
	public  void studentRegistration(Student std){
	SessionFactory sf = DBUtilHibernate.getSessionFactroy();
	Session session = sf.openSession();
	session.save(std);
	session.beginTransaction().commit();
	}

	public List<Student> loginStudent(Student std) {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		Criteria crt = session.createCriteria(Student.class);
		crt.add(Restrictions.eq("email", std.getEmail()));
		crt.add(Restrictions.eq("pwd", std.getPwd()));
		@SuppressWarnings("unchecked")
		List<Student> loginStdLitObj = crt.list();
		return loginStdLitObj;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		Criteria getAllQuery = session.createCriteria(Student.class);
		List<Student> getAllRcrds = getAllQuery.list();
		return getAllRcrds;
	}

	public void deleteStudentWIthHyperLink(String email) {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		Query delQry = session.createQuery("delete from Student where email =:email");
	  	delQry.setParameter("email",email);
	  	int delCount =delQry.executeUpdate(); 
	  	session.beginTransaction().commit();
	  	System.out.println("record deleted : " +delCount);
	}

	public void deleteMultipleRecords(List<String> checkDeleteAll) {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		if(!checkDeleteAll.isEmpty()){
			Query delMul = session.createQuery("delete from Student where email IN (:email)");
			delMul.setParameterList("email", checkDeleteAll);
			int delCount = delMul.executeUpdate();
			System.out.println("Deleted Count : " +delCount);
			session.beginTransaction().commit();
		}
	}

	@SuppressWarnings("unchecked")
	public Student editViewStudent(String email) {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		Query editQry = session.createQuery("from Student where email =:email");
		editQry.setParameter("email", email);
		List<Student> list = editQry.list();
		Student student = list.get(0);
		return student;
	}

	public void updateStudent(Student std,String email) {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		Query updateQry = session.createQuery("update Student SET stdName=:stdName,pwd=:pwd,mobile=:mobile,role=:stdrole WHERE email=:email");
		updateQry.setParameter("stdName", std.getStdName());
		updateQry.setParameter("pwd", std.getPwd());
		updateQry.setParameter("mobile", std.getMobile());
		updateQry.setParameter("email", std.getEmail());
		updateQry.setParameter("stdrole", std.getRole());
		/*List<Student> list = updateQry.list();
		std = list.get(0);*/
		int updateCount = updateQry.executeUpdate();
		session.beginTransaction().commit();
		System.out.println("Updated " + updateCount);
	}

	@SuppressWarnings("unchecked")
	public Student viewStudent(String email) {
		SessionFactory sf = DBUtilHibernate.getSessionFactroy();
		Session session = sf.openSession();
		Student stdInfo = new Student();
		Query getStdInfo = session.createQuery("from Student WHERE email=:email");
		getStdInfo.setParameter("email", email);
		List<Student> list = getStdInfo.list();
		if(list != null && list.size() !=0){
			System.out.println("Size : " +list.size());
			stdInfo = list.get(0);
			System.out.println("Name : " + stdInfo.getStdName() + stdInfo.getEmail());
			return stdInfo;
		}else{
			System.out.println("Your entered email not found,Please enter Correct Email");
			return null;
		}
	}

	public Student searchStudent(String email) {
		UserDaoImplHibernate userDaoImpl = new UserDaoImplHibernate();
		Student searchStd = userDaoImpl.viewStudent(email);
		return searchStd;
	}
}
