package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements ShoppingCartOperation {

    // JAVA 8
    Map<String, ProductInfo> productsList = new HashMap<>();

    public boolean addProducts(String productName, int price, int quantity) {
        if(productsList.size() >= PRODUCTS_LIMIT || price <= 0 || quantity <= 0){
            return false;
        } else {
            if(!productsList.containsKey(productName)){
                productsList.put(productName, new ProductInfo(price, quantity));
                return true;
            } else if(productsList.containsKey(productName) && productsList.get(productName).getPrice() == price){
                productsList.replace(productName, new ProductInfo(price,
                        productsList.get(productName).getQuantity()+quantity));
                return true;
            } else return false;
        }
    }

    public boolean deleteProducts(String productName, int quantity) {
        if(productsList.containsKey(productName)){
            if(productsList.get(productName).getQuantity() > quantity){
                productsList.replace(productName, new ProductInfo(productsList.get(productName).getPrice(), productsList.get(productName).getQuantity()-quantity));
                return true;
            } else if(quantity == productsList.get(productName).getQuantity()){
                productsList.remove(productName);
                return true;
            }
            else return false;
        }

        return false;
    }

    public int getQuantityOfProduct(String productName) {
        if(productsList.containsKey(productName)){
            return productsList.get(productName).getQuantity();
        } else return 0;
    }

    public int getSumProductsPrices() {
        int sum = 0;
        for(Map.Entry<String, ProductInfo> entiry: productsList.entrySet()) {
            sum+=entiry.getValue().getPrice();
        }
        return sum;
    }

    public int getProductPrice(String productName) {
        if(productsList.containsKey(productName)){
            return productsList.get(productName).getPrice();
        } else return 0;
    }

    public List<String> getProductsNames() {
        List<String> productnames = new ArrayList<>();
        for(Map.Entry<String, ProductInfo> entiry: productsList.entrySet()){
            productnames.add(entiry.getKey());
        }
        return productnames;
    }
}
