package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Servlet implementation class sellerInfoDAO
 */

public class SellerInfoDAO {
	private DBConnection db;

	public SellerInfoDAO(DBConnection db) {
		this.db = db;
	}

	public void update(String name, int houseID) throws Exception {
		String sql = "UPDATE sellers SET houseID = ? WHERE name = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, houseID);
		stmt.setString(2, name);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の売り別荘情報を更新しました。");
	}

	public void make(String name, String password) throws Exception {
		String sql = "INSERT INTO sellers VALUES(?, ?, ?)";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, password);
		stmt.setInt(3, 0);

		int count = stmt.executeUpdate();

		System.out.println(count + "件の売主アカウントを作成しました。");
	}

	public SellerInfo find(String name) throws Exception {
		SellerInfo seller = null;
		String sql = "SELECT name, password, houseID FROM sellers WHERE name = ?";
		Connection con = db.getConnect();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String sellerName = rs.getString("name");
			String password = rs.getString("password");
			int houseID = rs.getInt("houseID");

			seller = new SellerInfo(sellerName, password, houseID);
		}

		return seller;
	}
}