package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class EmployeeAvg {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 클래스가 로드되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 로드 실패");
			e.printStackTrace();
		}

	}
	private String url; // = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id; // = "hr";
	private String pw; // = "hr";

	public EmployeeAvg(String url, String id, String pw) {
		this.url = url;
		this.id = id;
		this.pw = pw;
	}

	public EmployeeAvg(ServletContext application) {
		this.url = application.getInitParameter("OracleURL");
		this.id = application.getInitParameter("OracleId");
		this.pw = application.getInitParameter("OraclePwd");
	}

	DataSource dataSource;

	public EmployeeAvg() {
		try {
			Context initCtx = new InitialContext();
			dataSource = (DataSource) initCtx.lookup("java:comp/env/dbcp_myoracle");
			// 생성자 생성되면 initCtx 만들고 myoracle 찾아서 datasource에 저장
			// Context ctx = (Context)initCtx.lookup("java:com/env");
		} catch (NamingException e) { // 예외 발새하면 Context안에 dbcp_myoracle이 없는 것임
			e.printStackTrace();
		}
	}

	public double getAverageSalaryByDepartment(int deptno) {
		double result = 0;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select round(avg(salary), 2) from employees where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, deptno);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}
	public double getAverageSalaryByDepartment() {
		double result = 0;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select round(avg(salary), 2) from employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return result;
	}

}
