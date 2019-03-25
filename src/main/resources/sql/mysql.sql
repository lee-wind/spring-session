--local connection
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON spring_session.*
TO 'wind'@'localhost'
IDENTIFIED BY 'wind';

--remote connection
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON spring_session.*
TO 'wind'@'192.168.56.1'
IDENTIFIED BY 'wind';

--remote connection
GRANT SELECT, INSERT, UPDATE
ON spring_session.*
TO 'wind'@'192.168.56.102'
IDENTIFIED BY 'wind';

select user, host from user;

GRANT ALL PRIVILEGES ON *.* TO 'sun'@'%' IDENTIFIED BY 'sun';