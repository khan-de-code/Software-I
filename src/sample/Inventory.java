package sample;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public Inventory(ObservableList<Part> allParts, ObservableList<Product> allProducts) {
        this.allParts = allParts;
        this.allProducts = allProducts;
    }

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        for (Part currentPart : allParts) {
            if (currentPart.getId() == partId) {
                return currentPart;
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (Product currentProduct : allProducts) {
            if (currentProduct.getId() == productId) {
                return currentProduct;
            }
        }
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> temp = null;

        for (Part currentPart : allParts) {
            if (currentPart.getName() == partName) {
                temp.add(currentPart);
            }
        }

        return temp;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> temp = null;

        for (Product currentProduct : allProducts) {
            if (currentProduct.getName() == productName) {
                temp.add(currentProduct);
            }
        }

        return temp;
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.remove(index);
        allParts.add(selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.remove(index);
        allProducts.add(newProduct);
    }

    public boolean deletePart(Part selectedPart) {
        int temp = allParts.indexOf(selectedPart);
        allParts.remove(temp);
        return true;
    }

    public boolean deleteProduct(Product selectedProduct) {
        int temp = allProducts.indexOf(selectedProduct);
        allProducts.remove(temp);
        return true;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
