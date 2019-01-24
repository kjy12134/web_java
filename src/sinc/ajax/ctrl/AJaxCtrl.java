package sinc.ajax.ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import sinc.user.model.vo.UserVO;

@WebServlet("/ajax.do")
public class AJaxCtrl extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AJaxCtrl request");
		System.out.println("param : " + request.getParameter("id"));
		System.out.println("param : " + request.getParameter("pwd"));

		UserVO usr = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		JSONObject jobj = new JSONObject(usr);
		response.setContentType("text/html;charset=utf-8"); // 한글 encoding
		PrintWriter out = response.getWriter(); // stream역할(request 바로 뿌려주기)
		out.print(jobj.toString());

	}

}
