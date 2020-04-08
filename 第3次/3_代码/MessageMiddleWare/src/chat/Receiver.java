package chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Input;
	private JTextArea textArea;
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ConnectionFactory connectionFactory;	// ConnectionFactory ：连接工厂，JMS 用它创建连接
	private Connection connection = null;	// Connection ：JMS 客户端到JMS Provider 的连接
	private Session session;	// Session： 一个发送或接收消息的线程
	// private Destination destination;
	private MessageProducer sender;
	private MessageConsumer receiver;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receiver frame = new Receiver();
					frame.setVisible(true);
					frame.init();
					frame.getMsg();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Receiver() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (session != null) {
					try {
						session.close();
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		setTitle("chenshuo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel Msg = new JPanel();
		contentPane.add(Msg, BorderLayout.CENTER);
		Msg.setLayout(new BoxLayout(Msg, BoxLayout.X_AXIS));

		textArea = new JTextArea();
		Msg.add(textArea);

		JPanel Edit = new JPanel();
		contentPane.add(Edit, BorderLayout.SOUTH);
		Edit.setLayout(new BoxLayout(Edit, BoxLayout.X_AXIS));

		Input = new JTextField();
		Input.setHorizontalAlignment(SwingConstants.LEFT);
		Edit.add(Input);
		Input.setColumns(10);

		JButton Send = new JButton("发送");
		Send.addActionListener(new ActionListener() {
			// 点击发送按钮之后的操作
			public void actionPerformed(ActionEvent e) {
				try {
					sendMessage("chenshuo " + df.format(new Date()) + "\n" + Input.getText() + "\n");
				} catch (JMSException e1) {
					e1.printStackTrace();
				}
				textArea.append("censek " + df.format(new Date()) + "\n");
				textArea.append(Input.getText() + "\n");
				Input.setText(""); // 清空输入框

			}
		});
		Send.setHorizontalAlignment(SwingConstants.RIGHT);
		Edit.add(Send);
	}

	// 从消息队列中获取消息
	private void getMsg() {
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Message msg = receiver.receive();
					if (msg instanceof TextMessage) {
						textArea.append(((TextMessage) msg).getText());
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}				
			}
		}, 0, 10);
	}

	private void init() {
		// TextMessage message;
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");	// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// destination = session.createQueue("Sender2Receiver");
			sender = session.createProducer(session.createQueue("Receiver2Sender"));
			receiver = session.createConsumer(session.createQueue("Sender2Receiver"));
			sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String message) throws JMSException {
		TextMessage msg = session.createTextMessage(message);
        sender.send(msg);
        session.commit();
	}

}
