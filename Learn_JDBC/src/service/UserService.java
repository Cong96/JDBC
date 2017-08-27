package service;

import dao.UserDao;
import entity.User;

public class UserService {
	private UserDao userDao;

	public UserService() {
	}

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean addUser() {
		return userDao.add();
	}

	public User getUser(Integer id) {
		return userDao.getById(id);
	}

	public boolean deleteUser() {
		return userDao.delete();
	}

	public boolean updateUser() {
		return userDao.update();
	}
}
