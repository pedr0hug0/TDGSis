--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-08-11 16:32:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 187 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2022 (class 0 OID 0)
-- Dependencies: 187
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 600759)
-- Name: bordado_producao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bordado_producao (
    id integer NOT NULL,
    turno text,
    maquina text,
    nome text,
    data_produzido date,
    codigo text NOT NULL,
    cor text,
    pontos integer,
    corte text,
    t1 integer,
    t2 integer,
    t3 integer,
    t4 integer,
    total integer,
    defeitos integer,
    obs text
);


ALTER TABLE public.bordado_producao OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 600765)
-- Name: bordado_producao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bordado_producao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bordado_producao_id_seq OWNER TO postgres;

--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 171
-- Name: bordado_producao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bordado_producao_id_seq OWNED BY bordado_producao.id;


--
-- TOC entry 172 (class 1259 OID 600767)
-- Name: corte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE corte (
    id integer NOT NULL,
    data_corte date NOT NULL,
    codigo text NOT NULL,
    cor text,
    qtd integer,
    corte text,
    t1 integer,
    t2 integer,
    t3 integer,
    t4 integer,
    tipo_pedido text,
    programado text,
    infesto text
);


ALTER TABLE public.corte OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 600773)
-- Name: corte_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE corte_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corte_id_seq OWNER TO postgres;

--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 173
-- Name: corte_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE corte_id_seq OWNED BY corte.id;


--
-- TOC entry 174 (class 1259 OID 600775)
-- Name: estoque; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estoque (
    id integer NOT NULL,
    data_entrada date,
    data_saida date,
    codigo text NOT NULL,
    cor text,
    t1 integer,
    t2 integer,
    t3 integer,
    t4 integer,
    total integer,
    n_pedido integer,
    descricao text
);


ALTER TABLE public.estoque OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 600781)
-- Name: estoque_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estoque_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estoque_id_seq OWNER TO postgres;

--
-- TOC entry 2025 (class 0 OID 0)
-- Dependencies: 175
-- Name: estoque_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estoque_id_seq OWNED BY estoque.id;


--
-- TOC entry 176 (class 1259 OID 600783)
-- Name: gera_n_pedido; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE gera_n_pedido
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.gera_n_pedido OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 600785)
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_pedido (
    id integer NOT NULL,
    n_pedido integer NOT NULL,
    codigo text NOT NULL,
    cor text,
    t1 integer,
    t2 integer,
    t3 integer,
    t4 integer,
    cor_original text,
    total integer,
    loja text
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 600791)
-- Name: item_pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_pedido_id_seq OWNER TO postgres;

--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 178
-- Name: item_pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_pedido_id_seq OWNED BY item_pedido.id;


--
-- TOC entry 179 (class 1259 OID 600793)
-- Name: pedidos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedidos (
    id integer NOT NULL,
    data_cadastro date DEFAULT ('now'::text)::date,
    n_pedido integer NOT NULL,
    codigo_cliente integer NOT NULL,
    data_entrega date,
    razao text,
    fantasia text,
    prioridade integer,
    status text,
    tipo_pedido text
);


ALTER TABLE public.pedidos OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 600800)
-- Name: pedidos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pedidos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedidos_id_seq OWNER TO postgres;

--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 180
-- Name: pedidos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pedidos_id_seq OWNED BY pedidos.id;


--
-- TOC entry 181 (class 1259 OID 600802)
-- Name: producao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE producao (
    id integer NOT NULL,
    corte text,
    codigo text,
    cor text,
    frente text,
    costas text,
    manga text,
    data_alteracao timestamp with time zone,
    data_inicio date DEFAULT ('now'::text)::date,
    qtd integer,
    status text,
    tipo_pedido text,
    etiqueta text,
    qtd_montado integer,
    perda integer,
    perdeu text
);


ALTER TABLE public.producao OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 600809)
-- Name: producao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE producao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.producao_id_seq OWNER TO postgres;

--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 182
-- Name: producao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE producao_id_seq OWNED BY producao.id;


--
-- TOC entry 183 (class 1259 OID 600811)
-- Name: produto_cores; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto_cores (
    id integer NOT NULL,
    codigo text NOT NULL,
    cor text NOT NULL,
    id_cor integer
);


ALTER TABLE public.produto_cores OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 600817)
-- Name: produto_cores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produto_cores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_cores_id_seq OWNER TO postgres;

--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 184
-- Name: produto_cores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produto_cores_id_seq OWNED BY produto_cores.id;


--
-- TOC entry 185 (class 1259 OID 600819)
-- Name: produtos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produtos (
    id integer NOT NULL,
    codigo text NOT NULL,
    descricao text,
    qtd_cores integer,
    qtd_tamanho integer,
    cor1 text,
    cor2 text,
    cor3 text,
    bordado integer,
    estampa integer,
    bolso integer,
    tipo_tamanho text,
    costas integer,
    manga integer,
    rendimento double precision,
    especial integer
);


ALTER TABLE public.produtos OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 600825)
-- Name: produtos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produtos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produtos_id_seq OWNER TO postgres;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 186
-- Name: produtos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produtos_id_seq OWNED BY produtos.id;


--
-- TOC entry 1875 (class 2604 OID 600827)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bordado_producao ALTER COLUMN id SET DEFAULT nextval('bordado_producao_id_seq'::regclass);


--
-- TOC entry 1876 (class 2604 OID 600828)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY corte ALTER COLUMN id SET DEFAULT nextval('corte_id_seq'::regclass);


--
-- TOC entry 1877 (class 2604 OID 600829)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estoque ALTER COLUMN id SET DEFAULT nextval('estoque_id_seq'::regclass);


--
-- TOC entry 1878 (class 2604 OID 600830)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido ALTER COLUMN id SET DEFAULT nextval('item_pedido_id_seq'::regclass);


--
-- TOC entry 1880 (class 2604 OID 600831)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidos ALTER COLUMN id SET DEFAULT nextval('pedidos_id_seq'::regclass);


--
-- TOC entry 1882 (class 2604 OID 600832)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto_cores ALTER COLUMN id SET DEFAULT nextval('produto_cores_id_seq'::regclass);


--
-- TOC entry 1883 (class 2604 OID 600833)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produtos ALTER COLUMN id SET DEFAULT nextval('produtos_id_seq'::regclass);


--
-- TOC entry 1885 (class 2606 OID 600835)
-- Name: bordado_producao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bordado_producao
    ADD CONSTRAINT bordado_producao_pkey PRIMARY KEY (id);


--
-- TOC entry 1887 (class 2606 OID 600837)
-- Name: corte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY corte
    ADD CONSTRAINT corte_pkey PRIMARY KEY (id);


--
-- TOC entry 1889 (class 2606 OID 600839)
-- Name: estoque_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estoque
    ADD CONSTRAINT estoque_pkey PRIMARY KEY (id);


--
-- TOC entry 1891 (class 2606 OID 600841)
-- Name: item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (id);


--
-- TOC entry 1893 (class 2606 OID 600843)
-- Name: pedidos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedidos
    ADD CONSTRAINT pedidos_pkey PRIMARY KEY (n_pedido);


--
-- TOC entry 1895 (class 2606 OID 600845)
-- Name: producao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT producao_pkey PRIMARY KEY (id);


--
-- TOC entry 1897 (class 2606 OID 600847)
-- Name: produto_cores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto_cores
    ADD CONSTRAINT produto_cores_pkey PRIMARY KEY (id);


--
-- TOC entry 1899 (class 2606 OID 600849)
-- Name: produtos_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produtos
    ADD CONSTRAINT produtos_codigo_key UNIQUE (codigo);


--
-- TOC entry 1901 (class 2606 OID 600851)
-- Name: produtos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produtos
    ADD CONSTRAINT produtos_pkey PRIMARY KEY (id);


--
-- TOC entry 1902 (class 2606 OID 600852)
-- Name: corte_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY corte
    ADD CONSTRAINT corte_codigo_fkey FOREIGN KEY (codigo) REFERENCES produtos(codigo);


--
-- TOC entry 1903 (class 2606 OID 600857)
-- Name: estoque_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estoque
    ADD CONSTRAINT estoque_codigo_fkey FOREIGN KEY (codigo) REFERENCES produtos(codigo);


--
-- TOC entry 1904 (class 2606 OID 600862)
-- Name: estoque_n_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estoque
    ADD CONSTRAINT estoque_n_pedido_fkey FOREIGN KEY (n_pedido) REFERENCES pedidos(n_pedido);


--
-- TOC entry 1905 (class 2606 OID 600867)
-- Name: item_pedido_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT item_pedido_codigo_fkey FOREIGN KEY (codigo) REFERENCES produtos(codigo);


--
-- TOC entry 1906 (class 2606 OID 600872)
-- Name: item_pedido_n_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT item_pedido_n_pedido_fkey FOREIGN KEY (n_pedido) REFERENCES pedidos(n_pedido) ON DELETE CASCADE;


--
-- TOC entry 1907 (class 2606 OID 600877)
-- Name: producao_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT producao_id_fkey FOREIGN KEY (id) REFERENCES corte(id) ON DELETE CASCADE;


--
-- TOC entry 2021 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-08-11 16:32:57

--
-- PostgreSQL database dump complete
--

