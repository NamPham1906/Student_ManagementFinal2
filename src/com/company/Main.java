package com.company;
import dao.CourseDAO;
import pojo.Course;
import pojo.Student;
import java.util.List;
import gui.*;
public class Main {
    public static void main(final String[] args)  {
        List<Course> rs = CourseDAO.getAllCourse();
        for (Course item:rs){
          System.out.println(item.getStudents().isEmpty());
        }
        new Frame().MainFrame();
    }



}