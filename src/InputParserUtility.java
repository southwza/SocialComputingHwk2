import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParserUtility {

	public static List<List<Person>> ParseInput(String filename) {
		File inputFile = new File(filename);
		if (!inputFile.canRead()) {
			System.out.println("cannot read input file: " + inputFile.getName());
		}
		try {
			List<String> lines = Files.readAllLines(inputFile.toPath());
			return parseInput(lines);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fail to read input file: " + e.getMessage());
			return null;
		}
	}

	private static List<List<Person>> parseInput(final List<String> inputLines) throws Exception {

		List<String> lines = inputLines.stream() //
				.map(line -> line.replaceAll("^\\s+", "")) // remove leading spaces
				.filter(line -> (line.matches("^\\d+.*$"))) // filter lines that don't start with a digit
				.map(line -> line.replaceAll("[^0-9 ].*$", "")) // remove nondigit values
				.collect(Collectors.toList()); //

		lines.remove(0); // we don't need the list size; we'll infer it.

		List<Person> people = lines.stream() //
				.map(line -> Arrays.stream(line.split("\\s+")) //
						.map(Integer::valueOf) //convert Strings to Integers
						.collect(Collectors.toList())) //
				.map(Person::new) // pass the preference list to the Person constructor
				.collect(Collectors.toList());

		List<List<Person>> groups = List.of(
				people.subList(0, people.size() / 2),
				people.subList(people.size() / 2, people.size()));

		return groups;
	}
}
