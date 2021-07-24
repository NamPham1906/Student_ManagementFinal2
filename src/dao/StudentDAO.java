package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Course;
import pojo.RegisterPeriod;
import pojo.Student;
import utils.HibernateUtil;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import static java.lang.Integer.parseInt;


public class StudentDAO {
    public static List<Student> getAllStudent(){
        List<Student> results =
                new Support<Student>().
                        executeHql("SELECT st FROM Student st");
        return results;
    }
    public static List<Student> findID (String studentid){
        List<Student> results =
                new Support<Student>().
                        executeHql("SELECT st FROM Student st WHERE st.studentId = '" + studentid + "'");
        return results;
    }
    public static List<Student> findUser (String username ){
        List<Student> results =
                new Support<Student>().
                        executeHql("SELECT st FROM Student st WHERE st.username = '" + username + "'");
        return results;
    }
    public static boolean checkPassword (char[] input, Student student){
        return new Support<Student>().stringCompare(input, student.getPasswords());
    }

    public static Vector extractData (){
        List<Student> coursesList = StudentDAO.getAllStudent();
        Vector datatable = new Vector();
        for (Student item:coursesList){
            Vector data = new Vector();
            data.add(item.getUsername());
            data.add(item.getPasswords());
            data.add(item.getStudentId());
            data.add(item.getFullname());
            data.add(item.getClassroom().getClassId());
            data.add(item.getBirthday());
            data.add(item.getGender());
            datatable.add(data);
        }
        return datatable;
    }
    public static  String[] extractAllStudentID (){
        List<String> results =
                new Support<String>().
                        executeHql("SELECT st.studentId FROM Student st");
        return results.toArray(new String[0]);
    }
    public static boolean deleteStudent(String courseid){
        return new Support<Student>().deleteRow("DELETE FROM Student hl  WHERE hl.studentId = '" + courseid + "'");
    }
    public static boolean addStudent (Vector<String> input){
        Student newCourse = new Student();
        if (!findID(input.elementAt(2)).isEmpty()){
            return false;
        }
        newCourse.setUsername(input.elementAt(0));
        newCourse.setPasswords(input.elementAt(1));
        newCourse.setStudentId(input.elementAt(2));
        newCourse.setFullname(input.elementAt(3));
        newCourse.setClassroom(ClassroomDAO.findID(input.elementAt(4)).get(0));
        newCourse.setBirthday(Date.valueOf(input.elementAt(5)));
        newCourse.setGender(input.elementAt(6));
        return new Support<Student>().addRow(newCourse);
    }
    public static boolean editStudent (Vector<String> input, String oldCourseIDVersion){
        boolean result = false;
        if (!input.elementAt(2).equals(oldCourseIDVersion)){
            if (addStudent(input)){
                return deleteStudent(oldCourseIDVersion);
            }else {
                result = false;
            }
        }
        else{
            Transaction transaction = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction = session.beginTransaction();
                Student loadrow = session.load(Student.class, oldCourseIDVersion);
                loadrow.setUsername(input.elementAt(0));
                loadrow.setPasswords(input.elementAt(1));
                loadrow.setFullname(input.elementAt(3));
                loadrow.setClassroom(ClassroomDAO.findID(input.elementAt(4)).get(0));
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
    public static Vector courseResult (String studentid){
        Set<Course> coursesList = findID(studentid).get(0).getRegisteredCourses();
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
    public static boolean deleteCourse(String courseid, String studentId){
        Set<Course> coursesList = findID(studentId).get(0).getRegisteredCourses();
        coursesList.remove(CourseDAO.findID(courseid).get(0));

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            Student loadrow = session.load(Student.class, studentId);
            loadrow.setRegisteredCourses(coursesList);
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
    public static boolean addCourseToStudent (String courseid, String studentId ){
        Set<Course> coursesList = findID(studentId).get(0).getRegisteredCourses();
        coursesList.add(CourseDAO.findID(courseid).get(0));

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            Student loadrow = session.load(Student.class, studentId);
            loadrow.setRegisteredCourses(coursesList);
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
