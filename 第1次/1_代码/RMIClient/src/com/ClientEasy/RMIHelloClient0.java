package com.ClientEasy;

import java.rmi.*;
import com.DB.DBManager.Student;
import com.Server.DataService;

public class RMIHelloClient0 {
    public static void main(String[] args) {
        try{
        	DataService ds = (DataService)Naming.lookup("//localhost:1111/ds");//更换为服务器ip地址，即可实现两台电脑之间的访问；
            ds.createTable();
            Student stu = new Student();
            stu.setStuNo("2015012976");
            stu.setStuName("陈硕");
            stu.setGrade(95);
            ds.insert(stu);
            Student stu2 = ds.getStu("2015012976");
            System.out.println("学号： "+stu2.getStuNo());
            System.out.println("姓名： "+stu2.getStuName());
            System.out.println("成绩： "+stu2.getGrade());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}