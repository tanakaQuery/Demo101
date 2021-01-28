package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BuyerLogin;
import model.SoldHouseInfo;

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
    	} else if (page.equals("detail")) {
    		String id = request.getParameter("id");
    		HttpSession session = request.getSession(true);
    		ArrayList<SoldHouseInfo> houseArray = (ArrayList<SoldHouseInfo>)session.getAttribute("houseArray");
    		SoldHouseInfo pickedHouse = null;
    		for (SoldHouseInfo house : houseArray) {
    			String pickedHouseID = String.valueOf(house.getId());
    			if (pickedHouseID.equals(id)) {
    				pickedHouse = house;
    			}
    			session.setAttribute("houseDetail", pickedHouse);
    		}

    		if (pickedHouse != null) {
    			forwardPath = "./view/buyingHouseDetail.jsp";
    		} else {
    			forwardPath = "./view/buyingHome.jsp";
    		}

    	} else if (page.equals("logout")) {
    		HttpSession session = request.getSession(false);

    		if (session != null) {
    			session.invalidate();
    		}
    		forwardPath =  "./view/buyingStart.jsp";
    	} else if (page.equals("home")){
    		BuyerLogin login = new BuyerLogin();
    		login.fetchHouseData(request);
    		forwardPath = "./view/buyingHome.jsp";
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
		} else if (action.equals("BUY")) {
			if (login.setHouseID(request) == true) {
				forwardPath = "./view/buyingNotificationPage.jsp";
			} else {
				forwardPath = "./view/buyingHome.jsp";
				System.out.println("購入検討依頼エラー");
			}
		} else if (action.equals("CANCEL")) {
			if (login.removeHouseID(request) == true) {
				forwardPath = "./view/buyingNotificationPage.jsp";
			} else {
				forwardPath = "./view/buyingHome.jsp";
				System.out.println("購入検討取り消しエラー");
			}
		} else {
			System.out.println("ページ移行エラー");
		}

		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
}

