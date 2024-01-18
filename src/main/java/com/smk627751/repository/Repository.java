package com.smk627751.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smk627751.model.Product;
import com.smk627751.model.User;

public class Repository {
	private static Repository repo;
	Connection con;
	private Repository()
	{
		try {
			Class.forName("org.postgresql.Driver");
			String dbURL = "jdbc:postgresql://localhost:5432/ecommerce";
			String user = "postgres";
			String pass = "627751";
			this.con = DriverManager.getConnection(dbURL, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Repository getInstance()
	{
		if(repo == null)
		{
			repo = new Repository();
		}
		return repo;
	}
	
	public User getUser(String email, String password)
	{
		Statement s;
		try {
			s = con.createStatement();
			String sql = "SELECT * FROM user_table WHERE email = '" + email + "' AND password = '" + password + "'";
			ResultSet rs = s.executeQuery(sql);
			boolean isEmpty = true;
			User username = null;
			while(rs.next())
			{
				isEmpty = false;
				username = new User(rs.getString("f_name"),rs.getString("l_name"),rs.getString("email"),rs.getString("password"));
			}
			return !isEmpty ? username : null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public User getUser(String email)
	{
		Statement s;
		try {
			s = con.createStatement();
			String sql = "SELECT * FROM user_table WHERE email = '" + email + "'";
			ResultSet rs = s.executeQuery(sql);
			boolean isEmpty = true;
			User username = null;
			while(rs.next())
			{
				isEmpty = false;
				username = new User(rs.getString("f_name"),rs.getString("l_name"),rs.getString("email"),rs.getString("password"));
			}
			return !isEmpty ? username : null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean addUser(User user)
	{
		String sql = "INSERT INTO user_table (f_name,l_name,email,password) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0 ? true : false; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Product> getProducts(String q)
	{
		List<Product> products = new ArrayList<>();
		try {
			String sql = "SELECT * FROM product_table WHERE name LIKE '%"+q+"%' OR catagory LIKE '%"+q+"%' AND quantity > 0";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next())
			{
				products.add(new Product(rs.getInt("id"),rs.getString("image"),rs.getString("name"),rs.getString("description"),rs.getString("catagory"),rs.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public Product getProductById(String id) {
		Product product = null;
		try {
			String sql = "SELECT * FROM product_table WHERE id ="+id+"";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next())
			{
				product = new Product(rs.getInt("id"),rs.getString("image"),rs.getString("name"),rs.getString("description"),rs.getString("catagory"),rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public boolean addToCart(String email, String productId) {
		String sql = "INSERT INTO cart_table (user_id,product_id) VALUES((SELECT id FROM user_table WHERE email='"+email+"'),"+productId+")";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0 ? true : false; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Product> getCartItems(String email) {
		List<Product> cartItems = new ArrayList<>();
		String sql = "select * from (select u.email,p.id,\r\n"
				+ "p.image,p.name,p.description,p.catagory,p.price \r\n"
				+ "from cart_table as c\r\n"
				+ "join product_table as p\r\n"
				+ "on c.product_id = p.id\r\n"
				+ "join user_table as u\r\n"
				+ "on c.user_id = u.id) as cart\r\n"
				+ "where email = '"+email+"'";
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next())
			{
				cartItems.add(new Product(rs.getInt("id"),rs.getString("image"),rs.getString("name"),rs.getString("description"),rs.getString("catagory"),rs.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItems;
	}
	public boolean removeCartItem(String email,String id)
	{
		String sql = "DELETE FROM cart_table WHERE user_id = (SELECT id FROM user_table WHERE email = ?) AND product_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, Integer.parseInt(id));
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	public boolean placeOrder(String email, String quantity, String product_id, String price) {
		try {
			String sql = "SELECT quantity FROM product_table WHERE id = "+product_id+"";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			int q = 0;
			while(rs.next())
			{
				q = rs.getInt("quantity");
			}
			if(q < Integer.parseInt(quantity))
			{
				return false;
			}
			sql = "UPDATE product_table SET quantity = "+(q - Integer.parseInt(quantity))+" WHERE id = "+product_id+"";
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsAffected = ps.executeUpdate();
			sql = "INSERT INTO orders_table (product_id,user_id,quantity,price) VALUES(? ,(SELECT id FROM user_table WHERE email = ? ) ,? ,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(product_id));
			ps.setString(2, email);
			ps.setInt(3, Integer.parseInt(quantity));
			ps.setInt(4, Integer.parseInt(price));
			rowsAffected = ps.executeUpdate();
			return rowsAffected > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
