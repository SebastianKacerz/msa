import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {

    private String[] rowA;
    private String[] rowB;
    private Status[] guessedA;
    private Status[] guessedB;

    Board(String[] words) {
        rowA = shuffleTable(words);
        rowB = shuffleTable(words);
        guessedA = new Status[words.length];
        guessedB = new Status[words.length];
        for (int i = 0; i < words.length; i++) {
            guessedA[i] = Status.COVERED;
            guessedB[i] = Status.COVERED;
        }
    }

    private String[] shuffleTable(String[] table) {
        List<String> list = Arrays.asList(table);
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

    public void print(int chances) {
        String level;
        if (rowA.length == 4) {
            level = "easy";
        } else {
            level = "hard";
        }
        System.out.println("—-----------------------------------");
        System.out.println("Level: " + level);
        System.out.println("Guess chances: " + chances);

        String row = " ";
        for (int i = 0; i < rowA.length; i++) {
            row += i + 1 + " ";
        }
        System.out.println(row);

        String rowAString = "A ";
        for (int i = 0; i < rowA.length; i++) {
            if (guessedA[i] != Status.COVERED) {
                rowAString += (rowA[i] + " ");
            } else {
                rowAString += "X ";
            }
        }
        System.out.println(rowAString);

        String rowBString = "B ";
        for (int i = 0; i < rowB.length; i++) {
            if (guessedB[i] != Status.COVERED) {
                rowBString += rowB[i] + " ";
            } else {
                rowBString += "X ";
            }
        }
        System.out.println(rowBString);
        System.out.println("—-----------------------------------");
    }

    public void show(char row, int index) {
        if (row == 'A') {
            guessedA[index - 1] = Status.TEMPORARY_SHOWN;
        } else {
            guessedB[index - 1] = Status.TEMPORARY_SHOWN;
        }
    }

    public void clean() {
        for (int a = 0; a < guessedA.length; a++) {
            if (guessedA[a] == Status.TEMPORARY_SHOWN) {
                for (int b = 0; b < guessedB.length; b++) {
                    if (guessedB[b] == Status.TEMPORARY_SHOWN) {
                        if (rowA[a] == rowB[b]) {
                            guessedA[a] = Status.GUESSED;
                            guessedB[b] = Status.GUESSED;
                            return;
                        }
                    }
                }
            }
        }

        for (int a = 0; a < guessedA.length; a++) {
            if (guessedA[a] == Status.TEMPORARY_SHOWN) {
                guessedA[a] = Status.COVERED;
            }
        }

        for (int b = 0; b < guessedB.length; b++) {
            if (guessedB[b] == Status.TEMPORARY_SHOWN) {
                guessedB[b] = Status.COVERED;
            }
        }
    }

    public boolean ready() {
        for (int a = 0; a < guessedA.length; a++) {
            if (guessedA[a] == Status.COVERED) {
                return false;
            }
        }

        for (int b = 0; b < guessedB.length; b++) {
            if (guessedB[b] == Status.COVERED) {
                return false;
            }
        }
        return true;
    }
}

enum Status {
    COVERED, GUESSED, TEMPORARY_SHOWN
}
