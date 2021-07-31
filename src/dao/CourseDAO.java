package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Course;
import utils.HibernateUtil;

import java.util.List;
import java.util.Vector;

import static java.lang.Integer.parseInt;


public class CourseDAO {
    public static List<Course> getAllCourse(){
        List<Course> results =
                new Support<Course>().
                        executeHql("SELECT st FROM Course st");
        return results;
    }
    public static List<Course> findID (String courseid){
        List<Course> results =
                new Support<Course>().
                        executeHql("SELECT st FROM Course st WHERE st.courseId = '" + courseid + "'");
        return results;
    }
    public static Vector extractData (){
        List<Course> coursesList = CourseDAO.getAllCourse();
        Vector datatable = new Vector();
        for (Course item:coursesList){
            Vector data = new Vector();
            data.add(item.getCourseId());
            data.add(item.getSemester().getSchoolyear());
            data.add(item.getSemester().getSemestername());
            data.add(item.getSchoolSubject().getSubjectId());
            data.add(item.getSchoolSubject().getSubjectname());
            data.add(item.getSchoolSubject().getCredits());
            data.add(item.getTeacher().getFullname());
            data.add(item.getRoomnum());
            data.add(item.getWeekday());
            data.add(item.getShift());
            datatable.add(data);
        }
        return datatable;
    }
    public static  String[] extractAllCourseID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.courseId FROM Course st");
        return results.toArray(new String[0]);
    }
    public static boolean deleteCourse(String courseid){
         return new Support<Course>().deleteRow("DELETE FROM Course hl  WHERE hl.courseId = '" + courseid + "'");
    }
    public static boolean addCourse (Vector<String> input){
            Course newCourse = new Course();
            if (!findID(input.elementAt(0)).isEmpty()){
              return false;
            }
            newCourse.setCourseId(input.elementAt(0));
            newCourse.setSemester(SemesterDAO.findID(input.elementAt(1)).get(0));
            newCourse.setSchoolSubject(SchoolSubjectDAO.findID(input.elementAt(2)).get(0));
            newCourse.setTeacher(TeacherDAO.findID(input.elementAt(3)).get(0));
            newCourse.setRoomnum(input.elementAt(4));
            newCourse.setWeekday(parseInt(input.elementAt(5)));
            newCourse.setShift(parseInt(input.elementAt(6)));
            return new Support<Course>().addRow(newCourse);
    }
    public static boolean editCourse (Vector<String> input, String oldCourseIDVersion){
        boolean result = false;
        if (!input.elementAt(0).equals(oldCourseIDVersion)){
            if (addCourse(input)){
                return deleteCourse(oldCourseIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                Course loadrow = session.load(Course.class, oldCourseIDVersion);
                loadrow.setSemester(SemesterDAO.findID(input.elementAt(1)).get(0));
                loadrow.setSchoolSubject(SchoolSubjectDAO.findID(input.elementAt(2)).get(0));
                loadrow.setTeacher(TeacherDAO.findID(input.elementAt(3)).get(0));
                loadrow.setRoomnum(input.elementAt(4));
                loadrow.setWeekday(parseInt(input.elementAt(5)));
                loadrow.setShift(parseInt(input.elementAt(6)));
                session.update(loadrow);
                transaction.commit();
                result = true;
            }catch (HibernateException ex){
                transaction.rollback();
                System.err.print(ex);
                result = false;
            }finally{
                session.close();
            }

        }
        return result;
    }
}


