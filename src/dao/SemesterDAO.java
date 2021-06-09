package dao;

import pojo.Semester;
import java.util.List;


public class SemesterDAO {
    public static List<Semester> getAllSemester(){
        List<Semester> results =
                new Support<Semester>().
                        executeHql("SELECT st FROM Semester st");
        return results;
    }
}
