import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Camera implements ICamera {
    public final String serialNumber;
    private final MemoryCard memoryCard;
    private final Chip[] chips;
    private final IRLed[] irLeds;
    private boolean isOn;

    public Camera(Builder builder) {
        serialNumber = builder.serialNumber;
        memoryCard = builder.memoryCard;
        chips = builder.chips;
        irLeds = new IRLed[24];
        isOn = false;

        for (int i = 0; i < irLeds.length; i++) {
            irLeds[i] = new IRLed();
        }
    }

    @Override
    public void on() {
        isOn = true;
    }

    @Override
    public char[][] getRawFacePicture(int faceID) {
        char[][] face = new char[21][14];
        try {
            File pictureDataFile = new File(Configuration.instance.dataDirectory + "face"+String.format("%02d", faceID)+".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pictureDataFile));
            String line;
            int numberOfRow = 0;
            while ((line = bufferedReader.readLine()) != null) {
                for(int i = 0; i<line.length(); i++){
                    face[i][numberOfRow] = line.charAt(i);
                }
                numberOfRow++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return face;
    }

    @Override
    public int[] getFaceArea(char[][] face) {
        int[] area = new int[4];
        boolean hasFountFirst = false;
        for(int i = 0; i<21; i++){
            for(int j = 0; j<14; j++){
                if(!hasFountFirst){
                    if(face[i][j] == '+'){
                        area[0] = j-1;
                        area[1] = i;
                        hasFountFirst = true;
                    }
                }
                else{
                    if(face[i][j] == '+'){
                        area[2] = j+1;
                        area[3] = i;
                        hasFountFirst = true;
                    }
                }
            }
        }
        return area;
    }

    @Override
    public Picture extractFace(int id, char[][] face, int[] area) {
        char[][] pictureContent = new char[10][10];
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                pictureContent[i][j] = face[i+area[0]][j+area[1]];
            }
        }
        Picture picture = new Picture(pictureContent, id);
        memoryCard.getStore().add(picture);
        return picture;
    }

    @Override
    public void off() {
        isOn = false;
    }

    public static class Builder {
        private Chip[] chips = new Chip[2];
        private MemoryCard memoryCard;
        private String serialNumber;

        public Builder() {
        }

        public Builder chips(Chip[] chips) {
            System.arraycopy(chips, 0, this.chips, 0, Math.min(chips.length, 2));
            return this;
        }

        public Builder memoryCard(MemoryCard memoryCard) {
            this.memoryCard = memoryCard;
            return this;
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder defaultConfiguration() {
            memoryCard = new MemoryCard();
            chips = new Chip[]{new Chip(), new Chip()};
            return this;
        }

        public Camera build() {
            return new Camera(this);
        }
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public MemoryCard getMemoryCard() {
        return memoryCard;
    }

    public Chip[] getChips() {
        return chips;
    }

    public IRLed[] getIrLeds() {
        return irLeds;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}