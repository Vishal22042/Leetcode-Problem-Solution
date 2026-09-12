import java.util.*;

public class Solution {

    private static class Interval {
        int start;
        int end;
        int weight;
        int id;

        Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }
    }

    private static class Result {
        long weight;
        List<Integer> ids;

        Result(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    private Result[][] memo;
    private Interval[] arr;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a.start, b.start));

        memo = new Result[n][5];

        Result bestResult = dp(0, 4);

        int[] result = new int[bestResult.ids.size()];

        for (int i = 0; i < bestResult.ids.size(); i++) {
            result[i] = bestResult.ids.get(i);
        }

        return result;
    }

    private Result dp(int i, int quota) {

        if (i == arr.length || quota == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (memo[i][quota] != null) {
            return memo[i][quota];
        }

        Result skipResult = dp(i + 1, quota);

        int nextIdx = findFirstGreater(arr[i].end);

        Result takeNext = dp(nextIdx, quota - 1);

        long takeWeight = arr[i].weight + takeNext.weight;

        List<Integer> takeIds =
            new ArrayList<>(takeNext.ids.size() + 1);

        takeIds.add(arr[i].id);
        takeIds.addAll(takeNext.ids);

        Collections.sort(takeIds);

        Result takeResult = new Result(takeWeight, takeIds);

        memo[i][quota] = getBest(takeResult, skipResult);

        return memo[i][quota];
    }

    private int findFirstGreater(int targetEnd) {

        int low = 0;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].start > targetEnd) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private Result getBest(Result r1, Result r2) {

        if (r1.weight != r2.weight) {
            return r1.weight > r2.weight ? r1 : r2;
        }

        int len1 = r1.ids.size();
        int len2 = r2.ids.size();

        int minLen = Math.min(len1, len2);

        for (int i = 0; i < minLen; i++) {

            int id1 = r1.ids.get(i);
            int id2 = r2.ids.get(i);

            if (id1 != id2) {
                return id1 < id2 ? r1 : r2;
            }
        }

        return len1 <= len2 ? r1 : r2;
    }
}