package org.team.j.geno.block.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GeneModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String geneNo;
	private String uid;
	private String name;
	private String chr;
	private String vcf;
	private String geneIDs;
	private String reportURL;
	private String registDate;
	private String modifyDate;
}
