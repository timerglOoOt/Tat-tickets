create table file_info
(
    id  serial primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100)
);

create table users
(
    id  serial primary key,
    first_name varchar(60) not null,
    last_name varchar(60) not null,
    email varchar(255) unique,
    hashed_password varchar(100) not null,
    avatar_id integer references file_info(id)
);

create table games
(
    id serial primary key,
    number_of_available_seats integer check (number_of_available_seats >= 0),
    game_date timestamp check (game_date >= current_date - interval '1 month' and game_date <= current_date + interval '1 month')
);

create table tickets
(
    id serial primary key,
    sector_name varchar(10) not null,
    seat_number integer not null,
    price integer check ( price > 0 and price < 20000 ),
    status bool not null,
    game_id integer references games(id)
);

create table bookings
(
    id  serial primary key,
    booking_date timestamp check (booking_date >= current_date - interval '5 years' and booking_date <= current_date),
    user_id integer references users(id),
    ticket_id integer references tickets(id)
);
