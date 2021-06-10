package com.company;
import dao.*;
import pojo.*;
import java.util.List;
//import SMUI.*;
public class Main {
    public static void main(final String[] args)  {


        List<Registerperiod> rs2 = RegisterperiodDAO.getAllRegisterperiod();
        for (Registerperiod item:rs2){
            System.out.println(item.getRegisterperiodId());
        }


        List<Course> rs23 = CourseDAO.getAllCourse();
        for (Course item:rs23){
            System.out.println(item.getCourseId() + "\n");
            System.out.println(item.getSemester().getSemestername()+ "\n");
            System.out.println(item.getSchoolSubject().getSubjectId() + "\n");
            System.out.println(item.getSchoolSubject().getSubjectname() + "\n");
            System.out.println(item.getSchoolSubject().getCredits()+ "\n");
            System.out.println(item.getTeacher().getFullname() + "\n");
            System.out.println(item.getRoomnum()+ "\n");
            System.out.println(item.getWeekday() + "\n");
            System.out.println(item.getShift()+ "\n");
        }
    }



}