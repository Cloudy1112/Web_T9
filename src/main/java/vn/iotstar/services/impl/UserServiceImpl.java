package vn.iotstar.services.impl;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao userdao = new UserDaoImpl();

	@Override
	public UserModel findByUserName(String username) {
		return userdao.findByUserName(username);

	}

	//so sanh tai khoan va nhap khau da nhap voi database
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}

		return null;
	}

	public static void main(String[] args) {
		try {
			IUserService userService = new UserServiceImpl();
			System.out.println(userService.login("van", "123"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
