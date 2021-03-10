
public class RankTile extends Tile {

	protected int rank;

	public RankTile(int rank) {
		this.rank = rank;
	}
	
	public boolean matches (Tile other) {
		if(super.matches(other)) {
			 RankTile otherObject = (RankTile)other;
			if(otherObject.rank == this.rank) {
			return true;
			}
		}
		
		
		return false;
		
		
	}
	
	
}
