---------- IncomeData table ----------

CREATE TABLE tax.income_data
(
    id           VARCHAR   NOT NULL PRIMARY KEY,
    tax_payer_id VARCHAR   NOT NULL,
    received_at  TIMESTAMP NOT NULL,
    amount       DOUBLE    NOT NULL
);

---------- TaxType table ----------

CREATE TABLE tax.tax_types
(
    id          VARCHAR NOT NULL PRIMARY KEY,
    title       VARCHAR NOT NULL,
    description VARCHAR NULL,
);

---------- TaxLayer table ----------

CREATE TABLE tax.tax_layers
(
    id         VARCHAR   NOT NULL PRIMARY KEY,
    type_id    VARCHAR   NOT NULL,
    percent    DOUBLE    NOT NULL,
    min_amount DOUBLE    NOT NULL,
    max_mount  DOUBLE    NOT NULL,
    valid_from TIMESTAMP NOT NULL,
    valid_to   TIMESTAMP NOT NULL,
    foreign key (type_id) references tax_types (id)
);

---------- TaxData table ----------

CREATE TABLE tax.taxes
(
    id           VARCHAR   NOT NULL PRIMARY KEY,
    type_id      VARCHAR   NOT NULL,
    tax_payer_id VARCHAR   NOT NULL,
    payed_at     TIMESTAMP NOT NULL,
    amount       DOUBLE    NOT NULL,
    foreign key (type_id) references tax_types (id)
);
