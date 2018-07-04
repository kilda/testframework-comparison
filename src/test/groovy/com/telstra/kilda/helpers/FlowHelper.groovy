package com.telstra.kilda.helpers

import com.telstra.kilda.model.Flow
import com.telstra.kilda.model.Switch

class FlowHelper {
    static Flow createFlow(Switch sw1, Switch sw2) {
        new Flow(flowId: "flowid", srcSwitch: sw1, dstSwitch: sw2)
    }
}