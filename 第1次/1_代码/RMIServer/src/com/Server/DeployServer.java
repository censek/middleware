package com.Server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class DeployServer{
    public DeployServer(){
}

    public static void main(String[] args) {
        try{
            DataService ds = new DataServiceImpl();
            LocateRegistry.createRegistry(1111);
            Naming.rebind("//localhost:1111/ds",ds);
            System.out.println("RMI服务器正在运行...");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}