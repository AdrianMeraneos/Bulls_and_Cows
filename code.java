package bullscows;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String l = sc.next();
        int länge;
        try {
            länge = Integer.parseInt(l);
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + l + "\" isn't a valid number.");
            return;
        }
        if (länge == 0) {
            System.out.print("Error: '0' is an invalid length for the secret code.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int moeglicheSymbole = sc.nextInt();
        if (moeglicheSymbole < länge) {
            System.out.println("Error: it's not possible to generate a code with a length of " + länge + " with " + moeglicheSymbole + " unique symbols.");
            return;
        }
        if (moeglicheSymbole > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        String secretnumber = createRandomCode(länge, moeglicheSymbole).toString();
        System.out.println("Okay, let's start a game!");
        boolean guessed = false;
        int i = 0, bulls = 0, cows = 0;
        while (!guessed) {
            System.out.println("Turn " + i + ":");
            i++;
            StringBuilder number = new StringBuilder();
            number.append(sc.nextLine());
            for (int j = 0; j < number.length(); j++) {
                for (int k = 0; k < secretnumber.length(); k++) {
                    if (number.charAt(j) == secretnumber.charAt(k)) {
                        if (j == k) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }
            System.out.println("Grade: " + bulls + ((bulls > 1) ? " bulls " : " bull ") + ((cows > 1) ? "and "+cows+" cows" : "and "+cows+" cow"));
            if (bulls == secretnumber.length()) {
                System.out.print("Congratulations! You guessed the secret code.");
                break;
            }
            bulls = 0;
            cows = 0;
        }
    }

    public static StringBuilder createRandomCode (int length, int possibleSymbols) {
        StringBuilder s1 = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");
        StringBuilder s =  new StringBuilder();
        if (length > 36) {
            System.out.print("Error: There aren't enough digits to create a secret number of length 11!");
            return s;
        }
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < length; i++) {System.out.print("*");}
        if (possibleSymbols == 1) System.out.print(" (0).");
        if (possibleSymbols <= 10) System.out.print(" (0-" + s1.charAt(possibleSymbols-1) + ").");
        if (possibleSymbols == 11) System.out.print(" (0-9, a).");
        if (possibleSymbols > 11) System.out.print(" (0-9, a-" + s1.charAt(possibleSymbols-1)+").");
        int secondNumber;
        for (int i = 0; i < length; i++) {
            secondNumber = (int) (Math.random() * length);
            s.append(s1.charAt(secondNumber));
            s1.delete(secondNumber, secondNumber+1);
        }
        return s;
    }
}
