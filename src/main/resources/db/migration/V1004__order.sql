CREATE TABLE "orders" (
    "id" SERIAL NOT NULL,
    "email" varchar(128) NOT NULL,
    "order_date" varchar(128) NOT NULL,
    "fullname" varchar(128) NOT NULL,
    "phone" varchar(128) NOT NULL,
    "address" varchar(128) NOT NULL,
    "payment_type" varchar(128) NOT NULL,
    "amount" double precision NOT NULL
);
