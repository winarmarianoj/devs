
CREATE SEQUENCE public.account_account_id_seq;

CREATE TABLE public.account (
                account_id BIGINT NOT NULL DEFAULT nextval('public.account_account_id_seq'),
                email VARCHAR NOT NULL,
                active BOOLEAN NOT NULL,
                available_tries SMALLINT NOT NULL,
                password VARCHAR NOT NULL,
                CONSTRAINT account_pk PRIMARY KEY (account_id)
);


ALTER SEQUENCE public.account_account_id_seq OWNED BY public.account.account_id;

CREATE TABLE public.user_1 (
                account_id BIGINT NOT NULL,
                birth DATE NOT NULL,
                name VARCHAR NOT NULL,
                last_name VARCHAR NOT NULL,
                status VARCHAR NOT NULL,
                profile_picture_path VARCHAR,
                CONSTRAINT user_1_pk PRIMARY KEY (account_id)
);


CREATE TABLE public.friend (
                account_id BIGINT NOT NULL,
                friend_id BIGINT NOT NULL,
                status VARCHAR NOT NULL,
                CONSTRAINT friend_pk PRIMARY KEY (account_id, friend_id)
);


CREATE TABLE public.post (
                account_id BIGINT NOT NULL,
                publication_id BIGINT NOT NULL,
                date TIMESTAMP NOT NULL,
                publication VARCHAR NOT NULL,
                image_path VARCHAR,
                CONSTRAINT publication_pk PRIMARY KEY (account_id, publication_id)
);


ALTER TABLE public.user_1 ADD CONSTRAINT user_login_user_data_fk
FOREIGN KEY (account_id)
REFERENCES public.account (account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.post ADD CONSTRAINT user_data_feed_fk
FOREIGN KEY (account_id)
REFERENCES public.user_1 (account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.friend ADD CONSTRAINT user_user_x_friend_fk
FOREIGN KEY (account_id)
REFERENCES public.user_1 (account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;