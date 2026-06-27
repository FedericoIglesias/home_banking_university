package model;

public class Admin extends User{
	
	public Admin() {
	}
	
	public Admin(String name, String lastName, Integer dni, String pass,Boolean admin) {
		super(name, lastName, dni, pass,admin);
	}
	
	public Admin(String name, String email, Integer dni,Boolean admin) {
		super(name, email, dni,admin);
	}
	public void makeClient(Client client) {
		// TODO client
	}
	
	public void makeAccount(String account) {
		// TODO account
	}
	

}
