package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	UserModel findByUserName(String username);
	UserModel login(String username, String password);

}
