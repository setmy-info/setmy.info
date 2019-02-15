# PostgreSQL

## Information

## Installation

### CentOS
    
    sudo yum -y install https://download.postgresql.org/pub/repos/yum/10/redhat/rhel-7-x86_64/pgdg-centos10-10-2.noarch.rpm
    sudo yum -y install postgresql10 postgresql10-server
    sudo /usr/pgsql-10/bin/postgresql-10-setup initdb
    sudo systemctl enable postgresql-10
    sudo systemctl start postgresql-10
    sudo su - postgres
        psql
            \password
            or...
            ALTER USER postgres WITH PASSWORD 'supersecretpassword';
            CREATE USER test WITH PASSWORD 'testuser';
            CREATE DATABASE testdb;
            ALTER DATABASE testdb OWNER TO test;
    In: /var/lib/pgsql/10/data pg_hba.conf and postgresql.conf
    systemctl restart postgresql-10

### Fedora

### FreeBSD

### OpenIndiana

## Configuration

## Usage, tips and tricks

    SELECT version();
    SELECT current_database();
    \connect ANOTHERDB;
    SELECT table_name FROM information_schema.tables WHERE table_schema='ANOTHER_SCHEMA';
    SELECT sequence_schema, sequence_name FROM information_schema.sequences WHERE sequence_schema='ANOTHER_SCHEMA';

## Control questions

    What is xxxx?

## See also

    [xxxx](http://yyyyy)

Paragraphs are separated
by a blank line.

Two spaces at the end of a line  
produces a line break.

Text attributes _italic_, 
**bold**, `monospace`.

Horizontal rule:

---

Bullet list:

  * apples
  * oranges
  * pears

Numbered list:

  1. wash
  2. rinse
  3. repeat

A [link](http://example.com).

![Image](images/island.jpg)

> Markdown uses email-style > characters for blockquoting.

Inline <abbr title="Hypertext Markup Language">HTML</abbr> is supported.

