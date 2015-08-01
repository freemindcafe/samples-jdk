package com.freemindcafe.utils;

public class FileSystemUtils {
	
	public static String currentDir(){
		return System.getProperty("user.dir").replaceAll("\\\\", "/");
	}

}
