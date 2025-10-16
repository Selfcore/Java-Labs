--
-- PostgreSQL database dump
--

\restrict isHT8IgExK0HRZyXZqiS4EdVG4kCN8zBefDv6i6LvxQ9AtwdcL7vQSPt8aU0nKz

-- Dumped from database version 18.0 (Debian 18.0-1.pgdg13+3)
-- Dumped by pg_dump version 18.0 (Debian 18.0-1.pgdg13+3)

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
-- Name: Clients; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."Clients" (
    id bigint NOT NULL,
    fullname character(128) NOT NULL,
    birthday date,
    phone_number character(12) NOT NULL,
    address character(128),
    discount real
);


ALTER TABLE public."Clients" OWNER TO selfcore;

--
-- Name: Clients_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Clients_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Clients_id_seq" OWNER TO selfcore;

--
-- Name: Clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Clients_id_seq" OWNED BY public."Clients".id;


--
-- Name: Days; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."Days" (
    id smallint NOT NULL,
    day character(16) NOT NULL
);


ALTER TABLE public."Days" OWNER TO selfcore;

--
-- Name: Days_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Days_id_seq"
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Days_id_seq" OWNER TO selfcore;

--
-- Name: Days_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Days_id_seq" OWNED BY public."Days".id;


--
-- Name: Items; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."Items" (
    id smallint NOT NULL,
    "name_UA" character(128) NOT NULL,
    "name_EN" character(128) NOT NULL,
    type character(32),
    price real NOT NULL
);


ALTER TABLE public."Items" OWNER TO selfcore;

--
-- Name: Items_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Items_id_seq"
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Items_id_seq" OWNER TO selfcore;

--
-- Name: Items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Items_id_seq" OWNED BY public."Items".id;


--
-- Name: OrderItems; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."OrderItems" (
    id bigint NOT NULL,
    "orderId" bigint NOT NULL,
    "itemId" integer NOT NULL,
    quantity smallint NOT NULL
);


ALTER TABLE public."OrderItems" OWNER TO selfcore;

--
-- Name: OrderItems_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."OrderItems_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."OrderItems_id_seq" OWNER TO selfcore;

--
-- Name: OrderItems_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."OrderItems_id_seq" OWNED BY public."OrderItems".id;


--
-- Name: OrderItems_itemId_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."OrderItems_itemId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."OrderItems_itemId_seq" OWNER TO selfcore;

--
-- Name: OrderItems_itemId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."OrderItems_itemId_seq" OWNED BY public."OrderItems"."itemId";


--
-- Name: OrderItems_orderId_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."OrderItems_orderId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."OrderItems_orderId_seq" OWNER TO selfcore;

--
-- Name: OrderItems_orderId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."OrderItems_orderId_seq" OWNED BY public."OrderItems"."orderId";


--
-- Name: Orders; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."Orders" (
    id bigint NOT NULL,
    "clientId" bigint NOT NULL,
    "orderDate" date NOT NULL,
    "totalAmount" real,
    "workerId" integer NOT NULL
);


ALTER TABLE public."Orders" OWNER TO selfcore;

--
-- Name: Orders_clientId_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Orders_clientId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Orders_clientId_seq" OWNER TO selfcore;

--
-- Name: Orders_clientId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Orders_clientId_seq" OWNED BY public."Orders"."clientId";


--
-- Name: Orders_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Orders_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Orders_id_seq" OWNER TO selfcore;

--
-- Name: Orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Orders_id_seq" OWNED BY public."Orders".id;


--
-- Name: Orders_workerId_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Orders_workerId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Orders_workerId_seq" OWNER TO selfcore;

--
-- Name: Orders_workerId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Orders_workerId_seq" OWNED BY public."Orders"."workerId";


--
-- Name: Positions; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."Positions" (
    id integer NOT NULL,
    title character(96) NOT NULL
);


ALTER TABLE public."Positions" OWNER TO selfcore;

--
-- Name: Positions_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Positions_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Positions_id_seq" OWNER TO selfcore;

--
-- Name: Positions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Positions_id_seq" OWNED BY public."Positions".id;


--
-- Name: Workers; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."Workers" (
    id bigint NOT NULL,
    fullname character(128) NOT NULL,
    phone_number character(12),
    address character(128),
    position_id integer NOT NULL
);


ALTER TABLE public."Workers" OWNER TO selfcore;

--
-- Name: ViewWorkers; Type: VIEW; Schema: public; Owner: selfcore
--

CREATE VIEW public."ViewWorkers" AS
 SELECT w.id,
    w.fullname,
    p.title
   FROM (public."Workers" w
     JOIN public."Positions" p ON ((p.id = w.position_id)));


ALTER VIEW public."ViewWorkers" OWNER TO selfcore;

--
-- Name: WorkSchedule; Type: TABLE; Schema: public; Owner: selfcore
--

CREATE TABLE public."WorkSchedule" (
    id integer NOT NULL,
    "workerId" bigint NOT NULL,
    "dayId" smallint NOT NULL,
    "startTime" time without time zone CONSTRAINT "WorkSchedule_time_not_null" NOT NULL,
    "endTime" time without time zone NOT NULL
);


ALTER TABLE public."WorkSchedule" OWNER TO selfcore;

--
-- Name: WorkSchedule_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."WorkSchedule_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."WorkSchedule_id_seq" OWNER TO selfcore;

--
-- Name: WorkSchedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."WorkSchedule_id_seq" OWNED BY public."WorkSchedule".id;


--
-- Name: WorkSchedule_workerId_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."WorkSchedule_workerId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."WorkSchedule_workerId_seq" OWNER TO selfcore;

--
-- Name: WorkSchedule_workerId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."WorkSchedule_workerId_seq" OWNED BY public."WorkSchedule"."workerId";


--
-- Name: Workers_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Workers_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Workers_id_seq" OWNER TO selfcore;

--
-- Name: Workers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Workers_id_seq" OWNED BY public."Workers".id;


--
-- Name: Workers_position_id_seq; Type: SEQUENCE; Schema: public; Owner: selfcore
--

CREATE SEQUENCE public."Workers_position_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Workers_position_id_seq" OWNER TO selfcore;

--
-- Name: Workers_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: selfcore
--

ALTER SEQUENCE public."Workers_position_id_seq" OWNED BY public."Workers".position_id;


--
-- Name: Clients id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Clients" ALTER COLUMN id SET DEFAULT nextval('public."Clients_id_seq"'::regclass);


--
-- Name: Days id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Days" ALTER COLUMN id SET DEFAULT nextval('public."Days_id_seq"'::regclass);


--
-- Name: Items id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Items" ALTER COLUMN id SET DEFAULT nextval('public."Items_id_seq"'::regclass);


--
-- Name: OrderItems id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."OrderItems" ALTER COLUMN id SET DEFAULT nextval('public."OrderItems_id_seq"'::regclass);


--
-- Name: OrderItems orderId; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."OrderItems" ALTER COLUMN "orderId" SET DEFAULT nextval('public."OrderItems_orderId_seq"'::regclass);


--
-- Name: OrderItems itemId; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."OrderItems" ALTER COLUMN "itemId" SET DEFAULT nextval('public."OrderItems_itemId_seq"'::regclass);


--
-- Name: Orders id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Orders" ALTER COLUMN id SET DEFAULT nextval('public."Orders_id_seq"'::regclass);


--
-- Name: Orders clientId; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Orders" ALTER COLUMN "clientId" SET DEFAULT nextval('public."Orders_clientId_seq"'::regclass);


--
-- Name: Orders workerId; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Orders" ALTER COLUMN "workerId" SET DEFAULT nextval('public."Orders_workerId_seq"'::regclass);


--
-- Name: Positions id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Positions" ALTER COLUMN id SET DEFAULT nextval('public."Positions_id_seq"'::regclass);


--
-- Name: WorkSchedule id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."WorkSchedule" ALTER COLUMN id SET DEFAULT nextval('public."WorkSchedule_id_seq"'::regclass);


--
-- Name: WorkSchedule workerId; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."WorkSchedule" ALTER COLUMN "workerId" SET DEFAULT nextval('public."WorkSchedule_workerId_seq"'::regclass);


--
-- Name: Workers id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Workers" ALTER COLUMN id SET DEFAULT nextval('public."Workers_id_seq"'::regclass);


--
-- Name: Workers position_id; Type: DEFAULT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Workers" ALTER COLUMN position_id SET DEFAULT nextval('public."Workers_position_id_seq"'::regclass);


--
-- Data for Name: Clients; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."Clients" (id, fullname, birthday, phone_number, address, discount) FROM stdin;
1	Jarvis Weeks                                                                                                                    	2008-03-20	173 140 7205	20 Claremont Lane                                                                                                               	0.06
2	Melamie Barham                                                                                                                  	2013-06-15	506 758 2991	2206 Arizona Alley                                                                                                              	0.06
3	Philipa Strettell                                                                                                               	2008-08-15	107 894 8555	6 Superior Parkway                                                                                                              	0.06
4	Angelina Ponder                                                                                                                 	2007-11-11	687 402 3335	084 Farragut Court                                                                                                              	0.72
5	Benedetta Doeg                                                                                                                  	2010-05-04	715 562 0807	549 Cambridge Crossing                                                                                                          	0.49
6	Bobby Bennen                                                                                                                    	2010-02-06	702 897 7247	0415 Gerald Place                                                                                                               	0.08
7	Bennie Comellini                                                                                                                	2013-09-10	822 946 4848	617 Drewry Place                                                                                                                	0.96
8	Maggie Stimson                                                                                                                  	2006-11-03	645 730 9903	9 Kim Lane                                                                                                                      	0.44
9	Luelle Garrod                                                                                                                   	2011-11-25	716 478 8435	924 Luster Pass                                                                                                                 	0.75
10	Forester Rogister                                                                                                               	2003-01-07	797 907 2509	8499 Carberry Alley                                                                                                             	0.47
\.


--
-- Data for Name: Days; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."Days" (id, day) FROM stdin;
1	Monday          
2	Tuesday         
3	Wednesday       
4	Thursday        
5	Friday          
6	Saturday        
7	Sunday          
\.


--
-- Data for Name: Items; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."Items" (id, "name_UA", "name_EN", type, price) FROM stdin;
1	Тірамісу                                                                                                                        	Tiramisu                                                                                                                        	десерт                          	135
2	Чізкейк Нью-Йорк                                                                                                                	New York Cheesecake                                                                                                             	десерт                          	145
3	Еклер ванільний                                                                                                                 	Vanilla Eclair                                                                                                                  	десерт                          	75
4	Макарони з малиною                                                                                                              	Raspberry Macaron                                                                                                               	десерт                          	55
5	Шоколадний мус                                                                                                                  	Chocolate Mousse                                                                                                                	десерт                          	120
6	Капучино                                                                                                                        	Cappuccino                                                                                                                      	напій                           	85
7	Лате карамельне                                                                                                                 	Caramel Latte                                                                                                                   	напій                           	95
8	Матча латте                                                                                                                     	Matcha Latte                                                                                                                    	напій                           	110
9	Айс кава                                                                                                                        	Iced Coffee                                                                                                                     	напій                           	80
10	Лимонад цитрусовий                                                                                                              	Citrus Lemonade                                                                                                                 	напій                           	70
11	Чай жасминовий                                                                                                                  	Jasmine Tea                                                                                                                     	напій                           	65
12	Морозиво фісташкове                                                                                                             	Pistachio Ice Cream                                                                                                             	десерт                          	90
13	Панна-котта з ягодами                                                                                                           	Berry Panna Cotta                                                                                                               	десерт                          	130
14	Гарячий шоколад                                                                                                                 	Hot Chocolate                                                                                                                   	напій                           	95
15	Фрапе ванільне                                                                                                                  	Vanilla Frappe                                                                                                                  	напій                           	100
\.


--
-- Data for Name: OrderItems; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."OrderItems" (id, "orderId", "itemId", quantity) FROM stdin;
7	4	3	3
8	12	14	3
9	5	14	3
10	6	10	1
11	8	2	3
12	12	11	2
13	11	10	2
14	5	8	2
15	13	2	1
16	7	4	1
\.


--
-- Data for Name: Orders; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."Orders" (id, "clientId", "orderDate", "totalAmount", "workerId") FROM stdin;
9	9	2025-04-01	0	2
10	7	2025-06-08	0	2
4	1	2025-03-18	225	2
5	4	2025-05-24	505	2
6	10	2025-03-26	70	2
7	4	2025-08-08	55	2
8	9	2024-12-05	435	2
11	5	2024-12-25	140	2
12	6	2025-03-05	415	2
13	9	2024-12-20	145	2
\.


--
-- Data for Name: Positions; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."Positions" (id, title) FROM stdin;
1	Бариста                                                                                         
2	Офіціант                                                                                        
3	Кондитер                                                                                        
\.


--
-- Data for Name: WorkSchedule; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."WorkSchedule" (id, "workerId", "dayId", "startTime", "endTime") FROM stdin;
1	1	1	14:30:00	18:00:00
2	1	2	12:00:00	17:30:00
\.


--
-- Data for Name: Workers; Type: TABLE DATA; Schema: public; Owner: selfcore
--

COPY public."Workers" (id, fullname, phone_number, address, position_id) FROM stdin;
1	Nelson Stook                                                                                                                    	997 762 3331	0 Grasskamp Lane                                                                                                                	1
2	Boote Walstow                                                                                                                   	631 819 3393	195 Memorial Trail                                                                                                              	2
3	Burl Elsay                                                                                                                      	326 585 5100	8 Montana Way                                                                                                                   	1
4	Lana Skyner                                                                                                                     	390 215 0004	43 Rieder Parkway                                                                                                               	3
\.


--
-- Name: Clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Clients_id_seq"', 10, true);


--
-- Name: Days_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Days_id_seq"', 7, true);


--
-- Name: Items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Items_id_seq"', 15, true);


--
-- Name: OrderItems_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."OrderItems_id_seq"', 16, true);


--
-- Name: OrderItems_itemId_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."OrderItems_itemId_seq"', 1, false);


--
-- Name: OrderItems_orderId_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."OrderItems_orderId_seq"', 1, false);


--
-- Name: Orders_clientId_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Orders_clientId_seq"', 1, false);


--
-- Name: Orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Orders_id_seq"', 13, true);


--
-- Name: Orders_workerId_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Orders_workerId_seq"', 10, true);


--
-- Name: Positions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Positions_id_seq"', 3, true);


--
-- Name: WorkSchedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."WorkSchedule_id_seq"', 2, true);


--
-- Name: WorkSchedule_workerId_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."WorkSchedule_workerId_seq"', 1, false);


--
-- Name: Workers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Workers_id_seq"', 4, true);


--
-- Name: Workers_position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: selfcore
--

SELECT pg_catalog.setval('public."Workers_position_id_seq"', 1, false);


--
-- Name: Clients Clients_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Clients"
    ADD CONSTRAINT "Clients_pkey" PRIMARY KEY (id);


--
-- Name: Days Days_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Days"
    ADD CONSTRAINT "Days_pkey" PRIMARY KEY (id);


--
-- Name: Items Items_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Items"
    ADD CONSTRAINT "Items_pkey" PRIMARY KEY (id);


--
-- Name: OrderItems OrderItems_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."OrderItems"
    ADD CONSTRAINT "OrderItems_pkey" PRIMARY KEY ("orderId", id);


--
-- Name: Orders Orders_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Orders"
    ADD CONSTRAINT "Orders_pkey" PRIMARY KEY (id);


--
-- Name: Positions Positions_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Positions"
    ADD CONSTRAINT "Positions_pkey" PRIMARY KEY (id);


--
-- Name: WorkSchedule WorkSchedule_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."WorkSchedule"
    ADD CONSTRAINT "WorkSchedule_pkey" PRIMARY KEY (id);


--
-- Name: Workers Workers_pkey; Type: CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Workers"
    ADD CONSTRAINT "Workers_pkey" PRIMARY KEY (id);


--
-- Name: Orders FK_clientId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Orders"
    ADD CONSTRAINT "FK_clientId" FOREIGN KEY ("clientId") REFERENCES public."Clients"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: WorkSchedule FK_dayId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."WorkSchedule"
    ADD CONSTRAINT "FK_dayId" FOREIGN KEY ("dayId") REFERENCES public."Days"(id) NOT VALID;


--
-- Name: OrderItems FK_itemId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."OrderItems"
    ADD CONSTRAINT "FK_itemId" FOREIGN KEY ("itemId") REFERENCES public."Items"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: OrderItems FK_orderId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."OrderItems"
    ADD CONSTRAINT "FK_orderId" FOREIGN KEY ("orderId") REFERENCES public."Orders"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: Workers FK_positionId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Workers"
    ADD CONSTRAINT "FK_positionId" FOREIGN KEY (position_id) REFERENCES public."Positions"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: WorkSchedule FK_workerId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."WorkSchedule"
    ADD CONSTRAINT "FK_workerId" FOREIGN KEY ("workerId") REFERENCES public."Workers"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: Orders FK_workerId; Type: FK CONSTRAINT; Schema: public; Owner: selfcore
--

ALTER TABLE ONLY public."Orders"
    ADD CONSTRAINT "FK_workerId" FOREIGN KEY ("workerId") REFERENCES public."Workers"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--

\unrestrict isHT8IgExK0HRZyXZqiS4EdVG4kCN8zBefDv6i6LvxQ9AtwdcL7vQSPt8aU0nKz

