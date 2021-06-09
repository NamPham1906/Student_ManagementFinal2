package dao;

import pojo.Classroom;
import java.util.List;


public class ClassroomDAO {
    public static List<Classroom> getAllClassroom(){
        List<Classroom> results =
                new Support<Classroom>().
                        executeHql("SELECT st FROM Classroom st");
        return results;
    }


    public static List<Classroom> getAClassroom(String classroomid){
        List<Classroom> results =
                new Support<Classroom>().
                        executeHql("SELECT st FROM Classroom st");
        return results;
    }
}
