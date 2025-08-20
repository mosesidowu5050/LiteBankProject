truncate table account cascade;
truncate table transaction cascade;

insert into account (id, account_number) values (
    1, '123456789');

insert into transaction (id, amount, account_number, transaction_type)
values (
    '100', 20000, '123456789', 0),
        ('101', 20000, '123456789', 1),
        ('102', 30000, '123456789', 1),
        ('103', 200000, '987654321', 0),
        ('104', 200000, '123456789', 0),
        ('105', 200000, '987654321', 1),
        ('106', 200000, '123456789', 0);
