interface Easel{
    void use();
}
interface Paint{
    void apply();
}
class ProfessionalEasel implements Easel{
    @Override
    public void use() {
        System.out.println("Using prof easels for larger canvas");
    }
}

class OilPaint implements Paint{
    @Override
    public void apply() {
        System.out.println("Applying vibrant oil paints");
    }
}
class ForBeginnersEasel implements Easel{
    @Override
    public void use() {
        System.out.println("Using beginners easels for smaller canvas");
    }
}

class WaterPaint implements Paint{
    @Override
    public void apply() {
        System.out.println("Applying easy-to-use water paints");
    }
}

// --------- Factory Pattern (Single Product Example) ---------
class EaselFactory {
    public static Easel createEasel(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        return switch (type.toLowerCase()) {
            case "professional" -> new ProfessionalEasel();
            case "beginner" -> new ForBeginnersEasel();
            default -> throw new IllegalArgumentException("Unknown easel type: " + type);
        };
    }
}

// --------- Abstract Factory Pattern (Family of Products) ---------
interface ArtSupplyFactory {
    Easel createEasel();
    Paint createPaint();
}

// Concrete Factories
class ProfessionalArtSupplyFactory implements ArtSupplyFactory {
    @Override
    public Easel createEasel() {
        return new ProfessionalEasel();
    }

    @Override
    public Paint createPaint() {
        return new OilPaint();
    }
}

class BeginnerArtSupplyFactory implements ArtSupplyFactory {
    @Override
    public Easel createEasel() {
        return new ForBeginnersEasel();
    }

    @Override
    public Paint createPaint() {
        return new WaterPaint();
    }
}
class ArtistClient {
    private final ArtSupplyFactory factory;

    // Конструктор получает фабрику извне
    public ArtistClient(ArtSupplyFactory factory) {
        this.factory = factory;
    }

    public void createArt() {
        Easel easel = factory.createEasel();
        Paint paint = factory.createPaint();

        easel.use();
        paint.apply();
    }
}

// --------- Client Code ---------
class MockArtSupplyFactory implements ArtSupplyFactory {
    @Override
    public Easel createEasel() {
        return () -> System.out.println("[Mock Easel]");
    }

    @Override
    public Paint createPaint() {
        return () -> System.out.println("[Mock Paint]");
    }
}
public class Artist {
    public static void main(String[] args) {
        // Можно легко поменять фабрику
        ArtSupplyFactory factory = new ProfessionalArtSupplyFactory();
        ArtistClient client = new ArtistClient(factory);

        client.createArt();
    }
}

