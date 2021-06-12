package dao;
import pojo.Teacher;
import java.util.List;

public class TeacherDAO {
    public static List<Teacher> getAllTeacher(){
        List<Teacher> results =
                new Support<Teacher>().
                        executeHql("SELECT st FROM Teacher st");
        return results;
    }

    public static List<Teacher> findID (String teacherid){
        List<Teacher> results =
                new Support<Teacher>().
                        executeHql("SELECT st FROM Teacher st WHERE st.teacherId = '" + teacherid + "'");
        return results;
    }

    public static List<Teacher> findUser (String username ){
        List<Teacher> results =
                new Support<Teacher>().
                        executeHql("SELECT st FROM Teacher st WHERE st.username = '" + username + "'");
        return results;
    }

    public static boolean checkPassword (char[] input, Teacher teacher){
        return new Support<Teacher>().stringCompare(input, teacher.getPasswords());
    }

    public static  String[] extractAllTeacherID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.teacherId FROM Teacher st");
        return results.toArray(new String[0]);
    }
}
