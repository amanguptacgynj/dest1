package org.dest.file.transformations.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.dest.file.transformations.service.FTWriterService;

import com.sun.javafx.collections.MappingChange.Map;

public class FTWriterServiceImpl implements FTWriterService{

	public void writeToFile(String numberOfColumns, String delimiter, String header, String fileName, String flagTrim) {
		try {
			File outputFile = new File(fileName+"_FTOutput");
			FileWriter fw = new FileWriter(outputFile);
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = br.readLine()) != null)
			{
				int numberOfRecords = StringUtils.countMatches(line, ",")+1;
				HashMap<String, String> m = new HashMap<String, String>();
				StringBuffer sb = new StringBuffer();
				if(flagTrim.equals("Yes"))
				{
					int beginIndex = 0;
					int endIndex;
					for(int i=0; i<numberOfRecords-1; i++)
					{
						endIndex = line.indexOf(',');
						fw.write(line.substring(beginIndex, endIndex).trim()+",");
						line = line.substring(line.indexOf(',')+1, line.length());
					}
					fw.write(line.trim());
				}
				else
				{
					fw.write(line);
				}
				fw.write("\n");
			}
			fw.flush();
			fw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
