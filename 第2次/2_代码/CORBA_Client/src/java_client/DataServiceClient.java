package java_client;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class DataServiceClient {
    /**
     * @param args
     */
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            String SetInfo, ReturnInfo, ref;
            float score = 0;
            org.omg.CORBA.Object objRef;
            DataService dataserviceRef;
            ORB orb = ORB.init(args, null);
            /*
             * if (args.length >= 1) { ref = args[0]; //System.out.println(ref);
             * } else { System.out.println("aaaaaaaaaaaaaaaaaa"); return ; }
             */
            // 下面一条语句得到的是一个NamingContext对象，并非SysProp对象
            // objRef = orb.string_to_object(ref);
            // dataserviceRef = DataServiceHelper.narrow(objRef);
            objRef = orb.resolve_initial_references("NameService");
            System.out.println(orb.object_to_string(objRef));
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
            NameComponent nc = new NameComponent("DataService", "");
            NameComponent path[] = { nc };
            dataserviceRef = DataServiceHelper.narrow(ncRef.resolve(path));
            if (args.length > 1) {
                SetInfo = args[1];
            } else {
                SetInfo = "0";
            }
            System.out.println("开始调用");
            System.out.println("运行成功！");
            System.out.println("**********成绩录入**************");
            dataserviceRef.insert("chenshuo", "2015012976", 95);
            System.out.println("成绩录入成功！\n");
            System.out.println("**********成绩查询**************");
            String searchStuNo = "2015012976";
            float getScore =  dataserviceRef.search(searchStuNo);
            System.out.println("学号 "+searchStuNo+" 的成绩为：  "+getScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}