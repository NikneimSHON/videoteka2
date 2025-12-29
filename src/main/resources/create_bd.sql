CREATE TABLE category
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(128)             NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- Основные сущности

CREATE TABLE users
(
    id                UUID PRIMARY KEY,
    password          VARCHAR(128)             NOT NULL,             -- ВРЕМЕННО! Заменить на hash+salt
    first_name        VARCHAR(128),
    last_name         VARCHAR(128),
    role              VARCHAR(128)             NOT NULL CHECK (role IN ('ADMIN', 'USER')),
    status            varchar(128)             NOT NULL CHECK (status IN ('ACTIVE', 'BANNED')),
    email             VARCHAR(128)             NOT NULL UNIQUE,
    is_email_verified BOOLEAN                  NOT NULL,
    birth_date        DATE                     NOT NULL,
    created_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()-- + DEFAULT
);

CREATE TABLE video
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(128) UNIQUE      NOT NULL,
    len_seconds INTEGER                  NOT NULL CHECK (len_seconds > 0),
    description TEXT,
    status      VARCHAR(128)             NOT NULL CHECK (status IN ('PUBLISHED', 'UNPUBLISHED')),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- Связи
CREATE TABLE video_category
(
    id          UUID PRIMARY KEY,
    video_id    UUID NOT NULL REFERENCES video (id) ON DELETE CASCADE,
    category_id UUID NOT NULL REFERENCES category (id) ON DELETE CASCADE,
    UNIQUE (video_id,category_id)

);

CREATE TABLE video_metadata
(
    id         UUID PRIMARY KEY,
    url        TEXT UNIQUE              NOT NULL CHECK (url ~ '^https?://'),
    quality    VARCHAR(10)              NOT NULL CHECK (quality IN ('360p', '480p', '720p', '1080p', '4K')),
    video_id   UUID                   NOT NULL REFERENCES video (id) ON DELETE CASCADE,
    status     VARCHAR(128)             NOT NULL CHECK (status IN ('PROCESSING', 'READY', 'FAILED')),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

drop table video_category;
drop table video_metadata;
drop table category;
drop table users;
drop table video;

ALTER TABLE video_category
    ADD CONSTRAINT uq_video_category UNIQUE (video_id, category_id);

