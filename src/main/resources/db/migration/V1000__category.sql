CREATE TABLE "categories" (
    "id" SERIAL NOT NULL,
    "name" varchar(128) NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "categories" ("name")
VALUES
    ('Phones'),
    ('Multimedia'),
    ('Software'),
    ('Household'),
    ('Action items - March 8 (empty)'),
    ('Action items - Christmas (empty)'),
    ('Action items - Black Friday (empty)'),
    ('Action items - Summer discounts (empty)')
    ;

