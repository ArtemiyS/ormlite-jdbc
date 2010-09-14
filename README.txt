-------------------------------------------------------------------------------------------------------

BACKGROUND:

This package provides a lightweight Object Relational Mapping between Java classes and SQL databases
that provide JDBC connections.  There is also a version of the package that supports the Android
database calls directly.  See the ormlite-android release.

There are certainly some much more mature packages which provide this functionality including Hibernate
and iBatis.  However, I wanted a simple wrapper around the JDBC functions from the Spring package and
Hibernate and iBatis are significantly more complicated.   The package supports natively MySQL,
Postgres, Microsoft SQL Server, H2, Derby, HSQLDB, and Sqlite.  It also contains initial, untested
support for DB2 and Oracle and can be extended to additional ones relatively easily.  Contact the author
if your database type is not supported.

This code is 97% my own.  Probably 2% is PD stuff that I've cherry picked from the web and
another 1% that that I've copied from other developers I've worked with -- all with their approval.

Hope it helps.

Gray Watson

http://ormlite.sourceforge.net/
http://256.com/gray/

-------------------------------------------------------------------------------------------------------

REQUIREMENTS:

- Java 5 or above (http://www.sun.com/java/)

-------------------------------------------------------------------------------------------------------

GETTING STARTED:

See the online documentation:  http://ormlite.sourceforge.net/

-------------------------------------------------------------------------------------------------------

MAVEN DEPENDENCIES:

ORMLite has no direct dependencies.  It has logging classes that use reflection to call out to
log4j and other logging classes but these will not be called unless they exist in the classpath.
Package versions can be tuned as necessary.

Test Dependencies:

	commons-logging -- 1.1.1
	log4j -- 1.2.15
	junit -- 4.8.1
	org.easymock -- 2.3
	com.h2database -- 1.2.134
	javax-persistence -- 1.0

-------------------------------------------------------------------------------------------------------
