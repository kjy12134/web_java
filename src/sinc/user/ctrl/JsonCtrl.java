package sinc.user.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import sinc.ctrl.util.Controller;
import sinc.ctrl.view.View;
import sinc.user.model.vo.ChartVO;

public class JsonCtrl implements Controller {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JsonCtrl");
		List<Object> list = new ArrayList<>();
		list.add(new ChartVO("Germany", 200));
		list.add(new ChartVO("United States", 300));
		list.add(new ChartVO("Brazil", 400));
		list.add(new ChartVO("Canada", 500));
		list.add(new ChartVO("France", 600));
		list.add(new ChartVO("RU", 700));

		JSONArray ary = new JSONArray(list);
		request.setAttribute("jsonAry", ary.toString()); // view단에서 쓸 이름 매칭
		return new View("geoChart.jsp", true);
	}

}
