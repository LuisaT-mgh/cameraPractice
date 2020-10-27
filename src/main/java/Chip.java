import java.util.UUID;

public class Chip {
    private final Core[] cores;
    private final String uuid;

    public Chip() {
        uuid = UUID.randomUUID().toString();
        cores = new Core[]{new Core(0), new Core(1)};
    }
}