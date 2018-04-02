

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
			{
		String EmailId = (String) req.getAttribute("emailid");
		String password = (String) req.getAttribute("password");
		PrintWriter out = resp.getWriter();
		
	
		
		
		RequestDispatcher rd=req.getRequestDispatcher("loginSuccess.jsp");
		
		rd.forward(req, resp);
		
		out.flush();
		out.close();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String qry="select name from customerdetails where EmailId=? and password=?";
	
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("class loaded");
			
			con = DriverManager 
					.getConnection("jdbc:mysql://localhost:3306/register ","root","dinga");
			System.out.println("connected sucessfully");
			
			pstmt = con.prepareStatement(qry);
			pstmt.setString(1, EmailId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			
			
		
		}
		
		catch(ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
			
		}
		
		finally
		{
			if (rs != null) 
			{
				try 
				{
					rs.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			
			
				if (pstmt != null) 
				{
					try
					{
						pstmt.close();
					} 
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
	
				
					if (con != null) 
					{
						try 
						{
							con.close();
						}
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
					}
			
			}
	
	
			}
}
	
	


