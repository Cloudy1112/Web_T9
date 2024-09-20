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

	// so sanh tai khoan va nhap khau da nhap voi database
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
			System.out.println(userService.checkExistUsername("thu1"));
			
			/*
			 * UserModel user = new UserModel(); user.setUsername("tutu");
			 * user.setPassword("321"); userService.insert(user);
			 */
			
			System.out.println(userService.checkExistUsername("tutu"));
			System.out.println(userService.checkExistUsername("van"));
			
			UserModel user = new UserModel(); user.setUsername("tutu");
			user.setPassword("321");
			
			userService.reset_password(user);
			System.out.println("sau khi reset "+userService.login("van", "123"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(UserModel user) {
		userdao.insert(user);
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		return userdao.checkExistUsername(username);
	}

	@Override
	public void reset_password(UserModel user) {
		userdao.reset_password(user);
	}

}
