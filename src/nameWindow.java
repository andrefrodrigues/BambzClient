import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Client;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class nameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nameWindow frame = new nameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 * @param newName 
	 */
	JLabel myName;
	Client c;
	public nameWindow(JLabel myName, Client c) {
		this.c = c;
		this.myName = myName;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 434, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(82, 61, 170, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton newNameButton = new JButton("Apply");
		newNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myName.setText(textField.getText());
				c.setName(textField.getText());
			}
		});
		newNameButton.setBounds(262, 60, 89, 23);
		contentPane.add(newNameButton);
		
		JLabel lblSetANew = new JLabel("Set a new Name");
		lblSetANew.setBounds(163, 11, 160, 14);
		contentPane.add(lblSetANew);
		this.setVisible(true);
	}

}
