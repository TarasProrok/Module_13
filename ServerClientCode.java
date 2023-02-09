import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

public class ServerClientCode {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static User postRequest (URI uri, User user) throws Exception {
        final String jsonRequest = GSON.toJson(user);
        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> postResponse = CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());
        return GSON.fromJson(postResponse.body(), User.class);

    }

    public static User getRequest (URI uri) throws Exception {

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> getResponse = CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getResponse.body());
        return GSON.fromJson(getResponse.body(), User.class);
    }
    public static User deleteRequest (URI uri) throws Exception {

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .build();

        HttpResponse<String> deleteResponse = CLIENT.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(deleteResponse.body(), User.class);
    }

    public static List<User> showAllUsers (URI uri) throws Exception {

        HttpRequest getAllRequest = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> getAllResponse = CLIENT.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getAllResponse.body());
        return GSON.fromJson(getAllResponse.body(), new TypeToken<List<User>>() {}.getType());
    }

    public static User getUserById (String url, int id) throws Exception {
        HttpRequest getAllRequest = HttpRequest.newBuilder()
                .uri(URI.create(url + id))
                .build();
        HttpResponse<String> getAllResponse = CLIENT.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getAllResponse.body());
        return GSON.fromJson(getAllResponse.body(), User.class);
    }

    public static void getUserNames () throws Exception {
        System.out.println("Usernames:");
        for (int i = 1; i <= 10; i++) {
            HttpRequest getAllRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://jsonplaceholder.typicode.com/users/" + i))
                    .build();

            HttpResponse<String> getAllResponse = CLIENT.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
            final User user = GSON.fromJson(getAllResponse.body(), User.class);
            System.out.println(user.getUsername());
        }
    }

    public static List<User> getUserByUserName (String url, String username) throws Exception {

        HttpRequest getAllRequest = HttpRequest.newBuilder()
                .uri(URI.create(url+username))
                .build();
        HttpResponse<String> getAllResponse = CLIENT.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getAllResponse.body());
        return GSON.fromJson(getAllResponse.body(), new TypeToken<List<User>>() {}.getType());

        }

    }
