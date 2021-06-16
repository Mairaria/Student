<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%
String id = request.getParameter("userid");
String driver = "org.postgresql.Driver";
String connectionUrl = "jdbc:postgresql://localhost:5432/";
String database = "student";
String userid = "postgres";
String password = "brioba98";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<body>

<h1>Retrieve data from database in jsp</h1>
<table border="1">
<tr>
<td>Student Id</td>
<td>Student Name</td>
<td>Marks</td>

</tr>
<%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="select * from public.student";
String avg = "select avg(marks) as avereage from public.student";
PreparedStatement pst = connection.prepareStatement(avg);
ResultSet rs = pst.executeQuery();
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td><%=resultSet.getString("studentid") %></td>
<td><%=resultSet.getString("studentname") %></td>
<td><%=resultSet.getString("marks") %></td>
</tr>
<%
}
while(rs.next()){
	%>
	<tr>Average<tr>
	<tr><%=rs.getInt("average") %><tr>
	<% 
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>