import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class InMapperWordCount {
	
	public static void main(String[] args) throws IOException {
		if(args.length < 1) {
			System.out.println("Usage:\n");
			System.out.println("mapper <inputFile> [<inputFile> ...]");
		}
		
		InMapperWordCount w = new InMapperWordCount(2);
		for(String inputFile : args) {
			w.addMapTask(inputFile);		
		}
		
		w.printMapperOutput();
		
		w.partition();
		w.runReducers();
	}
	
	private int r = 1;
	List<Mapper> mappers = new ArrayList<>();
	List<Reducer> reducers = new ArrayList<>();
	
	public InMapperWordCount(int numReducer) {
		r = numReducer;
	}
	
	public void addMapTask(String inputFile) throws IOException {
		mappers.add(new Mapper(inputFile));
	}
	
	public void printMapperOutput() {
		int i = 0;
		for(i=0;i<mappers.size();i++) {
			System.out.println("Mapper " + i + " Output");
			Mapper mapper = mappers.get(i);
			mapper.printOutput();
		}
	}
	
	public int getPartition(NeighborEntry key){
		if(key.getSource().charAt(0) < 'k')
			return 0;
		return 1;
	}
	
	public void partition() {
		ArrayList<NeighborEntry>[] partitionedPairLists = new ArrayList[r];
		
		for(int i=0;i<r;i++) {
			partitionedPairLists[i] = new ArrayList<NeighborEntry>();
		}
		
		for(Mapper mapper : mappers) {
			for(NeighborEntry p : mapper.data) {			
				int par = getPartition(p);
				partitionedPairLists[par].add(p);
			}
		}
		
		
		for(int i=0;i<r;i++) {
			reducers.add(new Reducer(partitionedPairLists[i]));
		}	
	}
	
	public void runReducers() {
		int index = 0;
		for(Reducer reducer : reducers) {
			System.out.println("Reducer "+ (index) + " Input");
			reducer.printInput();
			index++;
		}

		index = 0;
		for(Reducer reducer : reducers) {
			System.out.println("Reducer "+ (index) + " Output");
			reducer.printOutput();
			index++;
		}
	}


}
