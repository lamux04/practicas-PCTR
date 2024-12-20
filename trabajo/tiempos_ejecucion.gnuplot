set title "Tiempo de Ejecución vs Número de hilos"
set xlabel "Número de Hilos"
set ylabel "Tiempo (s)"
set grid
set key left top
set style data linespoints
set terminal png
set output "grafica_tiempos_ejecucion.png"
plot "datos_tiempo.txt" using 1:2 title "Tiempo de ejecución" lc rgb "blue"