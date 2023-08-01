package org.team.j.geno.block.analsysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value="/result")
	public String result() {
		
		return "analysis/analysis_results";
	}
}
