

public class Helpers {
	
	/**
	 * 
	 * @param best Array des valeurs de la solution optimale (nombre de vehicules)
	 * @param cas Reference au informations du cas resolu
	 * @return String de message d'affichage au bon format
	 */
	
	public String formatFinalAnswer(int[] best, CaseProblem cas) {
		int totalCap = 0;
		int totalCost = 0;
		

		int[] inverse = new int[best.length];
		for(int i = 0; i < best.length; i++) {
			inverse[best.length -1 -i] = best[i];
		}
		
		for (int i = 0; i < inverse.length; i++) {
			totalCap += best[i] * cas.getCapacity()[i];
			totalCost += best[i] * cas.getCost()[i];
		}

		int placeRestantes = totalCap - cas.getTotalPersons();
		
		String message = "Le coût total pour transporter " + cas.getTotalPersons() + " personnes est de " + totalCost
				+ " $. Il faut louer ";

		for (int i = 0; i < inverse.length; i++) {

			if (inverse[i] > 0) {

				if (i == inverse.length - 1) {
					message += " et ";
				} else if (i != 0) {

					boolean lastOne = false;

					for (int j = i; j < inverse.length; j++) {
						if (inverse[j] == 0)
							lastOne = true;
						else
							lastOne = false;
					}

					if (!lastOne) {
						message += ", ";
					} else {
						message += " et ";
					}
				}

				message += inverse[i] + " ";

				if (inverse[i] > 1) {
					message += "véhicules ";
				} else
					message += "véhicule ";

				message += "de catégorie " + (i + 1);

			}
		}

		message += ".\n";

		if (placeRestantes != 0) {
			message += " Il y aura " + placeRestantes;

			if (placeRestantes > 1) {
				message += " places libres disponibles";
			} else {
				message += " place libre disponible";
			}
		} else {
			message += " Il n'y a pas de places libres disponibles.";
		}
		message += "\n";
		return message;
	}
}
