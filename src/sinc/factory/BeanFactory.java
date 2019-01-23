package sinc.factory;

import java.util.HashMap;
import java.util.Map;

import sinc.board.ctrl.ListCtrl;
import sinc.ctrl.util.Controller;
import sinc.main.ctrl.IndexCtrl;
import sinc.user.ctrl.JoinCtrl;
import sinc.user.ctrl.LoginCtrl;
import sinc.user.ctrl.LogoutCtrl;
import sinc.user.ctrl.RegisterFormCtrl;

public class BeanFactory {
	private Map<String, Controller> map;
	private static BeanFactory instance;

	private BeanFactory() {
		map = new HashMap<>();
//		map.put("/testWEB/select.sinc",			new SelectCtrl());
//		map.put("/testWEB/register.sinc",		new RegisterCtrl());	
		map.put("/testWEB/index.sinc",			new IndexCtrl());
		map.put("/testWEB/login.sinc",			new LoginCtrl());	
		map.put("/testWEB/logout.sinc",			new LogoutCtrl());
		map.put("/testWEB/registForm.sinc",		new RegisterFormCtrl());
		map.put("/testWEB/join.sinc",			new JoinCtrl());
		map.put("/testWEB/board/list.sinc",		new ListCtrl());	
	}

	public static BeanFactory getInstance() {
		if (instance == null) {
			instance = new BeanFactory();
		}
		return instance;
	}
	
	public Controller getBean(String uri) {
		return map.get(uri);
	}
}
