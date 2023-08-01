package org.team.j.geno.block.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.team.j.geno.block.api.client.HyperledgerFabricAPIClient;
import org.team.j.geno.block.model.GeneModel;
import org.team.j.geno.block.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangho
 *
 */
@Slf4j
@RequestMapping(value = "clinic")
@Controller
public class ClinicController {

	@Autowired
	private HyperledgerFabricAPIClient client;

	/**
	 * @Author : wangho
	 * @Date : 2023. 8. 1.
	 * @Description :
	 * @ModifiedHistory :
	 */
	@RequestMapping(value = "/")
	public String clinic() {

		return "clinic/clinic";
	}

	/**
	 * @Author : wangho
	 * @Date : 2023. 8. 1.
	 * @Description :
	 * @ModifiedHistory :
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("id_info") GeneModel geneModel) {

		log.debug("geneModel: {}", geneModel);

		String uid = Utils.getInstance().getNewUID();

		GeneModel gene = new GeneModel();
		gene.setGeneNo(Utils.getInstance().getNewHash(uid));
		gene.setUid(uid);
		gene.setName(geneModel.getName());
		gene.setChr(geneModel.getChr());
		gene.setVcf(geneModel.getVcf());
		gene.setGeneIDs(geneModel.getGeneIDs());
		gene.setReportURL(geneModel.getReportURL());
		gene.setRegistDate(Utils.getInstance().getCurruntTime());
		gene.setModifyDate(Utils.getInstance().getCurruntTime());

		String res = client.insertGenes(gene);

		log.debug("res: {}", res);

		return res;
	}
}
