package com.hackersnews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.CommentController;
import controllers.NoticeController;
import models.Notice;
import models.Session;
import models.User;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Notice parentNotice = NoticeController.searchNotice(Integer.parseInt(request.getParameter("parent")));
		User user = ((Session) request.getSession().getAttribute("sessionUser")).getUser();
		String text = request.getParameter("text");
		if (!text.equals("")) {
			CommentController.newComment(user, parentNotice, null, text);
		}
		response.sendRedirect(request.getContextPath() + "/item?id=" + parentNotice.getId());
	}

}