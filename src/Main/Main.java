package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/** This class opens the program and fills adds parts and products to lists
 *     Compatible feature to add to application. Create "Shipment" list that includes products. Additional button to add shipment and set of fields to shipment recipient. Shipment list will include status updates including "Ordered", "Arriving", "Shipped", and "Cancelled". */
public class Main extends Application {

    /** This is the start method. Loads inventory lists and main menu GUI.
     * @param primaryStage Passes primary stage argument.
     * @return void. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Inventory inv = new Inventory();
        addData(inv);

        Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        primaryStage.setTitle("Nicholas Hasty - WGU C482 Project");
        primaryStage.setScene(new Scene(root, 940, 600));
        primaryStage.show();
    }

    /** This is the start method. Loads inventory lists and main menu GUI.
     * @param inv Passes inv.
     * @return void. */
    void addData(Inventory inv) {
        return;
    }

        /** This is the main method.
         * First method that gets called to run program.
         * @param args The arguments for inventory parts and products.
         * @return void. */

        public static void main(String[] args) {
        Part inHousePart1 = new InHousePart(1, "Beam", 50.00, 9, 5, 15, 1);
        Part inHousePart2 = new InHousePart(2, "Gear", 20.00, 11, 5, 15, 2);
        Part inHousePart3 = new InHousePart(3, "Banding", 15.00, 7, 5, 15, 3);
        Part inHousePart4 = new InHousePart(4, "Plank", 25.00, 14, 5, 15, 4);
        Part inHousePart5 = new InHousePart(5, "Panel", 20.00, 12, 5, 15, 5);


        Inventory.addPart(inHousePart1);
        Inventory.addPart(inHousePart2);
        Inventory.addPart(inHousePart3);
        Inventory.addPart(inHousePart4);
        Inventory.addPart(inHousePart5);

        Part outSourcedPart1 = new OutSourcedPart(6, "Monitor", 50.00, 9, 5, 15, "Acme");
        Part outSourcedPart2 = new OutSourcedPart(7, "Stickers", 20.00, 11, 5, 15, "Boeing");
        Part outSourcedPart3 = new OutSourcedPart(8, "Power Cord", 15.00, 7, 5, 15, "Lexmark");
        Part outSourcedPart4 = new OutSourcedPart(9, "Keyboard", 25.00, 14, 5, 15, "General Electric");
        Part outSourcedPart5 = new OutSourcedPart(10, "Hard Disk", 20.00, 12, 5, 15, "Allsteel");

        Inventory.addPart(outSourcedPart1);
        Inventory.addPart(outSourcedPart2);
        Inventory.addPart(outSourcedPart3);
        Inventory.addPart(outSourcedPart4);
        Inventory.addPart(outSourcedPart5);

        Product product1 = new Product(1, "Desk", 200, 10, 7, 12);
        product1.addAssociatedPart(inHousePart1);
        product1.addAssociatedPart(outSourcedPart1);
        Product product2 = new Product(2, "Cabinet", 100, 7, 2, 8);
        product2.addAssociatedPart(inHousePart2);
        product2.addAssociatedPart(outSourcedPart2);
        Product product3 = new Product(3, "Motor", 500, 10, 5, 15);
        product3.addAssociatedPart(inHousePart3);
        product3.addAssociatedPart(outSourcedPart3);
        Product product4 = new Product(4, "Chair", 50, 6, 1, 7);
        product4.addAssociatedPart(inHousePart4);
        product4.addAssociatedPart(outSourcedPart4);
        Product product5 = new Product(5, "Table", 150, 10, 6, 21);
        product5.addAssociatedPart(inHousePart5);
        product5.addAssociatedPart(outSourcedPart5);
        Product product6 = new Product(5, "No Parts", 150, 10, 6, 21);


        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);
        Inventory.addProduct(product6);

        launch(args);
    }
}
