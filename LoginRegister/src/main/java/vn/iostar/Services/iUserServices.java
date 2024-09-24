package vn.iostar.Services;

import java.util.List;

import vn.iostar.Models.UserModel;

public interface iUserServices {
	List<UserModel> findAll();
	
	UserModel login(String username, String password);
	
	UserModel findByUserName(String username);

	void insert(UserModel user);
	
	boolean register(String username, String password, String fullname, String email, String phone);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistPhone(String phone);
	
	boolean checkExistUsername(String username); 
}
