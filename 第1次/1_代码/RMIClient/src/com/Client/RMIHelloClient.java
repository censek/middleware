package com.Client;

import java.rmi.Naming;
import com.Server.DataService;

public class RMIHelloClient{
    public static void main(String[] args) {
    	  try{
          	 DataService ds = (DataService)Naming.lookup("//localhost:1111/ds");//更换为服务器ip地址，即可实现两台电脑之间的访问；
             ds.createTable();
             new StudentWindow();
          }catch(Exception e){
              e.printStackTrace();
          }   
    }
}
