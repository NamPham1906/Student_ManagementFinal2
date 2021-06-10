package dao;

import pojo.Classroom;
import pojo.Course;
import java.util.List;


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
}
