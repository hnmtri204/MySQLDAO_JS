package tri.dev.data.model;

public class User {
	public int id;
	public String email;
	public String password;
	public String role;
	public User(int id, String email, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}
}
