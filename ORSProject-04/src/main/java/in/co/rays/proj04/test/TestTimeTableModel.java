package in.co.rays.proj04.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj04.model.CourseModel;
import in.co.rays.proj04.model.FacultyModel;
import in.co.rays.proj04.model.MarksheetModel;
import in.co.rays.proj04.model.TimeTableModel;
import in.co.rays.proj04.bean.CourseBean;
import in.co.rays.proj04.bean.FacultyBean;
import in.co.rays.proj04.bean.MarksheetBean;
import in.co.rays.proj04.bean.TimeTableBean;
import in.co.rays.proj04.exception.ApplicationException;
import in.co.rays.proj04.exception.DatabaseException;
import in.co.rays.proj04.exception.DuplicateRecordException;

public class TestTimeTableModel {

	public static void main(String[] args) throws ParseException {

		// testAdd();
		// testUpdate();
		// testDelete();
		testSearch();
	}

	public static void testAdd() throws ParseException {

		TimeTableModel model = new TimeTableModel();
		TimeTableBean bean = new TimeTableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setSemester("second");
		bean.setDescription("Must");
		bean.setExamDate(sdf.parse("2025-11-20"));
		bean.setExamTime("9:00 to 12:00");
		bean.setCourseId(1);
		bean.setSubjectId(1);

		try {
			model.add(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		TimeTableModel model = new TimeTableModel();
		try {
			TimeTableBean bean = model.findByPk(1l);
			bean.setSemester("first");

			model.update(bean);
		} catch (DuplicateRecordException | ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		TimeTableModel model = new TimeTableModel();
		TimeTableBean bean;
		try {
			bean = model.findByPk(1l);
			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testSearch() {

		TimeTableModel model = new TimeTableModel();
		TimeTableBean bean = new TimeTableBean();

		try {
			List list = model.search(bean, 0, 0);
			Iterator<TimeTableBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}