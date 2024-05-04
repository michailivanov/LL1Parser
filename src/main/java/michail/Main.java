package michail;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        MyGrammar grammar = new MyGrammar();

        Scanner scanner = new Scanner(System.in);

        List<String> options = readOptionsFromFile("src/main/resources/sampleSentences.txt");

        int choice = -1;

        do {
            try {
                displayMenu(options);
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                    continue; // Restart the loop
                }
                String inputString = choiceString(choice, options, scanner);

                if(inputString != null && Character.isLowerCase(inputString.charAt(0))) // if the first char is lower
                {
                    System.out.println("A sentence should start with a capital letter! Try again.");
                } else if (inputString != null) {


                    LL1 ll1Parser = new LL1(grammar.getRules(), List.of(".", ",", "!"));
                    List<Terminal> tokenizedInput = LL1.tokenizeInput(inputString, grammar.getTerminals()); // Tokenize the input string
                    Iterable<Expression> expressions = ll1Parser.parse(tokenizedInput);
                    ll1Parser.printParsingSteps(expressions);
                    System.out.println("\n");

                }
                System.out.println("Press enter to continue...");
                System.in.read(); // Wait for user input
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != options.size() + 2);
    }



    private static void displayMenu(List<String> options) {
        System.out.println("\n-----------------\nMenu:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println((options.size() + 1) + ". Enter custom input");
        System.out.println((options.size() + 2) + ". Exit");
        System.out.print("Enter your choice: ");
    }

    private static List<String> readOptionsFromFile(String fileName) {
        List<String> options = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                options.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return options;
    }

    private static String choiceString(int choice, List<String> options, Scanner scanner) {
        if (choice >= 1 && choice <= options.size()) {
            System.out.println("You chose " + choice + ". " + options.get(choice - 1));
            return options.get(choice - 1);
        } else if (choice == options.size() + 1) {
            System.out.print("Enter your custom input: ");
            scanner.nextLine(); // Consume the newline character
            String customInput = scanner.nextLine();
            System.out.println("You entered: " + customInput);
            return customInput;
        }
        else if (choice == options.size() + 2) {
            System.out.println("Exiting...");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return null;
    }
}


