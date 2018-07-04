package com.telstra.kilda.services

import com.telstra.kilda.model.Meter

class Floodlight {
    static calledTimes = 0 //want to return one less meter when called second time, just for test purposes

    List<Meter> getMeters(switchId) {
        calledTimes++;
        if(calledTimes == 1) {
            return [new Meter(meterId: 1), new Meter(meterId: 2)]
        }
        if(calledTimes == 2) {
            calledTimes = 0
            return [new Meter(meterId: 2)]
        }
    }
}