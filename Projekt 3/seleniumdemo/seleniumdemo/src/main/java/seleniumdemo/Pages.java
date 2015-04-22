package seleniumdemo;

import org.jbehave.web.selenium.WebDriverProvider;

import seleniumdemo.pages.Basket;
import seleniumdemo.pages.Home;
import seleniumdemo.pages.Notebooki;
import seleniumdemo.pages.Shure;

public class Pages {

	private WebDriverProvider driverProvider;
	
	//Pages
	private Home home;
	private Basket basket;
	private Notebooki notebooki;
	private Shure shure;
	// ...

	public Pages(WebDriverProvider driverProvider) {
		super();
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public Basket basket(){
		if (basket == null) {
			basket = new Basket(driverProvider);
		}
		return basket;
	}
	
	public Notebooki notebooki() {
		if (notebooki == null) {
			notebooki = new Notebooki(driverProvider);
		}
		return notebooki;
	}
	
	public Shure shure() {
		if (shure == null) {
			shure = new Shure(driverProvider);
		}
		return shure;
	}
}

