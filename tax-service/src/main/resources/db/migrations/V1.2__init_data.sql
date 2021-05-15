SET @tax_type_id = UUID();
INSERT INTO tax.tax_types(id, title, description) VALUES ( @tax_type_id, 'Inc1', 'Test income tax type' );

SET @valid_from = parsedatetime('01-01-2020 00:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS');
SET @valid_to = parsedatetime('31-12-2050 23:59:59.999', 'dd-MM-yyyy hh:mm:ss.SS');
INSERT INTO tax.tax_layers(id, type_id, percent, min_amount, max_amount, valid_from, valid_to) VALUES ( RANDOM_UUID(), @tax_type_id, 10, 0, 600, @valid_from, @valid_to );
INSERT INTO tax.tax_layers(id, type_id, percent, min_amount, max_amount, valid_from, valid_to) VALUES ( RANDOM_UUID(), @tax_type_id, 15, 600.01, 1200, @valid_from, @valid_to );
INSERT INTO tax.tax_layers(id, type_id, percent, min_amount, max_amount, valid_from, valid_to) VALUES ( RANDOM_UUID(), @tax_type_id, 20, 1200.01, 1600, @valid_from, @valid_to );
INSERT INTO tax.tax_layers(id, type_id, percent, min_amount, max_amount, valid_from, valid_to) VALUES ( RANDOM_UUID(), @tax_type_id, 30, 1600.01, 999999999999999, @valid_from, @valid_to );
