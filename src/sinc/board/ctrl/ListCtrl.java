package sinc.board.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;
import sinc.user.service.UserService;
import sinc.user.service.UserServiceImpl;

public class ListCtrl implements Controller {

	private UserService service;

	public ListCtrl() {
		service = new UserServiceImpl();
	}

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ListCtrl execute");
		
		List<Object> boards = service.boardList();
		Map<String, List<Object>> map = new HashMap<>();
		map.put("list01", boards);
		request.setAttribute("maps", map);
		// request.setAttribute("boards", boards);
		
		View view = new View("list.jsp", true);
		// ** request의 데이터값을 가지고 list.jsp로 이동! forward방식! forward는 페이지로 지정(*.jsp), select
		// redirect는 요청 재지정(insert, update, delete), 이럴땐 url로 지정(*.sinc)

		return view;
	}

}
