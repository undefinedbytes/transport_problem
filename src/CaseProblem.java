

import java.util.ArrayList;

/**
 * 
 * @author Jonathan Fillion
 *
 */

public class CaseProblem {

	private int totalPersons;
	private int[] capacity;
	private int[] cost;

	/**
	 * 
	 * Structure d'information contenant les informations des cas a resoudre
	 * 
	 * @param numberOfTransports nombre de vehicules disponibles
	 * @param totalPersons total
	 * @param data les informations de vehicules (capacite, cout)
	 */
	
	public CaseProblem(int numberOfTransports, int totalPersons, ArrayList<String> data) {

		// set data
		setTotalPersons(totalPersons);
		int[] capacity = new int[data.size()];
		int[] cost = new int[data.size()];
		for (int i = 0; i < data.size(); i++) {
			String[] tab = data.get(i).split(" ");
			capacity[i] = Integer.parseInt(tab[0]);
			cost[i] = Integer.parseInt(tab[1]);
		}
		// sort
		for (int i = 0; i < capacity.length; i++) {
			int max = i;
			for (int j = 1 + i; j < capacity.length; j++) {
				if (capacity[max] < capacity[j])
					max = j;
			}
			int temp = capacity[max];
			int temp1 = cost[max];
			capacity[max] = capacity[i];
			cost[max] = cost[i];
			capacity[i] = temp;
			cost[i] = temp1;
		}
		setCapacity(capacity);
		setCost(cost);
	}

	public int getTotalPersons() {
		return totalPersons;
	}

	public void setTotalPersons(int totalPersons) {
		this.totalPersons = totalPersons;
	}

	public int[] getCapacity() {
		return capacity;
	}

	public void setCapacity(int[] capacity) {
		this.capacity = capacity;
	}

	public int[] getCost() {
		return cost;
	}

	public void setCost(int[] cost) {
		this.cost = cost;
	}

}