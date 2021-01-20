package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SellerSell
 */
public class SellerSell {
	public boolean execute(HttpServletRequest request) {
		boolean state = false;

		String houseName = request.getParameter("name");
		String housePrice = request.getParameter("price");
		String houseImage = request.getParameter("image");

		DBConnection db = new DBConnection();

		HttpSession session = request.getSession(true);
		SellerInfo seller = (SellerInfo)session.getAttribute("seller");

		String ownerName = seller.getName();


		try {
			SellerInfoDAO sellerDAO = new SellerInfoDAO(db);
			HouseInfoDAO houseDAO = new HouseInfoDAO(db);

			houseDAO.make(houseName, housePrice, houseImage, ownerName);

			SoldHouseInfo house = houseDAO.find(ownerName);

			int houseID = house.getId();

			sellerDAO.update(ownerName, houseID);

			seller = sellerDAO.find(ownerName);

			session.setAttribute("seller", seller);

			session.setAttribute("house", house);

			state = true;

		} catch (Exception e) {
			state = false;
		} finally {
			try {
				db.closeConnect();
			} catch (Exception e) {
				state = false;
			}
		}
		return state;
	}

	public boolean getHouseData(HttpServletRequest request, int houseID) {
		boolean state = false;

		DBConnection db = new DBConnection();

		HttpSession session = request.getSession(true);

		try {
			HouseInfoDAO houseDAO = new HouseInfoDAO(db);

			SoldHouseInfo house = houseDAO.findFromID(houseID);

			session.setAttribute("house", house);

			state = true;

		} catch (Exception e) {
			state = false;
		} finally {
			try {
				db.closeConnect();
			} catch (Exception e) {
				state = false;
			}
		}

		return state;
	}

	public boolean deleteHouseData(HttpServletRequest request, int houseID) {
		boolean state = false;

		DBConnection db = new DBConnection();

		HttpSession session = request.getSession(true);

		SellerInfo seller = (SellerInfo)session.getAttribute("seller");
		String ownerName = seller.getName();

		try {
			SellerInfoDAO sellerDAO = new SellerInfoDAO(db);
			HouseInfoDAO houseDAO = new HouseInfoDAO(db);

			houseDAO.delete(houseID);

			sellerDAO.update(ownerName, 0);

			session.setAttribute("house", null);

			seller.setSellingHouseID(0);
			session.setAttribute("seller", seller);

			state = true;

		} catch (Exception e) {
			state = false;
		} finally {
			try {
				db.closeConnect();
			} catch (Exception e) {
				state = false;
			}
		}

		return state;
	}

}
