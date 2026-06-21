--
-- PostgreSQL database dump
--

\restrict vwO1zqDwkxAh0vOwk4gQNpR7VnUYUTt0gYyZbEDtkrzJKI3OKAOwvAnpt4Z9Xvl

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

-- Started on 2026-06-21 15:49:15 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 26966)
-- Name: children; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.children (
    id integer NOT NULL,
    second_name text,
    first_name text,
    patronymic text,
    birthdate text,
    gender integer,
    snils text,
    passport_number text,
    passport_series text,
    id_education_group integer,
    status integer,
    archive boolean
);


ALTER TABLE public.children OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 26972)
-- Name: children_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.children ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.children_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 26973)
-- Name: children_interaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.children_interaction (
    id integer NOT NULL,
    id_children integer,
    id_interaction integer
);


ALTER TABLE public.children_interaction OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 26977)
-- Name: children_interaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.children_interaction ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.children_interaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 266 (class 1259 OID 27997)
-- Name: document; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.document (
    id integer NOT NULL,
    template_id integer,
    user_id integer,
    created_at character varying,
    archive boolean,
    label character varying
);


ALTER TABLE public.document OWNER TO postgres;

--
-- TOC entry 268 (class 1259 OID 28008)
-- Name: document_field_values; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.document_field_values (
    id integer NOT NULL,
    document_id integer,
    field_id integer,
    value character varying
);


ALTER TABLE public.document_field_values OWNER TO postgres;

--
-- TOC entry 267 (class 1259 OID 28007)
-- Name: document_field_values_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.document_field_values ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.document_field_values_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 265 (class 1259 OID 27996)
-- Name: document_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.document ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.document_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 223 (class 1259 OID 26978)
-- Name: education_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.education_group (
    id integer NOT NULL,
    group_name text,
    id_tutor integer
);


ALTER TABLE public.education_group OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 26984)
-- Name: education_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.education_group ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.education_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 225 (class 1259 OID 26985)
-- Name: education_level; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.education_level (
    id integer NOT NULL,
    education_name text
);


ALTER TABLE public.education_level OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 26991)
-- Name: education_level_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.education_level ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.education_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 26992)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id integer CONSTRAINT employees_id_not_null NOT NULL,
    second_name text,
    first_name text,
    patronymic text,
    email text,
    phone text,
    post integer
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 26998)
-- Name: employees_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.employee ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.employees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 229 (class 1259 OID 26999)
-- Name: family_situation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.family_situation (
    id integer NOT NULL,
    situation_name text
);


ALTER TABLE public.family_situation OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 27005)
-- Name: family_situation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.family_situation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.family_situation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 231 (class 1259 OID 27006)
-- Name: gender; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gender (
    id integer NOT NULL,
    gender_name text
);


ALTER TABLE public.gender OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 27012)
-- Name: gender_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.gender ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.gender_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 233 (class 1259 OID 27013)
-- Name: health_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.health_group (
    id integer NOT NULL,
    group_name text
);


ALTER TABLE public.health_group OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 27019)
-- Name: health_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.health_group ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.health_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 235 (class 1259 OID 27020)
-- Name: housing_rights; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.housing_rights (
    id integer NOT NULL,
    id_children integer,
    availability_of_housing text,
    form_of_ownership integer,
    registration_date text,
    city text,
    street text,
    build text,
    archive boolean
);


ALTER TABLE public.housing_rights OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 27026)
-- Name: housing_rights_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.housing_rights ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.housing_rights_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 237 (class 1259 OID 27027)
-- Name: interaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.interaction (
    id integer NOT NULL,
    id_organization integer,
    id_user integer,
    interaction_date text,
    interaction_type integer,
    interaction_result character varying,
    archive boolean
);


ALTER TABLE public.interaction OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 27033)
-- Name: interaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.interaction ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.interaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 260 (class 1259 OID 27430)
-- Name: interaction_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.interaction_type (
    id integer NOT NULL,
    interaction_name character varying NOT NULL
);


ALTER TABLE public.interaction_type OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 27429)
-- Name: interaction_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.interaction_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.interaction_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 239 (class 1259 OID 27034)
-- Name: monitoring_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.monitoring_type (
    id integer NOT NULL,
    monitoring_name text
);


ALTER TABLE public.monitoring_type OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 27040)
-- Name: monitoring_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.monitoring_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.monitoring_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 241 (class 1259 OID 27041)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id integer NOT NULL,
    organization_name character varying,
    city character varying,
    street character varying,
    build character varying,
    phone character varying,
    email character varying,
    type integer
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 27047)
-- Name: organization_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.organization ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 243 (class 1259 OID 27048)
-- Name: organization_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization_type (
    id integer NOT NULL,
    type_name character varying
);


ALTER TABLE public.organization_type OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 27054)
-- Name: organization_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.organization_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.organization_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 258 (class 1259 OID 27291)
-- Name: ownership_form; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ownership_form (
    id integer NOT NULL,
    form_name character varying NOT NULL
);


ALTER TABLE public.ownership_form OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 27290)
-- Name: ownership_form_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ownership_form ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ownership_form_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 245 (class 1259 OID 27055)
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post (
    id integer NOT NULL,
    post_name character varying
);


ALTER TABLE public.post OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 27061)
-- Name: post_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.post ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 270 (class 1259 OID 28047)
-- Name: security_access_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.security_access_log (
    id integer NOT NULL,
    event_timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    event_type character varying(50) NOT NULL,
    event_result character varying(20) NOT NULL,
    subject_identifier character varying(255) NOT NULL,
    details text
);


ALTER TABLE public.security_access_log OWNER TO postgres;

--
-- TOC entry 269 (class 1259 OID 28046)
-- Name: security_access_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.security_access_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.security_access_log_id_seq OWNER TO postgres;

--
-- TOC entry 3769 (class 0 OID 0)
-- Dependencies: 269
-- Name: security_access_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.security_access_log_id_seq OWNED BY public.security_access_log.id;


--
-- TOC entry 247 (class 1259 OID 27062)
-- Name: social_monitoring; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.social_monitoring (
    id integer NOT NULL,
    id_children integer,
    date_of_fixation text,
    id_monitoring_type integer,
    old_value text,
    new_value text,
    change_reason text,
    id_user integer,
    archive boolean
);


ALTER TABLE public.social_monitoring OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 27068)
-- Name: social_monitoring_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.social_monitoring ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.social_monitoring_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 249 (class 1259 OID 27069)
-- Name: social_passport; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.social_passport (
    id_passport integer NOT NULL,
    id_children integer,
    id_education integer,
    id_health_group integer,
    id_family_situation integer,
    having_a_disability text,
    date_create text,
    archive boolean
);


ALTER TABLE public.social_passport OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 27073)
-- Name: social_passport_id_passport_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.social_passport ALTER COLUMN id_passport ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.social_passport_id_passport_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 251 (class 1259 OID 27074)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id integer NOT NULL,
    status_name text
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 27080)
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.status ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 262 (class 1259 OID 27972)
-- Name: template; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.template (
    id integer NOT NULL,
    template_name character varying NOT NULL,
    file_path character varying NOT NULL,
    created_at character varying,
    updated_at character varying
);


ALTER TABLE public.template OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 27983)
-- Name: template_field; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.template_field (
    id integer NOT NULL,
    template_id integer,
    placeholder character varying,
    label character varying,
    is_required boolean,
    default_value character varying
);


ALTER TABLE public.template_field OWNER TO postgres;

--
-- TOC entry 263 (class 1259 OID 27982)
-- Name: template_field_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.template_field ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.template_field_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 261 (class 1259 OID 27971)
-- Name: template_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.template ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.template_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 253 (class 1259 OID 27081)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    login character varying,
    password_hash text,
    id_employee integer
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 27087)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 255 (class 1259 OID 27088)
-- Name: waiting_list_for_housing; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.waiting_list_for_housing (
    id integer NOT NULL,
    id_children integer,
    number_in_the_queue character varying,
    date_added text,
    expected_date_of_issue text,
    current_step character varying,
    archive boolean
);


ALTER TABLE public.waiting_list_for_housing OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 27094)
-- Name: waiting_list_for_housing_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.waiting_list_for_housing ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.waiting_list_for_housing_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3453 (class 2604 OID 28050)
-- Name: security_access_log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.security_access_log ALTER COLUMN id SET DEFAULT nextval('public.security_access_log_id_seq'::regclass);


--
-- TOC entry 3693 (class 0 OID 26966)
-- Dependencies: 219
-- Data for Name: children; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (11, 'G5g5XHrqFlN9RbSmrza5nen/o+Tri6BqYBtcf1B4X32D/xEJlwmXLCLA', 'uQ+BN5KiwIdK9fvQejGbFATuN3eUBSM/rzSgKL9gwrVBuXqaCV9tvxIZ', '8hEB0oqGZJIBwkG/GBBKB6H5/eKevPt8UqFdWmrBqiYIoFI0oQi0F+QJx1g=', '9TQQn4HPezGMf1NGkA90UACBIYajXyqGKaau80lUSsu/beYrHyo=', 3, 'd6v8KLEPjIzvFcCOmP4YYThQ/THtgzLeMvA5yHp0nqwfJ6DQ74SnNotN', '5cP/ElysNNpAi213w7fO3eWk5EPAnxyJ7keGBwmrdYMeNw==', '5PJQXvz1nNfh59oeGsCWrRzVPca9contHBggHoHcxZY=', 15, 8, false);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (12, 'J9jnPWL6duUG1u++jI7HRK2DsgOemPKf8mS0ovA69ZsMYtjiVKLCLQLEGUj7CA==', '99uPbHeXFu04rYJNA8r/X0ga3vk9Y+fLEQHBdZiW4/kmwOq3', 'vFuJhQqCkvTk11YA4Asf3wWOfHmWh8NKBDeRC3/tb8+xcF0tSM3bSiGkwcE=', 'o8NI1SowedyS9lTW4+gXIh9b8rxaDZeR15KfoUu90xS/1jHyK2c=', 4, 'xk/xxpanQcB77GhaDrzqPxIQV2RUSL8pa3pwZln3VXxTwrW6GO3AC/NV', 'h0YggSezNaUbIQB0u4eJtVykVhdtthxLrTT0yUjy1UPElA==', 'mpV1wPdV8fqfPePpcUq+8Qqf8q81xdAQ8eQkIPVnL2A=', 12, 10, false);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (13, 'zxHIjfssSBrSPxnSrpQrjunxbLtQ+MiFXvvbPBHBEmdDf7ZjX8lxrIr4tt4=', 'iRDywlOPKzez3433rr1AqdDwxAzPhf41x2ll5iNXQIqZRJMIlvEyY5Pd', 'r4S5Go5fnqb+j2HoDAleSF9ziUTsPLtfHnZM2iNaM2c3bePPa1WRWXV0KYAk+w==', 'sOF3iv3qA2m9Hch9g27zZEWnO1JEaP6HV21wXDRvLUh9v5VpR8Q=', 3, 'hhgwuyzT+i9WKvXgDkjwAULW7dyx/93MXCGV0KSu2WeVE2Sb2+DLKFxq', 'fzKiqRTO3aef+60hOvdgWGy4ZnM9AacAW91OWacQ1g5/pw==', 'WauFzaSXn3ujHa2YVdWmAygJKHyS0XynKSY47hFBvt4=', 16, 9, false);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (15, 'yGeYLeFlkOv+IiGjb64AJLUaMifFAazuiaqJ/8QnhQyghBXUCbPEA45lVgU=', 'YI4bB6qyoZd4lOT6HZfBsipOVLSy/LHzgX9JRekHO3tfZucm7Ygpx9CChGXw1g==', 'IkUaYalBKKPSZAitHzABTcy0T0DtKdJebYOSo2bjs1gLgf5REhLdlV5ZZ+Y=', '0RJtfZyAjWpqSlPceLwA23y6AQfYeQhQu8rSV7xMf38MiOIHURc=', 4, '5NqVcKJBk5V/LqpmuivjHogy5ZpQyLNla/Vbn/+1sc3r6VZPC/jw8iS8', 'E+S37F3Q4F0mDvO0nVb3KgutzGooX2R/u/arALBViTU4Lw==', '9kyZ7jWu+ih0bk3N0P5HXANy0Nha5rjSKYnBFz770HI=', 14, 10, false);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (16, 'EhKOXPgyRJNYLDRV3eK0iPKGCX19pobX1t69VmaOEadPIsrvl7nlpQ==', 'dMRKn6Icc6TmFdxYIPAIz4sWlBQrU0HvI4+cl+ukCTYXSo9l+gs=', 'P5AHp/p3qCDdoMSztcZfDM82p+bBLDcnbv5z26s/mpSCfl1NcS/rD6tYLN0=', 'wWxw8BqoCLzoQrXmueANTcI5frVKY1psMAjxn23yZWodGkwzMB8=', 3, 'Obx8EOmmVFRxr6Go/Gn7zy3P+OVTIr1aOTxMnHTBZgmHbGx/4+XuA4rt', 'rszvuo0i74bjqWjIzXyvWeacY7mYafUjmfQBimOGfziHbg==', 'JACVEdyKu29yAvXA5DxN8O4I8L3ZBp8GwRi+jPIf6OU=', 16, 8, false);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (18, 's/JUQhxDM8KyCcuLCC4Pg1AqC3vXdVwiQ51RtX27pkduVAaZDkGhjQ==', '7/JC53l67flntxVlXSLh8sZMV+HG0n3xHoU99hl9Cie68XD21my1+w==', 'YEgT7AWtkDJmOjwYwhPedXtMtOzpRmSBF3eRH4ysGwgAaI2iVAfjibaVMtmJDw==', 'N0AiWjHMwYXsEMb++FaMZR7gmZ1t88TOrgoVi/vELAz4M3aLpKk=', 3, 'StRTQbiPQmGK+Cy3pOEnoZUixc364rYI+del7sS4UAg6ZQywFX8C3Zkk', '5fDT+pQfj4ADa6WzPdSUoD3Z90+588qfCAkQgcK0VpiaBA==', 'DY2X9q0S48BtgaGyToNeSBUtfTiAyCcsdUBg/PBeHbw=', 14, 14, false);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (19, '+4wpRIQ9F1qImq5LITsUlnAwHdyrNGl9Pohej6KaLoSRFWWZtvVH48fQFD4=', 'r2Al6Olu2PYPuhFmVGDWACwcH6vWzrDvzFo6ic4n0X+nA1f2oa4=', 'PowTZBP5lw1QRSX1C43WuBKCrnq9wiaPz22xWsx1oUJwviWSKPKvKc0qXnRhhgPM', 'kZj0DYG0t0u2MluLFkPJN1PxTRkjGdKavPUNbTy1uYPEN36suGU=', 4, 'GEBXVSh4kkeb8IlrfCuEW2o+zcgxY7zhZccUrxcekwVGXChZDDwI3ccF', 'TNN/qWMzDvairz9wZR1KUZHlH8WoV4QEbTaQwLeVqh5/Sg==', 'q9FS27ReB7eWkGvRA1/gce8PAjdlSlClm2X31Qn2xKk=', 9, 8, true);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (17, 'Yb+9P+G5YuTB7d+kV8IX9ovVp8L3VLF3UKreHO+39MtTDG8QhHZqeOXxSpA=', '2Ei13HLX3CLLwSiK3sEdA7ezuI9yhKOMYmCFqkY2ilcp817loQ0=', 'WAmiF07dlTc/0LlDAzw1wL5PH9+gxVWLgFfD9rZNZzeO2gJsTk8f8S1y/cAx481U6stgqA==', 'Ji9dkOkYnhSBt1CRKFlXkS+PiiW23F/xrmRCvPB+Qgwi/InzgaE=', 4, 'RBCXYX41xu+gaB/wv/AUqgLL3MrTEHk2KC3IA8KJJbnMEhDhKUJBmjCR', 'j22AnOmQNps8QYTeZ9T1YXuD52ITCz7wSsUXadz+z5aMZw==', 'xtIZw0h+xBJ0wed50OoOlVwHaxk0fnVR98o70wqRVXA=', 11, 9, true);
INSERT INTO public.children OVERRIDING SYSTEM VALUE VALUES (14, '7G6h6I9A6mEo47pu6NOFxBzfqXsiHEKqvR42DQIVhhXL1uB7Q4tTHf9L', 'LoNlgU69wa8U/8t8JtQLdb4XS0GYGpeE3l5KVJ39YSaJ/6W8ZLs=', 'hM/2l9csUwt1yrP8KcdkokPWd0htB6iBOmKehtGhSs3CV5cBmac68T7VoXERMZGzWdF5fTf9', 'U0coT3YpQs5QsWS0kMEn5AeCO67UasfNV6t8gnzOsZ/XzXjXoFw=', 4, 'Wzdxnw/WSzYfaNnh0b0HFoKCd85tgRa10YhNzpdKhdU83RcQdxPRKjD+', '87Gq2yh//jsU18fVCZ7zEV0cjndaEaROBNbvDRnxLD3tEw==', 'XPFmiamAxUTWqUNRZDZYNIxuMsJXf1iN8Ttvma96j3U=', 10, 8, true);


--
-- TOC entry 3695 (class 0 OID 26973)
-- Dependencies: 221
-- Data for Name: children_interaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (22, 11, 15);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (23, 12, 16);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (24, 13, 17);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (25, 14, 18);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (26, 15, 19);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (27, 16, 20);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (28, 17, 21);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (29, 18, 22);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (30, 19, 23);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (31, 11, 24);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (32, 16, 27);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (38, 12, 33);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (39, 13, 34);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (40, 18, 35);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (41, 18, 36);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (42, 17, 37);
INSERT INTO public.children_interaction OVERRIDING SYSTEM VALUE VALUES (43, 14, 38);


--
-- TOC entry 3740 (class 0 OID 27997)
-- Dependencies: 266
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.document OVERRIDING SYSTEM VALUE VALUES (12, 18, 22, '2026-05-14T20:01:12.518960455', false, 'Приказ о зачислении ВИА №2300-1');
INSERT INTO public.document OVERRIDING SYSTEM VALUE VALUES (13, 19, 22, '2026-05-15T16:50:08.352002269', false, 'Докумнет');
INSERT INTO public.document OVERRIDING SYSTEM VALUE VALUES (15, 21, 22, '2026-05-15T17:10:21.224240686', false, 'Документтт');
INSERT INTO public.document OVERRIDING SYSTEM VALUE VALUES (16, 22, 22, '2026-05-30T01:51:37.154099657', false, 'имя');
INSERT INTO public.document OVERRIDING SYSTEM VALUE VALUES (17, 24, 12, '2026-06-19T00:05:14.068817025', false, 'Анкета №4040-10-2 В17');
INSERT INTO public.document OVERRIDING SYSTEM VALUE VALUES (18, 25, 12, '2026-06-19T00:08:22.419360011', false, 'Анкета 7070-12 В17');


--
-- TOC entry 3742 (class 0 OID 28008)
-- Dependencies: 268
-- Data for Name: document_field_values; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (19, 12, 110, 'Петрухин Алексей Иванович');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (20, 12, 111, '1120');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (21, 12, 112, '123456');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (22, 12, 113, '123-451-541 82');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (23, 12, 114, '');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (24, 12, 115, '05.06.2001');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (25, 13, 116, 'Дмитрий Апельсин');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (26, 13, 117, '1103');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (27, 13, 118, '111000');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (28, 13, 119, '213');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (29, 13, 120, '123');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (30, 13, 121, '12.12.2012');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (37, 15, 128, 'имя');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (38, 15, 129, '123');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (39, 15, 130, '1234');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (40, 15, 131, '1234');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (41, 15, 132, '');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (42, 15, 133, '');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (43, 16, 134, 'ляояояо');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (44, 16, 135, '');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (45, 17, 138, 'Вадим');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (46, 17, 139, '17');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (47, 18, 140, 'Вадим');
INSERT INTO public.document_field_values OVERRIDING SYSTEM VALUE VALUES (48, 18, 141, '17');


--
-- TOC entry 3697 (class 0 OID 26978)
-- Dependencies: 223
-- Data for Name: education_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (9, 'dfke2MDscpozK+9hzwgPdT7HNYY+YS7im3SPjB770UD7zR0=', 11);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (10, 'kD8M+F36MO7tXyQzS0IjsYu8gU9Q7tTO9lOZ1nfHur2pCys=', 12);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (11, 'SFwNAB/ZhxKd66kx6RBwfF1Z9218cbIVqlfx7htFmab1OJ0=', 13);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (12, 'IAwSr4CYc/HE347jbGeI4WhWzz88gQ/xg0vPwFRMR73RcPI=', 13);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (13, 'RjAN75Yk+3bMfdBWlXuVFDt2KbRuUlyMnaAZo4EwOHCUL94=', 14);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (14, 'pulh+x1VOTa5mpyTSfjfa+4J6OjnKaHIO9Dp9frjjk/SvFk=', 15);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (15, 'ZFP/NadHeYg4tvq6b/TpSuiAJpXxJ32MMNsgHtul1lOTODG2/g==', 11);
INSERT INTO public.education_group OVERRIDING SYSTEM VALUE VALUES (16, 'tNag1Pj1xx/e4kWCe8AA5nsk9B7i6jTBmo1X+31D15JRboLrgg==', 15);


--
-- TOC entry 3699 (class 0 OID 26985)
-- Dependencies: 225
-- Data for Name: education_level; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.education_level OVERRIDING SYSTEM VALUE VALUES (6, 'aTxuvFxo/MEeLb+rzkmWvl11EZsFJIbL1TvljCuxKZE/3bmAzt6caXqrtP+OIPoGrlMP0zu6eQ==');
INSERT INTO public.education_level OVERRIDING SYSTEM VALUE VALUES (7, '0sovNuL3KHvROnQfjXmvYLz8NIQpJwSyPNe4HbbdVwzkNrawv/wJ9cAXSIpBA45xu9XdlPc=');
INSERT INTO public.education_level OVERRIDING SYSTEM VALUE VALUES (8, 'hqyxIAtljJiEn1h2qncqGwQfBIV1SmJTAjN9BrPBC2zveAJkGwnFj9eiVk3CMoY+QDrTh6APPAaXSlQIm6EsFVJfTzp6DpkRviT3');
INSERT INTO public.education_level OVERRIDING SYSTEM VALUE VALUES (9, 'TCe7923uC8iZVNxxjNQjTkwq5H0fE+PzchDzW4Dkaux0ET3xrlJlvLxvxbbFlcFX2AUfV/6t6gQV2dAdBKdm');
INSERT INTO public.education_level OVERRIDING SYSTEM VALUE VALUES (10, '1j2WdZetWDn3PfPB9RUBVpAB4sVooAZjM3jaTtBqDVKk6b9eQyVPZT7KRws8JqqcvyaMI1fW3r5dta2tUpUtrrMxyp8BjQoGBVZrbp0=');


--
-- TOC entry 3701 (class 0 OID 26992)
-- Dependencies: 227
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (13, 'NL5d/xTJDEvbipXXBMrayUUEJRu4e2/2QSKeazqBrHw60b2VhTLlNXa/71A=', '47ecf0H1ezDedNtXesiPVK4qAbIpF/bmYuafQ66sYfhJeKmn', 'fWYcy3EQauvLkrqO+nIdnFV3SycgeWiovEIpR3PlWDAqp6PKRt2vrpranTDSAGEpK2E5aQ==', 'reb7s79PFY7jbncBSGABlYtsxWQgI5AzmWQrbYqGeJ+58lLH7yzAtvWdrnOXIA==', 'p5lQbmcfWna8rWL+pijUReAY3qkzIoazLMWcFh6Tk080CFM1aacIWpDL+fk=', 8);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (14, 'I30WeuG/J6qBpVWywls3lDRY3g6JLDGPzD0XiJsIoZT2pO/IDdGCfQ==', 'vhWRjM/7xu1LLlbK9PuFpcB50Qj6uRsZqktwsVih6pzMHHKYRmCVre8j', 'mk99hZQw0TO12Ee/bT2jyDl2yJC06yZM3EfnR/sPeDoCu22pgwaUbgIdWRA=', 'mziF31+u1Y51Q7ddenVf+iQjZ5qWXfM2uAYU1MJMhzEIPZ6z9360IgTdBls=', '/L3EeZVMflBCHYCugu2peEcUz4A1fF3IbHmhrWQQNHZA5tikkVwK9b3o76Y=', 9);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (15, '+VwATf2JPn1KkYvipDua9I9DGPCN5nkhq8nXGDFUw2HTydABO8ABCyRIJtg=', 'AaqjKsF8vz7759fKAITyyMhs6ZF9Str+TQPqfiHenetViUqBCu8=', 'FsSC20LcFCZ0q/d/XOQN6bh0C3RYHUcLZ3baP/ZXmyng05WX79LjvT7dHeTg9g==', 'dhS395KLOlD+9oyeBeokIKGzDlTt72pVL7qtDmLX3nKiQYQ9Y1n8m+jSfWQuoA==', 'zB92yNziIo0I51QMSXO9QOoD+OXBRdmuYkNGxHzePxVeDBMgpzYhCPH5EBU=', 10);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (16, 'A5X3BjyiipiTp66AKW6XljSmiNLTrIGdt8BZ+Jn+GoXmdpyD4opwt6F4', 'tJmpiUcTg3lxZZ20kSRoB/0zc9zrtZSeTcBuakzojTmZTk/xpqlSRg==', 'gfkiKu3Ib4v9Xxwi3qkQZyKP5eiYiSuh7M9kbU6wC3mc7ym4z5P/nbokbY8=', 'kPc7zlqmR1cCmCcLnq4IOwttxPcT4Xpu9WatkhC8IQorZAFamFgcGHNEhOUp', '7zeOxK+NTBpv0JqnuTHb/Faw3UB5IyZrZDUqisqXpgbekuryUZ7/63wRYlE=', 10);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (17, '+hqOe84bH19DKd19w4xdzRkJUlsxLdwvkw2bt8udH/ZB0ab9ppwC4z5f', 'YKOI56Er/12INqRcZLIYTx00RZp3glvkNPjbLfSWwFy6JLj2nDI=', 'yE+OlaxR2OQW0UpVz9ZGR1qBAn7YfcBzf4naIFXPMQHE8/sC//QeTKv5AWfFIpxS', 'l24Vw6OCodpCuKlqJ/9NXjwCNainrqo6P3jRlkk+LVhOHoML8qAApqwk3aG1', '/DtuIT7U0/sP9ljYxi1EdoBV/aSpmR9oKKEvJSMGku3KGVEYMnts4iVPXJc=', 9);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (18, 'la/0exRaEDFxkAorQ7vmr+SOXP3x+qEyxSSWpMr9hNgpUDsIm7eL1sH5', 'z41F+YjrqvsgG35Oqgd3euGKbjDPDyR7kU10wSnBcc/edpYzLsY=', 'KJpeQZxdPFrehx0+2dgAde2Nq3L7aVBrkgPfVnAbCiphfA5Wys68N4oBcsPAHg==', 'z0tw54/QUWNEe+F5f8uZ4Bj0jtribxYeeH93mJugVgUlwyc1Z8AyaCSzc6FG', 'VroIWNiIx8Q8u2l+I3K0SLDATFHpI6KFFNfos92Ttzy0lCDQ2ZSNgFRoSdk=', 6);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (19, 'BpxoryR+81+GG42lOfp/u9pXf55Z93WNeU4rPFDmlIFM3OCpGSu+4221zHo=', 'ETtuTg23XOCVFrOM+hiVlT106ImxDpdg/Lg4FIT2weARUOMaDKvKQMkM', 'yFtbKw+o5bg5l+OTmo95NMcfAPkhLLD0yinMn3JNyVQzWbkfYcDNDSe9IJelI4uQ', 'TzjCJBesjhfoTSXp94sx3jebQ6v15TdjUJhVJWcbifggNT1buZ55xbrkLGhMKA==', 'G4GrIGVHb0SczQj2OzIcWmLyBnr6OYmz5VXrnG96/oeZh2ACjfOGx7UuEBk=', 7);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (12, '2qc41zWogrlGNjYAKnNxOKfWqmbVN8+D7RFv9CZZCJLlyAEdTVIJuw==', 'sEEwywZRpCHmfWP+vJks8/kInMxpVRxGd5NpwPLZXnfhle+QNjSnAQ==', 'gxBsV7UE1QQOjigc5RRFnyIoUKs2Lgoy4P8az2d9JW1JKINvvl/UG0Dy8xjaAdQ52aQ2GKgR', '3Y7wKkMWnE3yvCaxWZPX4J55CW+ecb+Z0kW8wChh1zJmmnlW/b6Xp9FAG5c=', 'ec/sYBkbz8Vr8Opb7FjDwLkbJqglMeTyINZnCX5d4cKI2ZbzflXgD4A/O3E=', 8);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (11, 'z7BSbm71zqp4XcSp9iwHtXrxbxzFaq4x4Zsq3LWHZY5Eiiketb3fXxFE', '5SiURAY4cye0R/miGpOuOwaQHpPcMhx9OshDcNvtJkUxXWcXimU=', '8O0Z3VqmHh3cOKkN0dAN6+y8rLxUa5UrrpgPt8RUIJHkjisn/5gWTjOuuvY=', '+T2A67odT+3jHB09F9wVoUJt14jt8qW5iJyUIqJj0f/Gb/6QLfTMbaxn3Qaz', '+7WhTkgTmy9ndaQ+J+ZYaN36MfCs3ydFH8JMhaJqKI7S/TqXi8oG7UOXSKU=', 11);
INSERT INTO public.employee OVERRIDING SYSTEM VALUE VALUES (20, 'mCe1+tZoaMlB+l6jDazf5G3BP+xBIklQxOMl4tuUGEXL8C+m', '99EsC/Ke6GXRLF5wjAn/QodzLyPYOuIjUk4WHq/zl70I0jgYZr8=', 'v+Jq5btzNDBvV6QFwbf+3Bt/GiBzQBUz8A4kelTlhiN2bJ8PtqxNswY+bq8620vP', 'GwoJdeBSOO7lsjUTBp7+F8NLubh78Ihq+HI2u9fOk7hnf1eWq6vdt9EWdA==', '6c4c1wL8HiHNDT6RgsPTP6ySPCD4rY5/XL5u78En5wcUHyNo7FCXZcM18Zw=', 6);


--
-- TOC entry 3703 (class 0 OID 26999)
-- Dependencies: 229
-- Data for Name: family_situation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (11, 'yVJrklSLpJ+3j+3I6ckaP3T9Y9ScL+Q+555NgjFHmPTZRldxMpTJnzo9FfHF3w6ccfa/lncYYF828PbENEZe5nsygbV4gt3m');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (12, '8aqiP6DMvHfPZWr5TbtYChSTj6uz+nQwRBOk4a8q0wMPfsL6URwvsPxtiaf2gkYdZopSFv8/Ju+zTSeBQqjbt0GnZ0tJsB0T');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (13, 'bkO50CFdl4HPEzUTJsg5/s3L/unJtiGJqmEcu/GVfmN6ZVAAYek9q6kwPxQCEJoCQZrV7eQwrJ7XM6Y85k6v3d8Dd67kV4aL+yyB3G6YjzRRCWli8dp7ydNHZw==');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (14, 'Mn5eH4mp0rJqSCw5kycWe8N6NAF4+aU5BIzyMI2ezCclkkcCN4FYSjJ6wQkL2vbsN9bPUxt3NO/DG/oC5CzkI69aGsDxGXHlbSLCuC4mvUdzcq5Zjzio4T2Mmece2tsGLUxUwt0YVUF/Tg==');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (15, 'o5KaahZmdq8TIGlUbZjesypqKZI/HUXhD3ea7z//AYLreyGqCeGZgRVmEIv5ltmGUjuxODMbvMKY8zXWsGgaRtjolgp8FBc6pzghMWsPAlopebbkB9LSXyN5F0idqzmmnfR1Lmc=');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (16, 'i3fg4Qu1gr7nrz+7FJjRpyg60djVO/tCZjuygbjTQSwarWcDffJIGoie3vwJjev/TGv9701i+OWJd4pacxU0z54=');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (17, 'mJsis2UsjVfCZOT/HG5SMsAFgWTp9I1LoI6CnhFO64k2KsgGQUrED1eUPm3pOdaroKjRS24cNI6Wl4e2XEUmQFO3Y1DpbFWyIJhd08+r9GLYymAf');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (18, 'ybZCeS8iFxmAe2Xvs6nuaWlzyH0CPsjHeTbc1A6E8BMs/w8XrUquXYtrMXC8PiuFb3uNBIl20UOZbj94NJEKtw==');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (19, 'gSqxi9ksTKSmySTnxUwtMpkINCKRIy7hUdGEhLWWX8SkcjCGxNhP7Qv0WBGxKj8wBMteuT+1f5e79zx40UFsdvYd');
INSERT INTO public.family_situation OVERRIDING SYSTEM VALUE VALUES (20, 'wiTf0OjDnQuIcy1Th+QRJGBwBh9p3Vbltz+cCyuyPZjDh+4kxwR9m0XR1hxItQY76bbwK5PTnA==');


--
-- TOC entry 3705 (class 0 OID 27006)
-- Dependencies: 231
-- Data for Name: gender; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.gender OVERRIDING SYSTEM VALUE VALUES (3, 's2jurRmyzSBuGoyC6WIWntMeVN1IEQpIewEuh0G7VrVxgZ1qU7WbYW9A');
INSERT INTO public.gender OVERRIDING SYSTEM VALUE VALUES (4, 'S1KZ8Rqh9+H/qEqmYVLbaCwvQzjTUbKxzPKp42SZ7ipXVeyT4W8aEeZ6');


--
-- TOC entry 3707 (class 0 OID 27013)
-- Dependencies: 233
-- Data for Name: health_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.health_group OVERRIDING SYSTEM VALUE VALUES (6, '5kCy6+huIzwBC7mFb9DKrtihE786AYcY65Op1XawJG+56YfXv8k8PVGAOq3CecO0Wk5el87lWNbCcD4j2w==');
INSERT INTO public.health_group OVERRIDING SYSTEM VALUE VALUES (7, 'RfVCOscTrk2cSPKx5XyQfLT7SYmA7OnEzhYKC96MPhxyWhhRJMpzP17UGDaS+6i/msYkn2AhpMcu3eEYemZ8t/myEul981zhOPA6Nq5oQzShbl3/KI/mzUeHIidEdAoNjbnMTvBmPX4=');
INSERT INTO public.health_group OVERRIDING SYSTEM VALUE VALUES (8, 'ku01drcOoViHKN/9yY369VqwzEgOUpdzwV5d3J40QPX+MNBj/5nMaqE519He9FaVrcsMdXZwhE2FueTZx/d/43Mbn7//p3HnuyOlqfDynV4Jll51eYQQC4+GKqig468Xj844qgg=');
INSERT INTO public.health_group OVERRIDING SYSTEM VALUE VALUES (9, '/icdtowjkVMCrZcH/g/i+EPk/a53m2/z0SqH2bRvCl9IzLw5jvUrqPQ3YhLB0YaYGhZonVGDvkGMfPR4Wby6aTAFX8tjKg7Q1HZywZa7hEm0zqdCnPeiuGoS8nX6cR6tvXjnzjgnnuVaC59bOR/gHJpqOAYRj7+EUI1KcBJUMnWzcgY=');
INSERT INTO public.health_group OVERRIDING SYSTEM VALUE VALUES (10, 'hF1CmVAdqCPQhqQAUPkk7rmznNTPOU0nlZ3Lj0g4ic30NoOSZWK4k+w+XBNQC+5l5RN/+l73U1n2ndMwC0LEfxXsIuKAQA==');


--
-- TOC entry 3709 (class 0 OID 27020)
-- Dependencies: 235
-- Data for Name: housing_rights; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (11, 11, 'Нет', 1, '', NULL, NULL, NULL, false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (12, 12, 'Да', 2, '15.02.2023', '+3G6rCfgpJctCsOkIclH1Shsr9mTPgsXOiLJ53u1Ru6OPXD1ytYThA==', 's9utQNFopR6ZSeRUHgHR0H83cuITQtW2z8ATwDN8MyM9PKyFi4/oKFVHifT+gQ==', 'Vz0QBRveoF2iNOseBanKZW8WeaMCneqHP7Qjan45uCmsU27582UeMEYcphs=', false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (13, 13, 'Нет', 1, '', NULL, NULL, NULL, false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (15, 16, 'Нет', 1, '', NULL, NULL, NULL, false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (16, 17, 'Нет', 1, '', NULL, NULL, NULL, false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (17, 15, 'Да', 3, '12.09.2022', 'Idn1qosjIw1SAL6Qkf2DFMwWd/49LiAf7CuCFAxv9jLoQc/uC8o8tA==', '82jYNXfeg0w09V4e1Q7/MCeIvpTB4+vLWOaials82QCOrMwvaKFQPmz0', '6hYetTZdnm6MyWSBWxS+rwPv5LS5jPWMh9BFzrbCQncv4B/HCv9vCscUQDM=', false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (18, 18, 'Да', 2, '01.04.2023', '/hhJ0iPmPXQwgIyTkl24DuWkBstc6irxSvNN+pPdeP5aOv+CP1bVQg==', 'WyuSb4rczRmDBG+sHR5eqYmtjb/l1ZpMG0LyKNr7GWVHCrYE6nEmL4wAIgd6pun+3aY=', 'NSGV0aGBb7jrsAJ75ADUfDOZT5+XEAw96g46RYJGHQe9o6CPPuRL8NZw', false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (19, 19, 'Да', 2, '05.10.2022', 'Q+zvf2EQ8piEYfRHWOAN6ifpK4cxOTygJ/75RmPffuh43xMnmQikpA==', '7U/rYez5HjFOaCElPB3zPHka8skR2cqNvF/HvetHypQ6qV4UgF1GmROKjtfTkQ==', 'LGkNjWJI7/BlynkOGQYknA0e6Oz8MzFtQ6rosbdiU20bbA0326K8bcW3ZV4=', false);
INSERT INTO public.housing_rights OVERRIDING SYSTEM VALUE VALUES (14, 14, 'Нет', 1, '', NULL, NULL, NULL, true);


--
-- TOC entry 3711 (class 0 OID 27027)
-- Dependencies: 237
-- Data for Name: interaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (15, 11, 12, '20.06.2023', 1, 'Пройдена диспансеризация', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (16, 12, 13, '05.07.2023', 2, 'Обсуждение успеваемости', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (17, 13, 14, '25.05.2023', 3, 'Оказана психологическая помощь', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (18, 14, 15, '15.08.2023', 4, 'Условия проживания удовлетворительные', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (19, 15, 16, '20.04.2023', 5, 'Пройден курс реабилитации', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (20, 16, 17, '05.09.2023', 6, 'Составлен план обучения', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (21, 17, 18, '30.03.2023', 7, 'Выбрана профессия', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (22, 18, 19, '10.10.2023', 8, 'Пройдено психологическое тестирование', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (23, 19, 20, '01.03.2023', 9, 'Получен паспорт', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (27, 16, 22, '07.04.2026', 9, 'Оформление документов о постановки под опеку', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (24, 20, 21, '20.11.2023', 10, 'Проведена беседа с ребенком', true);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (32, 12, 22, '13.02.2333', 3, '', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (33, 13, 22, '13.08.2026', 3, 'Проведена консультация', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (34, 17, 22, '12.02.2024', 5, 'Проведено лечение', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (35, 13, 22, '13.02.2027', 9, 'Выдача документов об обучающемся', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (36, 16, 12, '10.12.2025', 9, 'Выдача документов', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (37, 12, 12, '12.10.2024', 6, 'Встреча с учителем', false);
INSERT INTO public.interaction OVERRIDING SYSTEM VALUE VALUES (38, 16, 12, '10.12.2024', 9, 'Выдача документов о ребенке', false);


--
-- TOC entry 3734 (class 0 OID 27430)
-- Dependencies: 260
-- Data for Name: interaction_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (1, 'Медицинский осмотр');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (2, 'Родительское собрание');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (3, 'Консультация');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (4, 'Проверка условий');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (5, 'Лечение');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (6, 'Встреча с учителем');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (7, 'Профориентация');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (8, 'Диагностика');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (9, 'Выдача документов');
INSERT INTO public.interaction_type OVERRIDING SYSTEM VALUE VALUES (10, 'Посещение');


--
-- TOC entry 3713 (class 0 OID 27034)
-- Dependencies: 239
-- Data for Name: monitoring_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (11, 'Изменение места жительства');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (12, 'Изменение состояния здоровья');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (13, 'Изменение успеваемости');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (14, 'Смена образовательного учреждения');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (15, 'Изменение семейного статуса');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (16, 'Трудоустройство');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (17, 'Получение жилья');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (18, 'Изменение группы здоровья');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (19, 'Назначение опекуна');
INSERT INTO public.monitoring_type OVERRIDING SYSTEM VALUE VALUES (20, 'Выпуск из учреждения');


--
-- TOC entry 3715 (class 0 OID 27041)
-- Dependencies: 241
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (11, 'ГБУЗ Городская больница №5', 'Нижний Новгород', 'ул. Ленина', '25', '+7(495)123-45-67', 'hospital5@mail.ru', 5);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (12, 'МБОУ Средняя школа №12', 'Нижний Новгород', 'пр. Мира', '45', '+7(495)234-56-78', 'school12@edu.ru', 6);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (13, 'Центр социальной помощи семье', 'Сергач', 'ул. Гагарина', '10', '+7(495)345-67-89', 'csps@mail.ru', 7);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (14, 'Детская поликлиника №3', 'Нижний Новгород', 'ул. Кирова', '8', '+7(495)456-78-90', 'poliklinika3@mail.ru', 5);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (15, 'Гимназия №1', 'Воротынец', 'ул. Пушкина', '15', '+7(495)567-89-01', 'gymnasia1@edu.ru', 6);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (16, 'Отдел опеки и попечительства', 'Сергач', 'ул. Советская', '1', '+7(495)678-90-12', 'opeka@adm.ru', 8);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (17, 'Центр занятости населения', 'Княгинино', 'ул. Труда', '30', '+7(495)789-01-23', 'czn@mail.ru', 8);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (18, 'Психолого-педагогический центр', 'Лысково', 'ул. Детская', '5', '+7(495)890-12-34', 'ppc@mail.ru', 7);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (19, 'Специализированная школа-интернат', 'Воротынец', 'ул. Школьная', '2', '+7(495)901-23-45', 'internat@mail.ru', 6);
INSERT INTO public.organization OVERRIDING SYSTEM VALUE VALUES (20, 'Детский дом №2', 'Нижний Новгород', 'ул. Сиротская', '7', '+7(495)012-34-56', 'detdom2@mail.ru', 7);


--
-- TOC entry 3717 (class 0 OID 27048)
-- Dependencies: 243
-- Data for Name: organization_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization_type OVERRIDING SYSTEM VALUE VALUES (5, 'Медицинское учреждение');
INSERT INTO public.organization_type OVERRIDING SYSTEM VALUE VALUES (6, 'Образовательное учреждение');
INSERT INTO public.organization_type OVERRIDING SYSTEM VALUE VALUES (7, 'Социальное учреждение');
INSERT INTO public.organization_type OVERRIDING SYSTEM VALUE VALUES (8, 'Государственный орган');


--
-- TOC entry 3732 (class 0 OID 27291)
-- Dependencies: 258
-- Data for Name: ownership_form; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ownership_form OVERRIDING SYSTEM VALUE VALUES (1, 'Отсутствует');
INSERT INTO public.ownership_form OVERRIDING SYSTEM VALUE VALUES (2, 'Собственность');
INSERT INTO public.ownership_form OVERRIDING SYSTEM VALUE VALUES (3, 'Социальный наем');


--
-- TOC entry 3719 (class 0 OID 27055)
-- Dependencies: 245
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.post OVERRIDING SYSTEM VALUE VALUES (6, 'Социальный педагог');
INSERT INTO public.post OVERRIDING SYSTEM VALUE VALUES (7, 'Психолог');
INSERT INTO public.post OVERRIDING SYSTEM VALUE VALUES (8, 'Воспитатель');
INSERT INTO public.post OVERRIDING SYSTEM VALUE VALUES (9, 'Специалист по социальной работе');
INSERT INTO public.post OVERRIDING SYSTEM VALUE VALUES (10, 'Заведующий отделением');
INSERT INTO public.post OVERRIDING SYSTEM VALUE VALUES (11, 'Администратор');


--
-- TOC entry 3744 (class 0 OID 28047)
-- Dependencies: 270
-- Data for Name: security_access_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.security_access_log VALUES (1, '2026-05-14 00:44:53.84652', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (2, '2026-05-14 00:45:18.616843', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (3, '2026-05-14 00:45:33.451637', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (4, '2026-05-14 00:45:42.893666', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (5, '2026-05-14 00:45:43.115138', 'SYSTEM_STARTUP', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (6, '2026-05-14 00:46:17.132657', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (7, '2026-05-14 02:30:45.527089', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (8, '2026-05-14 02:30:52.3512', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (9, '2026-05-14 02:45:13.767916', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (10, '2026-05-14 16:35:40.907059', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (11, '2026-05-14 16:35:47.013703', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (12, '2026-05-14 17:11:10.645331', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (13, '2026-05-14 17:14:51.092778', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (14, '2026-05-14 17:14:59.530495', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (15, '2026-05-14 17:15:14.672356', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (16, '2026-05-14 17:17:11.675703', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (17, '2026-05-14 17:17:17.4208', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (18, '2026-05-14 17:17:42.338482', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (19, '2026-05-14 19:32:27.59711', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (20, '2026-05-14 19:32:34.193016', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (21, '2026-05-14 19:35:20.52213', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (22, '2026-05-14 19:36:43.29339', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (23, '2026-05-14 19:36:47.733528', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (24, '2026-05-14 20:06:25.093106', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (25, '2026-05-14 20:06:36.006058', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (26, '2026-05-14 20:06:36.015814', 'LOGIN', 'FAILURE', 'alsu', 'Ошибка: 
/home/Alsu/IdeaProjects/CSA/target/classes/com/chealown/csa/FXML/MainPage-view.fxml
');
INSERT INTO public.security_access_log VALUES (27, '2026-05-14 20:06:38.891351', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (28, '2026-05-14 20:06:38.901658', 'LOGIN', 'FAILURE', 'alsu', 'Ошибка: 
/home/Alsu/IdeaProjects/CSA/target/classes/com/chealown/csa/FXML/MainPage-view.fxml
');
INSERT INTO public.security_access_log VALUES (29, '2026-05-14 20:06:46.617547', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (30, '2026-05-14 20:06:51.324748', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (31, '2026-05-14 20:06:54.871949', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (32, '2026-05-14 20:06:58.001744', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (33, '2026-05-14 20:07:01.639766', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (34, '2026-05-14 20:07:01.655473', 'LOGIN', 'FAILURE', 'alsu', 'Ошибка: 
/home/Alsu/IdeaProjects/CSA/target/classes/com/chealown/csa/FXML/MainPage-view.fxml
');
INSERT INTO public.security_access_log VALUES (35, '2026-05-14 20:08:22.809881', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (36, '2026-05-14 20:08:26.18522', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (37, '2026-05-14 20:15:21.183883', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (38, '2026-05-14 20:15:25.576774', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (39, '2026-05-14 20:15:32.832527', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (40, '2026-05-14 20:20:22.380707', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (41, '2026-05-14 20:20:26.556917', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (42, '2026-05-14 20:20:28.060735', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (43, '2026-05-14 20:20:30.821241', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (44, '2026-05-14 20:20:33.97682', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (45, '2026-05-14 21:53:42.440417', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (46, '2026-05-15 03:28:13.482828', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (47, '2026-05-15 03:28:19.40318', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (48, '2026-05-15 03:28:24.051886', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (49, '2026-05-15 15:53:07.416657', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (50, '2026-05-15 16:31:22.949702', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (51, '2026-05-15 16:31:28.620913', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (52, '2026-05-15 16:31:34.581645', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (53, '2026-05-15 16:36:31.550914', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (54, '2026-05-15 16:36:35.044898', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (55, '2026-05-15 16:36:38.48481', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (56, '2026-05-15 16:37:31.683005', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (57, '2026-05-15 16:37:51.085476', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (58, '2026-05-15 16:37:59.449374', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (59, '2026-05-15 16:38:03.580149', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (60, '2026-05-15 16:38:17.018064', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (61, '2026-05-15 16:39:13.478413', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (62, '2026-05-15 16:39:16.509067', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (63, '2026-05-15 16:39:20.955397', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (64, '2026-05-15 16:39:33.82742', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (65, '2026-05-15 16:39:40.748516', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (66, '2026-05-15 16:39:44.419949', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (67, '2026-05-15 16:40:43.00461', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (68, '2026-05-15 16:40:53.474479', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (69, '2026-05-15 16:41:02.457363', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (70, '2026-05-15 16:41:08.278148', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (71, '2026-05-15 16:41:46.051213', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (72, '2026-05-15 16:41:51.137538', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (73, '2026-05-15 16:41:55.400933', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (74, '2026-05-15 16:41:59.727975', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (75, '2026-05-15 16:52:54.324674', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (76, '2026-05-15 16:53:22.221659', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (77, '2026-05-15 16:53:45.010992', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (78, '2026-05-15 16:53:48.479261', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (79, '2026-05-15 16:54:51.693841', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (80, '2026-05-15 16:55:08.028246', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (81, '2026-05-15 16:55:14.854436', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (82, '2026-05-15 16:55:18.922181', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (83, '2026-05-15 16:56:35.857259', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (84, '2026-05-15 16:56:46.0317', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (85, '2026-05-15 16:56:50.924218', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (86, '2026-05-15 16:56:54.579666', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (87, '2026-05-15 17:04:28.089311', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (88, '2026-05-15 17:04:36.587613', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (89, '2026-05-15 17:04:39.757196', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (90, '2026-05-15 17:13:40.759286', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (91, '2026-05-15 17:13:53.474295', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (92, '2026-05-15 17:14:09.763674', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (93, '2026-05-15 17:14:20.795886', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (94, '2026-05-15 17:29:08.06988', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (95, '2026-05-30 01:48:47.259707', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (96, '2026-05-30 01:49:01.805343', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (97, '2026-05-30 01:54:52.99373', 'LOGOUT', 'SUCCESS', 'alsu', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (98, '2026-06-10 23:48:23.450671', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (99, '2026-06-10 23:49:40.642449', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (100, '2026-06-10 23:49:47.597328', 'LOGIN', 'FAILURE', 'alsu', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (101, '2026-06-10 23:49:50.868422', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (102, '2026-06-11 00:02:15.30944', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (103, '2026-06-18 22:30:34.375432', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (104, '2026-06-18 22:30:41.796518', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (105, '2026-06-18 22:59:17.369691', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (106, '2026-06-18 22:59:59.695184', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (107, '2026-06-18 23:02:16.244261', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (108, '2026-06-18 23:02:39.154688', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (109, '2026-06-18 23:06:47.767261', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (110, '2026-06-18 23:07:11.889652', 'LOGIN', 'SUCCESS', 'alsu', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (111, '2026-06-18 23:10:25.760501', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'alsu (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (112, '2026-06-18 23:11:37.28529', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (113, '2026-06-18 23:51:07.98463', 'LOGIN', 'FAILURE', 'a', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (114, '2026-06-18 23:51:59.986523', 'LOGIN', 'FAILURE', 'petrova.an', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (115, '2026-06-18 23:52:04.021431', 'LOGIN', 'FAILURE', 'petrova.an', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (116, '2026-06-18 23:52:18.179499', 'LOGIN', 'FAILURE', 'petrova.an', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (117, '2026-06-18 23:52:22.023637', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (118, '2026-06-18 23:53:20.545494', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (119, '2026-06-18 23:53:41.714157', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (120, '2026-06-18 23:54:05.057925', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (121, '2026-06-18 23:54:29.131645', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (122, '2026-06-18 23:56:29.734266', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (123, '2026-06-18 23:56:42.539327', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (124, '2026-06-18 23:56:46.861669', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (125, '2026-06-18 23:56:56.910599', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (126, '2026-06-18 23:58:29.686988', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (127, '2026-06-18 23:58:39.90875', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (128, '2026-06-18 23:59:00.158593', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (129, '2026-06-18 23:59:10.754175', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (130, '2026-06-18 23:59:34.383614', 'LOGIN', 'SUCCESS', 'petrova.an', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (131, '2026-06-18 23:59:59.543904', 'LOGOUT', 'SUCCESS', 'petrova.an', 'Пользователь вышел из учетной записи');
INSERT INTO public.security_access_log VALUES (132, '2026-06-19 00:00:13.851765', 'LOGIN', 'SUCCESS', 'ivanova.mp', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (133, '2026-06-19 00:02:43.386118', 'SYSTEM_SHUTDOWN', 'SUCCESS', 'ivanova.mp (Иванова Мария)', 'Завершение работы приложения');
INSERT INTO public.security_access_log VALUES (134, '2026-06-19 00:02:50.006548', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (135, '2026-06-19 00:03:03.244641', 'LOGIN', 'FAILURE', 'ivanova.mp', 'Неверный логин или пароль');
INSERT INTO public.security_access_log VALUES (136, '2026-06-19 00:03:06.910747', 'LOGIN', 'SUCCESS', 'ivanova.mp', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (137, '2026-06-19 00:06:09.226244', 'SYSTEM_STARTUP', 'SUCCESS', 'UNKNOWN', 'Запуск работы приложения');
INSERT INTO public.security_access_log VALUES (138, '2026-06-19 00:06:28.44811', 'LOGIN', 'SUCCESS', 'ivanova.mp', 'Успешная авторизация');
INSERT INTO public.security_access_log VALUES (139, '2026-06-19 00:09:17.768918', 'LOGOUT', 'SUCCESS', 'ivanova.mp', 'Пользователь вышел из учетной записи');


--
-- TOC entry 3721 (class 0 OID 27062)
-- Dependencies: 247
-- Data for Name: social_monitoring; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (11, 11, '10.04.2026', 13, 'yEVTpzx4o+4yk0SRWpuHWiYvR6F8uN7Zux1MJe2jSOFH7ei+Vaes4m8yXOn5RQFTUldTlceudpTkfhVXIWY=', 'tq9558FPvauea5T9ZKqgBUUIVLKv/25MnRbr8wEbXn+MesVPrmV0nQ==', 'zhk4As0VnCoV9vf0Hd50degj2OoNQR+Fo5ketB56lf3EPw4dTEDDV4zBqc3mwE/vmIjOIkdeUjQVPMv1swcX4RfQNpuqFBg=', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (12, 12, '10.04.2026', 12, '4By+h5cXZzbJIjrTk+K7HMnw7IGo9jHHHB4/y9EKWGM5KFvXWddYW2MwkQ==', 'dojDV9SmiRcdduAPmWV7fzoLAv15jy9V4hPl5BAGQSxDziqzXpQUYDaR', 'Jn1tHZpHKKIJWPLfPh6zxpbEd55mBuqKZ5ytzg6ZpL54BDjFJjf3aAlR4rhxQSJSTO+c8JG0RBKpRMRL/Bz9', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (13, 13, '10.04.2026', 11, 'EH+ifZg0ZCmsYWLJr7d+XEYsTqvnSgv3OboI9MSq3tByVfXOk+Uk1qOSmwwswgjp1g==', '4yDdQgDNjEuxqN7MR2mJRR3k2SF7o1AXF5FtfDgqtOBVhBLlsdTMgN3jxE2fYUWw79JIUKiv4w==', 'aJ4KrxJRaoFS+OQ1biaXCZlkgj19Zk3+Mk4tQJ7xTkml6AlCjx50SK/BVWeGqFMIComyh/VmB+2uPb/J91pMD8SSSkzvft/4z/Lf', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (14, 14, '10.04.2026', 16, 'CCOs3/lf1gmRWgSTa6n4EE2q/Ay4b8ejD8vmt7o9kUnJ2j/rpcFhw1uY2J2B5YBQaA==', 'rd+y4cGJuBEAylo/JkIxjUjj62klEkI52yurq1Gxe2argaM5hggeunNK6VKvomidANiTEQ==', 'm05roSUihhabR+LJDugaOAkmBoMZXQQo+9WIBV88en+WM1Wjr/y8APVc2QBd2vHiCnyZY9VBAeg0jc4=', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (16, 17, '10.04.2026', 15, 'BNeCzPFg6MsPvFb4oLeANFLUnC9KM5rmQ/+bn1NR82qQxpJ5UNlKuJiFzqm4h0k=', 'AfBbHV6QMZo4DSAizwtg1t4RhlIfOxEc9M3ZW7fD9zNw91MBfubPJBAzurnAyIcbDhTH', 'qPygWny0DkBeauW10EXCLZ3NwUoBC3i9hwIwy5XQbN9wmYRzJ7qCsPjNqaDhboWieLhTaabe+z3lkHbLYpIfbsQ0', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (17, 18, '10.04.2026', 13, 'f5KW7HKHQRPUrwP37vxuET4V3mV+S//AMjT3vBsOFKRR8W4JGhvXVx/wBgIo+3I25GJ6+fZ9Sd7pk7/OhMYKjZoB', 'ybDD07Hq10PExjfoZ0PTji8RO39fIqJL+XGsw4QH+7xmv556ulziSMkhX/bNgZC7RqrG97x6IZsP8wVAwIg=', '/dcNSPfzi+G1dH1jVsc0O+BZ42vrfDpew8jhX4ArpDHQvF2Ii2JaLwKFbS5wNKL0yzA8DCMNDNM4DpiBTb0=', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (18, 18, '10.04.2026', 20, 'G2AKYX1GEka6k+q965nl2Rhxy28CaTXZRJ/ry9dU9q7e8b1tX/me3NQYEFFG5PqYPKTp', 'lbPUk6Ltqa8Q6ACj94yfpEWV+5a5lO7Arnibl+D0XIxJgYvtBe7LAUfQMIgVAw==', 'IXGl9kDj27C3NiyvSQG4ntwm+6fZww1xI3KJyksM628H2aq4b7XzvI8jpnH2ZLZul9jxvylm7dzdqA==', 22, false);
INSERT INTO public.social_monitoring OVERRIDING SYSTEM VALUE VALUES (15, 16, '10.04.2026', 18, 'K7xBrXud5yKkYNnZh2ZUQfG5k0dzYt1TGF376GSoLTzBJ7/IJWKV9NKOau8=', '+OowUqUl+gY3v/uXDDJKEIXmYTQwpamyHXrhurnHlax5pYDpxfnLBOIa6A==', 'kHSr7oCKu4tqNV7oRVba3/c4sn+v/h9i1zHvEg0O8sp3fbMB/AMBm3NYfxZncv0GfLjf2inVBvwzIrirRiHuv235OA+1+bFzcw==', 22, true);


--
-- TOC entry 3723 (class 0 OID 27069)
-- Dependencies: 249
-- Data for Name: social_passport; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (12, 11, 6, 10, 11, 'hzKm/T4aSfz8K69MEnWnjgA0zY/phBz9tW6zKPTYrkQdGg==', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (13, 12, 7, 9, 12, '7E3vx7fO+/D0/2ABSUHg4bMynHH+LTeHx2xvGHv0pKh1hw==', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (14, 13, 8, 8, 13, '1V+XTrZKz7t/pK7PcceuWZTfduI900txOWAfxfpci7+brw==', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (15, 14, 9, 7, 15, 'qenTD3nknvhxy5seOp7sqHXGtgGtAYRtXVADNrFkP5A0FA==', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (16, 15, 10, 6, 11, '+sFu9LggUkuNZ3hkxOCZbqtwxSgFMdqWAN/gcKQ9NiQ=', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (17, 16, 6, 7, 11, 'VMnviEkUDCx/8Rh7Gd5MWzDUEKneOs5B5DRpXm3jPBzgrQ==', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (18, 17, 8, 8, 12, 'jcg2NHZjqwuG0t9okAYXRydzSLE9UahLX/CLvHe4ciVHCw==', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (20, 18, 9, 9, 15, 'SFDYg4qgmuLKV2aSHX8Hog8gDPewBZ5U0qX+epoNVog=', '10.04.2026', false);
INSERT INTO public.social_passport OVERRIDING SYSTEM VALUE VALUES (21, 19, 7, 8, 13, 'j4JLYfZdMhTKAC/GRnoFnYRxROqzRFWQvOqDYZUdC20=', '10.04.2026', false);


--
-- TOC entry 3725 (class 0 OID 27074)
-- Dependencies: 251
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (8, 'wDdsKwbHuttFejdFS49o43cZ0KbxYAbDIfZp+K6FohDINW8Ukonvj9xN1vLgPLq4ERHRNEVndZIUqviYMhzoyVmd4SokfQ==');
INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (9, 'PHj0ivtTOjeeoKVc4rm7p8iqsstGyfe8z0l5GYk2Oq5kAFX30cbm2dDZWvQE3gH/qcgJkDlq2Gwa1qaJUuE5pfleKUhwb/cq1IOHe/T/WmA=');
INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (10, 'g3r2I386QXFRfYO+bP/8VrDgzw7r0q0Od+QjyvWXBxuv6AuDDSiJaWy8zvWj8odudIZDPe1ZL2/Cxg==');
INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (11, 'S9HKkT8vSvKPhHVQiEXuFMRxdZiIldQKRlAs+ywkbV0cysF/QO84J3XMvHGTPw==');
INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (12, 'Ezkb2cgX9EKLO+dML7z3rp3c/MqdKvgdbXwKRcH2BoOIANG6bA1tgCcXLdzz1HT7iD+BojrCB5GsuLZS8xTu6Tc=');
INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (13, '/NtwcHT55Nv35ZQbqK+woivscNlT6Ol2Z/ToN+ymdW43fuQ1Tpk5QzciucQxDYFcuCP9');
INSERT INTO public.status OVERRIDING SYSTEM VALUE VALUES (14, 'fV33E/djBcfWGG+Kt6CfOJwO9/yg0tQ4F0vcincxZxpRMzWAVwy/HHvKrDpF');


--
-- TOC entry 3736 (class 0 OID 27972)
-- Dependencies: 262
-- Data for Name: template; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (18, 'Приказ о зачислении', 'templates/1778188239299____________________.docx', '2026-05-08T00:10:39.342254817', '2026-05-08T00:11:30.245692380');
INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (19, 'Приказ о зачислении №1', 'templates/1778852902628______________________1.docx', '2026-05-15T16:48:22.629374538', NULL);
INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (21, 'Приказ о зачислении 23', 'templates/1778854169331_____________________23.docx', '2026-05-15T17:09:29.332717109', NULL);
INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (22, 'название', 'templates/1780095053055_________.odt', '2026-05-30T01:50:53.057295268', NULL);
INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (23, 'Анкета №2020-7', 'templates/1781816531668_________2020-7.odt', '2026-06-19T00:02:11.670536003', NULL);
INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (24, 'Анкета №4040-10-2', 'templates/1781816684586_________4040-10-2.odt', '2026-06-19T00:04:44.589148245', NULL);
INSERT INTO public.template OVERRIDING SYSTEM VALUE VALUES (25, 'Анкета 7070-12', 'templates/1781816878592________7070-12.odt', '2026-06-19T00:07:58.593417999', NULL);


--
-- TOC entry 3738 (class 0 OID 27983)
-- Dependencies: 264
-- Data for Name: template_field; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (110, 18, '{{name}}', 'ФИО', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (111, 18, '{{serPasp}}', 'Серия паспорта', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (112, 18, '${numPasp}', 'Номер паспорта', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (113, 18, '${SNILS}', 'СНИЛС', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (114, 18, '${inn}', 'ИНН', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (115, 18, '${birthdaye}', 'Дата рождения', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (116, 19, '{{name}}', 'Имя', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (117, 19, '{{serPasp}}', 'Серия паспорта', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (118, 19, '${numPasp}', 'Num pasp', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (119, 19, '${SNILS}', 'Snils', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (120, 19, '${inn}', 'Inn', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (121, 19, '${birthdaye}', 'Дата рождения', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (128, 21, '{{name}}', 'Имя', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (129, 21, '{{serPasp}}', 'Серия паспорта', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (130, 21, '${numPasp}', 'Num pasp', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (131, 21, '${SNILS}', 'Snils', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (132, 21, '${inn}', 'Inn', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (133, 21, '${birthdaye}', 'Birthdaye', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (134, 22, '${name}', 'Имя', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (135, 22, '${age}', 'Возраст', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (136, 23, '${name}', 'Name', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (137, 23, '${age}', 'Возраст', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (138, 24, '${name}', 'Имя', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (139, 24, '${age}', 'Возраст', false, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (140, 25, '${name}', 'Имя', true, NULL);
INSERT INTO public.template_field OVERRIDING SYSTEM VALUE VALUES (141, 25, '${age}', 'Возраст', false, NULL);


--
-- TOC entry 3727 (class 0 OID 27081)
-- Dependencies: 253
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (13, 'petrov.sa', '$2a$12$MX4KepZCXgRTvMm2DS2rlOxcb2ltizqMO9qgxknHznnWMfZDQ1Dry', 12);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (14, 'sidorova.av', '$2a$12$ya.gxp8fk8FQHfPDrT2anu3ZtNEXeVeNZu.6RRYcqB2P7ncfxo63W', 13);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (15, 'kozlov.di', '$2a$12$4mJ2USP8cNlXiPzjWEi/D.My1LQJTXgcXNjE7hhkUmsgEPv8Jz99q', 14);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (16, 'novikova.es', '$2a$12$FS8ic9QY2a5g6wYXNnOHDO4X96HKSVFac/UVp3fvctiauhNrSvrQm', 15);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (17, 'morozov.ap', '$2a$12$.fPhw5b9wA4c2LCy57dvGOcuXi6bq9ZgLzg04SCIPJI.eebGO3/ee', 16);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (18, 'volkova.od', '$2a$12$wJ/7YA69iHfYU5OYYpy8Kew./2qXMA7.4N.QG8RtDRT9YauHJpa9u', 17);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (19, 'lebedev.pa', '$2a$12$7OcfMTN5J1Eds1.Coe3p0Od80.JOV.KGh89uIljiuv4sBlWhfczzy', 18);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (20, 'sokolova.tn', '$2a$12$EDHxIZjz8akK7qoBBkoZCu3oNFbmy6twfhBvsqRS1WLAO1q55LRX.', 19);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (22, 'petrova.an', '$2a$12$izfZmIOQUP4TTOZZfavnduXqM24gJ57Apt.yKhD4UKmOJgdLIgCdS', 11);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (21, 'popov.im', '$2a$12$gc9V360N1MyPeKQsN9xu9eDgIj8LueRVuXHHVkFNPdo83OCDluzp.', 20);
INSERT INTO public."user" OVERRIDING SYSTEM VALUE VALUES (12, 'ivanova.mp', '$2a$12$dBRa23Bf4kh0ezKtIUK5iOd1LnWiw3tLa/bk8XeSUCGRfUzUHeV5W', 11);


--
-- TOC entry 3729 (class 0 OID 27088)
-- Dependencies: 255
-- Data for Name: waiting_list_for_housing; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (11, 11, '45', '15.01.2023', '01.06.2028', 'Сбор документов', false);
INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (12, 13, '67', '01.12.2022', '15.03.2029', 'Ожидание', false);
INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (13, 14, '89', '10.03.2023', '20.01.2030', 'Постановка на учет', false);
INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (14, 16, '34', '01.02.2023', '10.11.2027', 'Строительство дома', false);
INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (16, 19, '78', '20.05.2023', '01.12.2029', 'Ожидание', false);
INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (17, 11, '45', '15.01.2023', '01.06.2028', 'Проверка документов', false);
INSERT INTO public.waiting_list_for_housing OVERRIDING SYSTEM VALUE VALUES (15, 17, '56', '05.01.2023', '25.09.2028', 'Сбор документов', true);


--
-- TOC entry 3778 (class 0 OID 0)
-- Dependencies: 220
-- Name: children_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.children_id_seq', 21, true);


--
-- TOC entry 3779 (class 0 OID 0)
-- Dependencies: 222
-- Name: children_interaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.children_interaction_id_seq', 43, true);


--
-- TOC entry 3780 (class 0 OID 0)
-- Dependencies: 267
-- Name: document_field_values_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.document_field_values_id_seq', 48, true);


--
-- TOC entry 3781 (class 0 OID 0)
-- Dependencies: 265
-- Name: document_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.document_id_seq', 18, true);


--
-- TOC entry 3782 (class 0 OID 0)
-- Dependencies: 224
-- Name: education_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.education_group_id_seq', 18, true);


--
-- TOC entry 3783 (class 0 OID 0)
-- Dependencies: 226
-- Name: education_level_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.education_level_id_seq', 10, true);


--
-- TOC entry 3784 (class 0 OID 0)
-- Dependencies: 228
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_id_seq', 21, true);


--
-- TOC entry 3785 (class 0 OID 0)
-- Dependencies: 230
-- Name: family_situation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.family_situation_id_seq', 21, true);


--
-- TOC entry 3786 (class 0 OID 0)
-- Dependencies: 232
-- Name: gender_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.gender_id_seq', 4, true);


--
-- TOC entry 3787 (class 0 OID 0)
-- Dependencies: 234
-- Name: health_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.health_group_id_seq', 12, true);


--
-- TOC entry 3788 (class 0 OID 0)
-- Dependencies: 236
-- Name: housing_rights_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.housing_rights_id_seq', 20, true);


--
-- TOC entry 3789 (class 0 OID 0)
-- Dependencies: 238
-- Name: interaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.interaction_id_seq', 38, true);


--
-- TOC entry 3790 (class 0 OID 0)
-- Dependencies: 259
-- Name: interaction_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.interaction_type_id_seq', 11, true);


--
-- TOC entry 3791 (class 0 OID 0)
-- Dependencies: 240
-- Name: monitoring_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.monitoring_type_id_seq', 20, true);


--
-- TOC entry 3792 (class 0 OID 0)
-- Dependencies: 242
-- Name: organization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_id_seq', 21, true);


--
-- TOC entry 3793 (class 0 OID 0)
-- Dependencies: 244
-- Name: organization_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_type_id_seq', 9, true);


--
-- TOC entry 3794 (class 0 OID 0)
-- Dependencies: 257
-- Name: ownership_form_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ownership_form_id_seq', 3, true);


--
-- TOC entry 3795 (class 0 OID 0)
-- Dependencies: 246
-- Name: post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.post_id_seq', 13, true);


--
-- TOC entry 3796 (class 0 OID 0)
-- Dependencies: 269
-- Name: security_access_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.security_access_log_id_seq', 139, true);


--
-- TOC entry 3797 (class 0 OID 0)
-- Dependencies: 248
-- Name: social_monitoring_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.social_monitoring_id_seq', 20, true);


--
-- TOC entry 3798 (class 0 OID 0)
-- Dependencies: 250
-- Name: social_passport_id_passport_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.social_passport_id_passport_seq', 30, true);


--
-- TOC entry 3799 (class 0 OID 0)
-- Dependencies: 252
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_id_seq', 17, true);


--
-- TOC entry 3800 (class 0 OID 0)
-- Dependencies: 263
-- Name: template_field_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.template_field_id_seq', 141, true);


--
-- TOC entry 3801 (class 0 OID 0)
-- Dependencies: 261
-- Name: template_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.template_id_seq', 25, true);


--
-- TOC entry 3802 (class 0 OID 0)
-- Dependencies: 254
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 23, true);


--
-- TOC entry 3803 (class 0 OID 0)
-- Dependencies: 256
-- Name: waiting_list_for_housing_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.waiting_list_for_housing_id_seq', 18, true);


--
-- TOC entry 3458 (class 2606 OID 27096)
-- Name: children_interaction children_interaction_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children_interaction
    ADD CONSTRAINT children_interaction_pk PRIMARY KEY (id);


--
-- TOC entry 3460 (class 2606 OID 27459)
-- Name: children_interaction children_interaction_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children_interaction
    ADD CONSTRAINT children_interaction_unique UNIQUE (id_interaction);


--
-- TOC entry 3456 (class 2606 OID 27098)
-- Name: children children_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children
    ADD CONSTRAINT children_pk PRIMARY KEY (id);


--
-- TOC entry 3514 (class 2606 OID 28004)
-- Name: document document_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pk PRIMARY KEY (id);


--
-- TOC entry 3462 (class 2606 OID 27100)
-- Name: education_group education_group_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education_group
    ADD CONSTRAINT education_group_pk PRIMARY KEY (id);


--
-- TOC entry 3464 (class 2606 OID 27102)
-- Name: education_level education_level_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education_level
    ADD CONSTRAINT education_level_pk PRIMARY KEY (id);


--
-- TOC entry 3466 (class 2606 OID 27104)
-- Name: employee employees_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employees_pk PRIMARY KEY (id);


--
-- TOC entry 3468 (class 2606 OID 27106)
-- Name: family_situation family_situation_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.family_situation
    ADD CONSTRAINT family_situation_pk PRIMARY KEY (id);


--
-- TOC entry 3470 (class 2606 OID 27108)
-- Name: gender gender_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gender
    ADD CONSTRAINT gender_pk PRIMARY KEY (id);


--
-- TOC entry 3472 (class 2606 OID 27110)
-- Name: health_group health_group_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.health_group
    ADD CONSTRAINT health_group_pk PRIMARY KEY (id);


--
-- TOC entry 3474 (class 2606 OID 27112)
-- Name: housing_rights housing_rights_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.housing_rights
    ADD CONSTRAINT housing_rights_pk PRIMARY KEY (id);


--
-- TOC entry 3476 (class 2606 OID 27114)
-- Name: interaction interaction_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interaction
    ADD CONSTRAINT interaction_pk PRIMARY KEY (id);


--
-- TOC entry 3504 (class 2606 OID 27438)
-- Name: interaction_type interaction_type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interaction_type
    ADD CONSTRAINT interaction_type_pk PRIMARY KEY (id);


--
-- TOC entry 3506 (class 2606 OID 27440)
-- Name: interaction_type interaction_type_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interaction_type
    ADD CONSTRAINT interaction_type_unique UNIQUE (interaction_name);


--
-- TOC entry 3478 (class 2606 OID 27116)
-- Name: monitoring_type monitoring_type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.monitoring_type
    ADD CONSTRAINT monitoring_type_pk PRIMARY KEY (id);


--
-- TOC entry 3516 (class 2606 OID 28015)
-- Name: document_field_values newtable_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document_field_values
    ADD CONSTRAINT newtable_pk PRIMARY KEY (id);


--
-- TOC entry 3480 (class 2606 OID 27118)
-- Name: organization organization_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pk PRIMARY KEY (id);


--
-- TOC entry 3482 (class 2606 OID 27120)
-- Name: organization_type organization_type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_type
    ADD CONSTRAINT organization_type_pk PRIMARY KEY (id);


--
-- TOC entry 3484 (class 2606 OID 27122)
-- Name: organization_type organization_type_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_type
    ADD CONSTRAINT organization_type_unique UNIQUE (type_name);


--
-- TOC entry 3500 (class 2606 OID 27299)
-- Name: ownership_form ownership_form_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ownership_form
    ADD CONSTRAINT ownership_form_pk PRIMARY KEY (id);


--
-- TOC entry 3502 (class 2606 OID 27301)
-- Name: ownership_form ownership_form_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ownership_form
    ADD CONSTRAINT ownership_form_unique UNIQUE (form_name);


--
-- TOC entry 3486 (class 2606 OID 27124)
-- Name: post post_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pk PRIMARY KEY (id);


--
-- TOC entry 3518 (class 2606 OID 28059)
-- Name: security_access_log security_access_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.security_access_log
    ADD CONSTRAINT security_access_log_pkey PRIMARY KEY (id);


--
-- TOC entry 3488 (class 2606 OID 27126)
-- Name: social_monitoring social_monitoring_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_monitoring
    ADD CONSTRAINT social_monitoring_pk PRIMARY KEY (id);


--
-- TOC entry 3490 (class 2606 OID 27128)
-- Name: social_passport social_passport_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_passport
    ADD CONSTRAINT social_passport_pk PRIMARY KEY (id_passport);


--
-- TOC entry 3492 (class 2606 OID 27398)
-- Name: social_passport social_passport_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_passport
    ADD CONSTRAINT social_passport_unique UNIQUE (id_children);


--
-- TOC entry 3494 (class 2606 OID 27130)
-- Name: status status_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pk PRIMARY KEY (id);


--
-- TOC entry 3510 (class 2606 OID 27990)
-- Name: template_field template_field_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.template_field
    ADD CONSTRAINT template_field_pk PRIMARY KEY (id);


--
-- TOC entry 3512 (class 2606 OID 28006)
-- Name: template_field template_field_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.template_field
    ADD CONSTRAINT template_field_unique UNIQUE (template_id, placeholder);


--
-- TOC entry 3508 (class 2606 OID 27981)
-- Name: template template_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.template
    ADD CONSTRAINT template_pk PRIMARY KEY (id);


--
-- TOC entry 3496 (class 2606 OID 27132)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 3498 (class 2606 OID 27134)
-- Name: waiting_list_for_housing waiting_list_for_housing_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.waiting_list_for_housing
    ADD CONSTRAINT waiting_list_for_housing_pk PRIMARY KEY (id);


--
-- TOC entry 3519 (class 2606 OID 27135)
-- Name: children children_education_group_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children
    ADD CONSTRAINT children_education_group_fk FOREIGN KEY (id_education_group) REFERENCES public.education_group(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3520 (class 2606 OID 27140)
-- Name: children children_gender_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children
    ADD CONSTRAINT children_gender_fk FOREIGN KEY (gender) REFERENCES public.gender(id) ON UPDATE CASCADE;


--
-- TOC entry 3522 (class 2606 OID 28041)
-- Name: children_interaction children_interaction_children_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children_interaction
    ADD CONSTRAINT children_interaction_children_fk FOREIGN KEY (id_children) REFERENCES public.children(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3523 (class 2606 OID 27150)
-- Name: children_interaction children_interaction_interaction_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children_interaction
    ADD CONSTRAINT children_interaction_interaction_fk FOREIGN KEY (id_interaction) REFERENCES public.interaction(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3521 (class 2606 OID 27155)
-- Name: children children_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.children
    ADD CONSTRAINT children_status_fk FOREIGN KEY (status) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3544 (class 2606 OID 28031)
-- Name: document_field_values document_field_values_document_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document_field_values
    ADD CONSTRAINT document_field_values_document_fk FOREIGN KEY (document_id) REFERENCES public.document(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3545 (class 2606 OID 28016)
-- Name: document_field_values document_field_values_template_field_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document_field_values
    ADD CONSTRAINT document_field_values_template_field_fk FOREIGN KEY (field_id) REFERENCES public.template_field(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3542 (class 2606 OID 28065)
-- Name: document document_template_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_template_fk FOREIGN KEY (template_id) REFERENCES public.template(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3543 (class 2606 OID 28060)
-- Name: document document_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_user_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3524 (class 2606 OID 27160)
-- Name: education_group education_group_employee_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education_group
    ADD CONSTRAINT education_group_employee_fk FOREIGN KEY (id_tutor) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3525 (class 2606 OID 27165)
-- Name: employee employee_post_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_post_fk FOREIGN KEY (post) REFERENCES public.post(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3526 (class 2606 OID 27170)
-- Name: housing_rights housing_rights_children_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.housing_rights
    ADD CONSTRAINT housing_rights_children_fk FOREIGN KEY (id_children) REFERENCES public.children(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3527 (class 2606 OID 27308)
-- Name: housing_rights housing_rights_ownership_form_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.housing_rights
    ADD CONSTRAINT housing_rights_ownership_form_fk FOREIGN KEY (form_of_ownership) REFERENCES public.ownership_form(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3528 (class 2606 OID 27453)
-- Name: interaction interaction_interaction_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interaction
    ADD CONSTRAINT interaction_interaction_type_fk FOREIGN KEY (interaction_type) REFERENCES public.interaction_type(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3529 (class 2606 OID 27175)
-- Name: interaction interaction_organization_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interaction
    ADD CONSTRAINT interaction_organization_fk FOREIGN KEY (id_organization) REFERENCES public.organization(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3530 (class 2606 OID 27180)
-- Name: interaction interaction_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interaction
    ADD CONSTRAINT interaction_user_fk FOREIGN KEY (id_user) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3531 (class 2606 OID 27185)
-- Name: organization organization_organization_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_organization_type_fk FOREIGN KEY (type) REFERENCES public.organization_type(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3532 (class 2606 OID 27190)
-- Name: social_monitoring social_monitoring_children_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_monitoring
    ADD CONSTRAINT social_monitoring_children_fk FOREIGN KEY (id_children) REFERENCES public.children(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3533 (class 2606 OID 27195)
-- Name: social_monitoring social_monitoring_monitoring_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_monitoring
    ADD CONSTRAINT social_monitoring_monitoring_type_fk FOREIGN KEY (id_monitoring_type) REFERENCES public.monitoring_type(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3534 (class 2606 OID 27200)
-- Name: social_monitoring social_monitoring_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_monitoring
    ADD CONSTRAINT social_monitoring_user_fk FOREIGN KEY (id_user) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3535 (class 2606 OID 27205)
-- Name: social_passport social_passport_children_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_passport
    ADD CONSTRAINT social_passport_children_fk FOREIGN KEY (id_children) REFERENCES public.children(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3536 (class 2606 OID 27210)
-- Name: social_passport social_passport_education_level_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_passport
    ADD CONSTRAINT social_passport_education_level_fk FOREIGN KEY (id_education) REFERENCES public.education_level(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3537 (class 2606 OID 27215)
-- Name: social_passport social_passport_family_situation_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_passport
    ADD CONSTRAINT social_passport_family_situation_fk FOREIGN KEY (id_family_situation) REFERENCES public.family_situation(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3538 (class 2606 OID 27220)
-- Name: social_passport social_passport_health_group_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.social_passport
    ADD CONSTRAINT social_passport_health_group_fk FOREIGN KEY (id_health_group) REFERENCES public.health_group(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3541 (class 2606 OID 27991)
-- Name: template_field template_field_template_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.template_field
    ADD CONSTRAINT template_field_template_fk FOREIGN KEY (template_id) REFERENCES public.template(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3539 (class 2606 OID 27225)
-- Name: user user_employee_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_employee_fk FOREIGN KEY (id_employee) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3540 (class 2606 OID 27230)
-- Name: waiting_list_for_housing waiting_list_for_housing_children_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.waiting_list_for_housing
    ADD CONSTRAINT waiting_list_for_housing_children_fk FOREIGN KEY (id_children) REFERENCES public.children(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3750 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE children; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.children TO app_user;


--
-- TOC entry 3751 (class 0 OID 0)
-- Dependencies: 221
-- Name: TABLE children_interaction; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.children_interaction TO app_user;


--
-- TOC entry 3752 (class 0 OID 0)
-- Dependencies: 266
-- Name: TABLE document; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.document TO app_user;


--
-- TOC entry 3753 (class 0 OID 0)
-- Dependencies: 268
-- Name: TABLE document_field_values; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.document_field_values TO app_user;


--
-- TOC entry 3754 (class 0 OID 0)
-- Dependencies: 223
-- Name: TABLE education_group; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.education_group TO app_user;


--
-- TOC entry 3755 (class 0 OID 0)
-- Dependencies: 225
-- Name: TABLE education_level; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.education_level TO app_user;


--
-- TOC entry 3756 (class 0 OID 0)
-- Dependencies: 227
-- Name: TABLE employee; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.employee TO app_user;


--
-- TOC entry 3757 (class 0 OID 0)
-- Dependencies: 229
-- Name: TABLE family_situation; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.family_situation TO app_user;


--
-- TOC entry 3758 (class 0 OID 0)
-- Dependencies: 231
-- Name: TABLE gender; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.gender TO app_user;


--
-- TOC entry 3759 (class 0 OID 0)
-- Dependencies: 233
-- Name: TABLE health_group; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.health_group TO app_user;


--
-- TOC entry 3760 (class 0 OID 0)
-- Dependencies: 235
-- Name: TABLE housing_rights; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.housing_rights TO app_user;


--
-- TOC entry 3761 (class 0 OID 0)
-- Dependencies: 237
-- Name: TABLE interaction; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.interaction TO app_user;


--
-- TOC entry 3762 (class 0 OID 0)
-- Dependencies: 260
-- Name: TABLE interaction_type; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.interaction_type TO app_user;


--
-- TOC entry 3763 (class 0 OID 0)
-- Dependencies: 239
-- Name: TABLE monitoring_type; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.monitoring_type TO app_user;


--
-- TOC entry 3764 (class 0 OID 0)
-- Dependencies: 241
-- Name: TABLE organization; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.organization TO app_user;


--
-- TOC entry 3765 (class 0 OID 0)
-- Dependencies: 243
-- Name: TABLE organization_type; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.organization_type TO app_user;


--
-- TOC entry 3766 (class 0 OID 0)
-- Dependencies: 258
-- Name: TABLE ownership_form; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ownership_form TO app_user;


--
-- TOC entry 3767 (class 0 OID 0)
-- Dependencies: 245
-- Name: TABLE post; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.post TO app_user;


--
-- TOC entry 3768 (class 0 OID 0)
-- Dependencies: 270
-- Name: TABLE security_access_log; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT ON TABLE public.security_access_log TO app_user;


--
-- TOC entry 3770 (class 0 OID 0)
-- Dependencies: 269
-- Name: SEQUENCE security_access_log_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.security_access_log_id_seq TO app_user;


--
-- TOC entry 3771 (class 0 OID 0)
-- Dependencies: 247
-- Name: TABLE social_monitoring; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.social_monitoring TO app_user;


--
-- TOC entry 3772 (class 0 OID 0)
-- Dependencies: 249
-- Name: TABLE social_passport; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.social_passport TO app_user;


--
-- TOC entry 3773 (class 0 OID 0)
-- Dependencies: 251
-- Name: TABLE status; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.status TO app_user;


--
-- TOC entry 3774 (class 0 OID 0)
-- Dependencies: 262
-- Name: TABLE template; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.template TO app_user;


--
-- TOC entry 3775 (class 0 OID 0)
-- Dependencies: 264
-- Name: TABLE template_field; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.template_field TO app_user;


--
-- TOC entry 3776 (class 0 OID 0)
-- Dependencies: 253
-- Name: TABLE "user"; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public."user" TO app_user;


--
-- TOC entry 3777 (class 0 OID 0)
-- Dependencies: 255
-- Name: TABLE waiting_list_for_housing; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.waiting_list_for_housing TO app_user;


-- Completed on 2026-06-21 15:49:15 MSK

--
-- PostgreSQL database dump complete
--

\unrestrict vwO1zqDwkxAh0vOwk4gQNpR7VnUYUTt0gYyZbEDtkrzJKI3OKAOwvAnpt4Z9Xvl

