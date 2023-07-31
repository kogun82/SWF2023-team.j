/*
SPDX-License-Identifier: Apache-2.0
*/

package hyperledger.fabric.ca;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

import hyperledger.fabric.ca.constant.Constant;

public class ClientApp {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get(Constant.BASE_DIR, "test-network", "organizations",
				"peerOrganizations", "org1.example.com", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "team").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("fabcar");

			byte[] result;

			result = contract.evaluateTransaction("queryAllCars");
//			System.out.println(new String(result));

			contract.submitTransaction("createCar", "gene1", "ch1", "AGTC", "InDel", "SNP");

			result = contract.evaluateTransaction("queryCar", "gene1");
			System.out.println(new String(result));

//			contract.submitTransaction("changeCarOwner", "CAR10", "Archie");

//			result = contract.evaluateTransaction("queryCar", "CAR10");
//			System.out.println("**");
//			System.out.println(new String(result));
//			System.out.println("**");
		}
	}

}
