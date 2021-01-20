package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BuyerLogin;

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
    		HttpSession session = request.getSession(false);

    		if (session != null) {
    			session.invalidate();
    		}

    		BuyerLogin login = new BuyerLogin();
    		login.fetchHouseData(request);
    		forwardPath = "./view/buyingHome.jsp";
    	} else if (page.equals("logout")) {
    		HttpSession session = request.getSession(false);

    		if (session != null) {
    			session.invalidate();
    		}
    		forwardPath =  "./view/buyingStart.jsp";
    	} else {
    		forwardPath =  "./view/buyingStart.jsp";
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

		BuyerLogin login = new BuyerLogin();

		if (action.equals("LOGIN")) {

			if (login.execute(request, true) == true) {
				login.fetchHouseData(request);
				forwardPath = "./view/buyingHome.jsp";
			} else {
				forwardPath = "./view/buyingStart.jsp";
				System.out.println("ログインエラー");
			}

		} else if (action.equals("NEW")) {

			if (login.execute(request, false) == true) {
				login.fetchHouseData(request);
				forwardPath = "./view/buyingHome.jsp";
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

