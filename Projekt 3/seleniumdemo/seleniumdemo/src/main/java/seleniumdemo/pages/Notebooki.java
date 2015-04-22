package seleniumdemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Notebooki extends WebDriverPage {

	public Notebooki(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public void open() {
		get("http://demo.presta.shop.pl/category.php?id_category=4");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
}
