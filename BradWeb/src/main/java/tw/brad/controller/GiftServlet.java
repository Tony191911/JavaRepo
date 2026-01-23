package tw.brad.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.brad.apis.Gift;
import tw.brad.dao.GiftDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/GiftMain")
public class GiftServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. User
		// 2. Model
		try {
			List<Gift> gifts = new GiftDAO().findAll();
			System.out.println(gifts.size());
			System.out.println(gifts.get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3. Viewer
	}
}
