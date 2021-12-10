import java.util.ArrayList;

public class practice {

    public static class heap {
        private ArrayList<Integer> arr;
        private boolean isMax;

        heap(boolean isMax) {
            this.arr = new ArrayList<>();
            this.isMax = isMax;
        }

        heap(int[] arr, boolean isMax) {
            this(isMax);
            for (int ele : arr) {
                this.arr.add(ele);
            }

            for (int i = this.arr.size() - 1; i >= 0; i--)
                downHeapify(i);
        }

        private void swap(int i, int j) {
            int temp = this.arr.get(i);
            this.arr.set(i, this.arr.get(j));
            this.arr.set(j, temp);
        }

        private boolean compareTo(int x, int y) {
            if (isMax)
                return this.arr.get(x) < this.arr.get(y);

            else
                return this.arr.get(x) > this.arr.get(y);
        }

        // log(n)
        private void upHeapify(int ci) {
            int pi = (ci - 1) / 2;

            if (pi >= 0 && compareTo(ci, pi)) {
                swap(pi, ci);
                upHeapify(pi);
            }
        }

        // Log(n)
        private void downHeapify(int pi) {
            int lci = 2 * pi + 1;
            int rci = 2 * pi + 2;
            int maxIdx = pi;

            if (lci < arr.size() && compareTo(lci, maxIdx))
                maxIdx = lci;

            if (rci < arr.size() && compareTo(rci, maxIdx))
                maxIdx = rci;

            if (pi != maxIdx) {
                swap(pi, maxIdx);
                downHeapify(maxIdx);
            }
        }

        public int size() {
            return this.arr.size();
        }

        public boolean isEmpty() {
            return this.arr.size() == 0;
        }

        public int remove() {
            int rele = this.arr.get(0);

            int n = this.arr.size();
            swap(0, n - 1);
            this.arr.remove(n - 1);

            downHeapify(0);
            return rele;
        }

        // O(logn)
        public void add(int data) {
            this.arr.add(data);
            int n = this.arr.size();

            upHeapify(n - 1);
        }
    }

    public static void heaper() {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        heap pq = new heap(arr, true);

        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    public static void main(String[] args) {
        heaper();
    }
}