package com.DB;

import java.io.Serializable;
import java.sql.*;

public class DBManager {
    private static final String userName = "root";
    private static final String password = "";
    private static final String dburl="jdbc:mysql://localhost:3306/rmi_grade?useUnicode=true&characterEncoding=utf8";
    private static final String driver="com.mysql.jdbc.Driver";
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static Statement stmt = null;

    public static Connection getConn(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl,userName,password);
        }catch(ClassNotFoundException e){
            System.out.println("没有找到数据库驱动程序");
        }catch(SQLException e){
            System.out.println("数据库连接时出现异常，可能由于数据库服务未启动造成，请先启动数据库服务");
        }
        return conn;
}
//  新建表stu,若表已经存在，则删除在新建
    public static void createTable(){
    	String checkTable="show tables like \"stu\""; 
        String sql = "create table stu(stuNo varchar(10),stuName varchar(10),grade int,PRIMARY KEY (stuNo));";
        conn = getConn();
        try{
            stmt = conn.createStatement();
            ResultSet resultSet=stmt.executeQuery(checkTable);  
            if (resultSet.next()) {  
            	stmt.execute("drop table stu");
//               System.out.println("table exist!"); 
               stmt.executeUpdate(sql);
           }else{  
               if(stmt.executeUpdate(sql)==0)  
            	   System.out.println("create table stu success!");  
           } 
//            stmt.execute(sql);
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

    public static void insert(Student stu){
        String sql = "insert into stu values('"+stu.getStuNo()+"','"+stu.getStuName()+"',"+stu.getGrade()+")";
        conn = getConn();
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

    public static Student getStu(String stuNo){
        String sql = "select * from stu where stuNo = "+stuNo;
        conn = getConn();
        Student stu = new Student();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                stu.setStuNo(rs.getString(1));
                stu.setStuName(rs.getString(2));
                stu.setGrade(rs.getInt(3));
            }else{stu=null;}
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return stu;
}

    @SuppressWarnings("serial")
	public static class Student implements Serializable{
        private String stuNo="";
        private String stuName="";
        private int grade=0;
        public Student(){}
        public String getStuNo() {
            return stuNo;
        }
        public void setStuNo(String stuNo) {
            this.stuNo = stuNo;
        }
        public String getStuName() {
            return stuName;
        }
        public void setStuName(String stuName) {
            this.stuName = stuName;
        }
        public int getGrade() {
            return grade;
        }
        public void setGrade(int grade) {
            this.grade = grade;
        }
    }
}