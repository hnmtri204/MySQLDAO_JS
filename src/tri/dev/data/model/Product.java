package tri.dev.data.model;

import java.sql.Timestamp;

public class Product {
	public int id;
	public String name;
	public String thumbnail;
	public String description;
	public double price;
	public int quantity;
	public int view;
	public int category_id;
	public Timestamp createdAt;
	public Product(int id, String name, String thumbnail, String description, double price, int quantity, int view,
			int category_id, Timestamp createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.view = view;
		this.category_id = category_id;
		this.createdAt = createdAt;
	}

}