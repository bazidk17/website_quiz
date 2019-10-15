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

import com.github.yaroslavyadrov.textshuffler.TextShuffler;

public class answer_checker extends HttpServlet {
	int counter = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        int count=Integer.parseInt(session.getAttribute("quescount").toString());
        count++;
        session.setAttribute("quescount", count);
        PrintWriter out = response.getWriter();
        Statement st=null;
        Connection conn;
        ResultSet rs;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_quiz_ise","root","");
        	st = conn.createStatement();
        	rs=st.executeQuery("select * from question_answers where id = "+request.getParameter("ques_id"));
        	if(rs.first())
        	{
        		 if(rs.getString(7).equals(request.getParameter("opt")))
        		 {
        			 int marks = Integer.parseInt(session.getAttribute("marks").toString());
        			 marks++;
        			 session.setAttribute("marks", marks);
        		 }
        	}
            response.sendRedirect("hellotest");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	//super.doPost(req, resp);
    	counter++;
    	PrintWriter out = resp.getWriter();
    	out.println("data = " + counter);
    }
}
