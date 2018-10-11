create table user
(
  user_id  int auto_increment,
  username varchar(255) not null,
  password varchar(255) not null,
  primary key (user_id, username),
  constraint username
  unique (username)
);

create table playlist
(
  playlist_id int auto_increment
    primary key,
  name        text       not null,
  owner       tinyint(1) not null,
  user_id     int        not null,
  constraint FK_user_playlist
  foreign key (user_id) references user (user_id)
    on update cascade
    on delete cascade
);

create table token
(
  username   varchar(255) not null,
  token      varchar(255) not null,
  expiryDate bigint       not null,
  primary key (username, token),
  constraint FK_token_user
  foreign key (username) references user (username)
    on update cascade
    on delete cascade
);

create table track
(
  playlist_id      int          not null,
  track_id         int auto_increment
    primary key,
  title            varchar(255) not null,
  performer        varchar(255) not null,
  duration         int          not null,
  playcount        int          not null,
  offlineAvailable tinyint(1)   not null,
  album            varchar(255) null,
  description      varchar(255) null,
  publication_date date         null,
  constraint FK_playlist_track
  foreign key (playlist_id) references playlist (playlist_id)
    on update cascade
    on delete cascade
);

