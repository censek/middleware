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
            // ����һ�����õ�����һ��NamingContext���󣬲���SysProp����
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
            System.out.println("��ʼ����");
            System.out.println("���гɹ���");
            System.out.println("**********�ɼ�¼��**************");
            dataserviceRef.insert("chenshuo", "2015012976", 95);
            System.out.println("�ɼ�¼��ɹ���\n");
            System.out.println("**********�ɼ���ѯ**************");
            String searchStuNo = "2015012976";
            float getScore =  dataserviceRef.search(searchStuNo);
            System.out.println("ѧ�� "+searchStuNo+" �ĳɼ�Ϊ��  "+getScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}