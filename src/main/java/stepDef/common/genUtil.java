package stepDef.common;

import java.io.File;
import java.util.Arrays;

import cucumber.api.java.en.Then;

public class genUtil {
	@Then("^The user gets latest downloaded file\\.$")
	public void the_user_gets_latest_downloaded_file() throws Throwable {
		System.out.println(util.general.FileTools.getLastDownloadedFile());	
}

}
