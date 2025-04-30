abstract class Store {
    protected String[] data = new String[2]; // ・・・※
    protected int index = 0;

    public void put(String value) {
        try {
            data[index] = value;
            index++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("これ以上追加できません!");
        }
    }

    public abstract String get();
    public int getSize() {
        return index;
    }

}

class FifoStore extends Store {

    public String get() {
        String value = data[0];
        for (int i = 0; i < index - 1; i++) {
            data[i] = data[i + 1];
        }
        data[index - 1] = null;
        index--;
        return value;
    }

}

class LifoStore extends Store {

    public String get() {
        String value = data[index - 1];
        data[index - 1] = null;
        index--;
        return value;
    }
}

public class StoreTest01 {
    public static void main(String[] args) {
        String[] str = { "起", "承", "転", "結" };
        FifoStore fifo = new FifoStore();
        LifoStore lifo = new LifoStore();
        for (int i = 0; i < str.length; i++) {
            fifo.put(str[i]);
            lifo.put(str[i]);
        }
        printData(fifo);
        printData(lifo);
    }

    public static void printData(Store store) {
        int size = store.getSize();
        for (int i = 0; i < size; i++) {
            System.out.print(i + ":" + store.get() + "");
        }
        System.out.println();
    }
}