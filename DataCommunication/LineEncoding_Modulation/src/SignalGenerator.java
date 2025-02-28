
import java.util.ArrayList;
import java.util.Scanner;

public class SignalGenerator {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Enter input type (digital/analog): ");
        String inputType = scanner.nextLine();

        if (inputType.equalsIgnoreCase("digital")) {
            handleDigitalInput(inputType);
        } else if (inputType.equalsIgnoreCase("analog")) {
            handleAnalogInput();
        } else {
            System.out.println("Invalid input type.");
        }
    }

    //added code from here
    private int[] parseStringToArray(String encodedSignal) {

        String input = encodedSignal;  // Your input string

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Check for -1
            if (ch == '-') {
                if (i + 1 < input.length() && input.charAt(i + 1) == '1') {
                    numbers.add(-1);
                    i++;  // Skip the next character since it's part of -1
                }
            } else {
                // Convert '0' or '1' characters to integers
                numbers.add(Character.getNumericValue(ch));
            }
        }

        // Convert ArrayList to an int array
        int[] result = numbers.stream().mapToInt(Integer::intValue).toArray();

        return result;

    }

//till here
    @SuppressWarnings("static-access")
    private void handleDigitalInput(String inputType) {
        System.out.println("Enter digital data stream (e.g., 1011001): ");
        String dataStream = scanner.nextLine();

        System.out.println("Choose encoding scheme (NRZ-L, NRZ-I, Manchester, Differential Manchester, AMI): ");
        String encodingScheme = scanner.nextLine();

        String encodedSignal = EncodingSchemes.encode(dataStream, encodingScheme);

        if (encodingScheme.equalsIgnoreCase("AMI")) {
            System.out.println("Do you want scrambling? (yes/no): ");
            String scramblingChoice = scanner.nextLine();
            if (scramblingChoice.equalsIgnoreCase("yes")) {
                System.out.println("Choose scrambling scheme (B8ZS, HDB3): ");
                String scrambleScheme = scanner.nextLine();
                encodedSignal = Scrambler.scramble(encodedSignal, scrambleScheme);
            }
        }

        System.out.println("Encoded Signal: " + encodedSignal);
        System.out.println("Longest Palindrome: " + PalindromeFinder.findLongestPalindrome(encodedSignal));

        //start
        if ("digital".equals(inputType)) {
            int[] result = parseStringToArray(encodedSignal);
            DigitalGraph obj = new DigitalGraph(result);
            obj.main(null, result);

        }

    }

    @SuppressWarnings("static-access")
    private void handleAnalogInput() {
        System.out.println("Enter analog data (e.g., a list of amplitude values from -7 to 8): ");
        String analogData = scanner.nextLine(); // Further parsing may be needed

        System.out.println("Choose modulation (PCM/Delta): ");
        String modulationType = scanner.nextLine();

        String modulatedSignal = PCM_DeltaModulation.modulate(analogData, modulationType);
        System.out.println("Modulated Signal: " + modulatedSignal);
        System.out.println("Longest Palindrome: " + PalindromeFinder.findLongestPalindrome(modulatedSignal));

        if (modulationType.equals("PCM")) {
            int result[] = parseStringToArray(modulatedSignal);
            PCMgraph obj1 = new PCMgraph(result);
            obj1.main(null, modulatedSignal);

        } else if (modulationType.equals("Delta")) {
            int result[] = parseStringToArray(modulatedSignal);
            DeltaGraph obj = new DeltaGraph(result);
            obj.main(null, result);
        }
    }

    public static void main(String[] args) {
        new SignalGenerator().start();
    }

}
