package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Servlet implementation class HouseInfoDAO
 */
public class HouseInfoDAO {
	private DBConnection db;

	public HouseInfoDAO(DBConnection db) {
		this.db = db;
	}

	public ArrayList<InquiryInfo> findAllInquiriesByID(int houseID) throws Exception {
		String sql = "SELECT * FROM inquiries WHERE houseID = ?";

		ArrayList<InquiryInfo> inquiryArray = new ArrayList<InquiryInfo>();

		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			int houseid = rs.getInt("houseID");
			String buyerName = rs.getString("buyerName");

			InquiryInfo inquiry = new InquiryInfo(id, houseid, buyerName);

			inquiryArray.add(inquiry);
		}

		return inquiryArray;
	}

	public ArrayList<InquiryInfo> findAllInquiries(String loginName) throws Exception {
		String sql = "SELECT * FROM inquiries WHERE buyerName = ?";

		ArrayList<InquiryInfo> inquiryArray = new ArrayList<InquiryInfo>();

		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, loginName);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			int houseID = rs.getInt("houseID");
			String buyerName = rs.getString("buyerName");

			InquiryInfo inquiry = new InquiryInfo(id, houseID, buyerName);

			inquiryArray.add(inquiry);
		}

		return inquiryArray;
	}

	public ArrayList<SoldHouseInfo> findAll() throws Exception {
		String sql = "SELECT * FROM houses ";

		ArrayList<SoldHouseInfo> houseArray = new ArrayList<SoldHouseInfo>();

		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String houseName = rs.getString("houseName");
			String housePrice = rs.getString("housePrice");
			String houseImage = rs.getString("houseImage");
			String ownerName = rs.getString("ownerName");

			SoldHouseInfo house = new SoldHouseInfo(id, houseName, housePrice, houseImage, ownerName);

			houseArray.add(house);
		}

		return houseArray;
	}

	public void make(String houseName, String housePrice, String houseImage, String ownerName) throws Exception {
		String sql = "INSERT INTO houses(houseName, housePrice, houseImage, ownerName) VALUES( ?, ?, ?, ?)";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, houseName);
		stmt.setString(2, housePrice);
		stmt.setString(3, houseImage);
		stmt.setString(4, ownerName);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の売り出し別荘情報を登録しました。");
	}

//別荘登録後のIDを登録オーナー名から検索する
	public SoldHouseInfo find(String ownerName) throws Exception {
		SoldHouseInfo house = null;
		String sql = "SELECT id, houseName, housePrice, houseImage FROM houses WHERE ownerName = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ownerName);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			String houseName = rs.getString("houseName");
			String housePrice = rs.getString("housePrice");
			String houseImage = rs.getString("houseImage");

			house = new SoldHouseInfo(id, houseName, housePrice, houseImage, ownerName);

		}

		return house;
	}


	public SoldHouseInfo findFromID(int houseID) throws Exception {
		SoldHouseInfo house = null;
		String sql = "SELECT id, houseName, housePrice, houseImage, ownerName FROM houses WHERE id = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			String houseName = rs.getString("houseName");
			String housePrice = rs.getString("housePrice");
			String houseImage = rs.getString("houseImage");
			String ownerName = rs.getString("ownerName");

			house = new SoldHouseInfo(id, houseName, housePrice, houseImage, ownerName);

		}

		return house;
	}

	public void delete(int houseID) throws Exception {
		String sql = "DELETE FROM houses WHERE id = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の売り出し別荘を消去しました。");

	}
}
