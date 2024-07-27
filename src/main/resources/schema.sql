create table if not exists manager(managerId UUID primary key,name text);

create table if not exists users(userId UUID primary key ,managerId UUID,fullName text NOT NULL,panNum text NOT NULL UNIQUE,mobileNumber text NOT NULL UNIQUE,createdAt datetime,updatedAt datetime,isActive boolean,foreign key(managerId) references manager(managerId));