import java.util.List;

public class Person {
	private List<Integer> preferenceList = List.of();
	private int matching;


	public Person(List<Integer> preferenceList) {
		this.preferenceList = preferenceList;
	}

	public List<Integer> getPreferenceList() {
		return preferenceList;
	}

	public void setPreferenceList(List<Integer> preferenceList) {
		this.preferenceList = preferenceList;
	}
}
