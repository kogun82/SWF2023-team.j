package org.team.j.geno.block.api.fabric;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class FabricTests {

	@Autowired
	private HyperledgerAPIController api;

	@Test
	public void contextLoads() throws Exception {
		log.info("context loads..");
		assertThat(api).isNull();
	}

}
