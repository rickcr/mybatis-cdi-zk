mybatis-cdi-zk
=================

Sample BETA application demonstrating integration of MyBatis, CDI, and ZK

It works but I'm calling it Beta since I'm unclear about a few things and I would like to get some integration testing working (Arquillian?)

**To build:**<br/>
Requires maven<br/>
From project directory: mvn clean install -DskipTests

(skipping tests for now until I get this Arquillian stuff to work)


**To Deploy:**<br/>
The war file can be deployed to Tomcat7 or JBoss6.

When I used Spring I was able to set up a nice 'in memory' version of HSQLDB, but doubt it's possible with vanilla CDI stuff (although maybe with Maven?)
So for now, you'll have to setup HSQLDB and run it locally first before the app will work.

Download HSQLDB from here http://sourceforge.net/projects/hsqldb/files/hsqldb/ and unzip to your directory of choice.
(Be sure to get the version that you are using in the pom.xml (At the time of this writing it's 2.3.2)

Copy the empmaint.script file from src/main/resources into your {hsqldb-dir}/data/ directory

From the {hsqldb-dir}/data/ directory run the following command to start up the hsqldb instance for our employee maintenance app
java -cp ../lib/hsqldb.jar org.hsqldb.Server -database.0 file:empmaint -dbname.0 empdb

In browser navigate to localhost:8080/mybatis-cdi-zk-1.0

**What I need help with**

Since I'm new to using CDI here are some questions/concerns...

1) You're supposed to be able to add a weld:scan section in beans.xml but when I do, I get start up errors? It works without it,
but I'd like it to only scan what it needs so I'd prefer to add the weld:scan section if I could.

    <weld:scan>
		<weld:include name="net.learntechnology.empmaint.*" />
		<weld:include name="net.learntechnology.empmaint.domain.*" />
		<weld:include name="net.learntechnology.empmaint.mapper.*" />
		<weld:include name="net.learntechnology.empmaint.services.*" />
		<weld:include name="net.learntechnology.empmaint.viewmodel.*" />
	</weld:scan>

2) I don't understand why/how to upgrade to weld-servlet 2.9? (see note in my pom.xml)
I posted about it here posted about it here on stackoverlfow
http://stackoverflow.com/questions/28622091/upgrading-my-weld-servlet-dependency-from-1-1-to-2-2-9-common-error-shows-up

3) Why do some examples also have a beans.xml in resources/META-INF/ along with WEB-INF/ ?







