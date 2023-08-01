package org.team.j.geno.block.user.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
	 * @Author : wangho
	 * @Date : 2023. 7. 31.
	 * @Description :
	 * @ModifiedHistory :
	 */
	@RequestMapping(value = "/")
	public String user() {

		return "user/data_list";
	}

	/**
	 * @Author : wangho
	 * @Date : 2023. 8. 1.
	 * @Description :
	 * @ModifiedHistory :
	 */
	@ResponseBody
	@RequestMapping(value = "get_data")
	public String getData() {

		List<String> dataList = new ArrayList<String>();

		try {
			String filePath = "src/main/resources/genoID.txt";

			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				dataList.add(line);
			}

			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> uniqueList = new ArrayList<>(new HashSet<String>(dataList));

		String res = client.userGenes("teamj");

		return res;
	}
}
