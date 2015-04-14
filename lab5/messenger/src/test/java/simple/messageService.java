package simple;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class messageService implements MessageService {

	public ConnectionStatus checkConnection(String server) {
            //wedlug wymagan wersji poprawionej
            if (server.endsWith(".pl")) return ConnectionStatus.SUCCESS;
		else return ConnectionStatus.FAILURE;
	}

	public SendingStatus send(String server, String message)
			throws MalformedRecipientException {
	int lenSerevr, lenMess;
	lenSerevr = server.length();
	lenMess = message.length();
	if(checkConnection(server) == ConnectionStatus.FAILURE) return SendingStatus.SENDING_ERROR;
	else if(lenSerevr < 4 || lenMess < 3) 
		throw new MalformedRecipientException();
	else return SendingStatus.SENT;
	}

}
