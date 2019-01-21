package sinc.businesscard.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;

public class RegisterCtrl implements Controller {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("Register Ctrl execute");
		request.setAttribute("msg", "임섭순");
		return new View("result.jsp", false);
	}

}
