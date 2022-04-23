package pl.edu.pwsztar;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ShoppingCartTest {

    @Test
    void checkAddProductIntoShoppingCart() {
        // given
        ShoppingCartOperation shoppingCart = new ShoppingCart();
        List<Boolean> addProductsResults = new ArrayList<>();
        List<Boolean> expectedProducts = Arrays.asList(true, true, false, false, false, true);

        // when
        addProductsResults.add(shoppingCart.addProducts("coca_cola", 450, 5));
        addProductsResults.add(shoppingCart.addProducts("iphone 6s", 100000, 1));
        addProductsResults.add(shoppingCart.addProducts("gum", 10, 0));
        addProductsResults.add(shoppingCart.addProducts("sprite", -100, 1));
        addProductsResults.add(shoppingCart.addProducts("coca_cola", 451, 1));
        addProductsResults.add(shoppingCart.addProducts("coca_cola", 450, 1));

        // then
        assertIterableEquals(expectedProducts, addProductsResults);
    }

    @Test
    void checkDeleteProductFromShoppingCart() {
        // given
        ShoppingCartOperation shoppingCart = new ShoppingCart();
        List<Boolean> deleteProductsResults = new ArrayList<>();
        List<Boolean> expectedProducts = Arrays.asList(false, true, false, true, false);

        // when
        shoppingCart.addProducts("coca_cola", 450, 5);
        shoppingCart.addProducts("iphone 6s", 100000, 1);

        deleteProductsResults.add(shoppingCart.deleteProducts("gum", 1));
        deleteProductsResults.add(shoppingCart.deleteProducts("coca_cola", 4));
        deleteProductsResults.add(shoppingCart.deleteProducts("iphone 6s", 2));
        deleteProductsResults.add(shoppingCart.deleteProducts("iphone 6s", 1));
        deleteProductsResults.add(shoppingCart.deleteProducts("iphone 6s", -1));

        // then
        assertIterableEquals(expectedProducts, deleteProductsResults);
    }

    @Test
    void checkEmptyQuantityOfProduct() {
        // given
        ShoppingCartOperation shoppingCart = new ShoppingCart();

        // when
        shoppingCart.addProducts("coca_cola", 450, -1);
        shoppingCart.deleteProducts("gum", 1);
        shoppingCart.addProducts("LG FLATRON", 80000, 1);
        shoppingCart.deleteProducts("LG FLATRON", 1);

        // then
        assertEquals(0, shoppingCart.getQuantityOfProduct("coca_cola"));
        assertEquals(0, shoppingCart.getQuantityOfProduct("gum"));
        assertEquals(0, shoppingCart.getQuantityOfProduct(""));
        assertEquals(0, shoppingCart.getQuantityOfProduct(null));
        assertEquals(0, shoppingCart.getQuantityOfProduct("LG FLATRON"));
    }

    @Test
    void checkQuantityOfProduct() {
        // given
        ShoppingCartOperation shoppingCart = new ShoppingCart();

        // when
        shoppingCart.addProducts("nintendo", 50000, 1);
        shoppingCart.addProducts("nintendo", 50000, 5);
        shoppingCart.addProducts("xbox 360", 90000, 5);
        shoppingCart.deleteProducts("xbox 360", 4);

        // then
        assertEquals(6, shoppingCart.getQuantityOfProduct("nintendo"));
        assertEquals(1, shoppingCart.getQuantityOfProduct("xbox 360"));
    }

    @Test
    void checkSumProductsPrices() {
        // given
        ShoppingCartOperation empty = new ShoppingCart();
        ShoppingCartOperation full = new ShoppingCart();

        // when
        for(int counter = 1; counter <= ShoppingCartOperation.PRODUCTS_LIMIT + 1; counter++) {
            full.addProducts("product_"+counter, 1, 1);
        }

        // then
        assertEquals(0, empty.getSumProductsPrices());
        assertEquals(ShoppingCartOperation.PRODUCTS_LIMIT, full.getSumProductsPrices());
    }

    @Test
    void checkProductPrice() {
        // given
        ShoppingCartOperation shoppingCartCov19 = new ShoppingCart();

        // when
        shoppingCartCov19.addProducts("toilet paper", 600, 100);
        shoppingCartCov19.addProducts("yeast", 200, 40);
        shoppingCartCov19.addProducts("mask", 400, 25);
        shoppingCartCov19.addProducts("wine", 1500, 15);
        shoppingCartCov19.addProducts("yeast", 1500, 19);

        //then
        assertEquals(600, shoppingCartCov19.getProductPrice("toilet paper"));
        assertEquals(200, shoppingCartCov19.getProductPrice("yeast"));
        assertEquals(400, shoppingCartCov19.getProductPrice("mask"));
        assertEquals(1500, shoppingCartCov19.getProductPrice("wine"));
    }

    @Test
    void checkProductsNames() {
        // given
        ShoppingCartOperation cart = new ShoppingCart();
        List<String> result = new ArrayList<>(Arrays.asList("a", "b", "c"));

        // when
        cart.addProducts("a", 100, 1);
        cart.addProducts("b", 200, 2);
        cart.addProducts("c", 300, 3);

        // then
        assertIterableEquals(result, cart.getProductsNames().stream().sorted().collect(Collectors.toList()));
    }

}
