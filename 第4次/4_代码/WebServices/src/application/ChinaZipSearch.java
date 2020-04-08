package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.com.WebXml.ChinaZipSearchWebServiceSoapProxy;
import cn.com.WebXml.ChinaZipSearchWebServiceSoap;
import cn.com.WebXml.GetAddressByZipCodeResponseGetAddressByZipCodeResult;
import cn.com.WebXml.GetZipCodeByAddressResponseGetZipCodeByAddressResult;

import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class ChinaZipSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private ChinaZipSearchWebServiceSoapProxy proxy;
	private JTextArea result1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChinaZipSearch frame = new ChinaZipSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChinaZipSearch() {
		setTitle("中国邮政编码查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("省份/直辖市(province)");
		//lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(25, 27, 153, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(182, 24, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblcity = new JLabel("城市/地区(city)");
		//lblcity.setFont(new Font("宋体", Font.PLAIN, 14));
		lblcity.setBounds(25, 52, 153, 15);
		contentPane.add(lblcity);
		
		textField1 = new JTextField();
		textField1.setBounds(182, 49, 116, 21);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("街道/乡镇(address)");
		//lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(25, 77, 126, 15);
		contentPane.add(lblNewLabel_1);
		
		textField2 = new JTextField();
		textField2.setBounds(182, 77, 116, 21);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if (proxy == null) {
						proxy = new ChinaZipSearchWebServiceSoapProxy();
					}
					
					try {
						//GetAddressByZipCodeResponseGetAddressByZipCodeResult result = proxy.getAddressByZipCode("712100", "");
						GetZipCodeByAddressResponseGetZipCodeByAddressResult result = proxy.getZipCodeByAddress(textField.getText(), textField1.getText(), textField2.getText(), "");
						
						/*取得存放着返回结果的xml文档，用数组存放，数组元素对应着XML根目录下子元素*/
						MessageElement elements[] = result.get_any();
						/*System.out.println(elements[1]);  //可看到XML根目下子元素1对应的xml片段*/
						
						/*取回所有名为'ZipInfo'的子元素*/
						NodeList list = elements[1].getElementsByTagName("ZipInfo");  
						String str ="";
						/*遍历名为'ZipInfo'的子元素*/
						for(int i = 0; i < list.getLength(); i++)
						{
							Node node = list.item(i);
							NodeList children = node.getChildNodes();
							
							/*取出名为'ZipInfo'的子元素下的省、城市、地址、邮编等值*/
							for(int j = 0; j < children.getLength(); j++)
							{
								System.out.println(children.item(j).getFirstChild());
							    result1.setText(children.item(j).getFirstChild().toString());
								//str += children.item(j).getFirstChild();
							    //str += "\n";
							}
							//System.out.println();
							//result1.setText(str);
						}
						//result.setText(proxy.getSupportCity(textField.getText())[1]);
						//result.setText(proxy.getZipCodeByAddress(textField.getText(), textField1.getText(), "安定路", null).toString());
						//System.out.println(textField.getText());
					} catch (RemoteException e1)
	                 {
						result1.setText("error：此地查无邮政编码-）");
					}
				}
			});		
		//button.setFont(new Font("隶书", Font.PLAIN, 20));		
		button.setBounds(318, 36, 93, 44);
		contentPane.add(button);
	
		JPanel textPane = new JPanel();
		textPane.setBounds(25, 113, 379, 120);
		contentPane.add(textPane);
		
		result1 = new JTextArea();
		textPane.add(result1);
		
		JLabel autherInfo = new JLabel("2015012976 陈硕 ©");
		autherInfo.setBounds(89, 240, 258, 15);
		autherInfo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(autherInfo);		
	}
}
