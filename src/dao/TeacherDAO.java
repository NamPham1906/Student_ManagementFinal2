package dao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Teacher;
import utils.HibernateUtil;

import java.sql.Date;
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

    public static boolean deleteTeacher(String teacherid){
        return new Support<Teacher>().deleteRow("DELETE FROM Teacher hl  WHERE hl.teacherId = '" + teacherid + "'");
    }
    public static boolean addTeacher (Vector<String> input){
        Teacher newTeacher = new Teacher();
        if (!findID(input.elementAt(2)).isEmpty()){
            return false;
        }
        newTeacher.setUsername(input.elementAt(0));
        newTeacher.setPasswords(input.elementAt(1));
        newTeacher.setTeacherId(input.elementAt(2));
        newTeacher.setFullname(input.elementAt(3));
        newTeacher.setOccupation(input.elementAt(4));
        newTeacher.setBirthday(Date.valueOf(input.elementAt(5)));
        newTeacher.setGender(input.elementAt(6));
        return new Support<Teacher>().addRow(newTeacher);
    }
    public static boolean editTeacher (Vector<String> input, String oldteacherIDVersion){
        boolean result = false;
        if (!input.elementAt(2).equals(oldteacherIDVersion)){
            if (addTeacher(input)){
                return deleteTeacher(oldteacherIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                Teacher loadrow = session.load(Teacher.class, oldteacherIDVersion);
                loadrow.setUsername(input.elementAt(0));
                loadrow.setPasswords(input.elementAt(1));
                loadrow.setFullname(input.elementAt(3));
                loadrow.setOccupation(input.elementAt(4));
                loadrow.setBirthday(Date.valueOf(input.elementAt(5)));
                loadrow.setGender(input.elementAt(6));
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
