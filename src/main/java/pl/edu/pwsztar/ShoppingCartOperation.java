package pl.edu.pwsztar;

import java.util.List;

interface ShoppingCartOperation {

    int PRODUCTS_LIMIT = 500;   // limit produktow w koszyku

    /**
     * Dodaje produkty do koszyka w odpowiedniej ilosci i cenie
     * Zwraca TRUE w przypadku poprawnego dodania produktow do koszyka - jesli istnieje produkt o takiej samej nazwie w
     * koszyku wowczas zwiekszamy jego ilosc, ale pod warunkiem ze cena za produkt przypadkiem nie ulegla zmianie
     * Zwraca FALSE w przypadku bledu: Np. Jesli istnieje produkt w koszyku ale cena za pojedynczy produkt sie nie zgadza
     * */
    boolean addProducts(String productName, int price, int quantity);

    /** Usuwa odpowiednia ilosc istniejacych produktow z koszyka */
    boolean deleteProducts(String productName, int quantity);

    /** Pobiera ilosc konkretnego produktu dostepnego w koszyku */
    int getQuantityOfProduct(String productName);

    /** Pobiera cene wszystkich produktow dostepnych w koszyku */
    int getSumProductsPrices();

    /** Pobiera cene konkretnego produktu z koszyka zakupowego */
    int getProductPrice(String productName);

    /** Pobiera nazwy wszystkich dostepnych produktow w koszyku */
    List<String> getProductsNames();
}
