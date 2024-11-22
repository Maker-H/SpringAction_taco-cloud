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
