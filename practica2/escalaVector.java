public class escalaVector {
    public static void main(String[] args) {
        int[] vector = new int[1000000];
        for (int i = 0; i < 1000000; i++)
            vector[i] = i;
        for (int i = 0; i < 1000000; i++)
            vector[i] *= 2;
    }
}
