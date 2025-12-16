cmake_minimum_required(VERSION 4.1)
project(Blatt_7_Cpp)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(Blatt_7_Cpp
        Token.cpp
        Token.h
        main.cpp
        RefCounter.h
        RefCounter.cpp
        SmartToken.cpp
        SmartToken.h
        RingBuffer.cpp
        RingBuffer.h
)
