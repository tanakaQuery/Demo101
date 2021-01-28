package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class BuyerInfoDAO
 */
public class BuyerInfoDAO {
	private DBConnection db;

	public BuyerInfoDAO(DBConnection db) {
		this.db = db;
	}

	public void removeInquiryByHouseID(int houseID) throws Exception {
		String sql = "DELETE FROM inquiries WHERE houseID = ? ";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);

		int count = stmt.executeUpdate();

		System.out.println(count + "物件の問い合わせデータを削除しました。");
	}

	public void removeInquiry(String name, int houseID) throws Exception {
		String sql = "DELETE FROM inquiries WHERE houseID = ? AND buyerName = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);
		stmt.setString(2, name);

		int count = stmt.executeUpdate();

		System.out.println(count + "物件問い合わせデータを削除しました。");
	}

	public void setInquiry(String name, int houseID) throws Exception {
		String sql = "INSERT INTO inquiries(houseID, buyerName) VALUES( ?, ? )";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);
		stmt.setString(2, name);

		int count = stmt.executeUpdate();

		System.out.println(count + "物件問い合わせデータを登録しました。");
	}


//	public ArrayList<BuyerInfo> findBuyerName(int id) throws Exception {
//		ArrayList<BuyerInfo> buyerArray = new ArrayList<BuyerInfo>();
//		String sql = "SELECT name, password, houseID FROM buyers WHERE houseID = ?";
//		Connection con = db.getConnect();
//
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setInt(1, id);
//
//		ResultSet rs = stmt.executeQuery();
//
//		while (rs.next()) {
//			String buyerName = rs.getString("name");
//			String password = rs.getString("password");
//			int houseID = rs.getInt("houseID");
//
//			BuyerInfo buyer = new BuyerInfo(buyerName, password);
//
//			buyerArray.add(buyer);
//		}
//
//		return buyerArray;
//	}

//	public void update(String name, int houseID) throws Exception {
//		String sql = "UPDATE buyers SET houseID = ? WHERE name = ?";
//		Connection con = db.getConnect();
//
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setInt(1, houseID);
//		stmt.setString(2, name);
//
//		int count = stmt.executeUpdate();
//
//		System.out.println(count + "件の購入別荘情報を更新しました。");
//	}

	public void make(String name, String password) throws Exception {
		String sql = "INSERT INTO buyers VALUES(?, ?)";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, password);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の購入者アカウントを作成しました。");
	}

	public BuyerInfo find(String name) throws Exception {
		BuyerInfo buyer = null;
		String sql = "SELECT name, password FROM buyers WHERE name = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String buyerName = rs.getString("name");
			String password = rs.getString("password");

			buyer = new BuyerInfo(buyerName, password);
		}
		return buyer;
	}
}
