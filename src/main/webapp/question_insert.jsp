<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>

<%
String question=request.getParameter("question");
String option1=request.getParameter("option1");
String option2=request.getParameter("option2");
String option3=request.getParameter("option3");
String option4=request.getParameter("option4");
String correct=request.getParameter("correct");

try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_quiz_ise", "root", "");
Statement st=conn.createStatement();

int i=st.executeUpdate("insert into question_answers (question,option1,option2,option3,option4,correct) values('"+question+"','"+option1+"','"+option2+"','"+option3+"','"+option4+"','"+correct+"');");
response.sendRedirect("success_add.jsp");
}
catch(Exception e)
{
System.out.print(e);
e.printStackTrace();
}
%>