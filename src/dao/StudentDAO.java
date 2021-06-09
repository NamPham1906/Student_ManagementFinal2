package dao;

import pojo.Student;
import java.util.List;


public class StudentDAO {
    public static List<Student> getAllStudent(){
        List<Student> results =
                new Support<Student>().
                        executeHql("SELECT st FROM Student st");
        return results;
    }
}
