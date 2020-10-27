import java.util.Stack;

public class MemoryCard {
    private Stack<Picture> store = new Stack<>();

    public Stack<Picture> getStore() {
        return store;
    }

    public void setStore(Stack<Picture> store) {
        this.store = store;
    }
}