package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Course;
import pojo.SchoolSubject;
import utils.HibernateUtil;

import java.util.List;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class SchoolSubjectDAO {
        public static List<SchoolSubject> getAllschoolSubject(){
            List<SchoolSubject> results =
                    new Support<SchoolSubject>().
                            executeHql("SELECT st FROM SchoolSubject st");
            return results;
        }
        public static List<SchoolSubject> findID (String schoolSubjectid){
            List<SchoolSubject> results =
                    new Support<SchoolSubject>().
                            executeHql("SELECT st FROM SchoolSubject st WHERE st.subjectId = '" + schoolSubjectid + "'");
            return results;
        }
        public static  String[] extractAllSchoolSubjectID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.subjectId FROM SchoolSubject st");
        return results.toArray(new String[0]);
    }
        public static Vector extractData (){
        List<SchoolSubject> subjectsList = SchoolSubjectDAO.getAllschoolSubject();
        Vector datatable = new Vector();
        for (SchoolSubject item:subjectsList){
            Vector data = new Vector();
            data.add(item.getSubjectId());
            data.add(item.getSubjectname());
            data.add(item.getCredits());
            datatable.add(data);
        }
        return datatable;
    }
        public static boolean deleteSchoolSubject(String schoolsubjectid){
            if (!new Support<Course>().
                    executeHql("SELECT co FROM Course co WHERE co.schoolSubject.subjectId = '" + schoolsubjectid + "'").
                    isEmpty()){
                return false;
            }


        return new Support<SchoolSubject>().
                deleteRow("DELETE FROM SchoolSubject hl  WHERE hl.subjectId = '" + schoolsubjectid + "'");
         }
        public static boolean addSchoolSubject (Vector<String> input){
            SchoolSubject newSchoolSubject = new SchoolSubject();
        if (!findID(input.elementAt(0)).isEmpty()){
            return false;
        }
            newSchoolSubject.setSubjectId(input.elementAt(0));
            newSchoolSubject.setSubjectname(input.elementAt(1));
            newSchoolSubject.setCredits(parseInt(input.elementAt(2)));
        return new Support<SchoolSubject>().addRow(newSchoolSubject);
    }
        public static boolean editSchoolSubject (Vector<String> input, String oldSchoolSubjectIDVersion){
        boolean result = false;
        if (!input.elementAt(0).equals(oldSchoolSubjectIDVersion)){
            if (addSchoolSubject(input)){
                return deleteSchoolSubject(oldSchoolSubjectIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                SchoolSubject loadrow = session.load(SchoolSubject.class, oldSchoolSubjectIDVersion);
                loadrow.setSubjectname(input.elementAt(1));
                loadrow.setCredits(parseInt(input.elementAt(2)));
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
