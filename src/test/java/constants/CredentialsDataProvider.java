package constants;

import org.testng.annotations.DataProvider;

public class CredentialsDataProvider {
	@DataProvider(name="credentials")
	public Object[][] getCredentials(){
		return new Object[][] {
			{"standard_user", "secret_sauce"},
			{"sauce_user", "secret_sauce"},
			{"problem_user", "secret_sauce"},
			{"visual_user", "secret_sauce"},
			{"performance_glitch_user", "secret_sauce"},
			{"locked_out_user", "secret_sauce"},
		};
		
	}

}
