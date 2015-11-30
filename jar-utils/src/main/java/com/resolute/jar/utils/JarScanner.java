package com.resolute.jar.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JarScanner {

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
	usage();
    }
    File fileOrDirectory = new File(args[0]);
    if (!fileOrDirectory.exists()) {
	System.out.println("The specified file or directory does not exist: " + fileOrDirectory.getAbsolutePath());
	System.exit(0);
    }
    if (fileOrDirectory.isFile()) {
	if (!fileOrDirectory.getName().toLowerCase().endsWith(".jar")) {
	   usage();
	}
	File jarFile = fileOrDirectory;
	scanJar(jarFile);
    } else {
	File directory = fileOrDirectory;
        scanDirectory(directory);
    }
  }

private static void scanDirectory(File directory) throws IOException {
    File [] children = directory.listFiles(new FileFilter () {

	@Override
	public boolean accept(File f) {
	    if (f.isDirectory() || f.getName().toLowerCase().endsWith(".jar")) {
	        return true;
	    }
	    return false;
	}
        
    });
    if (children != null) {
        for (File child : children) {
            if (child.isDirectory()) {
        	scanDirectory(child);
            } else {
                scanJar(child);
            }
        }
    }
}

private static void scanJar(File jarFile) throws IOException {
    try (JarFile jar = new JarFile(jarFile)) {
      for (Enumeration<? extends JarEntry> enumeration = jar.entries(); enumeration.hasMoreElements();) {
  	ZipEntry zipEntry = enumeration.nextElement();
  	if (zipEntry.getName().endsWith(".class")) {
  	    String className = zipEntry.getName();
  	    className = className.replace(".class", "").replace("/", ".");
  	    System.out.println(jarFile.getAbsolutePath() + " - " + className);
  	}
      }
    }
}

private static void usage() {
    // NOTE: if a directory is passed in all JAR files within that directory will be scanned
    System.out.println("Usage: ./run.bat <jarFile> | <directory>");
    System.exit(0);
}
}
