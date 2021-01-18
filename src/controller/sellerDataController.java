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
 * Servlet implementation class sellerDataController
 */
@WebServlet("/sellerDataController")
public class sellerDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerDataController() {
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
    		forwardPath = "./view/sellingLogin.jsp";
    	} else if (page.equals("new")) {
    		forwardPath = "./view/sellingNewAccount.jsp";
    	} else if (page.equals("start")) {
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

		if (action.equals("LOGIN")) {

			if (login.execute(request, true) == true) {
				forwardPath = "./view/sellingMyPage.jsp";
			} else {
				forwardPath = "./view/sellingStart.jsp";
				System.out.println("ログインエラー");
			}

		} else if (action.equals("NEW")) {

			if (login.execute(request, false) == true) {
				forwardPath = "./view/sellingMyPage.jsp";
			} else {
				forwardPath = "./view/sellingStart.jsp";
				System.out.println("新規登録エラー");
			}
		} else {
			System.out.println("ページ移行エラー");
		}

		System.out.println(forwardPath);

		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}
}

