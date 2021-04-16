drop table if exists word_syns;
drop table if exists category_keywords;

create table word_syns(
        word_id serial primary key,
        word varchar(64) not null,
        syns varchar(64) not null,
        category varchar(64) not null
        
);

create table category_keywords(
        category varchar(64) unique not null,
        keywords varchar(64) not null
        
);


select * from word_syns;

insert into word_syns(word, syns) values ("testWord", "testWordSyn");
