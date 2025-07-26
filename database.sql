-- Active: 1753176555801@@127.0.0.1@5432@project@public

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE kelas(
    id text DEFAULT uuid_generate_v4() NOT NULL,
    created_by text DEFAULT 'admin'::text NOT NULL,
    created_at timestamptz DEFAULT now() NOT NULL,
    updated_by text DEFAULT 'admin'::text NOT NULL,
    updated_at timestamptz DEFAULT now() NOT NULL,
    nama TEXT NOT NULL,
    CONSTRAINT kelas_pk PRIMARY KEY (id),
    CONSTRAINT kelas_un UNIQUE (nama)
    
);

-- Tabel asisten
CREATE TABLE asisten (
    id text DEFAULT uuid_generate_v4() NOT NULL,
    created_by text DEFAULT 'admin'::text NOT NULL,
    created_at timestamptz DEFAULT now() NOT NULL,
    updated_by text DEFAULT 'admin'::text NOT NULL,
    updated_at timestamptz DEFAULT now() NOT NULL,
    nama TEXT NOT NULL,
    npm TEXT NOT NULL,
    email TEXT NOT NULL,
    CONSTRAINT asisten_pk PRIMARY KEY (id),
    CONSTRAINT asisten_un1 UNIQUE (npm)
);

CREATE TABLE praktikum (
    id text DEFAULT uuid_generate_v4() NOT NULL,
    created_by text DEFAULT 'admin'::text NOT NULL,
    created_at timestamptz DEFAULT now() NOT NULL,
    updated_by text DEFAULT 'admin'::text NOT NULL,
    updated_at timestamptz DEFAULT now() NOT NULL,
    nama TEXT NOT NULL,
    CONSTRAINT praktikum_pk PRIMARY KEY (id),
    CONSTRAINT praktikum_un1 UNIQUE (nama)
);
CREATE TABLE ruangan (
    id text DEFAULT uuid_generate_v4() NOT NULL,
    created_by text DEFAULT 'admin'::text NOT NULL,
    created_at timestamptz DEFAULT now() NOT NULL,
    updated_by text DEFAULT 'admin'::text NOT NULL,
    updated_at timestamptz DEFAULT now() NOT NULL,
    nama TEXT NOT NULL,
    CONSTRAINT ruangan_pk PRIMARY KEY (id),
    CONSTRAINT ruangan_un1 UNIQUE (nama)
);

CREATE TABLE jadwal_praktikum (
    id text DEFAULT uuid_generate_v4() NOT NULL,
    created_by text DEFAULT 'admin'::text NOT NULL,
    created_at timestamptz DEFAULT now() NOT NULL,
    updated_by text DEFAULT 'admin'::text NOT NULL,
    updated_at timestamptz DEFAULT now() NOT NULL,
    kelas_id TEXT NOT NULL,
    asisten_id TEXT NOT NULL,
    praktikum_id TEXT NOT NULL,
    ruangan_id TEXT NOT NULL,
    tanggal_praktikum TIMESTAMPTZ NOT NULL,
    CONSTRAINT jadwal_praktikum_pk PRIMARY KEY (id),
    CONSTRAINT jadwal_praktikum_un1 UNIQUE (ruangan_id, tanggal_praktikum),
    CONSTRAINT jadwal_praktikum_un2 UNIQUE (asisten_id, tanggal_praktikum),
    CONSTRAINT jadwal_praktikum_un3 UNIQUE (kelas_id, tanggal_praktikum),
    CONSTRAINT jadwal_praktikum_fk1 FOREIGN KEY (kelas_id) REFERENCES kelas(id),
    CONSTRAINT jadwal_praktikum_fk2 FOREIGN KEY (asisten_id) REFERENCES asisten(id),
    CONSTRAINT jadwal_praktikum_fk3 FOREIGN KEY (praktikum_id) REFERENCES praktikum(id),
    CONSTRAINT jadwal_praktikum_fk4 FOREIGN KEY (ruangan_id) REFERENCES ruangan(id)
);
