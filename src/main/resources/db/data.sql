insert into account (id, account_number) values (
    1, '123456789');

insert into transaction (id, amount, account_number, transaction_type)
values (
    '100', 20000, '123456789', 0),
        ('101', 5000, '123456789', 1),
        ('102', 15000, '123456789', 0),
        ('103', 3000, '987654321', 1),
        ('104', 7000, '123456789', 0),
        ('105', 10000, '987654321', 1),
        ('106', 12000, '123456789', 0);
