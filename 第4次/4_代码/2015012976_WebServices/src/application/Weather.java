package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.com.WebXml.WeatherWebServiceSoapProxy;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class Weather extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private WeatherWebServiceSoapProxy proxy;
	private JTextArea result1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Weather frame = new Weather();
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
	public Weather() {
		setTitle("实时天气查询");
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
		
		result1 = new JTextArea("");
//		result1.setHorizontalAlignment(SwingConstants.CENTER);
		panel6.add(result1);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (proxy == null) {
					proxy = new WeatherWebServiceSoapProxy();
				}
				String st="";
				try {
					String[] weatherInfo = proxy.getWeatherbyCityName(textField.getText());
						for (String str : weatherInfo) {
					    	//result1.setText(str);
					    	//System.out.println(str);
							st += str ;
						    st += "  ";
						}
						result1.setText(st);
					//java.lang.String[] result = proxy.getWeatherbyCityName(textField.getText());					
					//result1.setText(proxy.getWeatherbyCityName(textField.getText()).toString());
				} catch (RemoteException e1)
				{
					result1.setText("error：此城市或区域暂时不被支持-）");
				}
			}
		});
				
		
		btnNewButton.setBounds(25, 13, 113, 35);
		panel_5.add(btnNewButton);
		
	}
}
