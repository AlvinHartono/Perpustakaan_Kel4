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
    echo json_encode(array('message' => 'required field is empty.'));
} else {
    $query = mysqli_query($CON, "UPDATE buku SET id_buku='$id_buku', judul_buku='$judul_buku', penerbit ='$penerbit ', pengarang='$pengarang', tahun_terbit='$tahun_terbit', id_kategori='$id_kategori', image_buku='$image_buku'");
    if($query){
        echo json_encode(array('message' => 'buku data successfully updated.'));
    } else {
        echo json_encode(array('message' => 'buku data failed to update.'));
    }
}