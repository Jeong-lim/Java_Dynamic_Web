package kr.kosa.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.println("EmpServlet 생성자 실행");
    }

    String email;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("EmpServlet init() 메서드 실행");
		email = config.getInitParameter("email");
		System.out.println("이메일 주소: " + email);
	}

	EmpDao dao = new EmpDao(); //import 하세요.
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
			System.out.println("모든 사원의 정보를 조회합니다.");
			// DAO 메서드 호출, request에 정보 저장
			request.setAttribute("empList", dao.getAllEmps()); // 이 값을 가져와서 넘김
			// System.out.println(dao.getAllEmps().size());
			// 뷰로 포워드(뷰 경로를 지정)
			view = "/WEB-INF/views/emp/emplist.jsp";
		}else if("/EmpInsert.do".equals(cmd)) {
			System.out.println("입력 양식을 요청합니다.");  
			request.setAttribute("jobIdList", dao.getJobIdList());
			request.setAttribute("empIdList", dao.getEmpIdList());
			request.setAttribute("deptIdList", dao.getDeptIdList());
			view = "/WEB-INF/views/emp/empform.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(view);
		disp.forward(request, respoinse);
		
//		System.out.println("doGet 메서드 실행");
//		System.out.println(email);
//		String cmd = request.getParameter("cmd");
//		String view = "/";
//		if("empcount".equals(cmd)) {
//			System.out.println("사원의 수를 조회하는 요청입니다.");
//			String deptStr = request.getParameter("deptid");
//			if(deptStr==null) {
//				int empcount = dao.getEmpCount();
//				request.setAttribute("empcount", empcount);
//			}else {
//				int deptid = Integer.parseInt(deptStr);
//				request.setAttribute("empcount", dao.getEmpCount(deptid));
//			}
//			view = "/emp/empcount.jsp";
//		}else if("getdept".equals(cmd)) {
//			System.out.println("사원의 부서이름을 조회하는 요청입니다.");
//			String empidStr = request.getParameter("empid");
//			if(empidStr!=null) {
//				int empid = Integer.parseInt(empidStr);
//				request.setAttribute("deptname", dao.getDepartmentNameByEmployeeId(empid));
//			}else {
//				request.setAttribute("deptname", "사원번호가 전달되어야 합니다.");
//			}
//			view = "/emp/getdept.jsp";
//		}else if("avgsal".equals(cmd)) {
//			System.out.println("부서의 평균 급여를 조회하는 요청입니다.");
//			int dept = Integer.parseInt(request.getParameter("deptid"));
//			request.setAttribute("avgsal", dao.getAverageSalaryByDepartment(dept));
//			view = "/emp/avgsal.jsp";
//		}else if("empsal".equals(cmd)) {
//			System.out.println("사원의 급여를 조회하는 요청입니다.");
//			int empid = Integer.parseInt(request.getParameter("empid"));
//			request.setAttribute("salary", dao.getSalaryByEmployeeId(empid));
//			view = "/emp/empsal.jsp";
//		}else if("empdetail".equals(cmd)) {
//			int empid = Integer.parseInt(request.getParameter("empid"));
//			request.setAttribute("emp", dao.getEmpDetails(empid));
//			view = "/emp/empdetail.jsp";
//		}
//		RequestDispatcher disp = request.getRequestDispatcher(view);
//		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf('/'));
		if("/EmpInsert.do".equals(cmd)) {
			// 입력을 처리
			String employeeId = request.getParameter("employeeId");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String hireDate = request.getParameter("hireDate");
			String jobId = request.getParameter("jobId");
			String salary = request.getParameter("salary");
			String commissionPct = request.getParameter("commissionPct");
			String managerId = request.getParameter("managerId");
			String departmentId = request.getParameter("departmentId");
			
			
			EmpVo empVo = new EmpVo();
			
			
			empVo.setEmployeeId(Integer.parseInt(employeeId));
			empVo.setFirstName(firstName);
			empVo.setLastName(lastName);
			empVo.setEmail(email);
			empVo.setPhoneNumber(phoneNumber);
			empVo.setHireDate(Date.valueOf(hireDate)); // 1.8부터 사용 가능
/*			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			try {
				empVo.setHireDate(new Date(format.parse(hireDate).getTime()));
			} catch (ParseException e) {
				System.out.println("날짜 형식에 맞지 않습니다.");
			}
			System.out.println(empVo);*/
			// 자바 1.8 버전이 아닐때
			empVo.setJobId(jobId);
			empVo.setSalary(Double.parseDouble(salary));
			empVo.setManagerId(Integer.parseInt(managerId));
			empVo.setDepartmentId(Integer.parseInt(departmentId));
			empVo.setCommissionPct(Double.parseDouble(commissionPct));
			
			dao.insertEmp(empVo);
			response.sendRedirect("EmpList.do");
			

		}
	}


}
