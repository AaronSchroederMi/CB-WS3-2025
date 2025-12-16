#include "RefCounter.h"

RefCounter::RefCounter() : count(1)
{
    std::cout << "[RefCounter] RefCounter erstellt, count = " << count << std::endl;
}

RefCounter::~RefCounter()
{
    std::cout << "[RefCounter] RefCounter zerstört" << std::endl;
}

void RefCounter::addRef()
{
    ++count;
    std::cout << "[RefCounter] addRef aufgerufen, count = " << count << std::endl;
}

void RefCounter::release()
{
    --count;
    std::cout << "[RefCounter] release aufgerufen, count = " << count << std::endl;
}

int RefCounter::getCount() const
{
    return count;
}
