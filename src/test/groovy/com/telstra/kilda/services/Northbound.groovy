package com.telstra.kilda.services

import com.telstra.kilda.model.Response

class Northbound {
    Response removeMeter(switchid, meterid) {
        println "Removing meter $meterid from switch $switchid"
        new Response(ok: true)
    }

    Response validateFlow(flowid){
        println "Validating flow $flowid"
        new Response(ok: true)
    }

    Response removeFlow(flowid){
        println "Removing flow $flowid"
        new Response(ok: true)
    }
}