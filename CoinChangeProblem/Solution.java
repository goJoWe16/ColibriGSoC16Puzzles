import java.io.*;
import java.util.*;

/**
 *
 * Computes the number of different ways to make change for an amount, given a list of coins.
 *
 * Given the Constraints
 * 1 ≤ Ci ≤ 50
 * 1 ≤ N ≤ 250
 * 1 ≤ M ≤ 50
 * the input is expected to be in the correct format and is not checked in this program.
 *
 */
public class Solution {

    public static void main(String[] args) {
        Integer n = -1;
        List<Integer> coins = new ArrayList<>();

        /**
         * parse input
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            /**
             * parse n, m is not needed
             */
            String[] inputString = in.readLine().split(" ");
            n = Integer.parseInt(inputString[0]);

            /**
             * parse second line
             */
            inputString = in.readLine().split(" ");
            for (String s : inputString) {
                coins.add(Integer.parseInt(s));
            }

        } catch (IOException e) {
            System.err.println("ERROR: Could not read from stdin!");
        }

        /**
         * Maps the number of counts to a special, already calculated coins / amount of money - pair.
         */
        Map<CoinsAmountPair, Long> alreadyCountedMap = new HashMap<>();

        System.out.println(numberOfSplits(n, coins, alreadyCountedMap));

    }

    /**
     * Computes the number of all possible splits recursively.
     *
     * @param n                 The remaining money which should be split.
     * @param coins             A list of coins which can be used to split the money.
     * @param alreadyCountedMap A map which contains all amount of money/list of coins - pairs that already have been computed.
     * @return                  The number of all possible splits.
     */
    private static long numberOfSplits(int n, List<Integer> coins, Map<CoinsAmountPair, Long> alreadyCountedMap) {
        if ((coins.size() == 0 && n == 0) || (n == 0)) {
            return 1;
        } else if (coins.size() == 0) {
            return 0;
        } else {
            CoinsAmountPair tempPair = new CoinsAmountPair(coins, n);
            if (alreadyCountedMap.containsKey(tempPair)) {
                return alreadyCountedMap.get(tempPair);
            } else {
                long count = 0;
                Integer actualCoinValue = coins.get(0);
                int times = n / actualCoinValue;
                for (int i = 0; i <= times; i++) {
                    count += numberOfSplits(n - (i * actualCoinValue), coins.subList(1, coins.size()), alreadyCountedMap);
                }
                alreadyCountedMap.put(tempPair, count);
                return count;
            }
        }
    }

    /**
     * A class for saving the remaining money paired with the remaining list of coins
     */
    private static class CoinsAmountPair {

        List<Integer> coins;
        Integer n;

        CoinsAmountPair(List<Integer> coins, Integer n) {
            this.n = n;
            this.coins = coins;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CoinsAmountPair that = (CoinsAmountPair) o;
            return coins != null ? coins.equals(that.coins) : that.coins == null && (n != null ? n.equals(that.n) : that.n == null);
        }

        @Override
        public int hashCode() {
            int result = coins != null ? coins.hashCode() : 0;
            result = 31 * result + (n != null ? n.hashCode() : 0);
            return result;
        }
    }
}
