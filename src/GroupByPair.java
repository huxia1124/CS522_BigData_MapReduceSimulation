import java.util.ArrayList;
import java.util.List;

public class GroupByPair {
	
	public GroupByPair(NeighborEntry key) {
		super();
		this.key = key;
	}

	public List<Integer> getValues() {
		return values;
	}
	public void setValues(List<Integer> values) {
		this.values = values;
	}
	
	public void addValue(int value) {
		values.add(value);
	}
	public NeighborEntry getKey() {
		return key;
	}

	public void setKey(NeighborEntry key) {
		this.key = key;
	}

	
	private NeighborEntry key;
	private List<Integer> values = new ArrayList<>();
	
	String trimEnd(String s)
	{
		int end = s.length() - 1;
		while(end >= 0 && !Character.isDigit(s.charAt(end)))
			end--;
		return s.substring(0, end + 1);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<(" + key.getSource() + "," + key.getTarget() + ")");
		str.append(", [");
		StringBuilder strList = new StringBuilder();
		for(Integer i : values) {
			strList.append(i + ", ");
		}
		str.append(trimEnd(strList.toString()));
		str.append("]>");
		return str.toString();
	}
	
	public String toSumString() {
		StringBuilder str = new StringBuilder();
		str.append("<(" + key.getSource() + "," + key.getTarget() + ")");
		Integer sum = 0;
		for(Integer i : values) {
			sum += i;
		}
		str.append(", " + sum);
		str.append(">");
		return str.toString();
	}
}
