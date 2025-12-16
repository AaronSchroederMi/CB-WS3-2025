#ifndef RINGBUFFER_H
#define RINGBUFFER_H

#include "SmartToken.h"

class RingBuffer {
public:
    RingBuffer(unsigned int size);
    ~RingBuffer();

    SmartToken readBuffer();
    void writeBuffer(const SmartToken& data);

private:
    unsigned int count;     ///< number of elements currently stored
    unsigned int head;      ///< index of the oldest element
    unsigned int size;      ///< total capacity of buffer
    SmartToken* elems;      ///< dynamically allocated array of SmartToken
};

#endif // RINGBUFFER_H
