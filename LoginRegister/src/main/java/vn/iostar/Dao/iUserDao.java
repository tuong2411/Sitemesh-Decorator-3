package vn.iostar.Dao;

import java.util.List;

import vn.iostar.Models.User;
import vn.iostar.Models.UserModel;

public interface iUserDao {
	List<UserModel> findAll();
	UserModel findID(int id);
	void insert(UserModel user);
	UserModel FindByUserName(String Username);
	void insert(User user);
	boolean checkExistMail(String email);
	boolean checkExistUserName(String username);
	boolean checkExistPhone(String phone);
}
