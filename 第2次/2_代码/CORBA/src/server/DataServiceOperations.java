package server;

/**
* DataServiceOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从DataService.idl
* 2018年4月13日 星期五 上午10时14分39秒 CST
*/

public interface DataServiceOperations 
{
  void insert (String stuName, String StuNo, float score);
  float search (String stuNo);
} // interface DataServiceOperations
