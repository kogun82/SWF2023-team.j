package org.team.j.geno.block.url;

public interface GenoBlockURL {

	public static final String BASE_URL = "http://10.0.14.171:1818/geno/api/v1/fabric";

	public static final String ENROLL_ADMIN = String.join("/", BASE_URL, "enroll_admin");
	public static final String REGISTER_USER = String.join("/", BASE_URL, "register_user");
	public static final String USER_GENES = String.join("/", BASE_URL, "user_genes");
	public static final String INSERT_GENES = String.join("/", BASE_URL, "insert_gene");
	public static final String UPDATE_REPORT_URL = String.join("/", BASE_URL, "update_report_url");
	public static final String SELECT_GENE_NO = String.join("/", BASE_URL, "select_gene_no");
}
