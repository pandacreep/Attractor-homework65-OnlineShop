CREATE TABLE "customers" (
    "id" SERIAL NOT NULL,
    "email" varchar(128) NOT NULL UNIQUE,
    "password" varchar(128) NOT NULL,
    "fullname" varchar(128) NOT NULL,
    "enabled" boolean NOT NULL default true,
    "role" varchar(16) NOT NULL default 'USER',
    PRIMARY KEY ("id"),
    UNIQUE ("email")
);
