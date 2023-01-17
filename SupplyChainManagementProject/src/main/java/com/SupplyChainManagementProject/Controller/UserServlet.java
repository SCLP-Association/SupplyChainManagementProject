package com.SupplyChainManagementProject.Controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.SupplyChainManagementProject.Core.DbConnection.PostgreSqlDbConnection;
import com.SupplyChainManagementProject.DAO.concretes.UserDAOImpl;
import com.SupplyChainManagementProject.Model.User;
import com.SupplyChainManagementProject.Service.abstracts.IUserService;
import com.SupplyChainManagementProject.Service.concretes.UserServiceImpl;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/api/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IUserService userService;

    public void init() {
        userService = new UserServiceImpl(null, null, null, null);
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
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < User > listUser = userDAOImpl.getAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
     {
        int id = Integer.parseInt(request.getParameter("id"));
        List<User> existingUser = userDAOImpl.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");
        
        User newUser = new User(name, email, phoneNumber,password);
        userDAOImpl.add(newUser);
        try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
     {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");

        User book = new User(id, name, email, phoneNumber,password);
        userDAOImpl.update(id,book);
        try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
     {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAOImpl.delete(id);
        try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
