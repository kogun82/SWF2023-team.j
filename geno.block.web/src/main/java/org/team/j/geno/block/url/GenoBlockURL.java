package org.team.j.geno.block.url;

public interface GenoBlockURL {

	public static final String BASE_URL = "http://10.0.14.171:1818/geno/api/v1/fabric";

	public static final String ENROLL_ADMIN = String.join("/", BASE_URL, "enroll_admin");
	public static final String REGISTER_USER = String.join("/", BASE_URL, "register_user");

}
