package com.telstra.kilda.spec

import com.telstra.kilda.BaseSpecification
import com.telstra.kilda.helpers.FlowHelper
import com.telstra.kilda.services.Floodlight
import com.telstra.kilda.services.Northbound
import com.telstra.kilda.services.TopologyDefinition
import spock.lang.Shared
import spock.lang.Unroll

/*
 Note the BaseSpecification base class. It can hold some fixture methods that will be executed
 by descendants in addition to their own; or any other repeating code. On more way to group tests.
 Cannot be achieved in Cucumber.
 */
class ExampleSpec extends BaseSpecification {
    @Shared
    TopologyDefinition topologyDefinition = new TopologyDefinition()
    Floodlight floodlight = new Floodlight()
    Northbound northbound = new Northbound()

    /*
    Difficult to achieve in Cucumber
     */
    def setupSpec() {
        println "before all tests in this Spec. For example: preparing the required topology"
    }

    def setup() {
        //can be achieved in cucumber using 'Background:'
        println "before every test in this spec."
    }

    /*
    The way the test is implemented is the MAIN advantage of Junit/Spock vs Cucumber, not even the fixtures.
    This one is certainly reproducible in Cucumber but in a more painful, slower and less readable way.
    Just showing how much more readable this test is, allowing to create variables
    and reuse them between 'steps' without any hassle.
    Will be good too see how the Cucumber analogue should look like.
     */
    @Unroll
    def "Able to remove a meter from '#data.srcSwitch.id' switch via Northbound"() {
        given: "A flow between 2 switches"
        def srcSwitch = data.srcSwitch
        def dstSwitch = data.dstSwitch
        def flow = FlowHelper.createFlow(srcSwitch, dstSwitch)

        when: "Remove meter from one of the switches"
        def meterToRemove = floodlight.getMeters(srcSwitch.id).first()
        def deleteResponse = northbound.removeMeter(srcSwitch.id, meterToRemove.meterId)

        then: "NB response says deletion is ok"
        deleteResponse.ok

        and: "Deleted meter is not returned by floodlight"
        !floodlight.getMeters(srcSwitch.id).contains(meterToRemove)

        and: "Flow remains valid"
        northbound.validateFlow(flow.flowId).ok

        /*
        Note the cleanup block which will be executed no matter what. Difficult to achieve in Cucumber
         */
        cleanup: "Remove created flow"
        northbound.removeFlow(flow.flowId)

        where:
        /*
        Can prepare input data for test using code, full data-provider possibilities.
        (generate data, read from external file, get data from some other class or service,
        use JAVA entities as input).
        Cannot be done in Cucumber, since it can only take table with strings/numbers. Maybe we can workaround some
        cases, but not with ease.
         */
        data << [
            [
                srcSwitch: topologyDefinition.getActiveSwitches()[0],
                dstSwitch: topologyDefinition.getActiveSwitches()[1]
            ],
            [
                srcSwitch: topologyDefinition.getActiveSwitches()[1],
                dstSwitch: topologyDefinition.getActiveSwitches()[0]
            ]
        ]
    }

    /*
    Difficult to achieve in Cucumber
     */
    def cleanup() {
        println "after every test in this spec"
    }

    /*
    Difficult to achieve in Cucumber
     */
    def cleanupSpec() {
        println "after all tests in this spec. e.g. revert topology state to the 'default' state"
    }
}
