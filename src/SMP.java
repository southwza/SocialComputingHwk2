import java.util.LinkedList;
import java.util.List;

public class SMP {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("usage: SMP <input> <m/w>\n");
			return;
		}
		List<List<Person>> groups = InputParserUtility.ParseInput(args[0]);

		if (args[1].equalsIgnoreCase("m")) {
			executeGSAlgorithm(groups, true);
		} else {
			executeGSAlgorithm(groups, false);
		}


	}

	private static void executeGSAlgorithm(List<List<Person>> groups, boolean manOptimal) {
		List<Person> groupA;
		List<Person> groupB;
		if (manOptimal) {
			groupA = groups.get(0);
			groupB = groups.get(1);
		} else {
			groupA = groups.get(1);
			groupB = groups.get(0);
		}

		LinkedList<Person> queue = new LinkedList<Person>(groupA);
		while (!queue.isEmpty()) {
			Person proposer = queue.remove();
			System.out.println("queue item" + groupA.indexOf(proposer) + ": " + proposer.toString());
			Person acceptor = proposalRound(proposer, groupB);
			if (acceptor.getMatch() != null) {
				queue.add(acceptor.getMatch());
			}
			acceptor.setMatch(proposer);
		}
		System.out.println("matching complete");
	}

	private static Person proposalRound(Person proposer, List<Person> groupB) {
		int matchIndex = 0;
		if (proposer.getLastRejected() != null) {
			matchIndex = groupB.indexOf(proposer.getLastRejected()) + 1;
		}
		while (proposer.getMatch() == null) {
			Person possibleMatch = groupB.get(matchIndex);
			if (propose(proposer, possibleMatch)) {
				proposer.setMatch(possibleMatch);
				return possibleMatch;
			} else {
				matchIndex++;
			}
		}
		//should never get here
		return null;
	}

	private static boolean propose(Person proposer, Person possibleMatch) {
		Person existingEngagement = possibleMatch.getMatch();
		if (existingEngagement == null) {
			return true;
		} else if (possibleMatch.prefers(proposer, existingEngagement)) {
			existingEngagement.setLastRejected(possibleMatch);
			existingEngagement.setMatch(null);
			return true;
		}
		return false;
	}


}
