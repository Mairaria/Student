package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	public static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		initialize();
		
	}
	void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		JScrollPane tblPane = new JScrollPane(table);
		tblPane.setBounds(10, 11, 414, 189);
		contentPane.add(tblPane);
		Student.loadStudents(table);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student.setfirstID(AddStudent.txtStudentId);
				Student.openframe(AddStudent.frame);
			}
		});
		btnAddStudent.setBounds(10, 227, 105, 23);
		contentPane.add(btnAddStudent);
		
		JButton btnNewButton = new JButton("Update Marks");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student.saveMarks(table);
			}
		});
		btnNewButton.setBounds(178, 227, 124, 23);
		contentPane.add(btnNewButton);
		
		JButton btnJSON = new JButton("JSON");
		btnJSON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student.importdatainJSON();
			}
		});
		btnJSON.setBounds(345, 227, 89, 23);
		contentPane.add(btnJSON);
	}
}
