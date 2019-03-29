package dao;

import java.util.Map;

public interface UserDAO {
	public int insertUser(Map<String,String> user);
	public boolean selectUser(Map<String,String> user);
}
