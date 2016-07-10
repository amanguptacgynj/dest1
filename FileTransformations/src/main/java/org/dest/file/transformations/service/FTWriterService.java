package org.dest.file.transformations.service;

public interface FTWriterService {
	
	public void writeToFile(String numberOfColumns, String delimiter, String fileName, String header, String flagTrim);

}
