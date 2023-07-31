/*
SPDX-License-Identifier: Apache-2.0
*/

package hyperledger.fabric.ca;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

public class ClientTest {

//	@Test
	@DisplayName("enroll admin..")
	public void enroll() throws Exception {
		EnrollAdmin.main(null);
	}

	@Disabled
//	@Test
	@DisplayName("register user..")
	public void register() throws Exception {
		RegisterUser.main(null);
//		ClientApp.main(null);
	}

	@Disabled
	@Test
	@DisplayName("clinet api..")
	public void client() throws Exception {
		ClientApp.main(null);
	}
}
