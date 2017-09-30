
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Transport {

	ArrayList<CaseProblem> cases = new ArrayList<CaseProblem>();
	ArrayList<String> finalAnswers = new ArrayList<String>();
	Set<String> combinations = Collections.synchronizedSet(new HashSet());
	int highPrice = Integer.MAX_VALUE;
	int[] best;

	private void readData() {
		ArrayList<String> lineArray = new ArrayList<String>();
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader("./data/data.txt"))) {
			while ((line = reader.readLine()) != null)
				lineArray.add(line);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		parseData(lineArray);
	}

	private void parseData(ArrayList<String> lineArray) {
		int compteur = 0;
		int caseNumber = Integer.parseInt(lineArray.get(compteur++));
		// caseNumber
		for (int i = 0; i < caseNumber; i++) {
			int caseLength = Integer.parseInt(lineArray.get(compteur++));
			ArrayList<String> data = new ArrayList<String>();
			for (int j = 0; j < caseLength; j++) {
				data.add(lineArray.get(compteur + j));
			}
			cases.add(new CaseProblem(caseLength, Integer.parseInt(lineArray.get(compteur + data.size())), data));
			compteur = data.size() + compteur + 1;
		}

	}

	public static void main(String[] args) {
		Transport tr = new Transport();
		tr.readData();
		tr.solveAll();
	}

	private void solveAll() {
		// cases.size()
		Helpers help = new Helpers();

		for (int i = 0; i < cases.size(); i++) {

			CaseProblem currentCase = cases.get(i);

			solve(currentCase, currentCase.getTotalPersons(), new int[currentCase.getCapacity().length]);
			// System.out.println(java.util.Arrays.toString(best));

			finalAnswers.add(help.formatFinalAnswer(best, currentCase));
			clearForNextCase();
		}
		displayAnswers();
	}

	private void solve(CaseProblem cas, int n, int[] index) {

		if (n <= 0) {
			combinations.add(java.util.Arrays.toString(index));
			int currentPrice = calcPrice(cas, index);
			if (currentPrice < highPrice) {
				highPrice = currentPrice;
				best = index.clone();
			}
			return;
		}

		for (int i = 0; i < index.length; i++) {
			index[i]++;
			if (combinations.contains(java.util.Arrays.toString(index))) {
				index[i]--;
				continue;
			}

			solve(cas, n - cas.getCapacity()[i], index);
			index[i]--;
		}
		combinations.add(java.util.Arrays.toString(index));

	}

	private void displayAnswers() {
		for (int i = 0; i < finalAnswers.size(); i++) {
			System.out.println(finalAnswers.get(i));
		}
		System.out.println();
		System.out.println("Écrit par Jonathan Fillion FILJ070990");

	}

	private void clearForNextCase() {
		best = null;
		combinations.clear();
		highPrice = Integer.MAX_VALUE;

	}

	private int calcPrice(CaseProblem cas, int[] index) {
		int sum = 0;

		for (int i = 0; i < index.length; i++) {
			sum += cas.getCost()[i] * index[i];
		}

		return sum;
	}

}
