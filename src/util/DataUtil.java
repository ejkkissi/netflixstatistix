package util;

import managers.DaoManager;
import model.Account;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DataUtil {
    private static String getJsonString(String name) {
        InputStream stream = DataUtil.class.getResourceAsStream("./" + name + ".json");
        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }

    public static void generateAccountData() {
        JSONArray arr = new JSONArray(getJsonString("accounts"));
        for (int i=0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Account account = new Account();
            account.setEmail(obj.getString("email"));
            account.setName(obj.getString("name"));
            account.setAddress(obj.getString("address"));
            account.setCity(obj.getString("city"));
            DaoManager.getInstance().getAccountDao().createAccount(account);
        }
    }
}
