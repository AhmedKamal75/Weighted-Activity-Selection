import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeightedActivitySelection {
    private Activity[] activities;
    private int lowestStart = Integer.MAX_VALUE;
    private int highestFinish = Integer.MIN_VALUE;

    public WeightedActivitySelection() {
    }

    public void loadActivities(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int n = Integer.parseInt(br.readLine());
        this.activities = new Activity[n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int finish = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            this.activities[i] = new Activity(start, finish, weight);
            this.lowestStart = Math.min(this.lowestStart, start);
            this.highestFinish = Math.max(this.highestFinish, finish);
        }
    }

    public void printActivities() {
        for (Activity activity : activities) {
            System.out.println(activity.toString());
        }
    }

    public int getMaxWeight() {
        HashMap<Pair<Integer, Integer>, Integer> dp = new HashMap<Pair<Integer,Integer>, Integer>();
        return topDown(lowestStart, highestFinish, dp);
    }

    private int topDown(int i, int j, HashMap<Pair<Integer,Integer>, Integer> memo) {
        if (i >= j) {
            return 0;
        }
        Pair<Integer, Integer> key = new Pair<>(i, j);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int maxWeight = 0;
        for (Activity activity : this.activities) {
            if (activity.start >= i && activity.finish <= j) {
                int weight = activity.weight;
                weight += topDown(activity.finish, j, memo);
                weight += topDown(i,activity.start,memo);
                maxWeight = Math.max(maxWeight, weight);
            }
        }
        memo.put(key, maxWeight);
        return memo.get(key);
    }

    static class Activity {
        private final int start;
        private final int finish;
        private final int weight;

        Activity(int start, int finish, int weight) {
            this.start = start;
            this.finish = finish;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + finish + ", " + weight + ']';
        }
    }

    static class Pair<T,D> {
        private final T start;
        private final D finish;

        public Pair(T start, D finish) {
            this.start = start;
            this.finish = finish;
        }

        public T getStart() {
            return start;
        }

        public D getFinish() {
            return finish;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(start, pair.start) && Objects.equals(finish, pair.finish);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, finish);
        }
    }

}
