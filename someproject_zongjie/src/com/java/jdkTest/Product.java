package com.java.jdkTest;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	
	private Long id;
	
	private String name;
	
	private String code;
	
	private Date createTime;
	
	private BigDecimal price=new BigDecimal("0.00");
	
	private String memo;
	
	
	public Product() {}

	public Product(Long id, String name, String code, Date createTime, BigDecimal price, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.createTime = createTime;
		this.price = price;
		this.memo = memo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	

}
