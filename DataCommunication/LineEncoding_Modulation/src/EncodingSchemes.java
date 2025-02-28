
public class EncodingSchemes {

    public static String encode(String data, String scheme) {
        switch (scheme) {
            case "NRZ-L":
                return nrzl(data);
            case "NRZ-I":
                return nrzi(data);
            case "Manchester":
                return manchester(data);
            case "Differential Manchester":
                return differentialManchester(data);
            case "AMI":
                return ami(data);
            default:
                throw new IllegalArgumentException("Unknown encoding scheme: " + scheme);
        }
    }

    private static String nrzl(String data) {

        StringBuilder opNRZL = new StringBuilder();
        for (char ch : data.toCharArray()) {
            if (ch == '0') {
                opNRZL.append('1');
            } else if (ch == '1') {
                opNRZL.append('0');
            } else {
                // Handle invalid characters if needed
                throw new IllegalArgumentException("Input string contains non-binary characters.");
            }

        }
        return opNRZL.toString();
    }

    private static String nrzi(String data) {

        StringBuilder opNRZI = new StringBuilder();
        int count = 0;
        for (char ch : data.toCharArray()) {
            if (ch == '1') {
                count++;
            }
            if (count % 2 == 0) {
                opNRZI.append("-1");
            } else {
                opNRZI.append('0');
            }
        }
        return opNRZI.toString();
    }

    private static String manchester(String data) {   // here i am using IEE  representation were  0 = 10 & 1 = 01
        StringBuilder opMANCHESTER = new StringBuilder();
        for (char ch : data.toCharArray()) {
            if (ch == '0') {
                opMANCHESTER.append("10");
            } else if (ch == '1') {
                opMANCHESTER.append("01");
            } else {
                // Handle invalid characters if needed
                throw new IllegalArgumentException("Input string contains non-binary characters.");
            }

        }
        return opMANCHESTER.toString();
    }

    private static String differentialManchester(String data) {
        StringBuilder opdiff = new StringBuilder();
        int count = 0;
        for (char ch : data.toCharArray()) {
            if (ch == '1') {
                count++;
            }
            //-----------------------------------
            if (count % 2 == 1) {
                if (ch == '1') {
                    opdiff.append("10");
                } else if (ch == '0') {
                    opdiff.append("10");
                }
            } else if (count % 2 == 0) {
                if (ch == '1') {
                    opdiff.append("01");
                } else if (ch == '0') {
                    opdiff.append("01");
                }
            }

        }
        return opdiff.toString();
    }

    private static String ami(String data) {    //here there are three states -1 , 0 , 1
        StringBuilder opAMI = new StringBuilder();
        int count = 0;
        for (char ch : data.toCharArray()) {
            if (ch == '1') {
                count++;

                if (count % 2 == 1) {
                    opAMI.append("1");
                } else if (count % 2 == 0) {
                    opAMI.append("-1");
                }
            } else {
                opAMI.append('0');
            }

        }

        return opAMI.toString();
    }
}
