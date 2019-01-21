package sinc.main.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;

public class IndexCtrl implements Controller {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("IndexCtrl execute");
		return new View(("main.jsp"), true);
	}
	
}
