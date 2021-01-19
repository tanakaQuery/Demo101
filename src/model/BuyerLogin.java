package model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class BuyerLogin
 */
public class BuyerLogin {

	public boolean execute(HttpServletRequest request, Boolean loginOrNew) {
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

		if (loginOrNew.equals(true)) {
			BuyerInfo buyer = dao.find(buyerName);

			if (buyer != null)	{
				String buyerPW = buyer.getPassword();

				if (buyerPW.equals(password)) {
					session = request.getSession(true);
					session.setAttribute("buyer", buyer);
					state = true;
				} else {
					System.out.println("パスワードが違います");
				}
			} else {
				System.out.println("ログインデータがありません");
			}
		} else {
			dao.make(buyerName, password);

			session = request.getSession(true);
			BuyerInfo buyer = new BuyerInfo(buyerName, password, null);
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
