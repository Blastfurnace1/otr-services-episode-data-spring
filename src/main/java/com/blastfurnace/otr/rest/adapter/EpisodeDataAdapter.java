package com.blastfurnace.otr.rest.adapter;

import java.util.List;
import java.util.Map;

import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.rest.response.GenericRestResponse;
import com.blastfurnace.otr.service.model.EpisodeDataWrapper;

public interface EpisodeDataAdapter {

	GenericRestResponse<EpisodeDataWrapper> get(Long id);
	
	GenericRestResponse<List<EpisodeDataWrapper>> getSeriesEpisodes(Long seriesId);

	GenericRestResponse<List<Map<String, Object>>> query(QueryData qry);

	GenericRestResponse<Long> getResultsCount(QueryData qry);

	GenericRestResponse<String> delete(Long id);

	GenericRestResponse<EpisodeDataWrapper> save(EpisodeDataWrapper episode);

}