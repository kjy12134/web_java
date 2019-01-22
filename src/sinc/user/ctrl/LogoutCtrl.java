package sinc.user.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;

public class LogoutCtrl implements Controller {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("LogoutCtrl execute");
		HttpSession session = request.getSession(false); // 세션 get
		session.invalidate(); // 세션 kill
		return new View("index.sinc", false);
	}

}
