package test.ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test.sinc")
public class TestCtrl extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("ctrl init");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ctrl doGet update");
		String msg = request.getParameter("msg"); // URL : 127.0.0.1:8088/testWEB/test.sinc?msg=hi
		// 화면분기 방식 2가지
		//response.sendRedirect("result.jsp");
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ctrl doPost");
	}

	public void destroy() {
		System.out.println("ctrl destroy");
	}
}
