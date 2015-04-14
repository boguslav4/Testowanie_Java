package notsimple;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;


public class EasyMock {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	@Mock
	private MessageService mock;
	@TestSubject
	private Messenger messenger = new Messenger(mock);

	@Test
	public void tConnOK1() {
		expect(mock.checkConnection(".pl")).andReturn(ConnectionStatus.SUCCESS);
		replay(mock);
		assertEquals(messenger.testConnection(".pl"),0);
		verify(mock);
	}
        
	@Test
	public void tConnOK2() {
		expect(mock.checkConnection("wp.pl")).andReturn(ConnectionStatus.SUCCESS);
		replay(mock);
		assertEquals(messenger.testConnection("wp.pl"),0);
		verify(mock);
	}
        
        @Test
	public void tConnFA1() {
		expect(mock.checkConnection("adresbezkropkipl")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(messenger.testConnection("adresbezkropkipl"),1);
		verify(mock);
	}
        
	@Test
	public void tConnFA2() {
		expect(mock.checkConnection(".uk")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(messenger.testConnection(".uk"),1);
		verify(mock);
	}
        
        @Test
	public void tConnFA3() {
		expect(mock.checkConnection(".nieistniejacadomena")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(messenger.testConnection(".nieistniejacadomena"),1);
		verify(mock);
	}
       
	@Test
	public void sendMessegeOK1() throws MalformedRecipientException {
		expect(mock.send("wp.pl", "messege")).andReturn(SendingStatus.SENT);
		replay(mock);
		assertEquals(messenger.sendMessage("wp.pl", "messege"), 0);
		verify(mock);
	}
        
	@Test
	public void sendMessegeOK2() throws MalformedRecipientException {
		expect(mock.send("wp.pl", "aaa")).andReturn(SendingStatus.SENT);
		replay(mock);
		assertEquals(messenger.sendMessage("wp.pl", "randomowelitery"), 0);
		verify(mock);
	}
         
	@Test
	public void sendMessegeFA2() throws MalformedRecipientException {
		expect(mock.send(".uk", "randomowelitery")).andReturn(SendingStatus.SENDING_ERROR);
		replay(mock);
		assertEquals(messenger.sendMessage(".uk", "randomowelitery"), 1);
		verify(mock);
	}
        
	@Test
	public void sendMessegeEX1() throws MalformedRecipientException {
		expect(mock.send(".pl", "randomowelitery")).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(messenger.sendMessage(".pl", "randomowelitery"), 2);
		verify(mock);
	}
        
	@Test
	public void sendMessegeEX2() throws MalformedRecipientException {
		expect(mock.send("wp.pl", "randomowelitery")).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(messenger.sendMessage("wp.pl", "randomowelitery"), 2);
		verify(mock);
	}
        
}
