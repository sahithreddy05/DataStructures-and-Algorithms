import java.util.ArrayList;

public class heap {
    private ArrayList<Integer> arr;
    private boolean isMaxHeap = true;
    
    heap(boolean isMaxHeap) {
        this.arr = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
    }

    // O(n + nLog(n)) -> O(n)
    heap(int[] arr, boolean isMaxHeap) {
        this(isMaxHeap);

        for (int ele : arr)
            this.arr.add(ele);

        for (int i = this.arr.size() - 1; i >= 0; i--)
            downHeapify(i);
    }

    // O(1)
    private boolean compareTo(int i, int j) {
        if (isMaxHeap)
            return this.arr.get(i) > this.arr.get(j);
        else
            return this.arr.get(i) < this.arr.get(j);
    }

    // O(1)
    private void swap(int i, int j) { // O(1)
        int temp = this.arr.get(i);
        this.arr.set(i, this.arr.get(j));
        this.arr.set(j, temp);
    }

    int size() {
        return this.arr.size();
    }

    boolean isEmpty() {
        return this.arr.size() == 0;
    }

    // O(logn)
    void add(int data) { 
        this.arr.add(data);
        int n = this.arr.size();

        upHeapify(n - 1);
    }

    // O(Logn)
    int remove() { 
        int rEle = arr.get(0);

        int n = this.arr.size();
        swap(0, n - 1);
        this.arr.remove(n - 1);

        downHeapify(0);
        return rEle;
    }

    int top() { // O(1)
        return this.arr.get(0);
    }

    // Log(n)
    private void downHeapify(int pi) { // O(logn)
        int maxIdx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci < arr.size() && compareTo(lci, maxIdx))
            maxIdx = lci;

        if (rci < arr.size() && compareTo(rci, maxIdx))
            maxIdx = rci;

        if (maxIdx != pi) {
            swap(pi, maxIdx);
            downHeapify(maxIdx);
        }
    }

    // Log(n)
    private void upHeapify(int ci) { // O(logn)
        int pi = (ci - 1) / 2;

        if (pi >= 0 && compareTo(ci, pi)) {
            swap(pi, ci);
            upHeapify(pi);
        }
    }
}