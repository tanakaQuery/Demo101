package model;

import java.io.Serializable;

/**
 * Servlet implementation class sellerInfo
 */

public class SellerInfo implements Serializable  {

	private String name;
	private String password;
	private String sellingHouseID;

    public SellerInfo() {
        super();
    }

    public SellerInfo(String name, String password,
    		String sellingHouseID) {
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

	public String getSellingHouseID() {
		return sellingHouseID;
	}

	public void setSellingHouseID(String sellingHouseID) {
		this.sellingHouseID = sellingHouseID;
	}
}
