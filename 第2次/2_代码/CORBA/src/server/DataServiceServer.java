package server;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import server.DBmanager;

//编写相对应的服务，一定要从 _类名ImplBase继承，并实现相应的方法
class DataServiceImpl extends DataServicePOA // 具体的服务实现
{
    private ORB orb;

    public void setOrb(ORB orb_val) {
        this.orb = orb_val;
    }

    @Override
    public void insert(String stuName, String stuNo, float score) {
        // TODO Auto-generated method stub
        DBmanager.insert(stuName, stuNo, score);
    }

    @Override
    public float search(String stuNo) {
        // TODO Auto-generated method stub
        return DBmanager.search(stuNo);
    }
}

public class DataServiceServer// 起动服务的程序
{
    public static void main(String args[]) {
        try {
            System.out.println("创建和初始化 ORB ");
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb
                    .resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            System.out.println("创建服务对象并将其向 ORB 注册 ");
            DataServiceImpl dataServiceImpl = new DataServiceImpl();
            dataServiceImpl.setOrb(orb);
            // System.out.println(orb.object_to_string(sysProImpl));
            org.omg.CORBA.Object objRef = orb
                    .resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            NameComponent[] path = { new NameComponent("DataService", "") };
            org.omg.CORBA.Object ref = rootpoa
                    .servant_to_reference(dataServiceImpl);
            DataService href = DataServiceHelper.narrow(ref);
            System.out.println(orb.object_to_string(href));
            System.out.println(ncRef.getClass().toString());
            ncRef.rebind(path, href);
            System.out.println("DataServiceServer ready and waiting ...");
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}