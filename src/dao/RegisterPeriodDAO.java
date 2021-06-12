package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Course;
import pojo.RegisterPeriod;
import utils.HibernateUtil;

import javax.print.attribute.standard.DateTimeAtProcessing;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class RegisterPeriodDAO {
    public static List<RegisterPeriod> getAllRegisterPeriod(){
        List<RegisterPeriod> results =
                new Support<RegisterPeriod>().
                        executeHql("SELECT st FROM RegisterPeriod st");
        return results;
    }
    public static List<RegisterPeriod> findID (String registerPeriodId){
        List<RegisterPeriod> results =
                new Support<RegisterPeriod>().
                        executeHql("SELECT st FROM RegisterPeriod st WHERE st.registerperiodId = '" + registerPeriodId + "'");
        return results;
    }
    public static  String[] extractAllRegisterPeriodID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.registerPeriodId FROM RegisterPeriod st");
        return results.toArray(new String[0]);
    }
    public static Vector extractData (){
        List<RegisterPeriod> registerperiodsList = RegisterPeriodDAO.getAllRegisterPeriod();
        Vector datatable = new Vector();
        for (RegisterPeriod item: registerperiodsList){
            Vector data = new Vector();
            data.add(item.getRegisterperiodId());
            data.add(item.getStartday().toString());
            data.add(item.getEndday().toString());
            datatable.add(data);
        }
        return datatable;
    }
    public static boolean deleteRegisterPeriod(String Registerperiodid){
        return new Support<RegisterPeriod>().
                deleteRow("DELETE FROM RegisterPeriod hl  WHERE hl.registerperiodId = '" + Registerperiodid + "'");
    }
    public static boolean addRegisterPeriod (Vector<String> input){
        RegisterPeriod newRegisterPeriod = new RegisterPeriod();
        if (!findID(input.elementAt(0).toString()).isEmpty()){
            return false;
        }
        newRegisterPeriod.setRegisterperiodId(input.elementAt(0));
        newRegisterPeriod.setEndday(Date.valueOf(input.elementAt(1)));
        newRegisterPeriod.setStartday(Date.valueOf(input.elementAt(2)));
        return new Support<RegisterPeriod>().addRow(newRegisterPeriod);
    }
    public static boolean editRegisterPeriod (Vector<String> input, String oldRegisterPeriodIDVersion){
        boolean result = false;
        if (!input.elementAt(0).equals(oldRegisterPeriodIDVersion)){
            if (addRegisterPeriod(input)){
                return deleteRegisterPeriod(oldRegisterPeriodIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                RegisterPeriod loadrow = session.load(RegisterPeriod.class, oldRegisterPeriodIDVersion);
                loadrow.setStartday(Date.valueOf(input.elementAt(1)));
                loadrow.setEndday(Date.valueOf(input.elementAt(2)));
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


    public static Vector courseToRegister (String registerPeriodId){
        Set<Course> coursesList = findID(registerPeriodId).get(0).getCourses();
        Vector datatable = new Vector();
        for (Course item:coursesList){
            Vector data = new Vector();
            data.add(item.getCourseId());
            data.add(item.getSemester().getSchoolyear());
            data.add(item.getSemester().getSemestername());
            data.add(item.getSchoolSubject().getSubjectId());
            data.add(item.getSchoolSubject().getSubjectname());
            data.add(item.getSchoolSubject().getCredits());
            data.add(item.getTeacher().getFullname());
            data.add(item.getRoomnum());
            data.add(item.getWeekday());
            data.add(item.getShift());
            datatable.add(data);
        }
        return datatable;
    }


    public static String availableRegisterPeriod (){
        List<RegisterPeriod> RegisterPeriodList = getAllRegisterPeriod();
        for (RegisterPeriod item: RegisterPeriodList){
            Date now = Date.valueOf(LocalDate.now());
            int start = item.getStartday().compareTo(now);
            int end = item.getEndday().compareTo(now);
            if ((start <=0)  && (end >=0)){
                return item.getRegisterperiodId();
            }
        }
        return null;
    }

    public static boolean addCourseToPeriod (String courseid, String registerPeriodId ){
        Set<Course> coursesList = findID(registerPeriodId).get(0).getCourses();
        coursesList.add(CourseDAO.findID(courseid).get(0));

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            RegisterPeriod loadrow = session.load(RegisterPeriod.class, registerPeriodId);
            loadrow.setCourses(coursesList);
            session.update(loadrow);
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            System.err.print(ex);
            session.close();
            return false;
        }finally{
            session.close();
        }
        return true;
    }

}
