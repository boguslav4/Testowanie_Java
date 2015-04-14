package notsimple;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;


public class Mockito {

    //czy zrobic je final?
	private MessageService mock = mock(MessageService.class);
	private Messenger messenger = new Messenger(mock);
	
	@Test
	public void tConnOK1() {
		when(mock.checkConnection("wp.pl")).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(messenger.testConnection("wp.pl"),0);
		verify(mock).checkConnection("wp.pl");
	}
        
        @Test
	public void tConnOK2() {
		when(mock.checkConnection(".pl")).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(messenger.testConnection(".pl"),0);
		verify(mock).checkConnection(".pl");
	}
        
        @Test
	public void tConnFA1() {
		when(mock.checkConnection("adresbezkropkipl")).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(messenger.testConnection("adresbezkropkipl"),1);
		verify(mock).checkConnection("adresbezkropkipl");
	}
        
	@Test
	public void tConnFA2() {
		when(mock.checkConnection(".uk")).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(messenger.testConnection(".uk"),1);
		verify(mock).checkConnection(".uk");
	}
	
	@Test
	public void sendMessengerS() throws MalformedRecipientException {
		when(mock.send("wp.pl", "randomowelitery")).thenReturn(SendingStatus.SENT);
		assertEquals(messenger.sendMessage("wp.pl", "randomowelitery"), 0);
		verify(mock).send("wp.pl", "randomowelitery");
	}
        
	@Test
	public void sendMessengerD() throws MalformedRecipientException {
		when(mock.send("aa", "randomowelitery")).thenReturn(SendingStatus.SENDING_ERROR);
		assertEquals(messenger.sendMessage("aa", "randomowelitery"), 1);
		verify(mock).send("aa", "randomowelitery");
	}
	
	@Test
	public void sendMessengerA() throws MalformedRecipientException {
		when(mock.send(".pl", "randomowelitery")).thenThrow(new MalformedRecipientException());
		assertEquals(messenger.sendMessage(".pl", "randomowelitery"), 2);
		verify(mock).send(".pl", "randomowelitery");
	}
	
}
