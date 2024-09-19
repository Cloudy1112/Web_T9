package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl implements IUserDao {

	//Tim Username trong mysql
	@Override
	public UserModel findByUserName(String username) {
		String sql = "select * from users where username = ?";
		
		try {
			new DBConnectMySQL();
			Connection conn = DBConnectMySQL.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username); //truyền tham số
			ResultSet rs = ps.executeQuery(); //thực hiên phát biểu prepared rồi đưa vào resultset
			
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));

				return user;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	
		return null;
	}
	
	
	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl() ;
			System.out.println(userDao.findByUserName("van"));
			System.out.println(userDao.checkExistUsername("van"));
			
			UserModel user = new UserModel();
			user.setUsername("tutu");
			user.setPassword("321");
			
			
			userDao.insert(user);
			System.out.println(user.getUsername()+" "+user.getPassword());
			System.out.println(userDao.checkExistUsername("tutu"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean checkExistUsername(String username) {
		String sql = "select * from users where username = ? ";
		
		try {
			new DBConnectMySQL();
			Connection conn = DBConnectMySQL.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if ( rs.next()) {
				return true;
			}

			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}


	@Override
	public void insert(UserModel user) {
		String sql = "insert into users (username, password) values(?,?)";
		
		try {
			new DBConnectMySQL();
			Connection conn = DBConnectMySQL.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			String username = user.getUsername().toString();
			String pass = user.getPassword().toString();
			ps.setString(1, username);
			ps.setString(2, pass);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
