
public class NeighborComparator implements java.util.Comparator<NeighborEntry>  {

	@Override
	public int compare(NeighborEntry e1, NeighborEntry e2) {
		// TODO Auto-generated method stub
		
		int r1 = e1.getSource().compareToIgnoreCase(e2.getSource());
		
		if(r1 != 0) {
			return r1;
		}
		return e1.getTarget().compareToIgnoreCase(e2.getTarget());
	}

}
