package com.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.DB.DBManager.Student;

public interface DataService extends Remote{
    public void createTable() throws RemoteException;
    public void insert(Student stu)throws RemoteException;
    public Student getStu(String stuNo)throws RemoteException;

}