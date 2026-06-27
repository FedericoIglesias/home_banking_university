package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{
	
	// private List<Account> listAccount;
	// private List<Card> listCard;
	
	public Client() {
		super();
	}
	
	public Client(Integer id,String name, String lastName, Integer dni, String pass,Boolean admin) {
		super(id, name, lastName, dni, pass, admin);
	}
	
	public Client(String name, String lastName, Integer dni, String pass,Boolean admin) {
		super(name, lastName, dni, pass,admin);
	}
	
	public Client(String name, String email, Integer dni,Boolean admin) {
		super(name, email, dni,admin);
	}

	public void setAccount() {
		// TODO account
	}

	public void setCard() {
		// TODO card
	}
	
	public List<Account> getListAccount(){
		List<Account> listAcc = new ArrayList<Account>();
		return listAcc; 
	}
	
	public List<Card> getListCard(){
		List<Card> listCard = new ArrayList<Card>();
		return listCard; 
	}
	
}
