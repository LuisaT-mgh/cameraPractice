public class Picture {
    private int faceID;
    private char[][] content;
    private long nanoTimeStamp;

    public Picture(char[][] content, int faceID) {
        nanoTimeStamp = System.nanoTime();
        this.content = content;
        this.faceID = faceID;
    }
}