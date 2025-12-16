#include "RingBuffer.h"
#include <iostream>

RingBuffer::RingBuffer(unsigned int s)
    : count(0), head(0), size(s)
{
    elems = new SmartToken[size];
    std::cout << "[RingBuffer] erstellt mit Größe " << size << std::endl;
}

RingBuffer::~RingBuffer()
{
    delete[] elems;
    elems = nullptr;
    std::cout << "[RingBuffer] zerstört" << std::endl;
}

// Lese das erste (älteste) Element, ohne es zu entfernen
SmartToken RingBuffer::readBuffer()
{
    if (count == 0) {
        return SmartToken(); // leerer SmartToken
    }

    SmartToken result = elems[head];
    head = (head + 1) % size; // nächste Position für read
    count--;
    return result;
}

// Füge ein neues Element hinzu, überschreibe ältestes bei voller Kapazität
void RingBuffer::writeBuffer(const SmartToken& data)
{
    if (count == size) {
        // Puffer voll: das älteste Element wird überschrieben
        elems[head] = data;   // SmartToken-Assignment reduziert RefCount korrekt
        head = (head + 1) % size;
    } else {
        unsigned int tail = (head + count) % size;
        elems[tail] = data;
        count++;
    }
}
