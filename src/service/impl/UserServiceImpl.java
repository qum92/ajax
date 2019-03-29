package service.impl;

import java.util.Map;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	UserDAO udao = new UserDAOImpl();
	public int insertUser(Map<String, String> user) {
		return udao.insertUser(user);
	}
	public boolean selectUser(Map<String, String> user) {

		return udao.selectUser(user);
	}

}
