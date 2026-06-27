package model;

public class Account {
	private Integer balance;
	private String CBU;
	private String alias;
	private String type;
	private Integer Id;
	private Integer clientId;
	
	public Account() {
		super();
	}
	
	public Account(Integer balance, String CBU, String alias, String type, Integer id, Integer clientId) {
		super();
		this.balance = balance;
		this.CBU = CBU;
		this.alias = alias;
		this.type = type;
		this.Id = id;
		this.clientId = clientId;
	}
	
	public Account(Integer balance, String CBU, String alias, String type, Integer clientId) {
		super();
		this.balance = balance;
		this.CBU = CBU;
		this.alias = alias;
		this.type = type;
		this.clientId = clientId;
	}
	
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getCBU() {
		return CBU;
	}
	public void setCBU(String cBU) {
		CBU = cBU;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return this.Id;
	}
	public void setId(Integer id) {
		this.Id = id;
	}
	public Integer getClient() {
		return clientId;
	}
	public void setClient(Integer clientId) {
		this.clientId = clientId;
	}	
}
