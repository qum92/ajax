package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import db.DBCon;

public class MovieDAOImpl implements MovieDAO {
	private static final String selectMovieList = "select mi_num, mi_name, mi_year, mi_national, mi_vendor, mi_director from movie_info";
	private static final String insertMovie = "insert into movie_info(mi_num, mi_name, mi_year, mi_national, mi_vendor, mi_director)"
			+ " values(seq_mi_num.nextval,?,?,?,?,?)";
	private static final String updateMovie = "update movie_info set ?,?,?,?,? where mi_num=?";
	private static final String deleteMovie = "delete from movie_info where mi_num=?";
	private String selectMovieByMiNum = " select * from movie_info where mi_num=?";
	public List<Map<String, String>> selectMovieList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectMovieList);
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> mList = new ArrayList<>();
			while(rs.next()) {
			Map<String,String> m = new HashMap<>();
			m.put("mi_num", rs.getString("mi_num"));
			m.put("mi_name", rs.getString("mi_name"));
			m.put("mi_year", rs.getString("mi_year"));
			m.put("mi_national", rs.getString("mi_national"));
			m.put("mi_vendor", rs.getString("mi_vendor"));
			m.put("mi_director", rs.getString("mi_director"));
			mList.add(m);
			}
			return mList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}
	public int insertMovie(Map<String, String> movie) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertMovie);
			ps.setString(1, movie.get("mi_name"));
			ps.setString(2, movie.get("mi_year"));
			ps.setString(3, movie.get("mi_national"));
			ps.setString(4, movie.get("mi_vendor"));
			ps.setString(5, movie.get("mi_director"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}

	public int updateMovie(Map<String, String> movie) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(updateMovie);
			int cnt = 1;
			if(movie.get("mi_name")==null) {
			ps.setString(cnt++, movie.get("mi_name"));
			}else if(movie.get("mi_year")==null) {
			ps.setString(cnt++, movie.get("mi_year"));
			}else if(movie.get("mi_national")==null) {
			ps.setString(cnt++, movie.get("mi_national"));
			}else if(movie.get("mi_vendor")==null) {
			ps.setString(cnt++, movie.get("mi_vendor"));
			}else if(movie.get("mi_director")==null) {
			ps.setString(cnt++, movie.get("mi_director"));
			}else {
			ps.setString(cnt++, movie.get("mi_num"));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}

	public int deleteMovie(int miNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(deleteMovie);
			ps.setInt(1, miNum);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}		
		return 0;
	}

	public Map<String, String> selectMovieByNum(int miNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectMovieByMiNum);
			ps.setInt(1, miNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			Map<String,String> m = new HashMap<>();
			m.put("mi_num", rs.getString("mi_num"));
			m.put("mi_name", rs.getString("mi_name"));
			m.put("mi_year", rs.getString("mi_year"));
			m.put("mi_national", rs.getString("mi_national"));
			m.put("mi_vendor", rs.getString("mi_vendor"));
			m.put("mi_director", rs.getString("mi_director"));
			return m;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}		
}
