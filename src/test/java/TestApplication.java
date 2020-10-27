import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    private Camera camera;

    @BeforeEach
    public void setup() {
        Chip chip01 = new Chip();
        Chip chip02 = new Chip();

        MemoryCard memoryCard = new MemoryCard();

        Camera camera = new Camera.Builder()
                .serialNumber("12345")
                .chips(new Chip[]{chip01, chip02})
                .memoryCard(memoryCard)
                .build();
    }

    @Test
    @Order(1)
    public void builderCamera() {

        Chip chip01 = new Chip();
        Chip chip02 = new Chip();

        MemoryCard memoryCard = new MemoryCard();

        Camera camera = new Camera.Builder()
                .serialNumber("12345")
                .chips(new Chip[]{chip01, chip02})
                .memoryCard(memoryCard)
                .build();

        assertEquals(memoryCard, camera.getMemoryCard());
        assertEquals("12345", camera.getSerialNumber());
    }

    @Test
    @Order(2)
    public void checkMethodOnOff(){
        camera.on();
        assertEquals(camera.isOn(), true);
        camera.off();
        assertEquals(camera.isOn(), false);
    }


    @Test
    @Order(3)
    public void getAreaOfFace(){

    }


    @Test
    @Order(4)
    public void getFace(){

    }


    @Test
    @Order(5)
    public void contentIsCorrect(){

    }
}