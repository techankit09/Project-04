package in.co.rays.proj04.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj04.bean.CollegeBean;
import in.co.rays.proj04.bean.StudentBean;
import in.co.rays.proj04.exception.ApplicationException;
import in.co.rays.proj04.exception.DuplicateRecordException;
import in.co.rays.proj04.model.CollegeModel;
import in.co.rays.proj04.model.StudentModel;

public class TestStudentModel {
	
	public static void main(String[]args) throws Exception {
		
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testSearch();
	}
	
	public static void testAdd() throws Exception {
		try {
			StudentModel model = new StudentModel();
			StudentBean bean = new StudentBean();
			
			String date = "13/12/2001";
			Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);

	        bean.setFirstName("Ankit");
	        bean.setLastName("rawat");
	        bean.setDob(d);
	        bean.setGender("male");
	        bean.setCollegeId(1);
	        bean.setMobileNo("9847565656");
	        bean.setEmail("ankitrawat0988@gmail.com");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			long pk = model.add(bean);
			System.out.println("Student added Succesfully");
		  StudentBean existbean = model.findByPk(pk);
			if (existbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
	
	public static void testUpdate() {
		
		try {
			StudentModel model = new StudentModel();
			
			StudentBean bean = model.findByPk(1);
			
			bean.setFirstName("aunkitt");
			
			model.update(bean);
			System.out.println("student updated successfully");
		
	   } catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
	}
}
	
	public static void testDelete() throws Exception {
		
		try {
			
			StudentModel model = new StudentModel();
			
			StudentBean bean = model.findByPk(1);
			
		    model.delete(bean);
		    System.out.println("student deleted successfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
	}
}
	public static void testFindByPk() throws Exception {
		
		StudentModel model = new StudentModel();
		
		StudentBean bean = model.findByPk(1);
		
		if(bean == null) {
			System.out.println("Records not found");
		} else {
			System.out.println(bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());;
		}
	}
	
	public static void testSearch() throws Exception {
		
		try {
			StudentModel model = new StudentModel();
		  StudentBean bean = new StudentBean();

			bean.setCollegeName("DAVV");

			List list = model.search(bean, 1, 5);
			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = (StudentBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getCollegeId());
				System.out.print("\t" + bean.getFirstName());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	}
