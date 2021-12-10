package task19;

import org.junit.Test;

public class TestScenario {

    public Application app = new Application();

    @Test
    public void addingAndDeletingProducts() throws Exception {
        int amountOfProductsToAdd = 3;
        while (amountOfProductsToAdd>0) {
            app.chooseProductByParameters("Most Popular");
            app.addProductToCart(2,"Large");
            amountOfProductsToAdd--;
        }
        app.removeAllProductsFromTheCart();
        app.closeApplication();
    }
}
