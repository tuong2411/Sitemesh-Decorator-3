package vn.iostar.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.Models.User;
import vn.iostar.Models.UserModel;
import vn.iostar.Dao.iUserDao;
import vn.iostar.configs.DBConnection;

public class UserDaoImpl extends DBConnection implements iUserDao {
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * fROM Users";
		List<UserModel> list = new ArrayList<UserModel>();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new UserModel(rs.getInt("id"), rs.getString("email"), rs.getString("username"), rs.getString("fullname"),
						rs.getString("password"), rs.getString("avatar"), rs.getInt("roleid"), rs.getString("phone"), rs.getDate("createdDate")));
				return list;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserModel findID(int id) {
		
		String sql = "SELECT * fROM Users WHERE id = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				UserModel model = new UserModel();
				model.setId(rs.getInt("id"));
				model.setEmail(rs.getString("email"));
				model.setUserName(rs.getString("username"));
				model.setFullName(rs.getString("fullname"));
				model.setPassWord(rs.getString("password"));
				model.setAvatar(rs.getString("avatar"));
				model.setRoleid(Integer.parseInt(rs.getString("roleid")));
				model.setPhone(rs.getString("phone"));
				model.setCreatedDate(rs.getDate("createdDate"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void insert(UserModel user) {
		
		String sql = "INSERT INTO Users(username, fullname, password, email, avatar, roleid, phone, createdDate) VALUES(?,?,?,?,?,?,?,?)";
		try 
		{
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getAvatar());
			ps.setString(4, user.getFullName());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getRoleid());
			ps.setDate(8, user.getCreatedDate());
			
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public UserModel FindByUserName(String Username) {
		String sql = "SELECT * FROM Users WHERE username = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, Username);
			rs = ps.executeQuery();
			while(rs.next())
			{
				UserModel model = new UserModel();
				model.setId(rs.getInt("id"));
				model.setEmail(rs.getString("email"));
				model.setUserName(rs.getString("username"));
				model.setFullName(rs.getString("fullname"));
				model.setPassWord(rs.getString("password"));
				model.setAvatar(rs.getString("avatar"));
				model.setRoleid(rs.getInt("roleid"));
				model.setPhone(rs.getString("phone"));
				model.setCreatedDate(rs.getDate("createdDate"));
				return model;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		try {
			iUserDao userDao = new UserDaoImpl();
			System.out.println(userDao.FindByUserName("manhtuong"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void insert(User user) {
		
		
	}

	@Override
	public boolean checkExistMail(String email) {
		
		boolean check = false;
		String query = "Select * from Users where email = ?";
		try {
			
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next())
			{
				check = true;
				
			}
			ps.close();
			conn.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return check;
		
	}

	@Override
	public boolean checkExistUserName(String username) {
		
		boolean check = false;
		String query = "Select * from Users where username = ?";
		try {
			
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next())
			{
				check = true;
				
			}
			ps.close();
			conn.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		
		boolean check = false;
		String query = "Select * from Users where phone = ?";
		try {
			
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			while(rs.next())
			{
				check = true;
				
			}
			ps.close();
			conn.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return check;
	}
    
    
}
