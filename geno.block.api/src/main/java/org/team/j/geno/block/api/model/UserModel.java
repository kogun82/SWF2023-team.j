package org.team.j.geno.block.api.model;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String account;
	private String affiliation;
	private Set<String> roles;

}
