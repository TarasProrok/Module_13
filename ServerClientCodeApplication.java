import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;

public class ServerClientCodeApplication {
    public static void main(String[] args) throws Exception {
        Geo geo = new Geo();
        Address address = new Address();
        Company company = new Company();
        User user = new User();
        Task task = new Task();


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

        ServerClientCode.postRequest(URI.create("https://jsonplaceholder.typicode.com/users"), user);

//        ServerClientCode.getRequest(URI.create("https://jsonplaceholder.typicode.com/users/11"));
//
//        ServerClientCode.deleteRequest(URI.create("https://jsonplaceholder.typicode.com/users/10"));
//
//        ServerClientCode.showAllUsers(URI.create("https://jsonplaceholder.typicode.com/users"));
//
//        ServerClientCode.getUserById("https://jsonplaceholder.typicode.com/users/", 8);
//
//        ServerClientCode.getUserNames();
//
//        ServerClientCode.getUserByUserName("https://jsonplaceholder.typicode.com/users?username=", "Bret");
//
        //Task 3
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String toJson = gson.toJson(ServerClientCode.openUserTasks("https://jsonplaceholder.typicode.com/users/",1));
        System.out.println("To Json : "+toJson);



    }
}
