-- Create the Address table
CREATE TABLE Address (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  address1 VARCHAR(255) NOT NULL,
  address2 VARCHAR(255),
  city VARCHAR(255) NOT NULL,
  state VARCHAR(100) NOT NULL,
  postal VARCHAR(10) NOT NULL
);

-- Populate the Address table with two records
INSERT INTO Address (address1, address2, city, state, postal)
VALUES ('123 Main St', 'Apt 4B', 'New York', 'NY', '10001'),
       ('456 Elm St', NULL, 'Los Angeles', 'CA', '90001');

create table User
(
    id        bigint              not null primary key,
    firstName varchar(255)        not null,
    lastName  varchar(255)        not null,
    username  varchar(255) unique not null,
    password  varchar(255)        not null , -- WHAT!? NOT ENCRYPTED!? ;-)
    addressId bigint,
    FOREIGN KEY (addressId) REFERENCES Address(id)
);

insert into User
    (id, firstName, lastName, username, password , addressId)
values (1, 'Phil', 'Ingwell', 'PhilIngwell', 'Password123' , 1) ,
    (2, 'Anna', 'Conda', 'AnnaConda', 'Password234', 2);
