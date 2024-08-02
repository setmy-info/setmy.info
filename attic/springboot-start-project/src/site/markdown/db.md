# DB

## PostgreSQL Database

### See also

[xxxx](http://yyyyy)

## H2 Database

Driver: org.h2.Driver

Embedded

jdbc:h2:~/test 'test' in the user home directory

jdbc:h2:/data/test 'test' in the directory /data

jdbc:h2:test in the current(!) working directory

In-Memory

jdbc:h2:mem:test multiple connections in one process

jdbc:h2:mem: unnamed private; one connection

Server Mode

jdbc:h2:tcp://localhost/~/test user home dir

jdbc:h2:tcp://localhost//data/test absolute dir

Dialect: org.hibernate.dialect.H2Dialect

## HSQLDB

Driver: org.hsqldb.jdbc.JDBCDriver

URL

jdbc:hsqldb:hsql://localhost/testdb

jdbc:hsqldb:file:testdb

jdbc:hsqldb:mem:testdb

user: sa

password:

### See also

[xxxx](http://yyyyy)
