# Jadwal Praktikum API

## Deskripsi
Jadwal Praktikum API adalah aplikasi berbasis Spring Boot yang dirancang untuk mengelola data master seperti kelas, ruangan, asisten, dan praktikum, serta membuat jadwal praktikum. Aplikasi ini menggunakan Java Persistence API (JPA) untuk operasi CRUD (Create, Read, Update, Delete) dan PostgreSQL sebagai database relasional. API ini menyediakan endpoint RESTful untuk mempermudah pengelolaan data dan penjadwalan praktikum.

## Fitur
- **Manajemen Data Master**:
  - CRUD untuk entitas **Kelas**.
  - CRUD untuk entitas **Ruangan**.
  - CRUD untuk entitas **Asisten**.
  - CRUD untuk entitas **Praktikum**.
- **Penjadwalan Praktikum**:
  - Membuat, mengubah, dan menghapus jadwal praktikum berdasarkan ketersediaan ruangan dan asisten.
  - Validasi konflik jadwal untuk mencegah tumpang tindih.
- **REST API**:
  - Endpoint untuk mengakses dan memanipulasi data melalui HTTP methods (GET, POST, PUT, DELETE).
  - Respons dalam format JSON untuk kemudahan integrasi.
- **Database PostgreSQL**:
  - Penyimpanan data yang terstruktur dan relasional.
  - Relasi antar entitas (misalnya, praktikum terkait dengan kelas, ruangan, dan asisten).
