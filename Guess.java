import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// ポケモンの情報を表すクラス
class Pokemon {
    String name; // 名前
    String type1; // タイプ1
    String type2; // タイプ2、もしなければnullでもいい
    int baseStat; // 種族値

    // コンストラクタで初期化
    public Pokemon(String name, String type1, String type2, int baseStat) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.baseStat = baseStat;
    }

    // タイプ判定
    public boolean hasSameType(Pokemon other) {
        return this.type1.equals(other.type1) ||
                this.type1.equals(other.type2) ||
                (this.type2 != null && (this.type2.equals(other.type1) || this.type2.equals(other.type2)));
    }

}

public class Guess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // ポケモンのリストを作成（こちらは将来でデーターベースを繋いでするほうがいいかな）
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon("フシギダネ", "くさ", "どく", 318));
        pokemonList.add(new Pokemon("フシギソウ", "くさ", "どく", 405));
        pokemonList.add(new Pokemon("フシギバナ", "くさ", "どく", 525));
        pokemonList.add(new Pokemon("ヒトカゲ", "ほのお", null, 309));
        pokemonList.add(new Pokemon("リザード", "ほのお", null, 405));
        pokemonList.add(new Pokemon("リザードン", "ほのお", "ひこう", 534));
        pokemonList.add(new Pokemon("ゼニガメ", "みず", null, 314));
        pokemonList.add(new Pokemon("カメール", "みず", null, 405));
        pokemonList.add(new Pokemon("カメックス", "みず", null, 530));
        pokemonList.add(new Pokemon("ピカチュウ", "でんき", null, 320));
        pokemonList.add(new Pokemon("ライチュウ", "でんき", null, 485));

        // 名前をkeyとして、valueのPokemonと繋いで探すことになる
        Map<String, Pokemon> nameMap = new HashMap<>();
        for (Pokemon p : pokemonList) {
            nameMap.put(p.name, p);
        }

        // 正解となるポケモンをランダムに選ぶ
        Pokemon answer = pokemonList.get(random.nextInt(pokemonList.size()));

        System.out.println("※※※　ポケモンクイズゲーム　※※※");
        System.out.print("何回挑戦しますか？：");
        int tries = scanner.nextInt();
        scanner.nextLine();

        // ゲームループ
        while (tries > 0) {
            System.out.println("\n残り回数：" + tries);
            System.out.print("ポケモンの名前を入力してください（日本語）： ");
            String input = scanner.nextLine().trim();
            input = input.replaceAll("　", "").trim(); // 全角スペースを削除
            input = input.replaceAll(" ", "").trim(); // 半角スペースを削除

            // 答えの正しさを判定する
            if (input.equals(answer.name)) {
                System.out.println("正解です！ポケモンは「" + answer.name + "」です！");
                System.out.println("タイプ：" + answer.type1 + (answer.type2 != null ? " / " + answer.type2 : ""));
                System.out.println("種族値：" + answer.baseStat);
                break;
            } else {
                System.out.println("不正解！");

                // リストにあるかどうか確認する
                Pokemon guess = nameMap.get(input);
                if (guess == null) {
                    System.out.println("カントー地方(151)のポケモンを入力してください");
                } else {
                    // タイプを比べる
                    if (guess.hasSameType(answer)) {
                        System.out.println("ヒント：タイプのどれかは一致しています！");
                    } else {
                        System.out.println("ヒント：タイプは全く違います。");
                    }

                    // 種族値を比べる
                    if (guess.baseStat > answer.baseStat) {
                        System.out.println("ヒント：種族値は高すぎます！");
                    } else if (guess.baseStat < answer.baseStat) {
                        System.out.println("ヒント：種族値は低すぎます！");
                    } else {
                        System.out.println("ヒント：種族値は同じです！");
                    }
                }
            }

            tries--; // 回答回数を減らす
        }

        // ゲームオーバーの処理
        if (tries == 0) {
            System.out.println("\n ゲームオーバー！");
            System.out.println("正解は「" + answer.name + "」でした。");
            System.out.println("タイプ：" + answer.type1 + (answer.type2 != null ? " / " + answer.type2 : ""));
            System.out.println("種族値：" + answer.baseStat);
        }

        scanner.close();
    }
}