package dao;

import pojo.Course;
import pojo.Semester;
import java.util.List;


public class SemesterDAO {
    public static List<Semester> getAllSemester(){
        List<Semester> results =
                new Support<Semester>().
                        executeHql("SELECT st FROM Semester st");
        return results;
    }

    public static List<Semester> findID (String semesterid){
        List<Semester> results =
                new Support<Semester>().
                        executeHql("SELECT st FROM Semester st WHERE st.semesterId = '" + semesterid + "'");
        return results;
    }
}
