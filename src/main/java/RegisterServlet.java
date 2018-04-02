

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("registration servlet started");
		
		String Username = req.getParameter("name");
		String EmailId = req.getParameter("emailid");
		String Password = req.getParameter("password");
		String Confirmpassword = req.getParameter("confirm");
		
		
		req.setAttribute("email", EmailId);
		req.setAttribute("Pass", Password);
	
	Connection con = null;
		PreparedStatement pstmt = null;
		String qry = "insert into customerdetails values(?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class loaded");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","dinga");
			System.out.println("connected sucessfully");
			pstmt = con.prepareStatement(qry);
			pstmt.setString(1, Username);
			pstmt.setString(2, EmailId);
			pstmt.setString(3, Password);
			pstmt.setString(4, Confirmpassword);
			pstmt.executeUpdate();
			PrintWriter out = resp.getWriter();
			
			
			RequestDispatcher rd=req.getRequestDispatcher("Success.jsp");
			rd.forward(req, resp);
			out.flush();
			out.close();

			
		}

		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
			RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
			rd.forward(req, resp);

		}

	}

