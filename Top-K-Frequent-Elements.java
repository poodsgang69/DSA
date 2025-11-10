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
}
