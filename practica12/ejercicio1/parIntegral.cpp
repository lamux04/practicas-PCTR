#include <vector>
#include <thread>
#include <random>
#include <iostream>
#include "parIntegral.hpp"

double monteCarlo::f(double x)
{
    return ((double)x * x * x) / (x * x * x * x + 2);
}

void monteCarlo::monteCarloIntegration(int numPoints)
{
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<> dis(0, 1);
    for (int i = 0; i < numPoints; ++i)
    {
        double x = dis(gen); // Número aleatorio
        double fx = f(x);
        cerrojo.lock();
        totalHits += fx;
        cerrojo.unlock();
    }
}

double monteCarlo::aproximacion()
{
    return (double)totalHits / puntosTotales;
}

int main()
{
    using namespace std;
    int puntos, hilos;
    cout << "Ingrese el numero de puntos a lanzar: ";
    cin >> puntos;
    cout << "\nIngrese el número de tareas paralelas: ";
    cin >> hilos;

    monteCarlo objeto(puntos);
    vector<std::thread> vhilos(hilos);

    for (int i = 0; i < hilos; ++i)
    {
        vhilos[i] = std::thread(&monteCarlo::monteCarloIntegration, &objeto, puntos / hilos);
    }

    for (int i = 0; i < hilos; ++i)
    {
        vhilos[i].join();
    }

    cout << "Aproximación: " << objeto.aproximacion() << endl;
}