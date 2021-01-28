package model;

import java.io.Serializable;

/**
 * Servlet implementation class BuyerInfo
 */

public class BuyerInfo implements Serializable {

	private String name;
	private String password;

    public BuyerInfo() {
        super();
    }

    public BuyerInfo(String name, String password) {
    	this.name = name;
    	this.password = password;
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

}
