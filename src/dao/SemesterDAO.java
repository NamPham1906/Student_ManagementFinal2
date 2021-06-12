package dao;

import pojo.Semester;
import pojo.Teacher;

import java.util.List;
import java.util.Vector;


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

    public static  String[] extractAllSemesterID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.semesterId FROM Semester st");
        return results.toArray(new String[0]);
    }

    public static Vector extractData () {
        List<Semester> coursesList = SemesterDAO.getAllSemester();
        Vector datatable = new Vector();
        for (Semester item : coursesList) {
            Vector data = new Vector();
            data.add(item.getSemesterId());
            data.add(item.getSemestername());
            data.add(item.getSchoolyear());
            data.add(item.getStartday());
            data.add(item.getEndday());
            datatable.add(data);
        }
        return datatable;
    }
}
