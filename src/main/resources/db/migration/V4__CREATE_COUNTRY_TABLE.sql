CREATE TABLE public.countries (
                              id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                              last_updated timestamp NOT NULL,
                              code varchar(3) NOT NULL,
                              iso2Code varchar(3) NOT NULL,
                              name varchar(255) NOT NULL,
                              longitude float8,
                              latitude float8,
                              population float8,
                              gdp float8,
                              income_level_id int8 NOT NULL,
                              region_id int8 NOT NULL,
                              CONSTRAINT countries_pkey PRIMARY KEY (id),
                              CONSTRAINT uk_name UNIQUE ("name"),
                              CONSTRAINT uk_code UNIQUE ("code")
);

ALTER TABLE public.countries ADD CONSTRAINT fk_income_level_id FOREIGN KEY (income_level_id) REFERENCES income_levels(id);
ALTER TABLE public.countries ADD CONSTRAINT fk_region_id FOREIGN KEY (region_id) REFERENCES regions(id);