// javac -cp $MPJ_HOME/lib/mpj.jar ProdInterno.java
// mpjrun.sh -np 2 ProdInterno

package practica10;

import mpi.*;

public class ProdInterno {
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();

        if(rank == 0)
        {
            int[] resultado = { 0 };
            MPI.COMM_WORLD.Recv(resultado, 0, 1, MPI.INT, 1, 0);
            System.out.println(resultado[0]);
        }else if (rank == 1)
        {
            int[] v1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int[] v2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
            
            int[] r1 = {0};
            for (int i = 0; i < 10; ++i)
                r1[0] += v1[i] * v2[i];
            
            MPI.COMM_WORLD.Send(r1, 0, 1, MPI.INT, 0, 0);
        }

        MPI.Finalize();
    }
    
}
