package model;

import java.io.Serializable;

/**
 * Servlet implementation class BuyerInfo
 */

public class BuyerInfo implements Serializable {

	private String name;
	private String password;
	private String boughtHouseID;

    public BuyerInfo() {
        super();
    }

    public BuyerInfo(String name, String password, String boughtHouseID) {
    	this.name = name;
    	this.password = password;
    	this.boughtHouseID = boughtHouseID;
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

	public String getBoughtHouseID() {
		return boughtHouseID;
	}

	public void setBoughtHouseID(String boughtHouseID) {
		this.boughtHouseID = boughtHouseID;
	}

}
