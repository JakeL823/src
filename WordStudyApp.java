import java.util.*;
import java.io.*;

public class WordStudyApp {
    
    private static List<Word> wordList = new ArrayList<>();
    
    static class Word {
        String english;
        String japanese;

        Word(String english, String japanese) {
            this.english = english;
            this.japanese = japanese;
        }
    }

    // 添加单词对
    public static void addWord(String english, String japanese) {
        wordList.add(new Word(english, japanese));
    }

    // 保存单词到文件
    public static void saveWords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("words.dat"))) {
            oos.writeObject(wordList);
        } catch (IOException e) {
            System.out.println("保存文件时出错: " + e.getMessage());
        }
    }

    // 加载单词列表
    public static void loadWords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("words.dat"))) {
            wordList = (List<Word>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("加载文件时出错: " + e.getMessage());
        }
    }

    // 复习
    public static void review() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        
        for (Word word : wordList) {
            System.out.println("日语单词: " + word.japanese);
            System.out.print("请输入对应的英语单词: ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase(word.english)) {
                System.out.println("正确！");
            } else {
                System.out.println("错误！正确答案是: " + word.english);
            }
        }
    }

    // 主程序
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 加载已保存的单词
        loadWords();

        while (true) {
            System.out.println("1. 添加单词");
            System.out.println("2. 复习");
            System.out.println("3. 保存并退出");
            System.out.print("请选择操作: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除输入缓冲区

            if (choice == 1) {
                System.out.print("请输入英语单词: ");
                String english = scanner.nextLine();
                System.out.print("请输入日语单词: ");
                String japanese = scanner.nextLine();
                addWord(english, japanese);
            } else if (choice == 2) {
                review();
            } else if (choice == 3) {
                saveWords();
                break;
            } else {
                System.out.println("无效的选项，请重新选择。");
            }
        }

        scanner.close();
    }
}