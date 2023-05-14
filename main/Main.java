package main;

/**
 * @author Nam Nguyen
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * Main class creates inventory management app with sample data.
 * JavaDoc files for this class can be found in the "doc" folder of the project directory.
 *  */
public class Main extends Application {

    /**
     * Start method initializes mainscreen.fxml
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 350);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *  Application is launched by the main method after loading the test data
     *
     * @param args
     */
    public static void main(String[] args) {

            Part brakes = new InHouse(1, "Brakes", 10.00, 10, 4, 32, 1);
            Inventory.addPart(brakes);

            Part wheel = new InHouse(2, "Wheel", 15.00, 16, 10, 30, 4);
            Inventory.addPart(wheel);

            Part seat = new InHouse(3, "Seat", 40.00, 10, 0, 120, 5);
            Inventory.addPart(seat);

            Part spoke = new Outsourced(4, "Spoke", 25.00, 10, 0, 120, "Bob's bikes");
            Inventory.addPart(spoke);

            Product GiantBike = new Product(1000, "Giant Bike", 299.99, 3, 1, 50);
            Inventory.addProduct(GiantBike);

            Product Tricycle = new Product(1001, "Tricycle", 99.99, 5, 1, 50);
            Inventory.addProduct(Tricycle);

            launch();
    }

}