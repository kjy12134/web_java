import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import sinc.user.model.vo.UserVO;

public class JsonMain {

	public static void main(String[] args) {
		System.out.println("obj");
		jsonObj();
		System.out.println("ary");
		jsonAry();
		System.out.println("map");
		jsonMap();
	}

	public static void jsonObj() {
		UserVO usr = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		JSONObject jobj = new JSONObject(usr);
		System.out.println(jobj);
	}

	public static void jsonAry() {
		UserVO usr = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		UserVO usr2 = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		List<UserVO> list = new ArrayList<>();
		list.add(usr);
		list.add(usr2);

		JSONArray jAry = new JSONArray(list);
		System.out.println(jAry);
	}

	public static void jsonMap() {
		UserVO usr = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		UserVO usr2 = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		List<Object> list = new ArrayList<>();
		list.add(usr);
		list.add(usr2);
		
		UserVO usr3 = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		UserVO usr4 = new UserVO("jslim", "jslim", "임섭순", 1000, "인사팀");
		List<Object> list2 = new ArrayList<>();
		list2.add(usr3);
		list2.add(usr4);
		
		Map<String, List<Object>> hMap = new HashMap<>();
		hMap.put("list1", list);
		hMap.put("list2", list2);
		
		JSONObject jMap = new JSONObject(hMap);
		
		System.out.println(jMap);
	}
}
