package com.jspiders.cardekho_case_study_jdbc.entity;

public class Car {
	private  int carID ;
	private  String carName;
	private  String brandName;
	private  String fuelType;
	private  float price;
	
	
	public String getCarName() {
		return carName;
	}
	@Override
	public String toString() {
		return "Car [carName=" + carName + ", price=" + price + ", brandName=" + brandName + ", fuelType=" + fuelType
				+ ", carID=" + carID + "]";
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	

}
