package tri.dev.data.dao;

import java.util.List;

import tri.dev.data.model.User;

public interface UserDao {
	public boolean insert(User user);
	public boolean update(User user);
	public boolean delete(int id);
	public User find(int id);
	public List<User> findAll();
}