import java.util.Scanner;

public class Ex018 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        System.out.println("○と●を交互に表示");
        int n = scan.nextInt();
        if (n>1) {
         for (int i = 0; i < n; i++) {
            if (i%2 == 0) {
                System.out.print("○");
            } else {
                System.out.print("●");
            }
        }   
        }else{
            System.out.println("次回から１以上の数字を入力してください");
        }
        
    }
}
