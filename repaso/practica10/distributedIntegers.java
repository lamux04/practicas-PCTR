package practica10;

import mpi.MPI;

public class distributedIntegers {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();

        int[] tam = { 1000000 };

        int[] contPar = { 0 };

        MPI.COMM_WORLD.Bcast(tam, 0, 1, MPI.INT, 0);

        for (int i = rank * tam[0]; i < (rank + 1) * tam[0]; ++i)
        {
            boolean primo = true;

            int j = 2;
            while (primo && j < Math.sqrt(i)) {
                if (i % j == 0)
                    primo = false;
                j++;
            }

            if (primo)
                contPar[0]++;
        }
        
        int[] n = { 0 };

        MPI.COMM_WORLD.Reduce(contPar, 0, n, 0, 1, MPI.INT, MPI.SUM, 0);

        if (rank == 0)
            System.out.println(n[0]);

        MPI.Finalize();
    }
}
