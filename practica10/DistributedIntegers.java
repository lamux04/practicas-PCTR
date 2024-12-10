// javac -cp $MPJ_HOME/lib/mpj.jar practica10/DistributedIntegers.java
// mpjrun.sh -np 10 -cp $MPJ_HOME/lib/mpj.jar:. practica10.DistributedIntegers
package practica10;

import mpi.*;

public class DistributedIntegers {
    public static void main(String[] args) {
        MPI.Init(args);

        int n = 10000000;
        int rank = MPI.COMM_WORLD.Rank();

        if (rank == 0)
        {
            int[] resultado = { 0 };
            int[] rango = { n / 9 };
            MPI.COMM_WORLD.Bcast(rango, 0, 1, MPI.INT, 0);
            MPI.COMM_WORLD.Reduce(resultado, 0, resultado, 0, 1, MPI.INT, MPI.SUM, 0);
            System.out.println(resultado[0]);
        } else 
        {
            int[] rango = { 0 };
            int[] envio = { 0 };
            MPI.COMM_WORLD.Bcast(rango, 0, 1, MPI.INT, 0);
            for (int i = (rank - 1) * rango[0]; i < rank * rango[0]; ++i)
            {
                // Comprobamos si es primo
                int divisor = 2;
                boolean divisible = false;
                while (divisor < Math.sqrt(i) && !divisible)
                {
                    if (i % divisor == 0)
                        divisible = true;
                    divisor++;
                }
                if (!divisible)
                    envio[0]++;
            }
            MPI.COMM_WORLD.Reduce(envio, 0, new int[1], 0, 1, MPI.INT, MPI.SUM, 0);
        }

        MPI.Finalize();
    }
}
