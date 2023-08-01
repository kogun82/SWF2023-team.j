package org.team.j.geno.block.api.client;

import org.json.simple.JSONObject;
import org.team.j.geno.block.model.GeneModel;
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

	@SuppressWarnings("unchecked")
	public String userGenes(String name) {

		JSONObject json = new JSONObject();
		json.put("user_name", name);

		String url = String.format(GenoBlockURL.USER_GENES);

		Object obj = Utils.getInstance().doPost(url, json.toString());

		String res = (String) obj;

		return res;
	}

	public boolean insertGenes(GeneModel geneModel) {

		String json = gson.toJson(geneModel);
		String url = String.format(GenoBlockURL.INSERT_GENES);

		Object obj = Utils.getInstance().doPost(url, json);

		boolean res = (boolean) obj;

		return res;
	}

	@SuppressWarnings("unchecked")
	public String updateReportURL(String name, String geneNo, String reportURL) {

		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("gene_no", geneNo);
		json.put("report_url", reportURL);

		String url = String.format(GenoBlockURL.UPDATE_REPORT_URL);

		Object obj = Utils.getInstance().doPost(url, json.toString());

		String res = (String) obj;

		return res;
	}

	@SuppressWarnings("unchecked")
	public String selectGeneNo(String name, String geneNo) {

		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("gene_no", geneNo);

		String url = String.format(GenoBlockURL.SELECT_GENE_NO);

		Object obj = Utils.getInstance().doPost(url, json.toString());

		String res = (String) obj;

		return res;
	}

}
