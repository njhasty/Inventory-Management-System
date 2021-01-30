package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class defines and manages observable lists for parts and products */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int partIdListCount = 0;

    private static int productIdListCount = 0;


    /** Method to add newPart.
     * @param newPart  Passes newPart.
     * @return void. */
    public static void addPart(Part newPart) {
        if (newPart != null) {
            allParts.add(newPart);
        }
    }

    /** Method to add newProduct.
     * @param newProduct  Passes newProduct.
     * @return void. */
    public static void addProduct(Product newProduct) {
        if (newProduct != null) {
            allProducts.add(newProduct);
        }
    }

    /** Method to look up partID.
     * @param partID  Passes partID.
     * @return p. */
    public static Part lookupPart(int partID) {
        for (Part p : allParts) {
            if (p.getPartID() == partID) {
                return p;
            }
        }
        return null;
    }

    /** Method to look up productID.
     * @param productID  Passes productID.
     * @return p. */
    public static Product lookupProduct(int productID) {
        for (Product p : allProducts) {
            if (p.getProductID() == productID) {
                return p;
            }
        }
        return null;
    }

    /** Method to look up part.
     * @param name  Passes name.
     * @return (ObservableList<Part>) p. */
    public static ObservableList<Part> lookupPart(String name) {
        for (Part p : allParts) {
            if (p.getName().equals(name)) {
                return (ObservableList<Part>) p;
            }
        }
        return null;

    }

    /** Method to look up part string, converts to lower case.
     * @param name  Passes name.
     * @return p. */
    public static Part lookupPartString(String name) {
        String lowerCaseName = name.toLowerCase();
        for (Part p : allParts) {
            if (p.getName().toLowerCase().contains(lowerCaseName)) {
                return p;
            }
        }
        return null;

    }


    /** Method to look up product string, converts to lower case.
     * @param name  Passes name.
     * @return p. */
    public static Product lookupProductString(String name) {
        String lowerCaseName = name.toLowerCase();
        for (Product p : allProducts) {
            if (p.getName().toLowerCase().contains(lowerCaseName)) {
                return p;
            }
        }
        return null;

    }


    /** Method to look up product.
     * @param name  Passes name.
     * @return (ObservableList<Product>) p. */
    public static ObservableList<Product> lookupProduct(String name) {
           for (Product p : allProducts) {
               if (p.getName().equals(name)) {
                   return (ObservableList<Product>) p;
               }
           }
           return null;
    }

    /** Method to update part list.
     * @param partID  Passes partID.
     * @param part  Passes part.
     * @return allParts.set(partID, part). */
    public static void updatePart(int partID, Part part) {
           allParts.set(partID, part);
    }

    /** Method to update product list.
     * @param partID  Passes partID.
     * @param newProduct  Passes newProduct.
     * @return allProducts.set(partID, newProduct). */
    public static void updateProduct(int partID, Product newProduct) {
          allProducts.set(partID, newProduct);
    }

    /** Method to delete selectedPart.
     * @param selectedPart  Passes selectedPart.
     * @return allParts.remove(selectedPart). */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /** Method to delete selectedProduct.
     * @param selectedProduct  Passes selectedProduct.
     * @return allProducts.remove(selectedProduct). */
    public static boolean deleteProduct(Product selectedProduct) {
           return allProducts.remove(selectedProduct);
    }


    /** Method to return incremented partID list count.
     * @return partIdListCount. */
    public static int getPartIdListCount() {
        partIdListCount++;
        return partIdListCount;
    }



    /** Method to return incremented productID list count.
     * @return productIdListCount. */
    public static int getProductIdListCount() {
        productIdListCount++;
        return productIdListCount;
    }


    /** Method to return allParts.
     * @return allParts. */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    /** Method to return allProducts.
     * @return allProducts. */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
