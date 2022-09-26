package kr.kosa.web;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kosa.emp.EmpDao;
import kr.kosa.emp.EmpVo;

/**
 * Servlet implementation class EmpServlet
 */
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
        System.out.println("EmpServlet ������ ����");
    }

    String email;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("EmpServlet init() �޼��� ����");
		email = config.getInitParameter("email");
		System.out.println("�̸��� �ּ�: " + email);
	}

	EmpDao dao = new EmpDao(); //import �ϼ���.
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
//		System.out.println(uri); // /EmpWeb/EmpList.do
//		System.out.println(uri.lastIndexOf('/')); // 7
//		System.out.println(uri.substring(7)); // /EmpList.do
		String cmd = uri.substring(uri.lastIndexOf('/'));
		
		String view = "/index.jsp";
		if("/EmpList.do".equals(cmd)) {
			System.out.println("��� ����� ������ ��ȸ�մϴ�.");
			// DAO �޼��� ȣ��, request�� ���� ����
			request.setAttribute("empList", dao.getAllEmps());
			// System.out.println(dao.getAllEmps().size());
			// ��� ������(�� ��θ� ����)
			view = "/WEB-INF/views/emp/emplist.jsp";
		}else if("/EmpInsert.do".equals(cmd)) {
			System.out.println("�Է� ����� ��û�մϴ�.");
			request.setAttribute("jobIdList", dao.getJobIdList());
			request.setAttribute("mgrIdList", dao.getEmpIdList());
			request.setAttribute("deptIdList", dao.getDeptIdList());
			view = "/WEB-INF/views/emp/empform.jsp";
		}else if("/EmpDetails.do".equals(cmd)) {
			System.out.println("�� ������ ��û�մϴ�.");
			String empidStr = request.getParameter("empid");
			int empid = Integer.parseInt(empidStr);
			request.setAttribute("emp", dao.getEmpDetails(empid));
			view = "/WEB-INF/views/emp/empdetails.jsp";
		}else if("/EmpUpdate.do".equals(cmd)) {
			System.out.println("���� ������ ��û�մϴ�.");
			String empidStr = request.getParameter("empid");
			int empid = Integer.parseInt(empidStr);
			request.setAttribute("emp", dao.getEmpDetails(empid));
			request.setAttribute("jobIdList", dao.getJobIdList());
			request.setAttribute("mgrIdList", dao.getEmpIdList());
			request.setAttribute("deptIdList", dao.getDeptIdList());
			view = "/WEB-INF/views/emp/empupdateform.jsp";
		}else if("/EmpDelete.do".equals(cmd)) {
			view = "/WEB-INF/views/emp/empdeleteform.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(view);
		disp.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf('/'));
		if("/EmpInsert.do".equals(cmd)) {
			// �Է��� ó��
			String employeeId = request.getParameter("employeeId");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String jobId = request.getParameter("jobId");
			String hireDate = request.getParameter("hireDate");
			String salary = request.getParameter("salary");
			String commissionPct = request.getParameter("commissionPct");
			String managerId = request.getParameter("managerId");
			String departmentId = request.getParameter("departmentId");
			
			EmpVo emp = new EmpVo();
			
			emp.setEmployeeId(Integer.parseInt(employeeId));
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber);
			emp.setJobId(jobId);
//			emp.setHireDate(Date.valueOf(hireDate)); //1.8���� ��� ����
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				emp.setHireDate(new Date(format.parse(hireDate).getTime()));
			} catch (ParseException e) {
				System.out.println("��¥ ���Ŀ� ���� �ʽ��ϴ�.");
			}
			emp.setSalary(Double.parseDouble(salary));
			emp.setCommissionPct(Double.parseDouble(commissionPct));
			emp.setManagerId(Integer.parseInt(managerId));
			emp.setDepartmentId(Integer.parseInt(departmentId));
//			System.out.println(emp);			
			dao.insertEmp(emp);
			response.sendRedirect("EmpList.do");
		}else if("/EmpUpdate.do".equals(cmd)) {
			// �Է��� ó��
			String employeeId = request.getParameter("employeeId");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String jobId = request.getParameter("jobId");
			String hireDate = request.getParameter("hireDate");
			String salary = request.getParameter("salary");
			String commissionPct = request.getParameter("commissionPct");
			String managerId = request.getParameter("managerId");
			String departmentId = request.getParameter("departmentId");
			
			EmpVo emp = new EmpVo();
			
			emp.setEmployeeId(Integer.parseInt(employeeId));
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber);
			emp.setJobId(jobId);
//			emp.setHireDate(Date.valueOf(hireDate)); //1.8���� ��� ����
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				emp.setHireDate(new Date(format.parse(hireDate).getTime()));
			} catch (ParseException e) {
				System.out.println("��¥ ���Ŀ� ���� �ʽ��ϴ�.");
			}
			emp.setSalary(Double.parseDouble(salary));
			emp.setCommissionPct(Double.parseDouble(commissionPct));
			emp.setManagerId(Integer.parseInt(managerId));
			emp.setDepartmentId(Integer.parseInt(departmentId));
//			System.out.println(emp);			
			dao.updateEmp(emp);
			response.sendRedirect("EmpDetails.do?empid="+employeeId);
		}else if("/EmpDelete.do".equals(cmd)){
			String empid = request.getParameter("empid");
			String email = request.getParameter("email");
			dao.deleteEmp(Integer.parseInt(empid), email);
			response.sendRedirect("EmpList.do");
		}// end if
		
	} // end doPost

}// end servlet class
