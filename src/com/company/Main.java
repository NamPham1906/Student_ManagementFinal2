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


        List<Student> rs23 = StudentDAO.findID("19120599");
        for (Student item:rs23){
            System.out.println(item.getFullname());
        }
    }



}