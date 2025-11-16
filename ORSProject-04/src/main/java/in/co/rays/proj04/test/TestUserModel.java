package in.co.rays.proj04.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj04.bean.UserBean;
import in.co.rays.proj04.exception.ApplicationException;
import in.co.rays.proj04.exception.DuplicateRecordException;
import in.co.rays.proj04.model.UserModel;

public class TestUserModel {
	
	public static void main(String[] args) throws Exception {
		
		//testAdd();
		//testUpdate();
		testDelete();
		//testFindByPk();
		//testFindByLogin();
		//testAuthenticate();
		//testSearch();
	}

	public static void testAdd() throws Exception {
		try {
			UserModel model = new UserModel();
			UserBean bean = new UserBean();

			String date = "13/12/2001";
			Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);

			bean.setFirstName("Ankit");
			bean.setLastName("Rawat");
			bean.setLogin("ankit123@gmail.com");
			bean.setPassword("12345");
			bean.setDob(d);
			bean.setMobileNo("9343178451");
			bean.setRoleId(1L);
			bean.setGender("Male");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);
			UserBean addedbean = model.findByPk(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws Exception {
		try {
			UserModel model = new UserModel();
			UserBean bean = model.findByPk(1L);

			bean.setFirstName("Ankit");
			bean.setLastName("Rawat");
            bean.setMobileNo("9343178452");
			model.update(bean);

			UserBean updatedbean = model.findByPk(1L);

		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean.setId(1);

		try {
			model.delete(bean);
			System.out.println("User Deleted Successfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean = model.findByPk(1);

		if (bean == null) {
			System.out.println("record not found");
		} else {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getGender());
		}
	}

	public static void testFindByLogin() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean = model.findByLogin("ankit123@gmail.com");

		if (bean == null) {
			System.out.println("no record found");
		} else {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getGender());
		}
	}

	public static void testAuthenticate() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean = model.authenticate("ankit123@gmail.com", "12345");

		if (bean != null) {
			System.out.print(bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getGender());
		} else {
			System.out.println("Invalid Login or Password");
		}
	}

	public static void testSearch() throws Exception {
		try {
			UserModel model = new UserModel();
			UserBean bean = new UserBean();

			bean.setFirstName("Ankit");

			List list = model.search(bean, 1, 5);
			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getFirstName());
				System.out.print("\t" + bean.getLastName());
				System.out.print("\t" + bean.getLogin());
				System.out.print("\t" + bean.getMobileNo());
				System.out.println("\t" + bean.getGender());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
