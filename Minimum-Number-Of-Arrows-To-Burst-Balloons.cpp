class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
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
};
