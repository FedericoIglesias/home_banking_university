package model;

abstract class User {
	private String name;
	private String email;
	private Integer dni;
	private String pass;
	private Integer id;
	private Boolean admin;
	
	public User() {
	}
	
	public User(String name, String email, Integer dni,Boolean admin) {
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.admin = admin;
	}
	
	public User(Integer id,String name, String email, Integer dni, String pass, Boolean admin) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.pass = pass;
		this.admin = admin;
	}
	
	public User(String name, String email, Integer dni, String pass, Boolean admin) {
		super();
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.pass = pass;
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setId(Integer Id) {
		this.id = Id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
}
