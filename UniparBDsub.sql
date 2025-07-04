PGDMP                      }            UniparBD    17.4    17.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16576    UniparBD    DATABASE     p   CREATE DATABASE "UniparBD" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pt-BR';
    DROP DATABASE "UniparBD";
                     postgres    false            �            1259    16577    Aluno    TABLE     �   CREATE TABLE public."Aluno" (
    "RA_ALUNO" integer NOT NULL,
    "NOME_ALUNO" character varying(255) NOT NULL,
    "DT_NASC_ALUNO" character varying(10) NOT NULL
);
    DROP TABLE public."Aluno";
       public         heap r       postgres    false            �            1259    16583    Endereco    TABLE     9  CREATE TABLE public."Endereco" (
    "ID_ENDERECO" integer NOT NULL,
    "CEP" character varying(10),
    "LOGRADOURO" character varying(255),
    "NUMERO" character varying(10),
    "BAIRRO" character varying(100),
    "CIDADE" character varying(100),
    "UF" character varying(2),
    "RA_ALUNO_FK" integer
);
    DROP TABLE public."Endereco";
       public         heap r       postgres    false            �            1259    16582    Endereco_ID_ENDERECO_seq    SEQUENCE     �   CREATE SEQUENCE public."Endereco_ID_ENDERECO_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public."Endereco_ID_ENDERECO_seq";
       public               postgres    false    219            �           0    0    Endereco_ID_ENDERECO_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public."Endereco_ID_ENDERECO_seq" OWNED BY public."Endereco"."ID_ENDERECO";
          public               postgres    false    218            [           2604    16586    Endereco ID_ENDERECO    DEFAULT     �   ALTER TABLE ONLY public."Endereco" ALTER COLUMN "ID_ENDERECO" SET DEFAULT nextval('public."Endereco_ID_ENDERECO_seq"'::regclass);
 G   ALTER TABLE public."Endereco" ALTER COLUMN "ID_ENDERECO" DROP DEFAULT;
       public               postgres    false    219    218    219            �          0    16577    Aluno 
   TABLE DATA           L   COPY public."Aluno" ("RA_ALUNO", "NOME_ALUNO", "DT_NASC_ALUNO") FROM stdin;
    public               postgres    false    217   
       �          0    16583    Endereco 
   TABLE DATA           {   COPY public."Endereco" ("ID_ENDERECO", "CEP", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF", "RA_ALUNO_FK") FROM stdin;
    public               postgres    false    219   �       �           0    0    Endereco_ID_ENDERECO_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public."Endereco_ID_ENDERECO_seq"', 5, true);
          public               postgres    false    218            ]           2606    16581    Aluno Aluno_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public."Aluno"
    ADD CONSTRAINT "Aluno_pkey" PRIMARY KEY ("RA_ALUNO");
 >   ALTER TABLE ONLY public."Aluno" DROP CONSTRAINT "Aluno_pkey";
       public                 postgres    false    217            _           2606    16588    Endereco Endereco_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public."Endereco"
    ADD CONSTRAINT "Endereco_pkey" PRIMARY KEY ("ID_ENDERECO");
 D   ALTER TABLE ONLY public."Endereco" DROP CONSTRAINT "Endereco_pkey";
       public                 postgres    false    219            `           2606    16589    Endereco FK_ALUNO    FK CONSTRAINT     �   ALTER TABLE ONLY public."Endereco"
    ADD CONSTRAINT "FK_ALUNO" FOREIGN KEY ("RA_ALUNO_FK") REFERENCES public."Aluno"("RA_ALUNO");
 ?   ALTER TABLE ONLY public."Endereco" DROP CONSTRAINT "FK_ALUNO";
       public               postgres    false    217    219    4701            �   m   x�%�1�0��>�O@������"f�1	:���	����7���O��\��W)!�w��8�%h2Ue�@g��%�^�����r��O�u�?�hZ�&nQ�5���~<��Q,      �   �   x�M�1�0Eg�9�Ned`AB�Z&�b��ںJÉ8
#E,����4�E$��E2Im�SV{�G_� 9�3�$��x*��Y�:�I!�@��7h<8O���S{�g�"g�Egd����A��@ё7��}���ORtkz���?��>���1�| �19�     