package com.aditya;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.*;

public class InsertServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		
		int input = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "aditya1234");
			PreparedStatement ps = connection.prepareStatement("insert into school(id, name, age) values (?, ?, ?)");
			ps.setInt(1, input);
			ps.setString(2, name);
			ps.setInt(3, age);
			
			res.setContentType("text/html");
			
			ps.executeUpdate();
			
			PrintWriter pw = res.getWriter();
			pw.write("<h2>Record Inserted!</h2>");
            
            pw.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
