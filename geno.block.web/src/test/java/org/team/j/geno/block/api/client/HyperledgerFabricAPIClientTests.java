package org.team.j.geno.block.api.client;

import org.junit.Test;
import org.team.j.geno.block.constant.Constant;
import org.team.j.geno.block.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HyperledgerFabricAPIClientTests {

	private HyperledgerFabricAPIClient client;

	public HyperledgerFabricAPIClientTests() {
		client = new HyperledgerFabricAPIClient();
	}

//	@Test
	public void enroll() {
		System.out.println(client);
		log.debug(client.enrollAdmin());
	}

	@Test
	public void register() {

		UserModel user = new UserModel();
		user.setName("kobic");
		user.setAccount("jjockjam-account");
		user.setAffiliation(Constant.AFFILIATION);

		String res = client.registerUser(user);

		log.debug(res);
	}
}
