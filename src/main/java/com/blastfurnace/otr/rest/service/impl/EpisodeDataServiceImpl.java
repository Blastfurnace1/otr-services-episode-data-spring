package com.blastfurnace.otr.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blastfurnace.otr.data.audiofile.model.AudioFileProperties;
import com.blastfurnace.otr.data.episode.EpisodeService;
import com.blastfurnace.otr.data.episode.model.Episode;
import com.blastfurnace.otr.data.episode.service.model.EpisodeDataWrapper;
import com.blastfurnace.otr.rest.request.QueryData;
import com.blastfurnace.otr.rest.service.EpisodeDataService;
import com.blastfurnace.otr.service.payload.PayloadWithCount;
import com.blastfurnace.otr.service.response.GenericResponse;
import com.blastfurnace.otr.util.reflection.ObjectData;
import com.blastfurnace.otr.util.reflection.Utils;


@Component("EpisodeDataService")
public class EpisodeDataServiceImpl implements EpisodeDataService {

	@Autowired
	private EpisodeService service;


	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#get(java.lang.Long)
	 */
	@Override
	public GenericResponse<EpisodeDataWrapper> get(Long id) {
		GenericResponse<EpisodeDataWrapper> response = new GenericResponse<EpisodeDataWrapper>(null);
		try {
			EpisodeDataWrapper episode = service.get(id);
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
	public GenericResponse<List<EpisodeDataWrapper>> getSeriesEpisodes(Long seriesId) {
		GenericResponse<List<EpisodeDataWrapper>> response = new GenericResponse<List<EpisodeDataWrapper>>(null);
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
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#query(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericResponse<List<Map<String,Object>>> query(QueryData qry) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		GenericResponse<List<Map<String,Object>>> response = new GenericResponse<List<Map<String,Object>>>(list);
		try {
			String[] columns = Utils.getFields(qry);

			Iterable<Episode> props = service.query(qry);

			if (props == null) {
				response.setStatus(10l);
				response.setMessage("No Results found");
			} else {
				for (Episode afp : props) {
					ObjectData<Episode> obj = new ObjectData<Episode>(afp);
					Map<String,Object> map = null;
					try {
						map = obj.addValues(columns, Episode.fieldDefinitions);
					} catch (Exception  e) {
						response.setErrorOccured(true);
						response.addError(e.getMessage());
					}
					list.add(map);
				}

				if (list.isEmpty() && response.getErrorOccured() == false) {
					response.setStatus(10l);
					response.setMessage("No Results found");
				}

				if (response.getErrorOccured()) {
					response.setStatus(-20l);
					response.setMessage("unable to get complete data");
				}
				response.setPayload(list);
			}
		} catch (Exception e) {
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get results");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}
		return response;
	}

	@Override
	public GenericResponse<PayloadWithCount<List<Map<String,Object>>>> queryWithCount(QueryData qry) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PayloadWithCount<List<Map<String,Object>>> payload = new PayloadWithCount<List<Map<String,Object>>>(list,0l);
		GenericResponse<PayloadWithCount<List<Map<String,Object>>>> response = new GenericResponse<PayloadWithCount<List<Map<String,Object>>>>(payload);
		try {
			String[] columns = Utils.getFields(qry);

			Iterable<Episode> props = service.query(qry);

			if (props == null) {
				response.setStatus(10l);
				response.setMessage("No Results found");
			} else {
				for (Episode ep : props) {
					ObjectData<Episode> obj = new ObjectData<Episode>(ep);
					Map<String,Object> map = null;
					try {
						map = obj.addValues(columns, AudioFileProperties.fieldDefinitions);
					} catch (Exception  e) {
						response.setErrorOccured(true);
						response.addError(e.getMessage());
					}
					list.add(map);
				}

				if (list.isEmpty() && response.getErrorOccured() == false) {
					response.setStatus(10l);
					response.setMessage("No Results found");
				}

				if (response.getErrorOccured()) {
					response.setStatus(-20l);
					response.setMessage("unable to get complete data");
				}
				response.getPayload().setPayload(list);
			}
			
			GenericResponse<Long> count = getResultsCount(qry);
			if (count.getErrorOccured() || count.getStatus() != 0) {
				response.updateGenericResponse(count);
				response.getPayload().setResultsCount(-1l);
			} else {
				response.getPayload().setResultsCount(count.getPayload());
			}
			 
		} catch (Exception e) {
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get results");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#getResultsCount(com.blastfurnace.otr.rest.request.QueryData)
	 */
	@Override
	public GenericResponse<Long> getResultsCount(QueryData qry) {
		GenericResponse<Long> response = new GenericResponse<Long>(null);
		try {
			Long count = service.getResultsCount(qry);
			response.setPayload(count);
			if (count == null) {
				response.setStatus(-30l);
				response.setMessage("Unable to save Record");
			}
		} catch (Exception e) {
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to get a result count");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}



	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#delete(java.lang.Long)
	 */
	@Override
	public GenericResponse<String> delete(Long  id) {

		GenericResponse<String> response = new GenericResponse<String>("");
		try {
			service.delete(id);
		} catch (Exception e) {
			response.setStatus(-10l);
			response.setMessage("An Error Occurred - unable to delete record");
			response.setErrorOccured(true);
			response.addError(e.getMessage());
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.blastfurnace.otr.rest.adapter.EpisodeDataAdapter#save(com.blastfurnace.otr.model.Episode)
	 */
	@Override
	public GenericResponse<EpisodeDataWrapper> save(EpisodeDataWrapper episode) {
		GenericResponse<EpisodeDataWrapper> response = new GenericResponse<EpisodeDataWrapper>(null);
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
