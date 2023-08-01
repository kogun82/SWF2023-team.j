package org.team.j.geno.block.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.team.j.geno.block.api.client.HyperledgerFabricAPIClient;

/**
 * @author wangho
 *
 */
@RequestMapping(value = "user")
@Controller
public class UserController {

	@Autowired
	private HyperledgerFabricAPIClient client;
	
	/**
	 * @Author          : wangho
	 * @Date            : 2023. 7. 31.
	 * @Description     : 
	 * @ModifiedHistory : 
	 */
	@RequestMapping(value = "/")
	public String user() {
		
		return "user/data_list";
	}
	
	/**
	 * @Author          : wangho
	 * @Date            : 2023. 8. 1.
	 * @Description     :
	 * @ModifiedHistory : 
	 */
	@ResponseBody
	@RequestMapping(value = "get_data")
	public String getData() {
		
		String res = client.userGenes("teamj");
		
		return res;
	}
}
