package com.Client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.Server.DataService;
import com.DB.DBManager.Student;

public class StudentWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -2747899957949508172L;
	JTextField t1, t2, t3;
	JButton b1, b2, b3;
	Box box1, box2, box3, box4, basebox;
	JLabel label;

	public StudentWindow() {
		setTitle("简单的成绩查询系统");
		setBounds(100, 100, 300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	public void init() {
		setLayout(new FlowLayout());
		box1 = Box.createHorizontalBox();
		t1 = new JTextField(10);
		box1.add(new JLabel("学号："));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(t1);

		box2 = Box.createHorizontalBox();
		b1 = new JButton("查询");
		b2 = new JButton("清空");
		b3 = new JButton("添加");

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		box2.add(b1);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(new JLabel(""));
		box2.add(b2);
		box2.add(new JLabel(""));
		box2.add(b3);
		box3 = Box.createHorizontalBox();
		t2 = new JTextField(10);
		box3.add(new JLabel("姓名："));
		box3.add(Box.createHorizontalStrut(10));
		box3.add(t2);

		box4 = Box.createHorizontalBox();
		t3 = new JTextField(10);
		box4.add(new JLabel("成绩："));
		box4.add(Box.createHorizontalStrut(10));
		box4.add(t3);

		t1.setEditable(true);
		t2.setEditable(true);
		t3.setEditable(true);

		basebox = Box.createVerticalBox();
		basebox.add(box1);
		basebox.add(Box.createVerticalStrut(10));
		basebox.add(box2);
		basebox.add(Box.createVerticalStrut(10));
		basebox.add(box3);
		basebox.add(Box.createVerticalStrut(10));
		basebox.add(box4);
		basebox.add(Box.createVerticalStrut(10));
		add(basebox);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) { // ① 根据学号，可以查询到学生的姓名和年龄；
			try {
		    	DataService ds = (DataService)Naming.lookup("//localhost:1111/ds");//更换为服务器ip地址，即可实现两台电脑之间的访问；
//		        ds.createTable();              
				Student stu2 = ds.getStu(t1.getText());
				if(stu2==null){
					JOptionPane.showMessageDialog(null,"对不起！该学生不存在！！！","警告！",JOptionPane.ERROR_MESSAGE);
				}else{
	            System.out.println("学号： "+stu2.getStuNo());
	            System.out.println("姓名： "+stu2.getStuName());
	            System.out.println("成绩： "+stu2.getGrade());
	            t2.setText(stu2.getStuName());
				t3.setText(String.valueOf(stu2.getGrade()));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == b2) { // 清空文本框。
			t1.setText("");
			t2.setText("");
			t3.setText("");
		} else if (e.getSource() == b3) { // ② 给定学生的学号、姓名、年龄，在表中追加一行信息；
			try {
				DataService ds = (DataService)Naming.lookup("//localhost:1111/ds");//更换为服务器ip地址，即可实现两台电脑之间的访问；
//		        ds.createTable(); 
				Student stu = new Student();
	            stu.setStuNo(t1.getText());
	            stu.setStuName(t2.getText());
	            if(t3.getText()==""){
	            	stu.setGrade(0);
	            }else{
	            	stu.setGrade(Integer.parseInt(t3.getText()));
	            }
	            Student stu2 = ds.getStu(t1.getText());
	            if(stu2==null){
	            	ds.insert(stu);
	            	JOptionPane.showMessageDialog(null, "插入成功！");
	            }else{
	            	JOptionPane.showMessageDialog(null,"对不起！该学生已经存在！！！","警告！",JOptionPane.ERROR_MESSAGE);	 
	            }
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		} 
	}

	private int parseInt(String text) {
		// TODO Auto-generated method stub
		return 0;
	}
}