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

import com.blastfurnace.otr.rest.adapter.SeriesDataAdapter;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.rest.response.GenericRestResponse;
import com.blastfurnace.otr.service.model.SeriesDataWrapper;

@RestController
@RequestMapping("/rest")
public class SeriesDataRestController {

	@Autowired
	private SeriesDataAdapter seriesAdapter;

    @RequestMapping(value = "/get/{id:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<SeriesDataWrapper>>  get(@PathVariable long  id) {
    	GenericRestResponse<SeriesDataWrapper> g = seriesAdapter.get(id);
    	ResponseEntity<GenericRestResponse<SeriesDataWrapper>> response = new ResponseEntity<GenericRestResponse<SeriesDataWrapper>>(g, HttpStatus.OK);
    	return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<GenericRestResponse<List<Map<String, Object>>>>> query(@RequestParam Map<String, String> queryParameters) {

    	QueryData qry = new QueryData(queryParameters);
    	qry.setSort("title");
    	GenericRestResponse<List<Map<String,Object>>> g = seriesAdapter.query(qry);
    	List<GenericRestResponse<List<Map<String, Object>>>> list = new ArrayList<GenericRestResponse<List<Map<String, Object>>>>();
    	list.add(g);
    	ResponseEntity<List<GenericRestResponse<List<Map<String, Object>>>>> response = new ResponseEntity<List<GenericRestResponse<List<Map<String, Object>>>>>(list, HttpStatus.OK);

    	return response;
    }
     
    @RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<String>> delete(@PathVariable long  id) {
    	
    	GenericRestResponse<String> g = seriesAdapter.delete(id);
    	ResponseEntity<GenericRestResponse<String>> response = new ResponseEntity<GenericRestResponse<String>>(g, HttpStatus.OK);

    	return response;
    }
    
    @RequestMapping(value = "/resultsCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<Long>> getResultsCount(@RequestParam Map<String, String> queryParameters) {
    	 QueryData qry = new QueryData(queryParameters);
    	 GenericRestResponse<Long> g = seriesAdapter.getResultsCount(qry);
    	return new ResponseEntity<GenericRestResponse<Long>>(g, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GenericRestResponse<SeriesDataWrapper>> save(SeriesDataWrapper series) {
    	GenericRestResponse<SeriesDataWrapper> g = seriesAdapter.save(series);
    	ResponseEntity<GenericRestResponse<SeriesDataWrapper>> response = new ResponseEntity<GenericRestResponse<SeriesDataWrapper>>(g, HttpStatus.OK);
    	return response;
    }

}
