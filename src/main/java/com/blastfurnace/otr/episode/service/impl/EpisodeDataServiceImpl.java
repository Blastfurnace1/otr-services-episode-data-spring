package com.blastfurnace.otr.episode.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blastfurnace.otr.data.episode.EpisodeService;
import com.blastfurnace.otr.data.episode.model.Episode;
import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.episode.service.EpisodeDataService;
import com.blastfurnace.otr.service.GenericService;
import com.blastfurnace.otr.service.payload.PayloadWithCount;
import com.blastfurnace.otr.service.request.QueryData;
import com.blastfurnace.otr.service.response.GenericServiceResponse;


@Component("EpisodeDataService")
public class EpisodeDataServiceImpl implements EpisodeDataService {

	@Autowired
	private EpisodeService service;

	private GenericService<Episode> gService;

	public EpisodeDataServiceImpl() {
		gService = new GenericService<Episode>(Episode.fieldDefinitions);
	}
	
	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#query(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericServiceResponse<List<Map<String,Object>>> query(QueryData qry) {
		return gService.query(qry, service);
	}

	@Override
	public GenericServiceResponse<PayloadWithCount<List<Map<String,Object>>>> queryWithCount(QueryData qry) {
		return gService.queryWithCount(qry, service);
	}
	
	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#getResultsCount(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericServiceResponse<Long> getResultsCount(QueryData qry) {
		return gService.getResultsCount(qry, service);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#delete(java.lang.Long)
	 */
	@Override
	public GenericServiceResponse<String> delete(Long id) {
		return gService.delete(id, service);
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#get(java.lang.Long)
	 */
	@Override
	public GenericServiceResponse<EpisodeDataWrapper> get(Long id) {
		GenericServiceResponse<EpisodeDataWrapper> response = new GenericServiceResponse<EpisodeDataWrapper>(null);
		try {
			EpisodeDataWrapper episode = service.getComplex(id);
			response.setPayload(episode);

			if (episode == null) {
				response.setStatus(10l);
				response.setMessage("No Results found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get a result");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}
	
	@Override
	public GenericServiceResponse<List<EpisodeDataWrapper>> getSeriesEpisodes(Long seriesId) {
		GenericServiceResponse<List<EpisodeDataWrapper>> response = new GenericServiceResponse<List<EpisodeDataWrapper>>(null);
		try {
			List<EpisodeDataWrapper> episode = service.getSeriesEpisodes(seriesId);
			response.setPayload(episode);

			if (episode == null) {
				response.setStatus(10l);
				response.setMessage("No Results found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get a result");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#save(com.blastfurnace.otr.model.Episode)
	 */
	@Override
	public GenericServiceResponse<EpisodeDataWrapper> save(EpisodeDataWrapper episode) {
		GenericServiceResponse<EpisodeDataWrapper> response = new GenericServiceResponse<EpisodeDataWrapper>(null);
		if (episode == null) {
			response.setStatus(-50l);
			response.setMessage("Unable to save Record - nothing to save");
			return response;
		} 
		
		try {
			EpisodeDataWrapper newEpisode = service.save(episode);
			response.setPayload(newEpisode);
			if (newEpisode == null) {
				response.setStatus(-50l);
				response.setMessage("Unable to save Record");
			}
		} catch (Exception e) {
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get a result");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}
	
	
}
