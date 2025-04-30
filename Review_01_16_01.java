public class Review_01_16_01 {
    public static void main(String[] args){
    Student d1 =new Student(1033,"山川太郎",new PlasticBottle(500));
    d1.studentDrink(200);
    d1.studentDisplay();
    }
}

class Student {
    private int id;
    private String name;

    private PlasticBottle pb;
    public Student(int id, String name,PlasticBottle pb) {
    this.id = id;
    this.name = name;
    this.pb = pb;
    }

    public void studentDrink(int ryou){
        pb.drink(ryou);
    }

    public void studentDisplay(){
    System.out.println("学籍番号:" + id + "\t" + "名前:" + name);
    System.out.println("残り" + pb.getNokori()+ "ml のペットボトルを持っています");
    }
}

class PlasticBottle {
    private int size;
    private int nokori;

    PlasticBottle(int size) {
        this.size = size;
        this.nokori = size;
    }

    int getSize() {
        return size;
    }

    int getNokori() {
        return nokori;
    }

    void drink(int ryou) {
        this.nokori = this.nokori - ryou;
    }
}