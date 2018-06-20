package com.blastfurnace.otr.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.rest.response.GenericRestResponse;
import com.blastfurnace.otr.service.model.EpisodeDataWrapper;

@RestController
@RequestMapping("/rest")
public class EpisodeDataRestController {

	@Autowired
	private EpisodeDataAdapter episodeAdapter;

    @RequestMapping(value = "/get/{id:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<EpisodeDataWrapper>>  get(@PathVariable long  id) {
    	GenericRestResponse<EpisodeDataWrapper> g = episodeAdapter.get(id);
    	ResponseEntity<GenericRestResponse<EpisodeDataWrapper>> response = new ResponseEntity<GenericRestResponse<EpisodeDataWrapper>>(g, HttpStatus.OK);
    	return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<GenericRestResponse<List<Map<String, Object>>>>> query(@RequestParam Map<String, String> queryParameters) {

    	QueryData qry = new QueryData(queryParameters);
    	qry.setSort("title");
    	GenericRestResponse<List<Map<String,Object>>> g = episodeAdapter.query(qry);
    	List<GenericRestResponse<List<Map<String, Object>>>> list = new ArrayList<GenericRestResponse<List<Map<String, Object>>>>();
    	list.add(g);
    	ResponseEntity<List<GenericRestResponse<List<Map<String, Object>>>>> response = new ResponseEntity<List<GenericRestResponse<List<Map<String, Object>>>>>(list, HttpStatus.OK);

    	return response;
    }
     
    @RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<String>> delete(@PathVariable long  id) {
    	
    	GenericRestResponse<String> g = episodeAdapter.delete(id);
    	ResponseEntity<GenericRestResponse<String>> response = new ResponseEntity<GenericRestResponse<String>>(g, HttpStatus.OK);

    	return response;
    }
    
    @RequestMapping(value = "/resultsCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<Long>> getResultsCount(@RequestParam Map<String, String> queryParameters) {
    	 QueryData qry = new QueryData(queryParameters);
    	 GenericRestResponse<Long> g = episodeAdapter.getResultsCount(qry);
    	return new ResponseEntity<GenericRestResponse<Long>>(g, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<EpisodeDataWrapper>> save(EpisodeDataWrapper episode) {
    	GenericRestResponse<EpisodeDataWrapper> g = episodeAdapter.save(episode);
    	ResponseEntity<GenericRestResponse<EpisodeDataWrapper>> response = new ResponseEntity<GenericRestResponse<EpisodeDataWrapper>>(g, HttpStatus.OK);
    	return response;
    }

}
