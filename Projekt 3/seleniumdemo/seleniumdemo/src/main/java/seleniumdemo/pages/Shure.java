package seleniumdemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Shure extends WebDriverPage {

	public Shure(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public void open() {
		get("http://demo.presta.shop.pl/manufacturer.php?id_manufacturer=2");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
}
