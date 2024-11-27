-- Users and Authorities
insert into users (username, password) values ('user1', 'password1');
insert into users (username, password) values ('user2', 'password2');

insert into authorities (username, authority)
    values ('user1', 'ROLE_USER');
insert into authorities (username, authority)
    values ('user2', 'ROLE_USER');

commit;

-- Clean up existing data
delete from taco_order_tacos;
delete from taco_ingredients;
delete from taco;
delete from taco_order;
delete from ingredient;

-- Insert Ingredients
insert into ingredient (id, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into ingredient (id, name, type) values ('F', 'Flour', 'WRAP');
insert into ingredient (id, name, type) values ('P', 'Ground Beef', 'PROTEIN');
insert into ingredient (id, name, type) values ('P22', 'Ground Beef222', 'PROTEIN');
insert into ingredient (id, name, type) values ('S', 'Sour Cream', 'SAUCE');

-- Insert Tacos
insert into taco (id, name, created_at) values (1, 'Classic Taco', current_timestamp);
insert into taco (id, name, created_at) values (2, 'Spicy Taco', current_timestamp);

-- Map Ingredients to Classic Taco
insert into taco_ingredients (taco, ingredient) values (1, 'FLTO'); -- Flour Tortilla
insert into taco_ingredients (taco, ingredient) values (1, 'P');    -- Ground Beef
insert into taco_ingredients (taco, ingredient) values (1, 'S');    -- Sour Cream

-- Map Ingredients to Spicy Taco
insert into taco_ingredients (taco, ingredient) values (2, 'F');    -- Flour
insert into taco_ingredients (taco, ingredient) values (2, 'P22');  -- Ground Beef222
insert into taco_ingredients (taco, ingredient) values (2, 'S');    -- Sour Cream

-- Insert Taco Orders
insert into taco_order (id, delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)
    values (1, 'John Doe', '123 Elm Street', 'Springfield', 'IL', '62704', '1234567812345678', '12/24', '123', current_timestamp);

insert into taco_order (id, delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)
    values (2, 'Jane Smith', '456 Oak Avenue', 'Shelbyville', 'IL', '61544', '8765432187654321', '11/25', '456', current_timestamp);

-- Map Tacos to Orders
insert into taco_order_tacos (taco_order, taco) values (1, 1); -- John Doe's order contains Classic Taco
insert into taco_order_tacos (taco_order, taco) values (2, 2); -- Jane Smith's order contains Spicy Taco
