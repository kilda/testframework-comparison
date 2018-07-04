package com.telstra.kilda.extension

import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.SpecInfo

/**
 * Set property "cleanups" to "false" in order to turn off cleanup() and cleanupSpec() methods.
 * Spock extension mechanism can allow tons of other possibilities.
 * Cannot be achieved in Cucumber even near.
 */
class CleanupLeverExtension extends AbstractGlobalExtension {
    @Override
    void visitSpec(SpecInfo spec) {
        spec.fixtureMethods.each { fixtureMethod ->
            fixtureMethod.addInterceptor(new IMethodInterceptor() {
                @Override
                void intercept(IMethodInvocation invocation) throws Throwable {
                    if(fixtureMethod.kind.isCleanupMethod()
                            && !System.getProperty("cleanups", "true").toBoolean()){
                        return //do not call invocation.proceed() for cleanups
                    }
                    invocation.proceed()
                }
            })
        }
    }
}
