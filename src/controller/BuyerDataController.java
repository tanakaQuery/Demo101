package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SellerLogin;

/**
 * Servlet implementation class BuyerDataController
 */
@WebServlet("/BuyerDataController")
public class BuyerDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerDataController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = null;

    	String page = request.getParameter("page");

    	if (page.equals("login")) {
    		forwardPath = "./view/buyingLogin.jsp";
    	} else if (page.equals("new")) {
    		forwardPath = "./view/buyingNewAccount.jsp";
    	} else if (page.equals("start")) {
    		forwardPath = "./view/buyingStart.jsp";
    	} else if (page.equals("guest")) {
    		forwardPath = "./view/buyingHome.jsp";
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

		if (action.equals("LOGIN")) {

			if (login.execute(request, true) == true) {
				forwardPath = "./view/buyingHoume.jsp";
			} else {
				forwardPath = "./view/buyingStart.jsp";
				System.out.println("ログインエラー");
			}

		} else if (action.equals("NEW")) {

			if (login.execute(request, false) == true) {
				forwardPath = "./view/buyingHoume.jsp";
			} else {
				forwardPath = "./view/buyingStart.jsp";
				System.out.println("新規登録エラー");
			}
		} else {
			System.out.println("ページ移行エラー");
		}

		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
}

