package com.sbs.example.jspCommunity.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.mysqlutil.MysqlUtil;

public abstract class DispatcherServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		run(req, resp); // 실제로 돌아가는		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);		
	}
	
	protected void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> doBeforeActionRs = doBeforeAction(req, resp);
		
		if( doBeforeActionRs == null) {
			return; // 받은게 없는 경우 그냥 끝냄
		}
		
		String jspPath = doAction(req, resp, (String) doBeforeActionRs.get("controllerName"),
				(String) doBeforeActionRs.get("actionMethodName"));
		
		if(jspPath == null) {
			resp.getWriter().append("jsp 정보가 없습니다.");
			return; // 없으면 실행 안함
		}
		
		doAfterAction(req, resp, jspPath);
	}
	
	// 서블릿이 실행되기 전에 처리(전처리) - 초기에 해줘야 하는 작업(url입력, DB연결)
	private Map<String, Object> doBeforeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 5) {
			resp.getWriter().append("올바른 요청이 아닙니다.");
			return null;
		}
		
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		String controllerName = requestUriBits[3];
		String actionMethodName = requestUriBits[4];
		
		// 들어온 녀석들을 쪼개서 return 넘겨줌
		Map<String, Object> rs = new HashMap<>();
		rs.put("controllerName", controllerName);
		rs.put("actionMethodName", actionMethodName);
		
		return rs;
	}
	
	protected abstract String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName, String actionMethodName);
	// protected 는 자식이 상속 받을 수 있음
	
	private void doAfterAction(HttpServletRequest req, HttpServletResponse resp, String jspPath) throws ServletException, IOException {
		MysqlUtil.closeConnection(); // doAction이 끝나면 DB연결을 끊어줌

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}
	
}
