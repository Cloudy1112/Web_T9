package vn.iotstar.dao;

import vn.iotstar.models.UserModel;

public interface IUserDao {
	//khai báo các hàm và thủ tục
	//tầng DAO là tầg querry dữ liệu, nếu tìm thấy sẽ trả kết quả cho tầng service
	
	UserModel findByUserName(String username);
	boolean checkExistUsername(String username);
	void insert (UserModel user);
}
