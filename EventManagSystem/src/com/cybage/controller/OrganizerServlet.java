package com.cybage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybage.model.Organizer;
import com.cybage.dao.OrganizerDAO;

@WebServlet("/organizerServlet")
public class OrganizerServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		private OrganizerDAO organizerDAO;
		
		public void init() {
			organizerDAO = new OrganizerDAO();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertOrganizer(request, response);
					break;
				case "/delete":
					deleteOrganizer(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateOrganizer(request, response);
					break;
				default:
					listOrganizer(request, response);
					break;
				}
			}
			
			catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

		private void listOrganizer(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Organizer> listOrganizer = organizerDAO.selectAllOrganizer();
			request.setAttribute("listOrganizer", listOrganizer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("organizer-list.jsp");
			dispatcher.forward(request, response);
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("organizer-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Organizer existingOrganizer = organizerDAO.selectOrganizer(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("organizer", existingOrganizer);
			dispatcher.forward(request, response);
		}

		private void insertOrganizer(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			Organizer newOrganizer = new Organizer(name, email, country);
			organizerDAO.insertOrganizer(newOrganizer);
			response.sendRedirect("list");
		}

		private void updateOrganizer(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("country");

			Organizer book = new Organizer(id, name, email, country);
			organizerDAO.updateOrganizer(book);
			response.sendRedirect("list");
		}

		private void deleteOrganizer(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			organizerDAO.deleteOrganizer(id);
			response.sendRedirect("list");
		}
	}

	

