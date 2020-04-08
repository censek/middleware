package cn.com.WebXml;

public class TrainTimeWebServiceSoapProxy implements cn.com.WebXml.TrainTimeWebServiceSoap {
  private String _endpoint = null;
  private cn.com.WebXml.TrainTimeWebServiceSoap trainTimeWebServiceSoap = null;
  
  public TrainTimeWebServiceSoapProxy() {
    _initTrainTimeWebServiceSoapProxy();
  }
  
  public TrainTimeWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initTrainTimeWebServiceSoapProxy();
  }
  
  private void _initTrainTimeWebServiceSoapProxy() {
    try {
      trainTimeWebServiceSoap = (new cn.com.WebXml.TrainTimeWebServiceLocator()).getTrainTimeWebServiceSoap();
      if (trainTimeWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)trainTimeWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)trainTimeWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (trainTimeWebServiceSoap != null)
      ((javax.xml.rpc.Stub)trainTimeWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cn.com.WebXml.TrainTimeWebServiceSoap getTrainTimeWebServiceSoap() {
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap;
  }
  
  public cn.com.WebXml.GetStationNameDataSetResponseGetStationNameDataSetResult getStationNameDataSet() throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getStationNameDataSet();
  }
  
  public java.lang.String[] getStationAndTimeByTrainCode(java.lang.String trainCode, java.lang.String userID) throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getStationAndTimeByTrainCode(trainCode, userID);
  }
  
  public java.lang.String getVersionTime() throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getVersionTime();
  }
  
  public cn.com.WebXml.GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult getStationAndTimeDataSetByTrainCode(java.lang.String trainCode, java.lang.String userID) throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getStationAndTimeDataSetByTrainCode(trainCode, userID);
  }
  
  public cn.com.WebXml.GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult getStationAndTimeDataSetByLikeTrainCode(java.lang.String trainCode, java.lang.String userID) throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getStationAndTimeDataSetByLikeTrainCode(trainCode, userID);
  }
  
  public cn.com.WebXml.GetDetailInfoByTrainCodeResponseGetDetailInfoByTrainCodeResult getDetailInfoByTrainCode(java.lang.String trainCode, java.lang.String userID) throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getDetailInfoByTrainCode(trainCode, userID);
  }
  
  public java.lang.String[] getStationName() throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getStationName();
  }
  
  public cn.com.WebXml.GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult getStationAndTimeByStationName(java.lang.String startStation, java.lang.String arriveStation, java.lang.String userID) throws java.rmi.RemoteException{
    if (trainTimeWebServiceSoap == null)
      _initTrainTimeWebServiceSoapProxy();
    return trainTimeWebServiceSoap.getStationAndTimeByStationName(startStation, arriveStation, userID);
  }
  
  
}