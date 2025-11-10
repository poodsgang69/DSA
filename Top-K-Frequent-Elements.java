//Using Java Streams
// 3 Steps: 
//   1) Build the frequency map
//   2) Sort the Frequency map based on the Value
//   3) Only get the first k elements and return

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // build the frequency map
        Map<Integer, Long> freqMap = Arrays.stream(nums)
                                                .boxed()
                                                .collect(
                                                    Collectors.groupingBy(
                                                        n -> n,
                                                        Collectors.counting()
                                                    )
                                                );

        Map<Integer, Long> sortedMap = freqMap.entrySet().stream()
                                            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                                            .collect(Collectors.toMap(
                                                Map.Entry::getKey,
                                                Map.Entry::getValue,
                                                (e1, e2) -> e1,
                                                LinkedHashMap::new
                                            ));
        
        List<Integer> topKKeys = sortedMap.entrySet()
        .stream()
        .limit(k)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());

        return topKKeys.stream()
        .mapToInt(Integer::intValue)
        .toArray();
    }

    public int[] topKFrequent(int[] nums, int k) {
        //Build the Frequency Map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for( int num : nums ) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        //Initialize the Buckets Array
        List<Integer>[] buckets = new List[nums.length + 1];
        for( int i = 0 ; i < buckets.length ; ++i ) buckets[i] = new ArrayList<>();

        //Populate the Buckets Array
        for( int key : frequencyMap.keySet() ) {
            buckets[frequencyMap.get(key)].add(key);
        }

        //Gather the results - Go in reverse order to get the most frequent elements
        // List<Integer> result = new ArrayList<>();
        // for( int i = buckets.length - 1 ; i >= 0 ; --i ) {
        //     for ( int num : buckets[i] ) result.add(num);
        //     if ( result.size() >= k ) break;
        // }
        // return result.subList(0,k).stream().mapToInt(Integer::intValue).toArray();

        int[] result = new int[k];
        int index = 0;

        for (int i = buckets.length - 1; i >= 0 && index < k; --i) {
            for (int num : buckets[i]) {
                result[index++] = num;
                if (index == k) break; // stop once we’ve collected k elements
            }
        }

        return result;
    }
}

// Second solution basically uses the bucketsort algorithm
/*
 * ------------------------- ALGORITHM EXPLANATION -------------------------
 * Problem: Return the k most frequent elements from an integer array.
 *
 * Approach: Bucket Sort (frequency-based grouping)
 *
 * 1. Build the Frequency Map
 *    - We first count how many times each number appears in the array.
 *    - This gives us a mapping from each element → its frequency.
 *      Example: nums = [1,1,2,2,3] → {1=2, 2=2, 3=1}
 *
 * 2. Initialize the Buckets Array
 *    - We create an array of lists called "buckets" where each index
 *      represents a possible frequency count.
 *    - The array size is nums.length + 1 because an element can appear
 *      at most nums.length times.
 *    - Conceptually, buckets[i] will hold all numbers that appear 'i' times.
 *    - This is similar to a C++ tactic where we use the array indices
 *      themselves as keys — here, the index represents the frequency.
 *
 * 3. Populate the Buckets
 *    - For every (key, frequency) pair in the frequency map,
 *      we add the key (the number) to the corresponding bucket at index = frequency.
 *      Example: frequencyMap = {1=2, 2=2, 3=1}
 *      → bucket[1] = [3], bucket[2] = [1, 2]
 *
 * 4. Gather Results (Reverse Traversal)
 *    - Starting from the end of the buckets array (highest frequency),
 *      we collect numbers until we have gathered 'k' elements.
 *    - This ensures that we pick the most frequent elements first.
 *    - Because the bucket indices represent frequencies, iterating from
 *      high to low is effectively a descending sort on frequency — but
 *      without using any explicit sorting algorithm.
 *
 * 5. Time & Space Complexity
 *    - Time: O(n) since we make one pass to count, one pass to bucket, and one pass to collect.
 *    - Space: O(n) due to the frequency map and buckets array.
 *
 * Key Idea:
 *    Instead of sorting by frequency (O(n log n)), we leverage the fact
 *    that frequency values are bounded by n, so we can directly index into
 *    an array by frequency — just like using array indices as keys in C++.
*/
