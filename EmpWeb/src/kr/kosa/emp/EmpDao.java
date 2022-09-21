package kr.kosa.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDao {
//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
//			System.out.println("드라이버 클래스가 로드되었습니다.");
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 클래스 로드 실패");
//			e.printStackTrace();
//		}
//	}
//	
//	private String url;// = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String id;// = "hr";
//	private String pw;// = "hr";
//	
//	public EmpDao(String url, String id, String pw) {
//		this.url = url;
//		this.id = id;
//		this.pw = pw;
//	}
//	
//	public EmpDao(ServletContext application) {
//		this.url = application.getInitParameter("OracleURL");
//		this.id = application.getInitParameter("OracleId");
//		this.pw = application.getInitParameter("OraclePwd");
//	}
	
	DataSource dataSource;
	
	public EmpDao() {
		try {
			Context initCtx = new InitialContext();
			dataSource = (DataSource)initCtx.lookup("java:comp/env/dbcp_myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int getEmpCount() {
		int count = 0;
		// Connection 생성
		Connection con = null;
		try {
//			con = DriverManager.getConnection(url, id, pw); // 커넥션 생성
			con = dataSource.getConnection();
			System.out.println(con);
			String sql = "select count(*) from employees";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement 생성
			ResultSet rs = stmt.executeQuery(); // SQL 쿼리 전송
			if(rs.next()) { // 결과집합소비
				count = rs.getInt(1);
			}
			System.out.println("사원의 수 : " + count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally { 		// Connection 닫기
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return count;
	}
	
	public int getEmpCount(int deptno) {
		int count = 0;
		// Connection 생성
		Connection con = null;
		try {
//			con = DriverManager.getConnection(url, id, pw); // 커넥션 생성
			con = dataSource.getConnection();
			System.out.println(con);
			String sql = "select count(*) from employees where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement 생성
			stmt.setInt(1, deptno);
			ResultSet rs = stmt.executeQuery(); // SQL 쿼리 전송
			if(rs.next()) { // 결과집합소비
				count = rs.getInt(1);
			}
			System.out.println("사원의 수 : " + count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally { 		// Connection 닫기
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return count;
	}
	
	public double getAverageSalaryByDepartment(int deptno) {
		Connection con = null;
		double result = 0;
		try {
			con = dataSource.getConnection();
			String sql = "select round(avg(salary), 2) from employees where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, deptno);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getDouble(1);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) { }
		}
		return result;
	}
	
	public int getSalaryByEmployeeId(int empid) {
		Connection con = null;
		int salary = 0; //사원의 급여를 저장할 변수
		try {
			con = dataSource.getConnection();
			String sql = "select salary from employees where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				salary = rs.getInt(1);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return salary;
	}
	
	public String getDepartmentNameByEmployeeId(int empid) {
		Connection con = null;
		String departmentName = null;
		try {
			con = dataSource.getConnection();
			String sql = "select department_name from employees "
					+ "join departments on employees.department_id=departments.department_id "
					+ "where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				departmentName = rs.getString("department_name");
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return departmentName;
	}
	
	public EmpVo getEmpDetails(int empid) {
		EmpVo emp = new EmpVo();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select employee_id, first_name, last_name, email, phone_number,"
					+ " job_id, hire_date, salary, commission_pct, manager_id, department_id "
					+ " from employees where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { // 결과를 VO 매핑시킴
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
			}else {
				emp = null;
			}
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return emp;
	}

	public List<EmpVo> getAllEmps() {
		List<EmpVo> empList = new ArrayList<>();
		Connection con = null;
		try {
			con = dataSource.getConnection(); // Connection 객체 생성
			String sql = "select * from employees";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement 생성
			ResultSet rs = stmt.executeQuery(); // 쿼리 실행
			while(rs.next()) {
				EmpVo emp = new EmpVo();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));				
				empList.add(emp);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return empList;
	}
	
	public void insertEmp(EmpVo empVo) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "insert into employees (employee_id, first_name, last_name, "
					+ "email, phone_number, hire_date, job_id, salary, commission_pct, "
					+ "manager_id, department_id) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,  empVo.getEmployeeId());
			statement.setString(2, empVo.getFirstName());
			statement.setString(3, empVo.getLastName());
			statement.setString(4, empVo.getEmail());
			statement.setString(5, empVo.getPhoneNumber());
			statement.setDate(6, empVo.getHireDate());
			statement.setString(7, empVo.getJobId());
			statement.setDouble(8, empVo.getSalary());
			statement.setDouble(9, empVo.getCommissionPct());
			statement.setInt(10, empVo.getManagerId());
			statement.setInt(11, empVo.getDepartmentId());
			
			statement.executeUpdate();
			System.out.println("데이터가 입력되었습니다.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {}
			}
		}
	}
	
	
	public List<Map<String, Object>> getJobIdList() { // String에는 이름 jobId, jobTitle, Object는 실제 값
		List<Map<String, Object>> jobIdList = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select job_id, job_title from jobs";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String jobId = resultSet.getString("job_id");
				String jobTitle = resultSet.getString("job_title");
				Map<String, Object> job = new HashMap<String, Object>();
				job.put("jobId", jobId);
				job.put("jobTitle", jobTitle);
				jobIdList.add(job);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null) try {connection.close();} catch (Exception e2) {
			}
		}
		return jobIdList;
		
	}
	
	public List<Map<String, Object>> getEmpIdList() { // String에는 이름 jobId, jobTitle, Object는 실제 값
		List<Map<String, Object>> empIdList = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select employee_id, first_name || ' ' || last_name as name from employees";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String empId = resultSet.getString("employee_id");
				String empName = resultSet.getString("name");
				Map<String, Object> emp = new HashMap<String, Object>();
				emp.put("empId", empId);
				emp.put("empName", empName);
				empIdList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null) try {connection.close();} catch (Exception e2) {
			}
		}
		return empIdList;
		
	}
	
	public List<Map<String, Object>> getDeptIdList() { // String에는 이름 jobId, jobTitle, Object는 실제 값
		List<Map<String, Object>> deptIdList = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select department_id, department_name from departments";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String deptId = resultSet.getString("department_id");
				String deptName = resultSet.getString("department_name");
				Map<String, Object> dept = new HashMap<String, Object>();
				dept.put("deptId", deptId);
				dept.put("deptName", deptName);
				deptIdList.add(dept);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null) try {connection.close();} catch (Exception e2) {
			}
		}
		return deptIdList;
		
	}
	
	

	

}
