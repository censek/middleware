package com.Server;

import java.rmi.server.*;
import java.rmi.*;
import com.DB.DBManager.Student;
import com.DB.DBManager;

public class DataServiceImpl extends UnicastRemoteObject implements DataService{
    private static final long serialVersionUID = -7777277221L;
    public DataServiceImpl() throws RemoteException{
        super();
    }
    @Override
    public void createTable() throws RemoteException{
        DBManager.createTable();
    }
    @Override
    public Student getStu(String stuNo) throws RemoteException{
        return DBManager.getStu(stuNo);
    }
    @Override
    public void insert(Student stu) throws RemoteException{
        DBManager.insert(stu);
    }
}
