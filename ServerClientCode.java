import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerClientCode {
    public static void main(String[] args) throws Exception {

        // Creating new user with params
        Geo geo = new Geo();
        Address address = new Address();
        Company company = new Company();
        User user = new User();

        user.setName("Test");
        user.setUsername("TEST");
        user.setEmail("test@test.com");
        user.setAddress(address);
        address.setStreet("Kulas Light");
        address.setSuite("Apt. 556");
        address.setCity("Gwenborough");
        address.setZipcode("92998-3874");
        address.setGeo(geo);
        geo.setLat(-37.3159);
        geo.setLng(81.1496);
        user.setPhone("1-770-736-8031 x56442");
        user.setWebsite("hildegard.org");
        user.setCompany(company);
        company.setName("Romaguera-Crona");
        company.setCatchPhrase("Multi-layered client-server neural-net");
        company.setBs("harness real-time e-markets");

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(user);
        System.out.println(jsonRequest);


        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());
        user = gson.fromJson(postResponse.body(), User.class);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users/9"))
                .build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        user = gson.fromJson(getResponse.body(), User.class);
        System.out.println(getResponse.body());

        System.out.println("Deleting");
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users/10"))
                .DELETE()
                .build();

        HttpResponse<String> deleteResponse = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(deleteResponse.body());

        System.out.println("All users info");
        for (int i=1; i<=10; i++) {
        HttpRequest getAllRequest = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users/"+i))
                .build();

            HttpResponse<String> getAllResponse = httpClient.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
            user = gson.fromJson(getAllResponse.body(), User.class);
            System.out.println(getAllResponse.body());

            }


        }
    }
