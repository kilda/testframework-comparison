package com.telstra.kilda.services

import com.telstra.kilda.model.Switch

class TopologyDefinition {
    List<Switch> getActiveSwitches() {
        [new Switch(id: 1), new Switch(id: 2), new Switch(id: 3)]
    }
}
