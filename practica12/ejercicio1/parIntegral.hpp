#ifndef PARINTEGRAL_HPP
#define PARiNTEGRAL_HPP

#include <random>
#include <mutex>
#include <iostream>

class monteCarlo
{
public:
    monteCarlo(int puntos) :
        totalHits(0),
        puntosTotales(puntos)
    {
    }
    double f(double x);
    void monteCarloIntegration(int numPoints);
    double aproximacion();
private:
    std::mutex cerrojo;
    double totalHits;
    int puntosTotales;
};

#endif