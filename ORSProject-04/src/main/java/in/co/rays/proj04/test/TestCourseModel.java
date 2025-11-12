package in.co.rays.proj04.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import in.co.rays.proj04.bean.CourseBean;
import in.co.rays.proj04.exception.ApplicationException;
import in.co.rays.proj04.exception.DuplicateRecordException;
import in.co.rays.proj04.model.CourseModel;

public class TestCourseModel {
	
	public static void main(String[] args) throws Exception {
		
		//testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testfindbyName();
		//testSearch();
	}
	

	public static void testAdd() throws Exception {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean = new CourseBean();
			
			bean.setName("Python");
			bean.setId(1);
			bean.setDuration("6months");
			bean.setDescription("hr");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			long pk = model.add(bean);
			System.out.println("Course added Succesfully");
		  CourseBean existbean = model.findByPk(pk);
			if (existbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws Exception {
		try {
			CourseModel  model = new CourseModel ();
			
			CourseBean bean = model.findByPk(1L);
	
			bean.setName("SAGE");
         
			model.update(bean);

		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean existbean = model.findByPk(1L);

	

		try {
			model.delete(existbean);
			System.out.println("Course Deleted Successfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();

		bean = model.findByPk(1);

		if (bean == null) {
			System.out.println("record not found");
		} else {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());
		}
	}


	public static void testSearch() throws Exception {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean = new CourseBean();

			bean.setName("Python");

			List list = model.search(bean, 1, 5);
			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	public static void  testfindbyName() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();

		bean = model.findByName("SAGE");

		if (bean == null) {
			System.out.println("record not found");
		} else {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
		}
	}

}

