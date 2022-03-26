public class amazoneOA {
    public int fillTruck(int n, int[] boxes, int unitSize, int[] unitsPerBox, int truckSize){
        int maxUnits = 0;
    
        if (boxes == null || boxes.length == 0 || unitsPerBox == null
                || unitsPerBox.length == 0 || truckSize <= 0 || n < 1) {
            return maxUnits;
        }
    
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=0; i<n; i++){
            int box = boxes[i];
            int unit = unitsPerBox[i];
            while(box > 0){
                maxHeap.add(unit);
                box--;
            }
        }
    
        while(truckSize >  0){
            if (!maxHeap.isEmpty()){
                maxUnits += maxHeap.poll();
            }
            truckSize--;
        }
    
        return maxUnits;
    }
    
    public static void main(String[] args) {
    
        AmazonFillTheTruck s = new AmazonFillTheTruck();
    
        int n1 = 3;
        int unitSize1 = 3;
        int[] boxes1 = {1,2,3};
        int[] unitsPerBox1 = {3,2,1};
        int truckSize1 = 3;
        System.out.println(s.fillTruck(n1, boxes1,unitSize1, unitsPerBox1, truckSize1 ));
    }
    
}
