package model;

import java.io.Serializable;


public class InquiryInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int inquiryId;
	private int houseId;
	private String buyerName;


    public InquiryInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


	public InquiryInfo(int inquiryId, int houseId, String buyerName) {
		super();
		this.inquiryId = inquiryId;
		this.houseId = houseId;
		this.buyerName = buyerName;
	}


	public int getInquiryId() {
		return inquiryId;
	}


	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}


	public int getHouseId() {
		return houseId;
	}


	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}


	public String getBuyerName() {
		return buyerName;
	}


	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}


}
