package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.com.WebXml.GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult;
import cn.com.WebXml.GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult;
import cn.com.WebXml.TrainTimeWebServiceSoapProxy;


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

public class TrainTime extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private TrainTimeWebServiceSoapProxy proxy;
	private JTextArea result1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainTime frame = new TrainTime();
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
	public TrainTime() {
		setTitle("中国铁路列车时刻查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel autherInfo = new JLabel("2015012976 陈硕 ©");
		
		panel.add(autherInfo);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 262, 61);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 13, 248, 35);
		panel_4.add(textField);
		textField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(259, 0, 163, 61);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel6 = new JPanel();
		panel_1.add(panel6);
		panel6.setLayout(new BorderLayout(0, 0));
		
		JTextArea text = new JTextArea();
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		panel6.add(scrollPane);
//		scrollPane.add(text);
//		setVisible(true);
		//scrollPane.setBorder(null);		
		
		//result1 = new JLabel("");
		result1 = text;
		//result1.setHorizontalAlignment(SwingConstants.CENTER);
		panel6.add(result1);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (proxy == null) {
					proxy = new TrainTimeWebServiceSoapProxy();
				}
				
				try {
					GetStationAndTimeDataSetByTrainCodeResponseGetStationAndTimeDataSetByTrainCodeResult result = proxy.getStationAndTimeDataSetByTrainCode(textField.getText(),"");
					//GetStationAndTimeDataSetByLikeTrainCodeResponseGetStationAndTimeDataSetByLikeTrainCodeResult result = proxy.getStationAndTimeDataSetByLikeTrainCode(textField.getText(),"");
					MessageElement elements[] = result.get_any();
					NodeList list = elements[1].getElementsByTagName("TimeTable");  

					for(int i = 0; i < list.getLength(); i++)
					{
						Node node = list.item(i);
						NodeList children = node.getChildNodes();
						String str ="";
						for(int j = 0; j < children.getLength(); j++)
						{
						    System.out.println(children.item(j).getFirstChild());
						    str += children.item(j).getFirstChild();
						    str += " - ";
						    //result1.setText(children.item(j).getFirstChild().toString());
						}
						//System.out.println();
						result1.setText(str);
					}
				} catch (RemoteException e1)
				{
					result1.setText("error：此列车不存在-）");
				}
			}
		});
				
		
		btnNewButton.setBounds(25, 13, 113, 35);
		panel_5.add(btnNewButton);
		
	}
}
