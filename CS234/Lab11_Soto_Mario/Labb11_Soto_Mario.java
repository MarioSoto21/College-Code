public class Labb11_Soto_Mario {

    public static void main(String[] args) {
        Runway airport = new Runway();
        
        airport.addLanding("LAX123");
        airport.addLanding("NY345");
        airport.addLanding("LAX234");
        airport.addTakeOff("FR234");
        airport.addLanding("LAX123");
        airport.addTakeOff("CHI234");
        airport.addLanding("AZ1234");
        airport.addTakeOff("MX234");
        
        airport.handleFlights();
    }
}
