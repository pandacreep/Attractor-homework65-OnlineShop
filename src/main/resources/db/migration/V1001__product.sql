CREATE TABLE "products" (
    "id" SERIAL NOT NULL,
    "name" varchar(128) NOT NULL,
    "image" varchar(128) NOT NULL,
    "qty" int NOT NULL,
    "description" varchar(128) NOT NULL,
    "price" double precision NOT NULL,
    "category_id" int NOT NULL ,
    PRIMARY KEY ("id"),
    CONSTRAINT "fk_product_category"
        FOREIGN KEY ("category_id")
        REFERENCES "categories" ("id")
);
