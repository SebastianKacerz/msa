import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] words = loadWords();

        Scanner scanner = new Scanner(System.in);
        String answer = "y";
        while (answer.equals("y")) {
            System.out.println("Welcome to memory game! \n Choose the mode, type: \n 1 - easy \n 2 - hard \n and press enter");
            int mode = Integer.parseInt(scanner.nextLine());

            boolean result;
            if (mode == 1) {
                result = startGame(drawXWords(4, words), 10, scanner);
            } else {
                result = startGame(drawXWords(8, words), 15, scanner);
            }

            if (result) {
                System.out.println("You won the game!");
            } else {
                System.out.println("You lost the game!");
            }

            System.out.println("Do you want to play again? Type y for yes or n for no and press enter");
            answer = scanner.nextLine();
        }

       System.out.println("Bye, bye!");
    }

    private static boolean startGame(String[] words, int chances, Scanner scanner) {
        Board board = new Board(words);
        while (chances > 0) {
            board.print(chances);
            System.out.println("Choose coordinates/number from first/A row and press enter");
            int indexA = Integer.parseInt(scanner.nextLine());
            board.show('A', indexA);
            board.print(chances);

            System.out.println("Choose coordinates from second/B row and press enter");
            int indexB = Integer.parseInt(scanner.nextLine());
            board.show('B', indexB);
            board.print(chances);
            board.clean();
            if (board.ready()) {
                return true;
            }
            chances--;
        }
        return false;
    }

    private static String[] drawXWords(int x, String[] words) {
        List<String> list = Arrays.asList(words);
        Collections.shuffle(list);

        String[] result = new String[x];
        for (int i = 0; i < x; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    private static String[] loadWords() throws IOException {
        String file = Files.readString(Path.of("src/Words.txt"));
        return file.split("\r\n");
    }
}
