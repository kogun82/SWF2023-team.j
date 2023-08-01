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

/**
 * @author wangho
 *
 */
@RequestMapping(value = "clinic")
@Controller
public class ClinicController {

	@Autowired
	private HyperledgerFabricAPIClient client;
	
	/**
	 * @Author          : wangho
	 * @Date            : 2023. 8. 1.
	 * @Description     : 
	 * @ModifiedHistory : 
	 */
	@RequestMapping(value = "/")
	public String clinic() {
		
		return "clinic/clinic";
	}
	
	/**
	 * @Author          : wangho
	 * @Date            : 2023. 8. 1.
	 * @Description     :
	 * @ModifiedHistory : 
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public boolean register(@ModelAttribute("id_info") GeneModel geneModel) {
		
		System.out.println("geneModel : " + geneModel);
		
		GeneModel gene = new GeneModel();
		gene.setGeneNo(Utils.getInstance().getNewGeneNo());
		gene.setUid(Utils.getInstance().getNewUID());
		gene.setName(geneModel.getName());
		gene.setChr(geneModel.getChr());
		gene.setVcf(geneModel.getVcf());
		gene.setGeneIDs(geneModel.getGeneIDs());
		gene.setReportURL(geneModel.getReportURL());
		gene.setRegistDate(Utils.getInstance().getCurruntTime());
		gene.setModifyDate(Utils.getInstance().getCurruntTime());

		boolean res = client.insertGenes(gene);
		
		System.out.println("res : " + res);
		
		return res;
	}
}
