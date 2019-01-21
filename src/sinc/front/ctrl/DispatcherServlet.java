package sinc.front.ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;
import sinc.factory.BeanFactory;

@WebServlet("*.sinc") 

// front controller!!
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProc(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProc(request,response);
	}

	public void requestProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BeanFactory bf = BeanFactory.getInstance();
		String uri = request.getRequestURI();
		Controller ct = bf.getBean(uri);
		System.out.println("client request: " + uri);
		
		View view = ct.execute(request, response);
		
		if(view.isSend()) { // dispatcher
			RequestDispatcher rd = request.getRequestDispatcher(view.getPath());
			rd.forward(request, response);
		} else { // redirect
			response.sendRedirect(view.getPath());
		}
	}
}
