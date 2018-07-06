package com.blastfurnace.otr.epsiode;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blastfurnace.otr.AppConfigTest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertTrue;

/**
 * Integration Tests for Series Services
 *
 * @author Jim Blackson
 */
public class EpisodeServiceApplicationTests extends AppConfigTest {
	
	private static final Logger log = LoggerFactory.getLogger(EpisodeServiceApplicationTests.class); 

	@Test
	public void shouldPerformEpisodeServiceTests() throws Exception {
		log.info("Episode Service Tests - Start");

		log.info("Episode Service Tests - End");
	}
	
}
