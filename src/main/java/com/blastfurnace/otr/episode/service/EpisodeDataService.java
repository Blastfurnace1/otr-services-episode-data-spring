package com.blastfurnace.otr.episode.service;

import java.util.List;
import java.util.Map;

import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.service.payload.PayloadWithCount;
import com.blastfurnace.otr.service.request.QueryData;
import com.blastfurnace.otr.service.response.GenericServiceResponse;

public interface EpisodeDataService {

	GenericServiceResponse<EpisodeDataWrapper> get(Long id);
	
	GenericServiceResponse<List<EpisodeDataWrapper>> getSeriesEpisodes(Long seriesId);

	GenericServiceResponse<List<Map<String, Object>>> query(QueryData qry);

	GenericServiceResponse<Long> getResultsCount(QueryData qry);

	GenericServiceResponse<String> delete(Long id);

	GenericServiceResponse<EpisodeDataWrapper> save(EpisodeDataWrapper episode);

	GenericServiceResponse<PayloadWithCount<List<Map<String, Object>>>> queryWithCount(QueryData qry);

}