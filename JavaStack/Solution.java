import java.util.*;

/**
 * This class checks if strings have balanced parentheses.
 */
class Solution {

    public static void main(String[] argh) {
        Scanner sc = new Scanner(System.in);
        Stack<String> stack = new Stack<>();

        while (sc.hasNext()) {
            String input = sc.next();

            // check for opening parentheses
            String[] parentheses = input.split("");
            boolean balanced = true;
            for (String parenthesis : parentheses) {
                if (parenthesis.matches("\\{|\\(|\\[")) {
                    stack.push(parenthesis);
                } else if (stack.isEmpty()) {
                    balanced = false;
                    break;
                } else {
                    balanced = isOpposite(stack.pop(), parenthesis);
                }
            }
            if(!stack.isEmpty()) {
                balanced = false;
                stack.clear();
            }
            System.out.println(balanced);
        }
    }

    /**
     * This method checks if two opposite parentheses are given as parameters.
     *
      * @param openingParenthesis   The opening parenthesis
     * @param closingParenthesis    The closing parenthesis
     * @return                      True, if the opening and closing parentheses match, otherwise false.
     */
    public static Boolean isOpposite(String openingParenthesis, String closingParenthesis) {
        switch (openingParenthesis) {
            case "{":
                return "}".equals(closingParenthesis);
            case "(":
                return ")".equals(closingParenthesis);
            case "[":
                return "]".equals(closingParenthesis);
            default:
                return false;
        }
    }
}
