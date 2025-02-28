
public class Decoder {

    public static String decode(String encodedData, String scheme) {
        switch (scheme) {
            case "NRZ-L":
                return decodeNRZL(encodedData);
            case "NRZ-I":
                return decodeNRZI(encodedData);
            case "Manchester":
                return decodeManchester(encodedData);
            case "Differential Manchester":
                return decodeDifferentialManchester(encodedData);
            case "AMI":
                return decodeAMI(encodedData);
            default:
                throw new IllegalArgumentException("Unknown decoding scheme: " + scheme);
        }
    }

    private static String decodeNRZL(String data) {
        /* NRZ-L decoding logic */ return "NRZ-L decoded data";
    }

    private static String decodeNRZI(String data) {
        /* NRZ-I decoding logic */ return "NRZ-I decoded data";
    }

    private static String decodeManchester(String data) {
        /* Manchester decoding logic */ return "Manchester decoded data";
    }

    private static String decodeDifferentialManchester(String data) {
        /* Differential Manchester decoding logic */ return "Differential Manchester decoded data";
    }

    private static String decodeAMI(String data) {
        /* AMI decoding logic */ return "AMI decoded data";
    }
}
