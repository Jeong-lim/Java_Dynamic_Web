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
		// Connection ����
		Connection con = null;
		try {
//			con = DriverManager.getConnection(url, id, pw); // Ŀ�ؼ� ����
			con = dataSource.getConnection();
			System.out.println(con);
			String sql = "select count(*) from employees";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement ����
			ResultSet rs = stmt.executeQuery(); // SQL ���� ����
			if(rs.next()) { // ������ռҺ�
				count = rs.getInt(1);
			}
			System.out.println("����� �� : " + count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally { 		// Connection �ݱ�
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return count;
	}
	
	public int getEmpCount(int deptno) {
		int count = 0;
		// Connection ����
		Connection con = null;
		try {
//			con = DriverManager.getConnection(url, id, pw); // Ŀ�ؼ� ����
			con = dataSource.getConnection();
			System.out.println(con);
			String sql = "select count(*) from employees where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement ����
			stmt.setInt(1, deptno);
			ResultSet rs = stmt.executeQuery(); // SQL ���� ����
			if(rs.next()) { // ������ռҺ�
				count = rs.getInt(1);
			}
			System.out.println("����� �� : " + count);
		}catch(Exception e) {
			e.printStackTrace();
		}finally { 		// Connection �ݱ�
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
		int salary = 0; //����� �޿��� ������ ����
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
			if(rs.next()) { // ����� VO ���ν�Ŵ
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
			con = dataSource.getConnection(); // Connection ��ü ����
			String sql = "select * from employees";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement ����
			ResultSet rs = stmt.executeQuery(); // ���� ����
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
	
	public void insertEmp(EmpVo emp) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "insert into employees (employee_id, first_name, last_name, "
					+ "email, phone_number, hire_date, job_id, salary, commission_pct, "
					+ "manager_id, department_id) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getPhoneNumber());
			stmt.setDate(6, emp.getHireDate());
			stmt.setString(7, emp.getJobId());
			stmt.setDouble(8, emp.getSalary());
			stmt.setDouble(9, emp.getCommissionPct());
			stmt.setInt(10, emp.getManagerId());
			stmt.setInt(11, emp.getDepartmentId());
			stmt.executeUpdate();
			System.out.println("�����Ͱ� �ԷµǾ����ϴ�.");
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
	}
	
	public List<Map<String, Object>> getJobIdList() {
		List<Map<String, Object>> jobIdList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select job_id, job_title from jobs";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String jobId = rs.getString("job_id");
				String jobTitle = rs.getString("job_title");
				Map<String, Object> job = new HashMap<String, Object>();
				job.put("jobId", jobId);
				job.put("jobTitle", jobTitle);
				jobIdList.add(job);
			}			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return jobIdList;
	}
	
	public List<Map<String, Object>> getEmpIdList() {
		List<Map<String, Object>> empIdList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select employee_id, first_name || ' ' || last_name as name from employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String empId = rs.getString("employee_id");
				String name = rs.getString("name");
				Map<String, Object> emp = new HashMap<String, Object>();
				emp.put("empId", empId);
				emp.put("name", name);
				empIdList.add(emp);
			}			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return empIdList;
	}
	
	public List<Map<String, Object>> getDeptIdList() {
		List<Map<String, Object>> deptIdList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select department_id, department_name from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String deptId = rs.getString("department_id");
				String deptName = rs.getString("department_name");
				Map<String, Object> dept = new HashMap<String, Object>();
				dept.put("deptId", deptId);
				dept.put("deptName", deptName);
				deptIdList.add(dept);
			}			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return deptIdList;
	}
	
	public void updateEmp(EmpVo emp) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "update employees set first_name=?, last_name=?, email=?, "
					   + "phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, "
					   + "manager_id=?, department_id=? "
					   + "where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, emp.getFirstName());
			stmt.setString(2, emp.getLastName());
			stmt.setString(3, emp.getEmail());
			stmt.setString(4, emp.getPhoneNumber());
			stmt.setDate(5, emp.getHireDate());
			stmt.setString(6, emp.getJobId());
			stmt.setDouble(7, emp.getSalary());
			stmt.setDouble(8, emp.getCommissionPct());
			stmt.setInt(9, emp.getManagerId());
			stmt.setInt(10, emp.getDepartmentId());
			stmt.setInt(11, emp.getEmployeeId());
			stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); }catch(Exception e) {}
		}
	}

	public void deleteEmp(int empid, String email) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			String sql0 = "delete job_history where employee_id=?";
			PreparedStatement stmt0 = con.prepareStatement(sql0);
			stmt0.setInt(1, empid);
			stmt0.executeUpdate();
			
			String sql = "delete employees where employee_id=? and email=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empid);
			stmt.setString(2, email);
			stmt.executeUpdate();
			
			con.commit();
		}catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(con!=null) try { con.close(); }catch(Exception e) {}
		}
	}
}// end class







