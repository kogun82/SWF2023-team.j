package org.team.j.geno.block.api.client;

import org.junit.Test;
import org.team.j.geno.block.constant.Constant;
import org.team.j.geno.block.model.GeneModel;
import org.team.j.geno.block.model.UserModel;
import org.team.j.geno.block.utils.Utils;

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

//	@Test
	public void register() {

		UserModel user = new UserModel();
		user.setName("teamj");
		user.setAccount("teamj-account");
		user.setAffiliation(Constant.AFFILIATION);

		String res = client.registerUser(user);

		log.debug(res);
	}

//	@Test
	public void genes() {
		String res = client.userGenes("teamj");
		log.debug(res);
	}

//	@Test
	public void insert() {

		String uid = Utils.getInstance().getNewUID();

		GeneModel gene = new GeneModel();
		gene.setGeneNo(Utils.getInstance().getNewHash(uid));
		gene.setUid(uid);
		gene.setName("teamj");
		gene.setChr("chr21");
		gene.setVcf("TCGA-55-6543.vcf");
		gene.setGeneIDs("MLH1, MSH2, MSH6, PMS2, EPCAM");
		gene.setReportURL("none");
		gene.setRegistDate(Utils.getInstance().getCurruntTime());
		gene.setModifyDate(Utils.getInstance().getCurruntTime());

		String res = client.insertGenes(gene);
		log.debug(res);
	}

	@Test
	public void update() {
		String res = client.updateReportURL("teamj", "056fdeeb1ea4a34e7058a595f900635b5f09a517b623376c6def98aab1c0cb05", "http://localhost:8080/genoblock/main/report");
		log.debug(res);
	}

//	@Test
	public void select() {
		String res = client.selectGeneNo("teamj", "GENE0");
		log.debug(res);
	}
}
