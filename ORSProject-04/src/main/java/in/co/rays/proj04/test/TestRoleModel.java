package in.co.rays.proj04.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj04.bean.RoleBean;
	import in.co.rays.proj04.exception.ApplicationException;
	import in.co.rays.proj04.exception.DuplicateRecordException;
	import in.co.rays.proj04.model.RoleModel;

	public class TestRoleModel {
		
		public static void main(String[] args) throws Exception {
			
			//testAdd();
			//testUpdate();
			//testDelete();
			//testFindByPk();
			//testFindbyName();
			  testbySearch();
		}
		
		public static void testAdd() throws Exception {
			
				RoleBean bean = new RoleBean();
				bean.setName("Ankit");
				bean.setDescription("hr");
				bean.setCreatedBy("admin");
				bean.setModifiedBy("admin");
				bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
				bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
				
				RoleModel model = new RoleModel();
				
				try {
					model.add(bean);
					System.out.println("Role Added Successfully");
				} catch (ApplicationException e) {
					e.printStackTrace();
				}	
		}

		public static void testUpdate() throws Exception {
			
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();

			bean.setId(1);
			bean.setName("Ankit");
			bean.setDescription("hr");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			
			
			try {
				model.update(bean);
				System.out.println("Role Updated Successfully");
			} catch (ApplicationException e) {
				e.printStackTrace();
			}	
	}
		public static void testDelete() throws Exception {
			
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();

			bean.setId(3);
			
			try {
			model.delete(bean);
			System.out.println("Role Deleted Succesfully");
		}  catch (ApplicationException e) {
			e.printStackTrace();
		}
			}
		public static void testFindByPk() throws Exception {

			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();

			bean = model.findByPk(2);

			if (bean == null) {
				System.out.println("record not found");
			} else {

				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
			    System.out.print("\t" + bean.getDescription());
			    System.out.print("\t" + bean.getCreatedBy());
			    System.out.print("\t" + bean.getModifiedBy());
			}
		}
       public static void testFindbyName() throws Exception {

    			RoleModel model = new RoleModel();
    		    RoleBean bean = new RoleBean();
    		    
    		    bean = model.findByName("Ankitt");

    			if (bean == null) {
    				System.out.println("no record found");
           }else {

				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
			    System.out.print("\t" + bean.getDescription());
			    System.out.print("\t" + bean.getCreatedBy());
			    System.out.print("\t" + bean.getModifiedBy());
           }
       }
       
       public static void testbySearch() throws Exception {
    	   try {
    		   
    	   RoleModel model = new RoleModel();
    	   RoleBean  bean  = new RoleBean();
    	   
			List list = new ArrayList();
			
			bean.setName("Ankit");
			
			list = model.search(bean,1,5);
		
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
			    System.out.print("\t" + bean.getModifiedBy());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    
       }
	}	
	


