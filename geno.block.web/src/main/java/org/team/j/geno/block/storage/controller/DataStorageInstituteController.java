package org.team.j.geno.block.storage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangho
 *
 */
@RequestMapping(value = "storage")
@Controller
public class DataStorageInstituteController {

	/**
	 * @Author          : wangho
	 * @Date            : 2023. 7. 31.
	 * @Description     : 
	 * @ModifiedHistory : 
	 */
	@RequestMapping(value = "/")
	public String storage() {
		
		return "storage/storage";
	}
}
