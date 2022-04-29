
describe Musician;

create table Email (
	emailId integer not null primary key auto_increment,
    musicianId integer not null,
    address varchar(100) not null,
    foreign key (musicianId) references Musician(musicianId)
);

insert into Musician (firstName, lastName, country)
	values ("Mike", "Oldfield", "UK");

describe Band;
describe BandMember;

insert into Band (name)
	values ("Mike Oldfield");

insert into BandMember (musicianId, bandId, yearJoined)
	values (
		(select musicianId from Musician where firstName = 'Mike' and lastName = 'Oldfield'),
        (select bandId from Band where name = 'Mike Oldfield'),
        1953
	);

describe Album;

insert into Album (title, year, number, bandId)
	values ('Incantations', 1978, 4, (select bandId from Band where name = 'Mike Oldfield'));


describe Song;

insert into Song (title) values ('Part 1');
insert into Song (title) values ('Part 2');

describe AlbumSong;

insert into AlbumSong (trackNumber, trackLength, albumId, songId)
	values (1, 1149, (select albumId from Album where title = 'Incantations'), (select songId from Song where title = 'Part 1'));

insert into AlbumSong (trackNumber, trackLength, albumId, songId)
	values (2, 1175, (select albumId from Album where title = 'Incantations'), (select songId from Song where title = 'Part 2'));

select * from Musician where lastName = 'Best';
select * from Musician;

update Musician set firstName = 'Pete'
where firstName = 'P.' and lastName = 'Best';

#SET SQL_SAFE_UPDATES = 0;

select * from Band where name like '%Beatles';
select * from BandMember where bandId = (select bandId from Band where name like '%Beatles');

delete from BandMember 
where bandId = (select bandId from Band where name like '%Beatles')
and musicianId = (select musicianId from Musician where firstName = 'Pete' and lastName = 'Best');

delete from Song where title = 'Big in Japan';

#delete from Song where songId in (select songId from AlbumSong where albumId = (select albumId from Album where title = 'Mule Variations'));
delete from AlbumSong where albumId = (select albumId from Album where title = 'Mule Variations');

select title from Song where songId in (select songId from AlbumSong where albumId = (select albumId from Album where title = 'Mule Variations'));

delete from Album where title = 'Mule Variations';

delete from Song where songId not in (select songId from AlbumSong);

create table Venue (
	venueId int not null primary key auto_increment,
    name varchar(200) not null,
    city varchar(100)
    # there could be a full address here, but I'm lazy
);

create table Concert (
	concertId int not null primary key auto_increment,
    concertName varchar(100),
    venueId int not null,
    bandId int not null,
    foreign key (venueId) references Venue(venueId),
    foreign key (bandId) references Band(bandId)
);

create table ConcertSong (
	concertSongId int not null primary key auto_increment,
	concertId int not null,
    songId int not null,
    trackLength int,
    trackNumber int,
    foreign key (concertId) references Concert(concertId),
    foreign key (songId) references Song(songId)
);

alter table Concert add column (date varchar(24));
alter table Concert add column (ticketsSold integer);

alter table Venue add column (seats integer);

insert into Venue (name, city)
values ('Lied Center', 'Lincoln');

insert into Venue (name, city)
values ('Madison Square Garden', 'New York');

insert into Concert (venueId, bandId)
values (
	(select venueId from Venue where city = 'Lincoln'),
    (select bandId from Band where name = 'The Beatles')
);

insert into Concert (venueId, bandId)
values (
	(select venueId from Venue where name like '%Madison%'),
    (select bandId from Band where name = 'Tom Waits')
);

describe ConcertSong;

insert into ConcertSong (concertId, songId, trackLength, trackNumber)
values (
	(select concertId from Concert where venueId = (select venueId from Venue where city = 'Lincoln')),
    (select songId from Song where title = 'Let It Be'),
    1200, 3
);

insert into ConcertSong (concertId, songId, trackLength, trackNumber)
values (
	(select concertId from Concert where venueId = (select venueId from Venue where city = 'Lincoln')),
    (select songId from Song where title = 'And I Love Her'),
    120, 1
);

insert into ConcertSong (concertId, songId, trackLength, trackNumber)
values (
	(select concertId from Concert where venueId = (select venueId from Venue where city = 'Lincoln')),
    (select songId from Song where title = 'Can\'t Buy Me Love'),
    300, 2
);

select * from ConcertSong;

insert into ConcertSong (concertId, songId, trackLength, trackNumber)
values (
	(select concertId from Concert where venueId = (select venueId from Venue where city like '%ork%')),
    (select songId from Song where title = 'Singapore'),
    200, 1
);

insert into ConcertSong (concertId, songId, trackLength, trackNumber)
values (
	(select concertId from Concert where venueId = (select venueId from Venue where city like '%ork%')),
    (select songId from Song where title = 'Big Black Mariah'),
    332, 2
);

insert into ConcertSong (concertId, songId, trackLength, trackNumber)
values (
	(select concertId from Concert where venueId = (select venueId from Venue where city like '%ork%')),
    (select songId from Song where title = 'Hang Down Your Head'),
    32, 3
);

select concat(b.name, " @ ", v.name), cs.trackNumber, s.title, concat(floor(cs.trackLength / 60), ':', lpad(cs.trackLength % 60, 2, '0')) from Concert c
join Venue v on c.venueId = v.venueId
join Band b on b.bandId = c.bandId
join ConcertSong cs on cs.concertId = c.concertId
join Song s on s.songId = cs.songId;

