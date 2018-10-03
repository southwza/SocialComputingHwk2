import java.util.List;

public class SMP {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("usage: SMP <input> <m/w>\n");
			return;
		}
		List<List<Person>> groups = InputParserUtility.ParseInput(args[0]);



	}

}
