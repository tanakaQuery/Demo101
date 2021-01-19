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

	public void update(String name, String houseID) throws Exception {
		String sql = "UPDATE buyers SET houseID = ? WHERE name = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, houseID);
		stmt.setString(2, name);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の購入別荘情報を更新しました。");
	}

	public void make(String name, String password) throws Exception {
		String sql = "INSERT INTO buyers VALUES(?, ?, ?)";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, password);
		stmt.setString(3, null);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の購入者アカウントを作成しました。");
	}

	public BuyerInfo find(String name) throws Exception {
		BuyerInfo buyer = null;
		String sql = "SELECT name, password, houseID FROM buyers WHERE name = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String buyerName = rs.getString("name");
			String password = rs.getString("password");
			String houseID = rs.getString("houseID");

			buyer = new BuyerInfo(buyerName, password, houseID);
		}
		return buyer;
	}
}
