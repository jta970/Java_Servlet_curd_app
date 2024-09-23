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

public class DeleteServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		int input = Integer.parseInt(req.getParameter("id"));
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "aditya1234");
			PreparedStatement ps = connection.prepareStatement("Delete from school where id = ?");
			ps.setInt(1, input);
			
			res.setContentType("text/html");
			
			ps.executeUpdate();
			
			PrintWriter pw = res.getWriter();
			pw.write("<h2>Record Deleted!</h2>");
            
            pw.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
