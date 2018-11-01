create table role
(
  role varchar(255) not null
    primary key
);

create table track
(
  track_id         int auto_increment
    primary key,
  title            varchar(255) not null,
  performer        varchar(255) not null,
  duration         int          not null,
  playcount        int          not null,
  offlineAvailable tinyint(1)   not null,
  album            varchar(255) null,
  description      varchar(255) null,
  publication_date date         null
);

create table user
(
  user_id  int auto_increment,
  username varchar(255) not null,
  password varchar(255) not null,
  role     varchar(255) not null,
  primary key (user_id, username),
  constraint username
  unique (username),
  constraint fk_user_role
  foreign key (role) references role (role)
    on update cascade
    on delete cascade
);

create table playlist
(
  playlist_id int auto_increment
    primary key,
  name        text         not null,
  owner       tinyint(1)   not null,
  username    varchar(255) not null,
  constraint FK_user_palylist
  foreign key (username) references user (username)
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

create table trackPlaylist
(
  track_id         int        not null,
  playlist_id      int        not null,
  offlineAvailable tinyint(1) not null,
  constraint FK_trackPalylist_track
  foreign key (track_id) references track (track_id)
    on update cascade
    on delete cascade,
  constraint FK_trackPlaylist_playlist
  foreign key (playlist_id) references playlist (playlist_id)
    on update cascade
    on delete cascade
);

