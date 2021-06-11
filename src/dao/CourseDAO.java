package dao;

import pojo.Course;
import java.util.List;
import java.util.Vector;


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

    public static boolean deleteCourse(String courseid){
         return new Support<Course>().deleteRow("DELETE FROM Course hl  WHERE hl.courseId = '" + courseid + "'");
    }


    public static void addCourse (Vector<String> input){

    }

}


