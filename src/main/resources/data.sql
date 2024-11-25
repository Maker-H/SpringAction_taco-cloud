insert into users (username, password) values ('user1', 'password1');
insert into users (username, password) values ('user2', 'password2');

insert into authorities (username, authority)
    values ('user1', 'ROLE_USER');
insert into authorities (username, authority)
    values ('user2', 'ROLE_USER');

commit;

--============================================

delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;

insert into Ingredient(id, name, type)
    values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient(id, name, type)
    values ('F', 'Flour', 'WRAP');
insert into Ingredient(id, name, type)
    values ('P', 'Ground Beef', 'PROTEIN');
insert into Ingredient(id, name, type)
    values ('P22', 'Ground Beef222', 'PROTEIN');
insert into Ingredient(id, name, type)
    values ('S', 'Sour Cream', 'SAUCE');
