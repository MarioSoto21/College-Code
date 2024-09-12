package objects;

public class Customer {
    private int userID;
    private String name; //Stored as (First Last)
    private String email; //NOT USED TO LOG IN
    private Card cardData;
    private int numberPeople;
    private int age;
    private boolean veteran;
    private Cart cartData;

    public Customer(int userID, String name, String email, Card cardData, int numberPeople, int age, boolean veteran, Cart cartData) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.cardData = cardData;
        this.numberPeople = numberPeople;
        this.age = age;
        this.veteran = veteran;
        this.cartData = cartData;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Card getCardData() {
        return cardData;
    }

    public void setCardData(Card cardData) {
        this.cardData = cardData;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }
    
    public Cart getCartData() {
        return cartData;
    }

    public void setCartData(Cart cartData) {
        this.cartData = cartData;
    }
}