package sinc.user.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;
import sinc.user.model.vo.UserVO;
import sinc.user.service.UserService;
import sinc.user.service.UserServiceImpl;

public class JoinCtrl implements Controller {

	private UserService service;

	public JoinCtrl() {
		service = new UserServiceImpl();
	}

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JoinCtrl execute");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");

		// 객체화 작업
		UserVO user = new UserVO();
		user.setId(id);
		user.setPwd(pwd);
		user.setName(name);
		user.setDept(dept);

		// service 호출
		int result = service.join(user);
		View view = new View();

		if (result == 1) {
			view.setPath("index.sinc");
			view.setSend(false);
		} else {
			view.setPath("error.jsp");
			view.setSend(true);
		}

		return view;
	}

}
