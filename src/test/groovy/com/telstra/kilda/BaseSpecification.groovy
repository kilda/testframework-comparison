package com.telstra.kilda

import spock.lang.Specification

class BaseSpecification extends Specification {
    def setupSpec() {
        println "This is an ancestor setupSpec. Executed in addition to other fixtures " +
                "implemented by those who extend this class"
    }
}
