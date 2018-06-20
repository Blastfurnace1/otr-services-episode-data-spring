package com.blastfurnace.otr.rest.adapter;

import java.util.List;
import java.util.Map;

import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.rest.response.GenericRestResponse;
import com.blastfurnace.otr.service.model.SeriesDataWrapper;

public interface SeriesDataAdapter {

	GenericRestResponse<SeriesDataWrapper> get(Long id);

	GenericRestResponse<List<Map<String, Object>>> query(QueryData qry);

	GenericRestResponse<Long> getResultsCount(QueryData qry);

	GenericRestResponse<String> delete(Long id);

	GenericRestResponse<SeriesDataWrapper> save(SeriesDataWrapper series);

}