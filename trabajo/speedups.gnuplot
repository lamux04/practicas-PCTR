set title "Speedup vs Número de hilos"
set xlabel "Speedup"
set ylabel "Tiempo (s)"
set grid
set key left top
set style data linespoints
set terminal png
set output "grafica_speedups.png"
plot "datos_tiempo.txt" using 1:3 title "Speedups" lc rgb "blue"