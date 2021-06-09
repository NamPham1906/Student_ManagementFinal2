package dao;

import pojo.Course;
import java.util.List;


public class CourseDAO {
    public static List<Course> getAllCourse(){
        List<Course> results =
                new Support<Course>().
                        executeHql("SELECT st FROM Course st");
        return results;
    }
}
