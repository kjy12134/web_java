package sinc.user.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;
import sinc.user.model.vo.UserVO;
import sinc.user.service.UserService;
import sinc.user.service.UserServiceImpl;

public class LoginCtrl implements Controller {

	private UserService service;
	public LoginCtrl() {
		service = new UserServiceImpl();
	}
	
	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("loginCtrl execute");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 객체화 작업
		UserVO user = new UserVO();
		user.setId(id);
		user.setPwd(pwd);
		//request.setCharacterEncoding("UTF-8"); // 인코딩 세팅 // 회원가입 정상 return 1 아니면 error
		// 서비스 접근
		Object result = service.login(user);
		View view = new View();
		
		if(result != null) {
			view.setPath("index.sinc"); // redirect
			view.setSend(false); // true일때(dispatcher) > main.jsp
			HttpSession session = request.getSession(); // 세션 생성
			session.setAttribute("loginUser", result);
		} else {
			view.setPath("error.jsp");
			view.setSend(true);
		}
		
		return view;
	}

}
