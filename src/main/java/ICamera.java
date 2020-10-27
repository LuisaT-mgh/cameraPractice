public interface ICamera {
    public void on();
    public char[][] getRawFacePicture(int faceID);
    public int[] getFaceArea(char[][] face);
    public Picture extractFace(int id, char[][] face, int[] area);
    public void off();
}