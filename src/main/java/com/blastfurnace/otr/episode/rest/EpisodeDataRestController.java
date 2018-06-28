package com.blastfurnace.otr.episode.rest;

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

import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.episode.adapter.EpisodeDataAdapter;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.service.response.GenericResponse;

@RestController
@RequestMapping("/rest")
public class EpisodeDataRestController {

	@Autowired
	private EpisodeDataAdapter episodeAdapter;

    @RequestMapping(value = "/get/{id:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericResponse<EpisodeDataWrapper>>  get(@PathVariable long  id) {
    	GenericResponse<EpisodeDataWrapper> g = episodeAdapter.get(id);
    	ResponseEntity<GenericResponse<EpisodeDataWrapper>> response = new ResponseEntity<GenericResponse<EpisodeDataWrapper>>(g, HttpStatus.OK);
    	return response;
    }

    @RequestMapping(value = "/series/episodes/{seriesId:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericResponse<List<EpisodeDataWrapper>>>  getSeriesEpisodes(@PathVariable long seriesId) {
    	GenericResponse<List<EpisodeDataWrapper>> g = episodeAdapter.getSeriesEpisodes(seriesId);
    	ResponseEntity<GenericResponse<List<EpisodeDataWrapper>>> response = new ResponseEntity<GenericResponse<List<EpisodeDataWrapper>>>(g, HttpStatus.OK);
    	return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<GenericResponse<List<Map<String, Object>>>>> query(@RequestParam Map<String, String> queryParameters) {

    	QueryData qry = new QueryData(queryParameters);
    	qry.setSort("title");
    	GenericResponse<List<Map<String,Object>>> g = episodeAdapter.query(qry);
    	List<GenericResponse<List<Map<String, Object>>>> list = new ArrayList<GenericResponse<List<Map<String, Object>>>>();
    	list.add(g);
    	ResponseEntity<List<GenericResponse<List<Map<String, Object>>>>> response = new ResponseEntity<List<GenericResponse<List<Map<String, Object>>>>>(list, HttpStatus.OK);

    	return response;
    }
     
    @RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<GenericResponse<String>> delete(@PathVariable long  id) {
    	
    	GenericResponse<String> g = episodeAdapter.delete(id);
    	ResponseEntity<GenericResponse<String>> response = new ResponseEntity<GenericResponse<String>>(g, HttpStatus.OK);

    	return response;
    }
    
    @RequestMapping(value = "/resultsCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericResponse<Long>> getResultsCount(@RequestParam Map<String, String> queryParameters) {
    	 QueryData qry = new QueryData(queryParameters);
    	 GenericResponse<Long> g = episodeAdapter.getResultsCount(qry);
    	return new ResponseEntity<GenericResponse<Long>>(g, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GenericResponse<EpisodeDataWrapper>> save(EpisodeDataWrapper episode) {
    	GenericResponse<EpisodeDataWrapper> g = episodeAdapter.save(episode);
    	ResponseEntity<GenericResponse<EpisodeDataWrapper>> response = new ResponseEntity<GenericResponse<EpisodeDataWrapper>>(g, HttpStatus.OK);
    	return response;
    }

}
