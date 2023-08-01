package org.team.j.geno.block.api.client;

import org.team.j.geno.block.model.UserModel;
import org.team.j.geno.block.url.GenoBlockURL;
import org.team.j.geno.block.utils.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HyperledgerFabricAPIClient {

	private Gson gson;

	public HyperledgerFabricAPIClient() {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public String enrollAdmin() {

		String url = String.format(GenoBlockURL.ENROLL_ADMIN);

		Object obj = Utils.getInstance().doGet(url);

		String res = (String) obj;

		return res;
	}

	public String registerUser(UserModel userModel) {

		String json = gson.toJson(userModel);
		String url = String.format(GenoBlockURL.REGISTER_USER);

		Object obj = Utils.getInstance().doPost(url, json);

		String res = (String) obj;

		return res;
	}

}
