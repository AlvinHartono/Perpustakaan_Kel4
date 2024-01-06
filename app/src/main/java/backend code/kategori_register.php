<?php

require_once('connection.php');

$id_kategori = $_POST['id_kategori'];
$nama_kategori = $_POST['nama_kategori'];

if(!$id_kategori|| !$nama_kategori){
    echo "required field is empty.";
}
else {
    $query = mysqli_query($CON, "INSERT INTO kategori (id_kategori, nama_kategori) VALUES ('$id_kategori', '$nama_kategori'");
    if($query){
        echo 'kategori data successfully added.';
    } else {
        echo "kategori data failed to add.";
    }
}
?>