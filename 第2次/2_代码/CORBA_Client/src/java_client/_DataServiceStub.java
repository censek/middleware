package java_client;

/**
* _DataServiceStub.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从DataService.idl
* 2018年4月13日 星期五 上午10时14分39秒 CST
*/

public class _DataServiceStub extends org.omg.CORBA.portable.ObjectImpl implements DataService
{

  public void insert (String stuName, String StuNo, float score)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("insert", true);
                $out.write_string (stuName);
                $out.write_string (StuNo);
                $out.write_float (score);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                insert (stuName, StuNo, score        );
            } finally {
                _releaseReply ($in);
            }
  } // insert

  public float search (String stuNo)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("search", true);
                $out.write_string (stuNo);
                $in = _invoke ($out);
                float $result = $in.read_float ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return search (stuNo        );
            } finally {
                _releaseReply ($in);
            }
  } // search

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:DataService:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _DataServiceStub
