package org.dest.file.transformations.utils;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class Sample {
	
	public static void main(String[] args) {
		String line = "a,kgk,  kgkjg  ,  kg1bbbb   ,j1kg,kug,1ki";
		int b = 0;
		int e;
		HashMap<String, String> m = new HashMap<String, String>();
		int count = StringUtils.countMatches(line, ",");
		for(int i=0; i<count; i++)
		{
			e = line.indexOf(',');
			m.put(line.substring(b, e), line.substring(b, e).trim());
			line = line.substring(line.indexOf(',')+1, line.length());
			
			System.out.println(line);
			System.out.println(m);
		}
	}

}
