class Solution {
public:
    int findMinArrowShotsBeginSort(vector<vector<int>>& points) {
        ios_base::sync_with_stdio(0);
        cin.tie(NULL);
        int i = 0 , j = i + 1 , ans = 0;
        size_t s = points.size();
        std::sort(points.begin(), points.end());
        for (auto& p : points) cout << p[0] << " " << p[1] << endl;
        vector<int> currInterval = points[0];
        while(j < s) {
            if ( points[j][0] <= currInterval[1] ) {
                if (points[j][1] < currInterval[1]) currInterval[1] = points[j][1];
                currInterval[0] = points[j][0];
            } else {
                ans++;
                currInterval = points[j];
            }
            j++;
        }

        return ans+1;
    }

    int findMinArrowShots(vector<vector<int>>& points) {
        int ans = 1;
        std::sort(points.begin(), points.end(), 
              [](const std::vector<int>& a, const std::vector<int>& b) {
                  return a[1] < b[1]; 
              });
        int arrowIdx = points[0][1];
        for ( int i = 1 ; i < points.size() ; ++i ) {
            if ( points[i][0] <= arrowIdx ) continue;
            ans++;
            arrowIdx = points[i][1];
        }
        return ans;
    }
};
