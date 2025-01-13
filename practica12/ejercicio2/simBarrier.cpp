#include <mutex>
#include <iostream>
#include <thread>
#include <condition_variable>

class simBarrier
{
public:
    simBarrier(int num) : num(num), n(0) {}
    void toWaitOnBarrier()
    {
        std::unique_lock<std::mutex> em(cerrojo);
        n++;
        if (n == num)
        {
            cv.notify_all();
        }
        else
        {
            cv.wait(em);
        }

    }
    void resetBarrier()
    {
        std::unique_lock<std::mutex> em(cerrojo);
        n = 0;
        cv.notify_all();
    }
private:
    int num;
    int n;
    std::mutex cerrojo;
    std::condition_variable cv;
};

void funcionBarrera(simBarrier& bar, std::mutex& cerrojo)
{
    using namespace std;
    cerrojo.lock();
    cout << this_thread::get_id() << " llegando a barrera...\n";
    cerrojo.unlock();
    bar.toWaitOnBarrier();
    cerrojo.lock();
    cout << this_thread::get_id() << " saliendo de barrera...\n";
    cerrojo.unlock();
}


int main()
{
    simBarrier bar(3);
    std::mutex cerrojo;
    std::thread hilo1(funcionBarrera, std::ref(bar), std::ref(cerrojo)),
        hilo2(funcionBarrera, std::ref(bar), std::ref(cerrojo)),
        hilo3(funcionBarrera, std::ref(bar), std::ref(cerrojo));
    hilo1.join();
    hilo2.join();
    hilo3.join();

    std::cout << "main reseteando barrera para tres nuevas hebras...";
    bar.resetBarrier();

    std::thread hilo4(funcionBarrera, std::ref(bar), std::ref(cerrojo)),
        hilo5(funcionBarrera, std::ref(bar), std::ref(cerrojo)),
        hilo6(funcionBarrera, std::ref(bar), std::ref(cerrojo));
    hilo4.join();
    hilo5.join();
    hilo6.join();
    return 0;
}