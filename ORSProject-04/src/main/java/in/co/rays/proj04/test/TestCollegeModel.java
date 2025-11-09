package in.co.rays.proj04.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj04.bean.CollegeBean;
import in.co.rays.proj04.exception.ApplicationException;
import in.co.rays.proj04.exception.DuplicateRecordException;
import in.co.rays.proj04.model.CollegeModel;

public class TestCollegeModel {
	
	public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testfindbyName();
		//testSearch();
	}
	

	public static void testAdd() throws Exception {
		try {
			CollegeModel model = new CollegeModel();
			CollegeBean bean = new CollegeBean();

			bean.setName("IPS");
			bean.setAddress("Indore");
			bean.setState("MP");
			bean.setCity("Indore");
			bean.setPhoneno("9867564745");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			long pk = model.add(bean);
			System.out.println("Collage added Succesfully");
		  CollegeBean existbean = model.findByPk(pk);
			if (existbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws Exception {
		try {
			CollegeModel model = new CollegeModel();
			
		CollegeBean bean = model.findByPk(1L);
	
			bean.setName("SAGE");;
         
			model.update(bean);

		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean existbean = model.findByPk(1L);

	

		try {
			model.delete(existbean);
			System.out.println("User Deleted Successfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();

		bean = model.findByPk(1);

		if (bean == null) {
			System.out.println("record not found");
		} else {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getPhoneno());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getId());
		}
	}


	public static void testSearch() throws Exception {
		try {
			CollegeModel model = new CollegeModel();
			CollegeBean bean = new CollegeBean();

			bean.setName("DAVV");

			List list = model.search(bean, 1, 5);
			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getPhoneno());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	public static void  testfindbyName() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();

		bean = model.findByName("SAGE");

		if (bean == null) {
			System.out.println("record not found");
		} else {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getPhoneno());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getId());
		}
	}

}

