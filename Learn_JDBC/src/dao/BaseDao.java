package dao;

import entity.User;

public interface BaseDao {
	public boolean add();

	public boolean update();

	public User getById(Integer id);

	public boolean delete();
}
