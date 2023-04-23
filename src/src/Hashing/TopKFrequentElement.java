package Hashing;

import java.util.*;

public class TopKFrequentElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        int[] ans = topKFrequent(arr, k);
        System.out.println(Arrays.toString(ans));

        System.out.println(Arrays.toString(topKFrequentBetter(arr, k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // created the map
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // converted the map into list to sort on values using custom comparator
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (a, b) -> {
            return b.getValue() - a.getValue();
        });

        int[] ans = new int[k];
        int index = 0;
        for (Map.Entry<Integer, Integer> set : list) {
            ans[index++] = set.getKey();
            if (index == ans.length)
                break;
        }
        return ans;
    }

    static class Helper {
        int num;
        int freq;

        Helper(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }

    public static int[] topKFrequentBetter(int[] arr, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : arr) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Helper> pq = new PriorityQueue<>((a, b) -> {
            return a.freq - b.freq;
        });

        for (int i : hm.keySet()) {
            pq.add(new Helper(i, hm.get(i)));
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[pq.size()];
        int index = 0;
        while (pq.size() > 0) {
            ans[index] = pq.remove().num;
            index++;
        }
        return ans;
    }
}

// medium artical for heap and pq
// https://medium.com/swlh/heap-and-priority-queue-fbd41333dc0d

// What is Heap ?
// A heap is a tree with some special properties. The basic requirement of a
// heap is that the value of a node must be ≥ (or ≤) than the values of its
// children. This is called heap property. A heap also has the additional
// property that all leaves should be at h or h — 1 levels (where h is the
// height of the tree) for some h > 0 (complete binary trees). That means heap
// should form a complete binary tree .

// Types of Heaps?
// Based on the property of a heap we can classify heaps into two types

// Min heap: The value of a node must be less than or equal to the values of its
// children

// Max heap: The value of a node must be greater than or equal to the values of
// its children

// Heapifying an Element
// After inserting an element into heap, it may not satisfy the heap property.
// In that case we need to adjust the locations of the heap to make it heap
// again. This process is called heapifying. In maxheap, to heapify an element,
// we have to find the maximum of its children and swap it with the current
// element and continue this process until the heap property is satisfied at
// every node.