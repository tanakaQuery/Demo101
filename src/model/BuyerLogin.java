package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyerLogin
 */
public class BuyerLogin {

	public boolean removeHouseID(HttpServletRequest request) {
		boolean state = false;

		DBConnection db = new DBConnection();

		HttpSession session = request.getSession(true);
		BuyerInfo buyer = (BuyerInfo) session.getAttribute("buyer");
		SoldHouseInfo pickedHouse = (SoldHouseInfo) session.getAttribute("houseDetail");

		try {
			BuyerInfoDAO buyerDAO = new BuyerInfoDAO(db);

			if (buyer != null) {
				String loginName = buyer.getName();
				int pickedHouseID = pickedHouse.getId();

				buyerDAO.removeInquiry(loginName, pickedHouseID);

				session.setAttribute("isSetHouseID", false);

				state = true;
			} else {
				state = false;
			}

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


	public boolean setHouseID(HttpServletRequest request) {
		boolean state = false;

		DBConnection db = new DBConnection();

		HttpSession session = request.getSession(true);
		BuyerInfo buyer = (BuyerInfo) session.getAttribute("buyer");
		SoldHouseInfo pickedHouse = (SoldHouseInfo) session.getAttribute("houseDetail");

		try {
			BuyerInfoDAO buyerDAO = new BuyerInfoDAO(db);

			if (buyer != null) {
				String loginName = buyer.getName();
				int pickedHouseID = pickedHouse.getId();
				//buyerDAO.update(loginName, pickedHouseID);
				buyerDAO.setInquiry(loginName, pickedHouseID);

				session.setAttribute("isSetHouseID", true);

				//buyer.setBoughtHouseID(pickedHouseID);
				//session.setAttribute("buyer", buyer);
				state = true;
			} else {
				state = false;
			}

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

	public boolean fetchHouseData(HttpServletRequest request) {
		boolean state = false;

		DBConnection db = new DBConnection();

		HttpSession session = request.getSession(true);
		BuyerInfo buyer = (BuyerInfo) session.getAttribute("buyer");

		try {
			HouseInfoDAO houseDAO = new HouseInfoDAO(db);
			ArrayList<SoldHouseInfo> houseArray;
			ArrayList<InquiryInfo> inquiryArray;

			if (buyer != null) {
				String loginName = buyer.getName();
				houseArray = houseDAO.findAll();
				inquiryArray = houseDAO.findAllInquiries(loginName);
			} else {
				houseArray = houseDAO.findAll();
				inquiryArray = null;
			}

			session.setAttribute("houseArray", houseArray);
			session.setAttribute("inquiryArray", inquiryArray);
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

	public boolean execute(HttpServletRequest request, Boolean isLogin) {
		boolean state = false;

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		String buyerName = request.getParameter("ID");
		String password = request.getParameter("PW");

		DBConnection db = new DBConnection();

		try {
			BuyerInfoDAO dao = new BuyerInfoDAO(db);

			if (isLogin.equals(true)) {
				BuyerInfo buyer = dao.find(buyerName);

				if (buyer != null) {
					String buyerPW = buyer.getPassword();

					if (buyerPW.equals(password)) {
						session = request.getSession(true);
						session.setAttribute("buyer", buyer);
						state = true;
					} else {
						state = false;
						System.out.println("パスワードが違います");
					}
				} else {
					state = false;
					System.out.println("ログインデータがありません");
				}
			} else {
				dao.make(buyerName, password);

				session = request.getSession(true);
				BuyerInfo buyer = new BuyerInfo(buyerName, password, 0);
				session.setAttribute("buyer", buyer);
				state = true;
			}
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
