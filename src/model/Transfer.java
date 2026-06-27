package model;

public class Transfer {
	private Integer dstId;
	private Integer originId;
	private Integer balance;
	private Integer id;
	private Long date;

	public Transfer(Integer dstId, Integer originId, Integer balance, Long date) {
		super();
		this.dstId = dstId;
		this.originId = originId;
		this.balance = balance;
		this.date = date;
	}

	public Transfer() {

	}

	public Integer getDstId() {
		return dstId;
	}

	public void setDstId(Integer dstId) {
		this.dstId = dstId;
	}

	public Integer getOriginId() {
		return originId;
	}

	public void setOriginId(Integer originId) {
		this.originId = originId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer value) {
		this.balance = value;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
