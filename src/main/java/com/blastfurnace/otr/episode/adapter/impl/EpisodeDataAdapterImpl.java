package com.blastfurnace.otr.episode.adapter.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.episode.adapter.EpisodeDataAdapter;
import com.blastfurnace.otr.episode.service.EpisodeDataService;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.service.response.GenericResponse;


@Component("EpisodeDataAdapter")
public class EpisodeDataAdapterImpl implements EpisodeDataAdapter {

	@Autowired
	private EpisodeDataService service;


	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#get(java.lang.Long)
	 */
	@Override
	public GenericResponse<EpisodeDataWrapper> get(Long id) {
		return service.get(id);
	}
	
	
	@Override
	public GenericResponse<List<EpisodeDataWrapper>> getSeriesEpisodes(Long seriesId) {
		return service.getSeriesEpisodes(seriesId);
	}


	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#query(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericResponse<List<Map<String,Object>>> query(QueryData qry) {
		return service.query(qry);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#getResultsCount(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericResponse<Long> getResultsCount(QueryData qry) {
		return service.getResultsCount(qry);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#delete(java.lang.Long)
	 */
	@Override
	public GenericResponse<String> delete(Long  id) {
		return service.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#save(com.blastfurnace.otr.model.Episode)
	 */
	@Override
	public GenericResponse<EpisodeDataWrapper> save(EpisodeDataWrapper episode) {
		return service.save(episode);
	}
}
