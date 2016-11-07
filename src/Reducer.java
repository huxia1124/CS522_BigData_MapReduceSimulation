import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reducer {
	
	private List<GroupByPair> reduced = new ArrayList<>();

	public Reducer(List<NeighborEntry> mapped) {
		
		Collections.sort(mapped, new NeighborComparator());
		GroupByPair lastGroupPair = null;
		for(NeighborEntry p : mapped) {
			if(lastGroupPair == null || !lastGroupPair.getKey().isEqualTo(p)) {
				if(lastGroupPair != null) {
					reduced.add(lastGroupPair);
				}
				lastGroupPair = new GroupByPair(p);
			}	
			lastGroupPair.addValue(p.getValue());
		}
		
		
		if(lastGroupPair != null) {
			reduced.add(lastGroupPair);
		}
	}
	
	public void printInput() {
		for(GroupByPair gp : reduced) {
			System.out.println(gp.toString());
		}
	}
	
	public void printOutput() {
		for(GroupByPair gp : reduced) {
			System.out.println(gp.toSumString());
		}
	}

}
