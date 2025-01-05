package practica10;

import mpi.MPI;

public class escalMultiple {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();

        int[] resultado = new int[4];

        if (rank == 0) {
            int[] envio = { 1, 2, 3, 4 };
            MPI.COMM_WORLD.Bcast(envio, 0, 4, MPI.INT, 0);
        } else {
            MPI.COMM_WORLD.Bcast(resultado, 0, 4, MPI.INT, 0);
            for (int i = 0; i < 4; ++i) {
                resultado[i] *= rank;
                System.out.println(resultado[i]);
            }
        }

        MPI.Finalize();
    }
}
