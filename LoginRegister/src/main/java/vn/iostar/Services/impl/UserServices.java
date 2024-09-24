package vn.iostar.Services.impl;

import java.util.List;

import vn.iostar.Dao.iUserDao;
import vn.iostar.Dao.impl.UserDaoImpl;
import vn.iostar.Models.UserModel;
import vn.iostar.Services.iUserServices;
import vn.iostar.configs.DBConnection;

public class UserServices extends DBConnection implements iUserServices{

	iUserDao userDao = new UserDaoImpl();
	@Override
	public UserModel login(String username, String password) {
		UserModel model = this.findByUserName(username);
		if(model != null && password.equals(model.getPassWord()))
		{
			return model;
		}
		return null;
	}

	@Override
	public UserModel findByUserName(String username) {
		
		return userDao.FindByUserName(username);
	}

	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String username, String password, String fullname, String email, String phone) {
		if(checkExistEmail(email))
		{
			System.out.println("Email đã tồn tại!");
			return false;
		}
		if(checkExistPhone(phone))
		{
			 System.out.println("Số điện thoại đã tồn tại!");
			return false;
		}
		if(checkExistUsername(username))
		{
			 System.out.println("Tên người dùng đã tồn tại!");
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		userDao.insert(new UserModel(0, email, username, fullname, password, null, 3, phone, date));
		System.out.println("Dang ky thanh cong !");
		return true;
		
	}

	@Override
	public boolean checkExistEmail(String email) {
		
		return userDao.checkExistMail(email);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean checkExistUsername(String username) {
		
		return userDao.checkExistUserName(username);
	}

	@Override
	public List<UserModel> findAll() {
		return userDao.findAll();
	}
	public static void main(String[] args) {
	    iUserServices userService = new UserServices();
	    String username = "tuong";
	    String password = "1";
	    String fullname = "hmt";
	    String email = "tuong2411@gmail.com";
	    String phone = "0987654321";

	    boolean isRegistered = userService.register(username, password, fullname, email, phone);

	    if (isRegistered) {
	        System.out.println("Đăng ký thành công!");
	    } else {
	        System.out.println("Đăng ký thất bại!");
	    }
	}

	

}
