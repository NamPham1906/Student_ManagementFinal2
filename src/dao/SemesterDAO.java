package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Semester;
import utils.HibernateUtil;

import java.sql.Date;
import java.util.List;
import java.util.Vector;
import static java.lang.Integer.parseInt;

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
    public static boolean deleteSemester (String semeseterid){
        return new Support<Semester>().deleteRow("DELETE FROM Semester hl  WHERE hl.semesterId = '" + semeseterid + "'");
    }
    public static boolean addSemester (Vector<String> input){
        Semester newSemester = new Semester();
        if (!findID(input.elementAt(0)).isEmpty()){
            return false;
        }
        newSemester.setSemesterId(input.elementAt(0));
        newSemester.setSemestername(input.elementAt(1));
        newSemester.setSchoolyear(parseInt(input.elementAt(2)));
        newSemester.setStartday(Date.valueOf(input.elementAt(3)));
        newSemester.setEndday(Date.valueOf(input.elementAt(4)));
        return new Support<Semester>().addRow(newSemester);
    }
    public static boolean editSemester (Vector<String> input, String oldSemesterIDVersion){
        boolean result = false;
        if (!input.elementAt(0).equals(oldSemesterIDVersion)){
            if (addSemester(input)){
                return deleteSemester(oldSemesterIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                Semester loadrow = session.load(Semester.class, oldSemesterIDVersion);
                loadrow.setSemestername(input.elementAt(1));
                loadrow.setSchoolyear(parseInt(input.elementAt(2)));
                loadrow.setStartday(Date.valueOf(input.elementAt(3)));
                loadrow.setEndday(Date.valueOf(input.elementAt(4)));
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
