package util;

import managers.DaoManager;
import model.Account;
import model.Profile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Collectors;

public class DataUtil {
    private static String getJsonString(String name) {
        InputStream stream = DataUtil.class.getResourceAsStream("./" + name + ".json");
        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }

    public static void generateAccountData() {
        JSONArray arr = new JSONArray(getJsonString("accounts"));
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Account account = new Account();
            account.setEmail(obj.getString("email"));
            account.setName(obj.getString("name"));
            account.setAddress(obj.getString("address"));
            account.setCity(obj.getString("city"));
            DaoManager.getInstance().getAccountDao().createAccount(account);
        }
    }

    public static void generateProfiles() {
        DaoManager daos = DaoManager.getInstance();

        String[] names = {"Ingrid", "Brendan", "Wesley", "Jessica", "Dion",
                "Albert", "Paul", "Blair", "Rafael", "Erica", "Jonas", "Lee",
                "Cooper", "Lydia", "Dagoth", "Louis", "Emmanuel", "Grace", "Gabriel",
                "Uriel", "Amy", "Yoko", "Ryan", "Ferris", "Melissa", "Erik", "Alex",
                "Willem", "Anna", "Hans", "Kevin", "Riley", "Jan", "Theodore", "Ben",
                "Peter", "Ray", "Veronica", "Karen", "Jonathan", "Marie", "David",
                "Luke", "Karliah", "Tasha", "Beck", "Emily", "Charlotte", "Emma", "Sam",
                "Jaimy", "Mark", "Jim", "Jeff", "Jacoba", "Sophia", "Isabella", "Derek",
                "Amelia", "Jacob", "Conor", "Daniel", "Abigail", "Joshua", "Marcus", "John",
                "Mason", "Ashley", "Jose", "Dibella", "Jeniffer", "Amanda", "Scarlett",
                "Violet", "Lucy", "Lachlan", "Max", "Finn", "Aria", "Sarah", "Hunter", "Artemis"};

        daos.getAccountDao().getAllAccounts().forEach(account -> {
            Random rand = new Random();
            int numprofiles = rand.nextInt((5)) + 1;
            for (int i = 0; i < numprofiles; i++) {
                Profile profile = new Profile();
                profile.setName(names[rand.nextInt((names.length))]);
                profile.setAccountEmail(account.getEmail());
                profile.setAge(rand.nextInt((80 - 4) + 1) + 4);

                daos.getProfileDao().createProfile(profile);
                System.out.println("Created " + profile.getName() + " on " + profile.getAccountEmail());
            }
        });
    }

    public static void generateViewerData() {
        DaoManager daos = DaoManager.getInstance();
        Random rand = new Random();

        daos.getMovieDao().getAllMovies().forEach(movie -> {
            daos.getProfileDao().getAllProfiles().forEach(profile -> {
                int watched = rand.nextInt(2); // 50% chance they watched the movie
                if (watched == 1) {
                    int percentage = rand.nextInt(100) + 1;
                    daos.getProfileDao().setProfileWatchTimeForMovie(profile, movie, percentage);
                    System.out.println("Set percentage " + profile.getName() + " : " + profile.getAccountEmail() + " for " + movie.getTitle() + " to " + percentage);
                }
            });
        });
    }
}
