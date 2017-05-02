CREATE DATABASE parteak;

GRANT ALL ON parteak.* TO parteak@'%' IDENTIFIED BY 'ELia1626';
GRANT ALL ON parteak.* TO parteak@localhost IDENTIFIED BY 'ELia1626';
GRANT ALL ON parteak.* To parteak@'%' IDENTIFIED BY '';
GRANT ALL ON parteak.* To parteak@localhost IDENTIFIED BY '';

USE parteak;

CREATE TABLE operation_type (
  id INTEGER PRIMARY KEY,
  name varchar(255)
);
CREATE INDEX operation_type_name ON operation_type(name);