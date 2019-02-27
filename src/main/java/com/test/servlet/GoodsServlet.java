package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.entity.Goods;

public class GoodsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Goods> list = new ArrayList<Goods>();
		for (int i = 1; i <= 30; i++) {
			double price = new BigDecimal(new Random().nextDouble() * 200).setScale(2, RoundingMode.HALF_UP).doubleValue();
			String imagePath = "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() + "/images/f" + i + ".jpg";
			list.add(new Goods(i, "goods" + i, price, imagePath));
		}
		
		String json = new Gson().toJson(list);
		
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();
	}

}
