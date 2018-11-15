//  tombstone.h
//  Project3
//
//  Created by Aure, Kyle J on 11/11/18.
//  Copyright © 2018 Aure, Kyle J. All rights reserved.

#include <iostream>

//Print pointer access error message and gracefully exit
void pointer_error(int number) {
    std::cerr << "Unaccessable pointer reference at tombstones.h line: " << number << std::endl;
    exit(1);
}

//Print memory leak error message and gracefully exit
void leak_memory_error(int number) {
    std::cerr << "Memory leak at tombstones.h line: " << number << std::endl;
    exit(1);
}

//Defintion of Tomb for larger pointer class
template <class T>
struct Tomb {
    T* data;    //pointer to where data is located.
    int count;  //count of pointers to this tomb.

    //Constructor with initialized basic values
    Tomb<T>() : data(nullptr), count(0){}

    //Deconstructor to put tomb back to default state
    ~Tomb<T>() {
        data = nullptr;
        count = 0;
    }
};

template <class T> class Ptr {
private:
    Tomb<T>* tomb;  //Pointer to tombstone where reference count and data pointer is held
public:
    //Default constructor
    Ptr<T>() : tomb(nullptr){
        //Initialize using default tombstone constructor
        //Data -> nullptr  count = 0     
        tomb = new Tomb<T>;
    }                           
    
    //Copy constructor
    Ptr<T>(Ptr<T>& pt) : tomb(pt.tomb) {
        //This Ptr tomb = passedPtr tomb
        //Increment count unless data is nullptr.
        //In this case assume we copied a Ptr that was initialized using the default constructor.
        if (!(tomb->data)) {
            tomb->count = 0;
        } else {
            tomb->count++;
        }
    }

    //Bootstrapping constructor
    Ptr<T>(T* data) : tomb(nullptr){
        //Create new tombstone using default constructor.
        tomb = new Tomb<T>;
        //Set data to call param
        tomb->data = data;

        //Set count to one unless data is nullptr.
        //In this case assume user is trying to create a Ptn in a similar fashion to the default constructor.
        if (!(tomb->data)) {
            tomb->count = 0;
        } else {
            tomb->count = 1;
        }
    }

    //Destructor
    ~Ptr<T>() {
        //When deconstructor is called decrement the reference count
        //If no more references to the data exist AND data has not been deallocated 
        //Throw Memory Leak
        if (--(tomb->count) == 0 && tomb->data) {
            leak_memory_error(__LINE__);
        }
    }
    
    //Derefencing
    T& operator* () const{
        //If data is nullptr throw pointer error else return *data
        if (!(tomb->data)) {
            pointer_error(__LINE__);
        }
        return *(tomb->data);
    }
    
    //Field dereferencing
    T* operator-> () const {
        return tomb->data;
    }

    //Delete pointed-at object
    friend void release (Ptr<T>& pt) {
        //If count != 1 this means either
        //1. Tombstone has already been released
        //2. Another reference has not been released and would result in dangling reference.
        if (pt.tomb->count != 1) {
            pointer_error(__LINE__);
        } else {
            delete pt.tomb->data;
            pt.tomb->count = 0;
            (pt.tomb)->data = nullptr;
        }
    }

    //Assignment
    Ptr<T>& operator=(const Ptr<T>& pt) {
        //First: Decrement count of old Ptr
        // If nothing references this tombstone AND data has not been deallocated this will leak memory! 
        if (--(tomb->count) == 0 && tomb->data) {
            leak_memory_error(__LINE__);
        }

        //Second: Overwrite tombstone to new Ptr
        tomb = pt.tomb;

        //Thrid: If tomb is non-zero and data is not nullptr then increment count.
        // else assume default constructor state.
        if (tomb && tomb->data) {
            tomb->count++;
        }

        return *this;
    }

    //Comparison operators    
    bool operator==(const Ptr<T>& x) const {
        //True iff the data pointer address' is equal between this tombstone and x's tombstone.
        return tomb->data == (x.tomb)->data;
    }
    bool operator!=(const Ptr<T>& x) const {
        //False iff this data pointer address' is equal between this tombstone and x's tombstone.
        return tomb->data != (x.tomb)->data;
    }
    bool operator==(const int x) const {
        //True iff Ptr is null and int is zero
        return (!(tomb->data) && x == 0) ? true : false;
    }
    bool operator!=(const int x) const {
        //False iff Ptr is null and int is zero
        return (!(tomb->data) && x == 0) ? false : true;
    }
};
