package server;

/**
* DataServiceOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��DataService.idl
* 2018��4��13�� ������ ����10ʱ14��39�� CST
*/

public interface DataServiceOperations 
{
  void insert (String stuName, String StuNo, float score);
  float search (String stuNo);
} // interface DataServiceOperations
