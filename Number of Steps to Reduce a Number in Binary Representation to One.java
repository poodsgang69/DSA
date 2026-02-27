import java.math.BigInteger;

class Solution {
    public int numSteps(String binary) {
        BigInteger n = new BigInteger(binary, 2);

        if (n.signum() == 0) {
            throw new IllegalArgumentException("Input must be >= 1.");
        }

        int steps = 0;
        while (n.compareTo(BigInteger.ONE) > 0) {
            if (!n.testBit(0)) {
                n = n.shiftRight(1);
            } else {
                n = n.add(BigInteger.ONE);
            }
            steps++;
        }

        return steps;
    }
}
