package main;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import net.proteanit.sql.DbUtils;

public class Student {
	static Connection connect = null;
	static Connection connectToDb() {
		Connection con = null ;
		try {
		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student","postgres","brioba98");
		}catch(SQLException e) {
			e.printStackTrace();	
		}
		return con;
	}
	static void connect() {
		connect =connectToDb();
	}
 static Boolean testConnection() {
	 Boolean connected=false;
	 if(connectToDb() != null) {
		 JOptionPane.showMessageDialog(null,"Connected");
		 connected = true;
	 }else{
		 JOptionPane.showMessageDialog(null,"Connection Failed ");
	 };
	return connected;
 }
 static void saveStudents(JTextField Id, JTextField Name) {
	 connect();
	 String sql = "insert into public.student values(?,?)";
	 try {
		PreparedStatement pst = connect.prepareStatement(sql); 
		pst.setInt(1,Integer.valueOf(Id.getText()));
		pst.setString(2,Name.getText());
		pst.executeUpdate();
	 }catch(Exception e) {
		e.printStackTrace(); 
	 }
 }
 static void loadStudents(JTable tbl) {
	 connect();
	 String sql = "Select * from public.student";
	 try {
		 PreparedStatement pst = connect.prepareStatement(sql);
		 ResultSet rs = pst.executeQuery();
		 tbl.setModel(DbUtils.resultSetToTableModel(rs));
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
 static int setStudentId() {
	 connect();
	String sql ="Select max(studentid) as maxid from public.student ";
	int nextID = 0;
	try {
		PreparedStatement pst = connect.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			nextID = rs.getInt("maxid") + 1;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return nextID;
 }
 
 static void setfirstID(JTextField txt) {
	 if(setStudentId() == 0) {
		 JOptionPane.showMessageDialog(null,"Enter The first ID in interger form");
	 }else{
		 txt.setText(Integer.toString(setStudentId()));
	 }
 }
 static void openframe(JFrame frame) {
	 frame.setVisible(true);
 }
 static void ifempty(JTextField Id, JTextField Name, JTable tbl) {
	 if(Id.getText().isEmpty()||Name.getText().isEmpty()) {
		 JOptionPane.showMessageDialog(null, "All fields are required");
	 }else {
		 saveStudents(Id, Name);
		 loadStudents(tbl);
		 setfirstID(Id);
		 Name.setText("");
	 }
 }

 static void importdatainJSON() {
	 JSONObject jsobj = new JSONObject();
	 JSONArray jsarray = new JSONArray();
	 
	 String sql ="select * from public.student";
	 String sqlavg = "select avg(marks) as ave from public.student";
	 try {
		 PreparedStatement pst = connect.prepareStatement(sql);
		 PreparedStatement pstavg = connect.prepareStatement(sqlavg);
		 ResultSet rs = pst.executeQuery();
		 ResultSet rsavg = pstavg.executeQuery();
		 while(rs.next()) {
			 JSONObject marks = new JSONObject();
			 marks.put("Student_ID", rs.getInt("studentid"));
			 marks.put("Student_Name",rs.getString("studentname"));
			 marks.put("Marks",rs.getInt("marks"));
			 jsarray.add(marks);
		 }
		 jsobj.put("Student_Marks",jsarray);
		 while(rsavg.next()) {
		 jsobj.put("Average",rsavg.getInt("ave"));}
		 try {
	         FileWriter file = new FileWriter("G:/output.json");
	         file.write(jsobj.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
 static void saveMarks(JTable tbl) {
	 connect();
	 try {
		 DefaultTableModel md = (DefaultTableModel)tbl.getModel();
		 int rows = md.getRowCount();
		 for(int r =0; r < rows; r++) {
			 int marks =Integer.valueOf(md.getValueAt(r, 2).toString());
			 int id = Integer.valueOf(md.getValueAt(r, 0).toString());
			 
			 String sql ="update public.student set marks = ? where studentid = ?";
			 PreparedStatement pst = connect.prepareStatement(sql);
			 pst.setInt(1, marks);
			 pst.setInt(2, id);
			 pst.executeUpdate();
		 }
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
}
