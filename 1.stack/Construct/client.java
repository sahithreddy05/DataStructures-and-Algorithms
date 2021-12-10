public class client {

    public static int dividesolve(int a, int b) throws Exception {
        if (b == 0) {
            throw new Exception("cannotdividebyzero");
        }
        return a / b;
    }

    public static int divide(int a, int b) throws Exception {
        int ans = -1;

        try {
            return dividesolve(a, b);
        } catch (Exception e) {
            System.out.println("INFI");
        }
        return ans;

    }

    public static void main(String args[]) throws Exception {
        dynamicStack st = new dynamicStack();
        for (int i = 0; i < 30; i++) {
            st.push(i + 10);
        }

        while (st.size() != 0) {
            System.out.println(st.pop());
        }
    }
}
