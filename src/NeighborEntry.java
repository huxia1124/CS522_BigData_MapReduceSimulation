
public class NeighborEntry {
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NeighborEntry(String source, String target, int value) {
		super();
		this.source = source;
		this.target = target;
		this.value = value;
	}
	
	boolean isEqualTo(NeighborEntry e1) {
		
		int r1 = source.compareToIgnoreCase(e1.getSource());
		
		if(r1 != 0) {
			return false;
		}
		
		boolean result = (target.compareToIgnoreCase(e1.getTarget()) == 0);
		return result;
	}

	private String source;
	private String target;
	
	private int value;
	

}
