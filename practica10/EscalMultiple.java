// javac -cp $MPJ_HOME/lib/mpj.jar practica10/EscalMultiple.java
// mpjrun.sh -np 5 -cp $MPJ_HOME/lib/mpj.jar:. practica10.EscalMultiple

package practica10;

import mpi.*;
public class EscalMultiple {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        
        if (rank == 0)
        {
            int[] vector = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
            MPI.COMM_WORLD.Bcast(vector, 0, 10, MPI.INT, 0);
        } else
        {
            int[] vRecibido = new int[10];
            MPI.COMM_WORLD.Bcast(vRecibido, 0, 10, MPI.INT, 0);
            for (int i = 0; i < 10; ++i)
                vRecibido[i] *= rank;
            System.out.println("Proceso " + rank + " -> " + vRecibido[9]);
        }

        MPI.Finalize();
    }
}
