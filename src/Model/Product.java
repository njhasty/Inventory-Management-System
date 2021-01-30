package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
/** This class defines and manages products for products list */
public class Product {
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int productID, String name, double price, int stock, int min, int max) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Method to return productID.
     * @return productID. */
    public int getProductID() {
        return productID;
    }

    /** Method to set productID.
     * @param productID  Passes productID.
     * @return void. */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /** Method to return name.
     * @return name. */
    public String getName() {
        return name;
    }

    /** Method to set name.
     * @param name  Passes name.
     * @return void. */
    public void setName(String name) {
        this.name = name;
    }

    /** Method to return price.
     * @return price. */
    public double getPrice() {
        return price;
    }

    /** Method to set price.
     * @param price  Passes price.
     * @return void. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Method to return stock.
     * @return stock. */
    public int getStock() {
        return stock;
    }

    /** Method to set stock.
     * @param stock  Passes stock.
     * @return void. */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Method to return min.
     * @return min. */
    public int getMin() {
        return min;
    }

    /** Method to set min.
     * @param min  Passes min.
     * @return void. */
    public void setMin(int min) {
        this.min = min;
    }

    /** Method to return max.
     * @return max. */
    public int getMax() {
        return max;
    }

    /** Method to set max.
     * @param max  Passes max.
     * @return void. */
    public void setMax(int max) {
        this.max = max;
    }

    /** Method to add part.
     * @param part  Passes part.
     * @return void. */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** Method to delete associated part.
     * @param selectedAssociatedPart  Passes selectedAssociatedPart.
     * @return false. */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for(int p = 0; p < associatedParts.size(); p++) {
            if (associatedParts.get(p) == selectedAssociatedPart) {
                associatedParts.remove(p);
                return true;
            }
        }
        return false;
    }

    /** Method to search associated part.
     * @param selectedAssociatedPart  Passes selectedAssociatedPart.
     * @return null. */
    public Part searchAssociatedPart(int selectedAssociatedPart) {
        for (int p = 0; p < associatedParts.size(); p++) {
            if (associatedParts.get(p).getPartID() == selectedAssociatedPart) {
                return associatedParts.get(p);
            }
        }
        return null;
    }

    /** Method to return associatedParts.
     * @return associatedParts. */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /** Method to return associatedParts.
     * @return associatedParts. */
    public ObservableList getProductParts() {
        return associatedParts;
    }
}
