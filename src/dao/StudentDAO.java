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

    public static List<Student> findID (String studentid){
        List<Student> results =
                new Support<Student>().
                        executeHql("SELECT st FROM Student st WHERE st.studentId = '" + studentid + "'");
        return results;
    }

    public static List<Student> findUser (String username ){
        List<Student> results =
                new Support<Student>().
                        executeHql("SELECT st FROM Student st WHERE st.username = '" + username + "'");
        return results;
    }

    public static boolean checkPassword (char[] input, Student student){
        return new Support<Student>().stringCompare(input, student.getPasswords());
    }

}
