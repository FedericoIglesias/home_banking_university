package model;

public class Card {

	private String number;
	private Integer limit;
	private Integer clientId;
	private Integer debt;

	public Card() {
	}

	public Card(String number, Integer limit, Integer clientId, Integer debt) {
		super();
		this.number = number;
		this.limit = limit;
		this.clientId = clientId;
		this.debt = debt;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getDebt() {
		return debt;
	}

	public void setDebt(Integer debt) {
		this.debt = debt;
	}

}
