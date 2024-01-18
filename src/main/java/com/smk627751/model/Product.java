package com.smk627751.model;

public class Product {
	private int id;
	private String image;
	private String name;
	private String description;
	private String catagory;
	private	int price;
	
	public Product(int id, String image,String name, String description, String catagory,int price)
	{
		this.setId(id);
		this.setImage(image);
		this.setName(name);
		this.setDescription(description);
		this.setCatagory(catagory);
		this.setPrice(price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
