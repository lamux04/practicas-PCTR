public class prodEscalar {
    public static void main(String[] args) {
        double[] v1 = new double[1000000];
        double[] v2 = new double[1000000];
        double prod = 0;
        for (int i = 0; i < 1000000; ++i)
        {
            v1[i] = i;
            v2[i] = 2 * i;
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < 1000000; ++i)
            prod += v1[i] * v2[i];

        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);
    }
}
