package com.jmh.server.commom.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.jmh.server.entity.CityEntity;
import com.jmh.server.service.IEvalService;

@Component
@WebServlet(name = "InitServlet", urlPatterns = { "/initServlet" }, loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	
	@Autowired
	private IEvalService evalService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1425659941978950581L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
		         config.getServletContext());
		super.init(config);
		List<CityEntity> provinceList = evalService.getProvinceList();
		this.getServletContext().setAttribute("provinceList", provinceList);
	}

}
