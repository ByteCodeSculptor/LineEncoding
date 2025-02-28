
public class Scrambler {

    public static String scramble(String data, String scheme) {
        if (scheme.equalsIgnoreCase("B8ZS")) {
            return applyB8ZSEncoding(data);
        } else if (scheme.equalsIgnoreCase("HDB3")) {
            return applyHDB3Encoding(data);
        } else {
            throw new IllegalArgumentException("Unknown scrambling scheme: " + scheme);
        }
    }

    // B8ZS Encoding
    private static String applyB8ZSEncoding(String data) {
        StringBuilder result = new StringBuilder();
        int lastPulse = 1; // Assuming the last pulse is positive to start

        for (int i = 0; i < data.length();) {
            // Check for 8 consecutive zeros
            if (i <= data.length() - 8 && data.substring(i, i + 8).equals("00000000")) {
                // Apply B8ZS substitution based on last pulse
                if (lastPulse == 1) {
                    // Positive pulse before eight zeros, substitute with "000-1101-1"
                    result.append("0001-10-11");
                    lastPulse = 1; // Set the last pulse according to the end of this pattern
                } else {
                    // Negative pulse before eight zeros, substitute with "0001-10-11"
                    result.append("1000-1101-1");
                    lastPulse = -1; // Set the last pulse according to the end of this pattern
                }
                i += 8; // Skip the next 8 zeros
            } else {
                // Add the current bit and check for pulses
                char currentBit = data.charAt(i);
                result.append(currentBit);

                // Update lastPulse if we encounter a '1' or '-1'
                if (currentBit == '1') {
                    lastPulse = 1;
                } else if (currentBit == '-') {
                    lastPulse = -1;
                    i++; // Skip over the next character since it's part of "-1"
                }

                i++;
            }
        }

        return result.toString();
    }

    // HDB3 Encoding
    private static String applyHDB3Encoding(String data) {
        StringBuilder result = new StringBuilder();
        int pulseCount = 0;
        int lastPulse = 1; // Assuming the last pulse is positive to start

        for (int i = 0; i < data.length();) {
            // Check for 4 consecutive zeros
            if (i <= data.length() - 4 && data.substring(i, i + 4).equals("0000")) {
                if (pulseCount % 2 == 0) {
                    result.append(lastPulse == 1 ? "100V" : "000V");
                } else {
                    result.append("000V");
                }

                // Add violation pulse and reset pulse count
                pulseCount = 0;
                result.replace(result.length() - 1, result.length(), lastPulse == 1 ? "1" : "-1"); // Replace V with violation
                lastPulse *= -1; // Toggle pulse
                i += 4;
            } else {
                char currentBit = data.charAt(i);
                result.append(currentBit);

                // Update pulse count and lastPulse if currentBit is non-zero
                if (currentBit == '1' || currentBit == '-') {
                    pulseCount++;
                    lastPulse *= -1;
                }

                i++;
            }
        }
        return result.toString();
    }
}
