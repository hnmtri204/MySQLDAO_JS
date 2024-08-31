package tri.dev.data.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tri.dev.data.dao.ProductDao;
import tri.dev.data.driver.MySQLDriver;
import tri.dev.data.model.Product;

public class ProductImpl implements ProductDao {
	@Override
	public boolean insert(Product product) {
		String sql = "INSERT INTO PRODUCTS(NAME, THUMBNAIL, DESCRIPTION, PRICE, QUANTITY, VIEW, CATEGORY_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.name);
			stmt.setString(2, product.thumbnail);
			stmt.setString(3, product.description);
			stmt.setDouble(4, product.price);
			stmt.setInt(5, product.quantity);
			stmt.setInt(6, product.view);
			stmt.setInt(7, product.category_id);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public boolean update(Product product) {
		String sql = "UPDATE PRODUCTS SET name = ?, thumbnail = ?, description = ?, price = ?, quantity = ?, view = ?, category_id = ? WHERE id = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.name);
			stmt.setString(2, product.thumbnail);
			stmt.setString(3, product.description);
			stmt.setDouble(4, product.price);
			stmt.setInt(5, product.quantity);
			stmt.setInt(6, product.view);
			stmt.setInt(7, product.category_id);
//	            stmt.setInt(3, category.id);
			stmt.setInt(3, 12);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM PRODUCTS WHERE ID = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Product find(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String thumbnail = rs.getString("thumbnail");
				String description = rs.getString("description");
				Double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int view = rs.getInt("view");
				int category_id = rs.getInt("category_id");
				Timestamp createAt = rs.getTimestamp("createAt");

				return new Product(id, name, thumbnail, description, price, quantity, view, category_id, createAt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		List<Product> productList = new ArrayList<Product>();
		String sql = "SELECT * FROM CATEGORIES";
		try {
			Connection conn = MySQLDriver.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String thumbnail = rs.getString("thumbnail");
				String description = rs.getString("description");
				Double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int view = rs.getInt("view");
				int category_id = rs.getInt("category_id");
				Timestamp createAt = rs.getTimestamp("createAt");

				productList.add(new Product(id, name, thumbnail, description, price, quantity, view, category_id, createAt));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

}