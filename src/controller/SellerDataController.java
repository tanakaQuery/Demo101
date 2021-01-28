package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SellerInfo;
import model.SellerLogin;
import model.SellerSell;


/**
 * Servlet implementation class sellerDataController
 */
@WebServlet("/SellerDataController")
public class SellerDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerDataController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = null;

    	String page = request.getParameter("page");

    	if (page.equals("login")) {
    		forwardPath = "./view/sellingLogin.jsp";
    	} else if (page.equals("new")) {
    		forwardPath = "./view/sellingNewAccount.jsp";
    	} else if (page.equals("start")) {
    		forwardPath = "./view/sellingStart.jsp";
    	} else if (page.equals("mypage")) {
    		forwardPath = "./view/sellingMyPage.jsp";
    	} else if (page.equals("logout")) {
    		HttpSession session = request.getSession(false);

    		if (session != null) {
    			session.invalidate();
    		}
    		forwardPath =  "./view/sellingStart.jsp";
    	} else {
    		forwardPath = "./view/sellingStart.jsp";
    	}

    	RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("ACTION");

		String forwardPath = null;

		SellerLogin login = new SellerLogin();
		SellerSell sell = new SellerSell();

		if (action.equals("LOGIN")) {

			if (login.execute(request, true) == true) {
				HttpSession session = request.getSession(true);

				SellerInfo seller = (SellerInfo)session.getAttribute("seller");

				int houseID = seller.getSellingHouseID();

				if ( houseID != 0) {
					if (sell.getHouseData(request, houseID) == true) {
		    			forwardPath = "./view/sellingMyPage.jsp";
					} else {
						forwardPath = "./view/sellingStart.jsp";
						System.out.println("売却中の別荘データ取得エラー");
					}
				} else {
					forwardPath = "./view/sellingMyPage.jsp";
					System.out.println("売却中の別荘はありません");
	    		}
			} else {
				request.setAttribute("errorLog", "ユーザー名もしくはパスワードが違います");
				forwardPath = "./view/sellingLogin.jsp";
				System.out.println("ログインエラー");
			}

		} else if (action.equals("NEW")) {

			if (login.execute(request, false) == true) {
				forwardPath = "./view/sellingMyPage.jsp";
			} else {
				request.setAttribute("errorLog", "入力したユーザー名はすでに使われております");
				forwardPath = "./view/sellingNewAccount.jsp";
				System.out.println("新規登録エラー");
			}
		} else if (action.equals("SELL")) {

			if (sell.execute(request) == true) {
				forwardPath = "./view/sellingNotificationPage.jsp";
			} else {
				forwardPath = "./view/sellingMyPage.jsp";
				System.out.println("売り出しエラー");
			}
		} else if (action.equals("DELETE")) {
			HttpSession session = request.getSession(true);

			SellerInfo seller = (SellerInfo)session.getAttribute("seller");

			int houseID = seller.getSellingHouseID();

			if (sell.deleteHouseData(request, houseID)) {
				forwardPath = "./view/sellingNotificationPage.jsp";
			} else {
				forwardPath = "./view/sellingMyPage.jsp";
				System.out.println("物件削除エラー");
			}
		} else {
			System.out.println("ページ移行エラー");
		}

		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
}

