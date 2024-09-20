package vn.iotstar.services;

import com.mysql.cj.util.DnsSrv.SrvRecord;

import vn.iotstar.models.UserModel;

public interface IUserService {
	UserModel findByUserName(String username);
	UserModel login(String username, String password);
	void insert(UserModel user);
	boolean checkExistUsername(String username);
	void reset_password(UserModel user);
}
