package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SellerLogin
 */
public class SellerLogin {
	public boolean execute(HttpServletRequest request, Boolean loginOrNew) {
		boolean state = false;

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		String sellerName = request.getParameter("ID");
		String password = request.getParameter("PW");

		DBConnection db = new DBConnection();

		try {
			SellerInfoDAO dao = new SellerInfoDAO(db);

			if (loginOrNew.equals(true)) {
				SellerInfo seller = dao.find(sellerName);

				if (seller != null)	{
					String sellerPW = seller.getPassword();

					if (sellerPW.equals(password)) {
						session = request.getSession(true);
						session.setAttribute("seller", seller);
						state = true;
					} else {
						System.out.println("パスワードが違います");
					}
				} else {
					System.out.println("ログインデータがありません");
				}
			} else {
				dao.make(sellerName, password);

				session = request.getSession(true);
				SellerInfo seller = new SellerInfo(sellerName, password, 0);
				session.setAttribute("seller", seller);
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
