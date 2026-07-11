public class Main {

    public static void main(String[] args) {

        PlanetGenerator generator = new PlanetGenerator();

        planet myPlanet = generator.generatePlanet();

        System.out.println("Planet Name: " + myPlanet.name);
        System.out.println("Planet Type: " + myPlanet.type);
        System.out.println("Planet Radius: " + myPlanet.radius + " km");
    }
}