package seleniumdemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Basket extends WebDriverPage {

	public Basket(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public void open() {
		get("http://demo.presta.shop.pl/order.php");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
}
