package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class hellotest
 */
public class hellotest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = 0;
        int max = 0;
        int marks = 0;
        HttpSession hs = request.getSession(false);
        if(hs!=null && hs.getAttribute("quescount")!=null)
        {
        	count = Integer.parseInt(hs.getAttribute("quescount").toString());
        	max = Integer.parseInt(hs.getAttribute("max_ques").toString());
        	marks = Integer.parseInt(hs.getAttribute("marks").toString());
        }
        if(count == max && count + max != 0)
        {
        	PrintWriter out = response.getWriter();
        	out.println("<html>"+
        			"<body>");
        	out.println("You have attempted all questions");
        	out.println("Marks scored = " + hs.getAttribute("marks").toString());
        	out.println(
        			"<a href=\"reset\"> Reset </a>" +
        			"</body>"+
        			"</html>"
        				);
        	hs.setAttribute("marks", 0);
        }
        else
        {
        PrintWriter out = response.getWriter();
        Statement st=null;
        String shuffled = TextShuffler.shuffle("3456");
        Connection conn;
        ResultSet rs;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_quiz_ise","root","");
        	st = conn.createStatement();
        	rs=st.executeQuery("select * from question_answers");
        	if(rs.first())
        	{
        		 for(int i = 0 ; i < count ; i++) // count may not be equal to the next question. might need to do equal to
        		 {
        			 rs.next();
        		 }
        		 out.println (
     	        		"<html>"+
     	        		"<body>"+
     	        		"<center>"+
     	        		rs.getString(2)+
     	        		"<form action=\"answer_checker\">"+
     	        		  "<input type=\"radio\" name=\"opt\" value=\""+rs.getString(Integer.parseInt(shuffled.charAt(0)+""))+"\" checked> "+rs.getString(Integer.parseInt(shuffled.charAt(0)+""))+"<br/>"+
     	        		  "<input type=\"radio\" name=\"opt\" value=\""+rs.getString(Integer.parseInt(shuffled.charAt(1)+""))+"\"> "+rs.getString(Integer.parseInt(shuffled.charAt(1)+""))+"<br>"+
     	        		  "<input type=\"radio\" name=\"opt\" value=\""+rs.getString(Integer.parseInt(shuffled.charAt(2)+""))+"\"> "+rs.getString(Integer.parseInt(shuffled.charAt(2)+""))+" <br/>"+  
     	        		 "<input type=\"radio\" name=\"opt\" value=\""+rs.getString(Integer.parseInt(shuffled.charAt(3)+""))+"\"> "+rs.getString(Integer.parseInt(shuffled.charAt(3)+""))+" <br/>"+
     	        		 "<input type=\"hidden\" name=\"ques_id\" value=\""+rs.getString(1)+"\">"+
     	        		  "<input type=\"submit\" value=\"Submit\">"+
     	        		"</form>"+
     	        		"</body>"+
     	        		"</center>"+
     	        		"</html>"
     	                );
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        try
        {
        	rs = st.executeQuery("select count(*) from question_answers");
        	if(rs.first())
        	{
        		max=rs.getInt(1);
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("quescount", count);
        session.setAttribute("max_ques", max);
        session.setAttribute("marks", marks);
        
        }
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	//super.doPost(req, resp);
    	PrintWriter out = resp.getWriter();
    }

}
