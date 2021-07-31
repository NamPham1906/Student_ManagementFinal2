package dao;

import pojo.Classroom;
import pojo.Semester;

import java.util.List;
import java.util.Vector;


public class ClassroomDAO {
    public static List<Classroom> getAllClassroom(){
        List<Classroom> results =
                new Support<Classroom>().
                        executeHql("SELECT st FROM Classroom st");
        return results;
    }
    public static List<Classroom> findID (String classroomid){
        List<Classroom> results =
                new Support<Classroom>().
                        executeHql("SELECT st FROM Classroom st WHERE st.classId = '" + classroomid + "'");
        return results;
    }
    public static  String[] extractAllClassID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.classId FROM Classroom st");
        return results.toArray(new String[0]);
    }
    public static Vector extractData () {
        List<Classroom> coursesList = ClassroomDAO.getAllClassroom();
        Vector datatable = new Vector();
        for (Classroom item : coursesList) {
            Vector data = new Vector();
            data.add(item.getClassId());
            data.add(item.getNumberofstudents());
            data.add(item.getNumberofmales());
            data.add(item.getNumberoffemales());
            datatable.add(data);
        }
        return datatable;
    }
}
