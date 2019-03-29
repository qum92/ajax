package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;
import db.DBCon;

public class UserDAOImpl implements UserDAO {
	private String insertUser = "insert into user_info(ui_num, ui_name, ui_id, ui_pwd, ui_email)"
			+ "	values(seq_ui_num.nextval,?,?,?,?)";
	private String selectUser = "select ui_id, ui_pwd from user_info";		
	public int insertUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertUser);
			ps.setString(1, user.get("uiName"));
			ps.setString(2, user.get("uiId"));
			ps.setString(3, user.get("uiPwd"));
			ps.setString(4, user.get("uiEmail"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}

	public boolean selectUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectUser);
			ResultSet rs = ps.executeQuery();
			Map<String,String> cuser = new HashMap<>();
			while(rs.next()) {
			cuser.put("ui_id", rs.getString("ui_id"));
			cuser.put("ui_pwd", rs.getString("ui_pwd"));
			}
			if(cuser.equals(user)) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return false;
	}

	public static void main(String[] args) {
		UserDAO udao = new UserDAOImpl();
		Map<String, String> user = new HashMap();
		user.put("ui_id", "icarus");
		user.put("ui_pwd","123456728");
		System.out.println(udao.selectUser(user));
		
	}
}
