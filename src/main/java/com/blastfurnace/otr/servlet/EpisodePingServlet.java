package com.blastfurnace.otr.servlet;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.episode.service.EpisodeDataService;
import com.blastfurnace.otr.service.response.GenericResponse;

@WebServlet("/ping")
public class EpisodePingServlet extends PingServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	EpisodeDataService service;
	
	protected String checkServices() {
		try {
			GenericResponse<EpisodeDataWrapper> response = service.get(1l);
			if (response.getStatus() != 0) {
				return response.getMessage();
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
		return "Episode Server - Status OK";
	}

	public EpisodePingServlet() {
		// TODO Auto-generated constructor stub
	}

}
