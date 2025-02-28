
public class PCM_DeltaModulation {

    public static String modulate(String analogData, String modulationType) {
        String[] amplitudeValues = analogData.trim().split("\\s+"); // Split by whitespace
        int[] amplitudes = new int[amplitudeValues.length];

        // Parse input string into integer amplitudes
        for (int i = 0; i < amplitudeValues.length; i++) {
            amplitudes[i] = Integer.parseInt(amplitudeValues[i]);
        }

        return switch (modulationType.toUpperCase()) {
            case "PCM" -> pcmModulation(amplitudes);
            case "DELTA" -> deltaModulation(amplitudes);
            default -> "Invalid modulation type!";
        };
    }

    private static String pcmModulation(int[] amplitudes) {
        StringBuilder pcmSignal = new StringBuilder();
        int quantizationLevels = 16; // Example quantization levels (4 bits for simplicity)

        for (int amplitude : amplitudes) {
            int quantizedValue = quantize(amplitude, quantizationLevels);
            String binaryString = String.format("%4s", Integer.toBinaryString(quantizedValue)).replace(' ', '0'); // 4-bit binary
            pcmSignal.append(binaryString).append(" ");
        }

        return pcmSignal.toString().trim();
    }

    private static int quantize(int amplitude, int levels) {
        if (levels <= 1) {
            throw new IllegalArgumentException("Quantization levels must be greater than 1.");
        }
    
        int maxAmplitude = 7;
        int minAmplitude = -8;
        int range = maxAmplitude - minAmplitude;
        int stepSize = range / levels;
    
        if (stepSize == 0) {
            stepSize = 1;  // Set a minimum step size to prevent division by zero
        }
    
        return (amplitude - minAmplitude) / stepSize;
    }
    

    private static String deltaModulation(int[] amplitudes) {
        StringBuilder deltaSignal = new StringBuilder();
        int previousAmplitude = 0; // Starting reference point

        for (int amplitude : amplitudes) {
            if (amplitude > previousAmplitude) {
                deltaSignal.append("1"); // Increase in amplitude
            } else {
                deltaSignal.append("0"); // Decrease in amplitude
            }
            previousAmplitude = amplitude;
        }

        return deltaSignal.toString();
    }
}
