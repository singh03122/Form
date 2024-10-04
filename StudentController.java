package in.Rohit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.Rohit.utility.JdbcUtil;

@WebServlet("/save")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SQLINSERT_QUERY = "insert into customer(Name,Age,Address) values (?,?,?)";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String age = request.getParameter("sage");
		String name = request.getParameter("sname");
		String address = request.getParameter("saddr");

		PrintWriter out = response.getWriter();

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = JdbcUtil.getDbConnection();
			pstmt = connection.prepareStatement(SQLINSERT_QUERY);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(age));
			pstmt.setString(3, address);
			int rowCount = pstmt.executeUpdate();
			out.println("<html><head><title>RESPONSE</title></head>");
			if (rowCount == 1) {
				out.println(
						"<body><h1 style='color:green; text-align:center;'>RECORD INSERTED SUCCESFULLY</h1></body>");
			} else {
				out.println("<body><h1 style='color:red; text-align:center;'>RECORD INSERTION FAILED</h1></body>");
			}
			out.println("</html>");
			out.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeResources(null, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}