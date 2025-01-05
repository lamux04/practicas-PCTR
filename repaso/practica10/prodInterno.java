package practica10;

import mpi.MPI;

public class prodInterno {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        
        if (rank == 0) {
            int[] v1 = { 1, 2, 3, 4 };
            int[] v2 = { 1, 2, 3, 4 };

            MPI.COMM_WORLD.Send(v1, 0, 4, MPI.INT, 1, 0);
            MPI.COMM_WORLD.Send(v2, 0, 4, MPI.INT, 1, 0);

            int[] resultado = new int[1];
            MPI.COMM_WORLD.Recv(resultado, 0, 1, MPI.INT, 1, 0);

            System.out.println(resultado[0]);

        } else {
            int[] v1 = new int[4];
            int[] v2 = new int[4];
            
            MPI.COMM_WORLD.Recv(v1, 0, 4, MPI.INT, 0, 0);
            MPI.COMM_WORLD.Recv(v2, 0, 4, MPI.INT, 0, 0);

            int[] resultado = new int[1];
            resultado[0] = 0;

            for (int i = 0; i < 4; ++i)
                resultado[0] += v1[i] * v2[i];

            MPI.COMM_WORLD.Send(resultado, 0, 1, MPI.INT, 0, 0);

        }

        MPI.Finalize();
    }
}
