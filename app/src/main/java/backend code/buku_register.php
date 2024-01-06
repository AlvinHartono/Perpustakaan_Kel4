<?php

require_once('connection.php');

$id_buku = $_POST['id_buku'];
$judul_buku = $_POST['judul_buku'];
$penerbit = $_POST['penerbit'];
$pengarang = $_POST['pengarang'];
$tahun_terbit = $_POST['tahun_terbit'];
$id_kategori = $_POST['id_kategori'];
$image_buku = $_POST['image_buku'];

if(!$id_buku|| !$judul_buku ||!$penerbit ||!$pengarang ||!$tahun_terbit ||!$id_kategori ||!$image_buku){
    echo "required field is empty.";
}
else {
    $query = mysqli_query($CON, "INSERT INTO buku (id_buku, judul_buku, penerbit, pengarang, tahun_terbit, id_kategori, image_buku) VALUES ('$id_buku', '$judul_buku', '$penerbit', '$pengarang', '$id_kategori', '$image_buku'");
    if($query){
        echo 'buku data successfully added.';
    } else {
        echo "buku data failed to add.";
    }
}
?>