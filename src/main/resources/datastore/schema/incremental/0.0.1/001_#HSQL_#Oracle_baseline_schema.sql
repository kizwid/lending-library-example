create table title_type(
  title_type_id int not null
 ,title_type_name varchar(150) not null
 ,primary key(title_type_id)
);
create unique index idx_tt_name on title_type(title_type_name);

create sequence title_type_seq;

create table title(
  title_id number(38,0) not null
 ,title_type_id int not null
 ,title_name varchar(255) not null
 ,primary key(title_id)
);
create unique index idx_t_pk on title(title_type_id, title_name);
create index idx_t_type on title(title_type_id);
create index idx_t_name on title(title_name);

create sequence title_seq;

create table title_item(
  title_item_id number(38,0) not null
 ,title_id number(38,0) not null
 ,primary key(title_item_id)
);
create index idx_ti_title on title_item(title_id);

create sequence title_item_seq;

create table borrower(
  borrower_id number(38,0) not null
 ,borrower_name varchar(150) not null
 ,primary key(borrower_id)
);
create unique index idx_b_name on borrower(borrower_name);

create sequence borrower_seq;

create table loan(
  loan_id number(38,0) not null
 ,title_item_id number(38,0) not null
 ,borrower_id number(38,0) not null
 ,taken_out int not null
 ,due_back int not null
 ,returned_on int not null
 ,primary key(loan_id)
);
create unique index idx_l_pk on loan(title_item_id,borrower_id,taken_out);
create index idx_l_who on loan(borrower_id)
create index idx_l_taken on loan(taken_out)
create index idx_l_due on loan(due_back)
create index idx_l_returned on loan(returned_on)

create sequence loan_seq;

