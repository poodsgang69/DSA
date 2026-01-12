class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int numOfSteps = 0;
        for( int i = 0 ; i < points.length - 1 ; ++i ) {
            numOfSteps += Math.max(
                Math.abs( points[i][0] - points[i+1][0] ),
                Math.abs( points[i][1] - points[i+1][1] )
            );
        }

        return numOfSteps;
    }
}

/*
Relies on the concept of "CHEBYSHEB" distance. Read that concept (of the minumum ways a king can move on a chessboard to a certain point)
to understand this algoriothm.

Time Complexity: O(N)
Space Complexity: O(1)
*/