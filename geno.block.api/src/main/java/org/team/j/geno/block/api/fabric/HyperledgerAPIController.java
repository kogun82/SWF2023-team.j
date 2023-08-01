package org.team.j.geno.block.api.fabric;

import java.nio.file.Paths;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.j.geno.block.api.constant.Constant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/fabric")
@Slf4j
public class HyperledgerAPIController {

	@SuppressWarnings("unused")
	private Gson gson;
	private Properties props;

	@PostConstruct
	public void init() {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.props = new Properties();
	}

	@GetMapping(value = "/enroll_admin")
	public void enrollAdmin() {
		
		try {

			props.put("pemFile", String.join("/", Constant.BASE_DIR,
					"test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem"));
			props.put("allowAllHostNames", "true");

			CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();

			HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
			caClient.setCryptoSuite(cryptoSuite);

			ClassPathResource resource = new ClassPathResource("src/main/resources/wallet");
			Wallet wallet = Wallets.newFileSystemWallet(Paths.get(resource.getPath()));

			if (wallet.get("admin") != null) {
				log.debug("An identity for the admin user \"admin\" already exists in the wallet");
				return;
			}

			final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
			enrollmentRequestTLS.addHost("localhost");
			enrollmentRequestTLS.setProfile("tls");

			Enrollment enrollment = caClient.enroll("admin", "adminpw", enrollmentRequestTLS);

			Identity user = Identities.newX509Identity("Org1MSP", enrollment);
			wallet.put("admin", user);

			log.debug("Successfully enrolled user \"admin\" and imported it into the wallet");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
