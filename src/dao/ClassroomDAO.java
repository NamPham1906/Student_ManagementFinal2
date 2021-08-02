package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Classroom;
import utils.HibernateUtil;

import java.util.List;
import java.util.Vector;

import static java.lang.Integer.parseInt;

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
    public static boolean deleteClassroom(String classroomid){
        return new Support<Classroom>().deleteRow("DELETE FROM Classroom hl  WHERE hl.classId = '" + classroomid + "'");
    }
    public static boolean addClassroom(Vector<String> input){
        Classroom newClassroom = new  Classroom();
        if (!findID(input.elementAt(0)).isEmpty()){
            return false;
        }
        newClassroom.setClassId(input.elementAt(0));
        newClassroom.setNumberofstudents(parseInt(input.elementAt(1)));
        newClassroom.setNumberofmales(parseInt(input.elementAt(2)));
        newClassroom.setNumberoffemales(parseInt(input.elementAt(3)));
        return new Support<Classroom>().addRow(newClassroom);
    }
    public static boolean editClassroom (Vector<String> input, String oldClassroomIDVersion){
        boolean result = false;
        if (!input.elementAt(0).equals(oldClassroomIDVersion)){
            if (addClassroom(input)){
                return deleteClassroom(oldClassroomIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                Classroom loadrow = session.load(Classroom.class, oldClassroomIDVersion);
                loadrow.setNumberofstudents(parseInt(input.elementAt(1)));
                loadrow.setNumberofmales(parseInt(input.elementAt(2)));
                loadrow.setNumberoffemales(parseInt(input.elementAt(3)));
                session.update(loadrow);
                transaction.commit();
                result = true;
            }catch (HibernateException ex){
                transaction.rollback();
                System.err.print(ex);
                result = false;
            }finally{
                session.close();
            }
        }
        return result;
    }
}
