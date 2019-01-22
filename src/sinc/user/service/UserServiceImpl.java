package sinc.user.service;

import sinc.user.model.sql.UserDao;
import sinc.user.model.sql.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private UserDao dao;
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	
	@Override
	public Object login(Object obj) {
		System.out.println("userService login");
		return dao.loginRow(obj);
	}

	@Override
	public int join(Object obj) {
		// TODO Auto-generated method stub
		return dao.joinRow(obj);
	}

	
	
}
