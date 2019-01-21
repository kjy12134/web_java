package sinc.factory;

import java.util.HashMap;
import java.util.Map;

import sinc.businesscard.ctrl.RegisterCtrl;
import sinc.businesscard.ctrl.SelectCtrl;
import sinc.ctrl.util.Controller;
import sinc.main.ctrl.IndexCtrl;

public class BeanFactory {
	private Map<String, Controller> map;
	private static BeanFactory instance;

	private BeanFactory() {
		map = new HashMap<>();
		map.put("/testWEB/select.sinc",		new SelectCtrl());
		map.put("/testWEB/register.sinc",	new RegisterCtrl());	
		map.put("/testWEB/index.sinc",		new IndexCtrl());
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
