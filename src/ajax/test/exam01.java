package ajax.test;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

class Person{
	private Double key;

	public Double getKey() {
		return key;
	}

	public void setKey(Double key) {
		this.key = key;
	}
	
	public String toString() {
		return "Person [key=" + key + "]";
	}
}
public class exam01 {
	
	public static void main(String[] args) {
		String jsonStr = "{\"key\":1}";
		String jsonStr2 = "[{\"key\":1},{\"key\":2}]";
		Gson g = new Gson();
		Person p = g.fromJson(jsonStr, Person.class);
		List<Map<String,Integer>> m = g.fromJson(jsonStr2, List.class);
		System.out.println(p);
		System.out.println(m.toString()+jsonStr);
	}
}
