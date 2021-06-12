package dao;
import pojo.Student;
import pojo.Teacher;
import java.util.List;
import java.util.Vector;

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
    public static Vector extractData (){
        List<Teacher> coursesList = TeacherDAO.getAllTeacher();
        Vector datatable = new Vector();
        for (Teacher item:coursesList){
            Vector data = new Vector();
            data.add(item.getUsername());
            data.add(item.getPasswords());
            data.add(item.getTeacherId());
            data.add(item.getFullname());
            data.add(item.getOccupation());
            data.add(item.getBirthday());
            data.add(item.getGender());
            datatable.add(data);
        }
        return datatable;
    }
}
