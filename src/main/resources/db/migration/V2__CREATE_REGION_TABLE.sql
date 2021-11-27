CREATE TABLE public.regions (
                                  id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                  iso2code varchar(3) NOT NULL,
                                  code varchar(3) NOT NULL,
                                  name varchar(255) NOT NULL,
                                  CONSTRAINT regions_pkey PRIMARY KEY (id),
                                  CONSTRAINT uk_region_name UNIQUE ("name"),
                                  CONSTRAINT uk_region_code UNIQUE ("code"),
                                  CONSTRAINT uk_region_iso2code UNIQUE ("iso2code")
);