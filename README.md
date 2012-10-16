**Yves Hwang**
**16.10.2012**

chuck
=====

chuck is a webapp used to "chuck" out unique data and accepts any URL for the requests. This is prodominantly used for testing.

build
-----
ensure that you have java and ant running, then:
ant bundle 

This will build both a jar file with no dependencies, a bundled jar with all dependencies and a war file. 

run
---
Easiest way to to run chuck is to build the bundle and do:

java -jar chuck-bundle.jar

configure
---------
You can configure the port by specifying the following on the command-line or as part of starting up your web container if you are using the war file.

-Dcom.varnish.chuck.port=1234



