# testframework-comparison
files of interest:  
`com.telstra.kilda.spec.ExampleSpec`  
`com.telstra.kilda.BaseSpecification`  
`com.telstra.kilda.extension.CleanupLeverExtension`

### Tests
Example test can be found in `com.telstra.kilda.spec` package. I tried to supply it with some
 additional comments inside in order to pin-point exactly what is the benefit/difference
 from Cucumber.  
 Tests are executable, but use some mock implementations of services.  
 You may have to mark 'groovy' directory as test sources root and 'resources' as test resources root
 in order to execute.
 
### Extensions
Situation: we decided that we want to be able to turn on/off our teardown methods on demand
(this is not a prepared case, just took it from my head). Quickly implemented
this via Spock extension `com.telstra.kilda.extension.CleanupLeverExtension`  
Just set system property `cleanups` to `false` and cleanup methods won't run, allowing engineer 
to debug the environment state in case of test failure.
