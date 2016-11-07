import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Mapper {
	
	List<NeighborEntry> data = new ArrayList<NeighborEntry>();
	
	boolean isWord(String s)
	{
		final Pattern pattern = Pattern.compile("^[A-Za-z\\-\\\"\\\']+[\\.]*$");
		return pattern.matcher(s).matches();
	}
	
	String trimStart(String s)
	{
		int start = 0;
		while(start < s.length() && !Character.isLetter(s.charAt(start)))
			start++;
		return s.substring(start);
	}
	
	String trimEnd(String s)
	{
		int end = s.length() - 1;
		while(end >= 0 && !Character.isLetter(s.charAt(end)))
			end--;
		return s.substring(0, end + 1);
	}
	
	public Mapper(String inputFile) throws IOException
	{
		//Read file
		FileReader fr;
		BufferedReader reader = null;
		
		try{
			fr = new FileReader(inputFile);
			reader = new BufferedReader(fr);
			
			//Read line by line
			String line = null;
			line = reader.readLine();
			
			while(line != null) {
				line = line.toLowerCase();
				String parts[] = line.split("[ ,]");
				
				for(int i=0;i<parts.length;i++) {
					String currentToken = parts[i];
					List<String> neighbors = getNeighbors(parts, i);
					
					for(String n : neighbors) {
						NeighborEntry entry = new NeighborEntry(currentToken, n, 1);
						data.add(entry);
					}
					
				}
				
				
				line = reader.readLine();
			}
		}
		finally
		{
			if(reader != null){
				reader.close();
			}
		}
		
		//Sort
		//Collections.sort(data, new PairComparator());
	}
	
	List<String> getNeighbors(String parts[], int currentIndex) {
		List<String> neighbors = new ArrayList<String>();
		String currentValue = parts[currentIndex];
		currentIndex++;
		
		while(currentIndex < parts.length)
		{
			if(currentValue.compareToIgnoreCase(parts[currentIndex]) == 0) {
				break;
			}
			neighbors.add(parts[currentIndex]);
			currentIndex++;
		}
		return neighbors;
	}

	
	public void Output(String outputFile)
	{
		try{
		    PrintWriter writer = new PrintWriter(outputFile, "UTF-8");	    
			for(NeighborEntry p : data) {
				writer.println("<(" + p.getSource() + "," + p.getTarget() + ")," + p.getValue() + ">");
			}
			
		    writer.close();
		} catch (Exception e) {
		   // do nothing
		}
	}
	
	public void printOutput() {
		
		for(NeighborEntry p : data) {
			System.out.println("<(" + p.getSource() + "," + p.getTarget() + ")," + p.getValue() + ">");
		}
	}

}
