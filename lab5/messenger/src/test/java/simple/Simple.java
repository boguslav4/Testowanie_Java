package simple;

import static org.junit.Assert.*;

import org.junit.Test;


public class Simple {

	messageService mse = new messageService();
	Messenger messenger = new Messenger(mse);
	
        @Test
	public void tConnOK1() {
            assertEquals(messenger.testConnection("wp.pl"),0);
	}
        
	@Test
	public void tConnOK2() {
            assertEquals(messenger.testConnection(".pl"),0);
	}
	
        @Test
	public void tConnFA1() {
            assertEquals(messenger.testConnection("adresbezkropkipl"),1);
	}
        
	@Test
	public void tConnFA2() {
            assertEquals(messenger.testConnection(".uk"),1);
	}
	
	@Test
	public void sendMessengerOK1() {
            assertEquals(messenger.sendMessage("onet.pl", "wiadomosc"), 0);
	}
        
	@Test
	public void sendMessengerOK2() {
            assertEquals(messenger.sendMessage("wp.pl", "aaa"), 0);
	}
         
	@Test
	public void sendMessengerFA1() {
            assertEquals(messenger.sendMessage(".uk", "randomowelitery"), 1);
	}
        
	@Test
	public void sendMessengerEX1() {
            assertEquals(messenger.sendMessage(".pl", "randomowelitery"), 2);
	}
        	
}
