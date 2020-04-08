package java_client;

/**
* DataServicePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从DataService.idl
* 2018年4月13日 星期五 上午10时14分39秒 CST
*/

public abstract class DataServicePOA extends org.omg.PortableServer.Servant
 implements DataServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("insert", new java.lang.Integer (0));
    _methods.put ("search", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // DataService/insert
       {
         String stuName = in.read_string ();
         String StuNo = in.read_string ();
         float score = in.read_float ();
         this.insert (stuName, StuNo, score);
         out = $rh.createReply();
         break;
       }

       case 1:  // DataService/search
       {
         String stuNo = in.read_string ();
         float $result = (float)0;
         $result = this.search (stuNo);
         out = $rh.createReply();
         out.write_float ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:DataService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DataService _this() 
  {
    return DataServiceHelper.narrow(
    super._this_object());
  }

  public DataService _this(org.omg.CORBA.ORB orb) 
  {
    return DataServiceHelper.narrow(
    super._this_object(orb));
  }


} // class DataServicePOA
