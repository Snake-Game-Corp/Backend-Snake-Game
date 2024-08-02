package com.lcaohoanq.views.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lcaohoanq.models.User;
import com.lcaohoanq.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import javax.swing.SwingUtilities;

@PageTitle("Snake Game Management")
@Route(value = "admin/management", layout = MainLayout.class)
public class SnakeGameManagement extends Composite<VerticalLayout> {

    private Grid<User> userGrid = new Grid<>(User.class);

    private final HttpClient httpClient;
    private ObjectMapper objectMapper;

    public SnakeGameManagement() {
        VerticalLayout layout = getContent();
        layout.add(userGrid);

        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).enable(
            SerializationFeature.INDENT_OUTPUT);

        fetchData();
    }

    private void fetchData() {
        UI currentUI = UI.getCurrent();

        new Thread(() -> {
            try {
                // Replace with your API URL
                //https://jsonplaceholder.typicode.com/posts/1
                String apiUrl = "http://localhost:8081/users";
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

                HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String responseBody = response.body();

                    //Object json = objectMapper.readValue(responseBody, Object.class);
                    List<User> usersList = objectMapper.readValue(responseBody, new TypeReference<List<User>>() {
                    }); //deserializing JSON array to a list of User objects

                    String formattedJson = objectMapper.writeValueAsString(usersList);
                    System.out.println("Formatted: " + formattedJson);

                    System.out.println(usersList.toString());

                    // Access the UI to update the grid
                    currentUI.access(() -> {
                        userGrid.setItems(usersList);
                        Notification.show("Data fetched successfully!");
                    });
                } else {
                    currentUI.access(() -> {
                        Notification.show("GET request failed: " + response.statusCode());
                    });
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }).start();

    }

}
