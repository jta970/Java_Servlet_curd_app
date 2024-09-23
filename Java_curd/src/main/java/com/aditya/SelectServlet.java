package com.aditya;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.*;

import entity.school;

public class SelectServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "aditya1234");
			PreparedStatement ps = connection.prepareStatement("select * from school");
			res.setContentType("text/html");
			
			ResultSet resultSet = ps.executeQuery();
			
			PrintWriter pw = res.getWriter();
			
			List<school> list = new ArrayList<>();
			
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                
                school school = new school(id, name, age);
                list.add(school);
            }
            
            req.setAttribute( "list", list );
		    RequestDispatcher rd =  req.getRequestDispatcher("display.jsp");
            rd.forward(req, res);
            pw.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
