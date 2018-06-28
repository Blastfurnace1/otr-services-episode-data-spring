package com.blastfurnace.otr.episode.service;

import java.util.List;
import java.util.Map;

import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.service.payload.PayloadWithCount;
import com.blastfurnace.otr.service.response.GenericResponse;

public interface EpisodeDataService {

	GenericResponse<EpisodeDataWrapper> get(Long id);
	
	GenericResponse<List<EpisodeDataWrapper>> getSeriesEpisodes(Long seriesId);

	GenericResponse<List<Map<String, Object>>> query(QueryData qry);

	GenericResponse<Long> getResultsCount(QueryData qry);

	GenericResponse<String> delete(Long id);

	GenericResponse<EpisodeDataWrapper> save(EpisodeDataWrapper episode);

	GenericResponse<PayloadWithCount<List<Map<String, Object>>>> queryWithCount(QueryData qry);

}