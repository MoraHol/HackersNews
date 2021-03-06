package com.hackersnews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hackersnews.controller.CommentController;
import com.hackersnews.controller.NoticeController;
import com.hackersnews.dao.CommentDaoImpl;
import com.hackersnews.dao.NoticeDaoImpl;
import com.hackersnews.idao.ICommentDao;
import com.hackersnews.idao.INoticeDao;
import com.hackersnews.model.Comment;
import com.hackersnews.model.Notice;
import com.hackersnews.model.Session;
import com.hackersnews.model.User;

/**
 * Servlet implementation class Vote
 */
@WebServlet("/vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		INoticeDao noticeDao = new NoticeDaoImpl();
		NoticeController noticeController = new NoticeController();
		CommentController commentController = new CommentController();
		ICommentDao commentDao = new CommentDaoImpl();
		try {
		User user = ((Session) request.getSession().getAttribute("sessionUser")).getUser();
		
		if (noticeController.searchNotice(Integer.parseInt(request.getParameter("id"))) != null) {
			Notice item = noticeController.searchNotice(Integer.parseInt(request.getParameter("id")));
			if (request.getParameter("how").equals("up")) {
				noticeDao.rateNotice(user, item);
			} else {
				noticeDao.removePoint(user, item);
			}
			response.sendRedirect(request.getContextPath() + "/newest");
		} else if (commentController.searchComment(Integer.parseInt(request.getParameter("id"))) != null) {
			Comment item = commentController.searchComment(Integer.parseInt(request.getParameter("id")));
			if (request.getParameter("how").equals("up")) {
				commentDao.rateComment(user, item);
			} else {
				commentDao.removePoint(user, item);
			}
			response.sendRedirect(request.getContextPath() + "/item?id="+item.getParentNotice().getId());
		}
		}catch (Exception e) {
			// TODO: handle exception
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
