package tri.dev.data.model;

import java.sql.Timestamp;

public class Order {
	public int id;
	public String code;
	public String status;
	public int user_id;
	private Timestamp createdAt;
	
	public Order(int id, String code, String status, int user_id, Timestamp createdAt) {
		super();
		this.id = id;
		this.code = code;
		this.status = status;
		this.user_id = user_id;
		this.createdAt = createdAt;
	}	
}