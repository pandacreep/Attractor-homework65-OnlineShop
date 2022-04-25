CREATE TABLE "order_items" (
    "id" SERIAL NOT NULL,
    "order_id" int NOT NULL,
    "product_id" int NOT NULL,
    "qty" int NOT NULL
);
