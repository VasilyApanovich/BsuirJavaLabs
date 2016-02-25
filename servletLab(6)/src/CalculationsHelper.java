/**
 * Created by v.apanovich on 25.02.2016.
 */
public class CalculationsHelper {

    public static String Calculate(String xValue, String accuracy, String function, String measure) {
        String result = "";
        switch (function) {
            case "sin": {
                if (measure.equals("Degrees")) {
                    result = String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(xValue))));
                } else {
                    result = String.valueOf(Math.sin(Double.parseDouble(xValue)));
                }
                break;
            }
            case "cos": {
                if (measure.equals("Degrees")) {
                    result = String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(xValue))));
                } else {
                    result = String.valueOf(Math.cos(Double.parseDouble(xValue)));
                }
                break;
            }
            case "tan": {
                if (measure.equals("Degrees")) {
                    result = String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(xValue))));
                } else {
                    result = String.valueOf(Math.tan(Double.parseDouble(xValue)));
                }
                break;
            }
        }
        String rounderString = "1";
        int decimalNumber = Integer.parseInt(accuracy);
        for (int i = 0; i < decimalNumber; i++) {
            rounderString += "0";
        }
        double rounder = Double.parseDouble(rounderString);
        double resDouble = Double.parseDouble(result);
        resDouble = Math.round(resDouble* rounder) / rounder;
        return String.valueOf(resDouble);
    }
}
