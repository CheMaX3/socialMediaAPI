CREATE TABLE users (user_id BIGSERIAL PRIMARY KEY NOT NULL, username VARCHAR(20) NOT NULL, email VARCHAR(50) NOT NULL,
password VARCHAR(16) NOT NULL);
CREATE TABLE friends (friend_id BIGINT NOT NULL, user_id BIGINT NOT NULL REFERENCES users (user_id));
CREATE TABLE subscribers (subscriber_id BIGINT NOT NULL, user_id BIGINT NOT NULL REFERENCES users (user_id));
CREATE TABLE subscribed (subscribed_id BIGINT NOT NULL, user_id BIGINT NOT NULL REFERENCES users (user_id));
CREATE TABLE posts (post_id BIGSERIAL PRIMARY KEY NOT NULL, header VARCHAR(255) NOT NULL, message TEXT,
creation_date_time TIMESTAMP(0) WITH TIME ZONE NOT NULL, updated_date_time TIMESTAMP(0) WITH TIME ZONE,
user_id BIGINT NOT NULL REFERENCES users (user_id));
CREATE TABLE images (image_id BIGSERIAL PRIMARY KEY NOT NULL, content BYTEA, post_id BIGINT NOT NULL REFERENCES posts (post_id));