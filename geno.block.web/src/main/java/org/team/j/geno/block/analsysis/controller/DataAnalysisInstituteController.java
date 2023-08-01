package org.team.j.geno.block.analsysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangho
 *
 */
@RequestMapping(value = "main")
@Controller
public class DataAnalysisInstituteController {

	/**
	 * @Author          : wangho
	 * @Date            : 2023. 7. 31.
	 * @Description     : 
	 * @ModifiedHistory : 
	 */
	@RequestMapping(value = "/")
	public String analysis() {
		
		return "analysis/analysis";
	}
	
	/**
	 * @Author          : wangho
	 * @Date            : 2023. 8. 2.
	 * @Description     :
	 * @ModifiedHistory : 
	 */
	@RequestMapping(value="/report")
	public String result() {
		
		return "analysis/analysis_results";
	}
	
	/**
	 * @Author          : wangho
	 * @Date            : 2023. 8. 2.
	 * @Description     :
	 * @ModifiedHistory : 
	 */
	@ResponseBody
	@RequestMapping(value = "/request", method=RequestMethod.POST)
	public String request(
			@RequestParam(value = "genoId", required = true) String genoId) {
		
		System.out.println(genoId);
		
		return genoId;
	}
}
