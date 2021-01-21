package model;

import java.io.Serializable;

/**
 * Servlet implementation class sellerInfo
 */

public class SellerInfo implements Serializable  {

	private String name;
	private String password;
	private int sellingHouseID;

    public SellerInfo() {
        super();
    }

    public SellerInfo(String name, String password, int sellingHouseID) {
    	this.name = name;
    	this.password = password;
    	this.sellingHouseID = sellingHouseID;
    }


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSellingHouseID() {
		return sellingHouseID;
	}

	public void setSellingHouseID(int sellingHouseID) {
		this.sellingHouseID = sellingHouseID;
	}
}
