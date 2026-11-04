package com.example.alotonmyplate

fun dosomething(): Short{

    val dosomerthing = 0;
    val dosomethingelse = 22;

   val value =  55 + dosomerthing + dosomethingelse;

    val newvalue = value.toShort()
    //now new value is stored in cache

    //not waiting for another operatino becuase this
    //does no operation
    // he wasted a single clock cycle or maybe 2 or 3 bad
    //access quickly after using to not use that long time to go to memmoryt
    return newvalue

    //now im using less memory
    // but another opration happening no worries
    //not sure what this does
    //yes stored back in memoery becuase its stack and stored in memoery
}

fun dosomethingelse(){
    val storeit = dosomething()
    //completely irrelivent that its named

    storeit + 5;


    // gets fro cache low mls
    //getting store it from cache because it was fast eneough and it copied that value.short in cache
    // actually it didnt
/// i saved 5~ 60 annoseconds
    // from 150 dang thats nice and smooth and fast
    // what else
    // i diddnt block another alu from waiting for another
}



