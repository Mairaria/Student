package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	public static  JFormattedTextField txtStudentId;
	private JTextField txtStudentName;
	static AddStudent frame = new AddStudent();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddStudent();
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
	public AddStudent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 342, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student IdNumber");
		lblNewLabel.setBounds(10, 11, 99, 14);
		contentPane.add(lblNewLabel);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    txtStudentId = new JFormattedTextField(formatter);
		
		txtStudentId.setBounds(142, 8, 107, 20);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setBounds(10, 57, 99, 14);
		contentPane.add(lblNewLabel_1);
		
		txtStudentName = new JTextField();
		txtStudentName.setBounds(142, 54, 107, 20);
		contentPane.add(txtStudentName);
		txtStudentName.setColumns(10);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student.ifempty(txtStudentId, txtStudentName, GUI.table);
			}
		});
		btnAddStudent.setBounds(142, 106, 107, 23);
		contentPane.add(btnAddStudent);
	}
}
