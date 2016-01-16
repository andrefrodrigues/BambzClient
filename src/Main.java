import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import client.Client;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

/* current version: beta*/
public class Main {

	private static final String DEFAULT_NAME = "User";
	private static final String ADDRESS ="localhost";
	private static final int PORT=3125;
	
	private static final String TITLE ="Bambz IM 0.01";
	private JFrame frame;
	private JTextField messageField;
	private JLabel connectionStatus;
	private static JLabel personName;
	private static JLabel myName;
	private JEditorPane messagePanel;
	private static Client c;

/* thread that manages message recieving from client */
	Thread messages = new Thread(()->{
		try{
			//first message is the name of the other person
			String message = c.getMessage();
			personName.setText(message);
		while(true){
			message =personName.getText()+":\n"+ c.getMessage()+"\n";
			messagePanel.setText(messagePanel.getText()+message);
		}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	});

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 666, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnNewMenu = new JMenu("Connect");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmServer = new JMenuItem("Server");
		mntmServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.connectServer(ADDRESS, PORT);
					connectionStatus.setText("Connected");
					
				} catch (UnknownHostException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmServer);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmSetName = new JMenuItem("Set Name");
		mntmSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName="";
				nameWindow n = new nameWindow(myName,c);
			}
		});
		mnOptions.add(mntmSetName);
		frame.getContentPane().setLayout(null);
		
		messageField = new JTextField();
		messageField.setBounds(10, 304, 531, 33);
		frame.getContentPane().add(messageField);
		messageField.setColumns(10);
		
		JButton sendMessageBtn = new JButton("Send");
		sendMessageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					c.sendMessage(messageField.getText());
					messagePanel.setText(c.getName()+":\n"+messageField.getText());
					messageField.setText("");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		sendMessageBtn.setBounds(551, 309, 89, 23);
		frame.getContentPane().add(sendMessageBtn);
		messagePanel = new JEditorPane();
		messagePanel.setEditable(false);
		messagePanel.setBounds(10, 11, 531, 282);
		frame.getContentPane().add(messagePanel);
		
		myName = new JLabel("myName");
		myName.setBounds(551, 32, 89, 14);
		frame.getContentPane().add(myName);
		
		personName = new JLabel("personName");
		personName.setBounds(551, 57, 89, 14);
		frame.getContentPane().add(personName);
		
		JLabel statusMsg = new JLabel("Status:");
		statusMsg.setBounds(10, 341, 89, 14);
		frame.getContentPane().add(statusMsg);
		
		connectionStatus = new JLabel("Not Connected");
		connectionStatus.setBounds(51, 341, 89, 14);
		frame.getContentPane().add(connectionStatus);
		//client initialization
		startClient();
		
	}
	
	public static void startClient(){
		c= new Client(DEFAULT_NAME);
		myName.setText(DEFAULT_NAME);
		personName.setText("");
	}
}
