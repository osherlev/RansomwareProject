package EncryptionAlgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.crypto.SecretKey;

public class CHECK {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			AES<SecretKey> som = new AES<SecretKey>();
			AES<String> som1 = new AES<String>();
			AES<Integer> som2 = new AES<Integer>();
			AES<Byte> som3 = new AES<Byte>();
		System.out.println(som.createKey().toString());
		System.out.println(som1.createKey().toString());
		System.out.println(som2.createKey().toString());
		System.out.println(som3.createKey().toString());
		
		    File file = new File("C:\\Users\\Admin\\Desktop\\RansomWare\\RansomWare\\src\\main\\java\\EncryptionAlgo\\try1");
//--------------regular text-----------------
	
	
		    System.out.println(" string: ");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println(e2.getMessage());
		}

		String st;
		try {
			while ((st = br.readLine()) != null)
				System.out.println(st);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		catch(NullPointerException e)
		{
			
		}
	
		try {
			
			som3.encrypt(file);
			System.out.println("key==" + som3.encrypt(file) );
			System.out.println(" work");
		} catch (Exception e) {
			System.out.println("didnt work");
		}
	
		//--------------encrypted text-----------------
		
		System.out.println("\nencrypted: \n");
		BufferedReader br1 = null;
		try {
			br1 = new BufferedReader(new FileReader(file+".encrypted"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println(e2.getMessage());
		}

		String st1;
		try {
			while ((st1 = br1.readLine()) != null)
				System.out.println(st1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		  File file1 = new File(file.getAbsolutePath()+".encrypted");
try {
	som.decrypt((som3.encrypt(file)), file1);
}catch(ClassCastException e)
{
	System.out.println("not working");
}
			//--------------decrypted text-----------------
		
		System.out.println("\n decrypted: \n");
		BufferedReader br11 = null;
		try {
			br11 = new BufferedReader(new FileReader(file.getAbsolutePath()+".decrypted"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println(e2.getMessage());
		}

		String st11;
		try {
			while ((st11 = br11.readLine()) != null)
				System.out.println(st11);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
	}
	
}
