package util.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import util.sharedData;

public class ZipUtil {
	public static String zipThisDir( String sourceDirPath ) throws IOException {
	    String zipFilePath = sharedData.generatedReportDir + sharedData.environmentUnderTest + util.general.tools.getCurrentDate() + ".zip";
	    try {
			new File(zipFilePath).delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Path p = Files.createFile(Paths.get(zipFilePath));
	    try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
	        Path pp = Paths.get(sourceDirPath);
	        Files.walk(pp)
	          .filter(path -> !Files.isDirectory(path))
	          .forEach(path -> {
	              ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
	              try {
	                  zs.putNextEntry(zipEntry);
	                  zs.write(Files.readAllBytes(path));
	                  zs.closeEntry();
            
	            } catch (Exception e) {
	               // System.err.println(e);
	            }
	          });
	    }
	   return zipFilePath;
	}
}

