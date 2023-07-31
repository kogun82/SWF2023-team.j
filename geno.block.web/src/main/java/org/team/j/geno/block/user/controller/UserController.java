package org.team.j.geno.block.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangho
 *
 */
@RequestMapping(value = "user")
@Controller
public class UserController {

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
}
