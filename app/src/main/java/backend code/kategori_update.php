<?php

require_once('connection.php');

$id_kategori = $_POST['id_kategori'];
$nama_kategori = $_POST['nama_kategori'];

if(!$id_kategori|| !$nama_kategori){
    echo json_encode(array('message' => 'required field is empty.'));
} else {
    $query = mysqli_query($CON, "UPDATE kategori SET id_kategori='$id_kategori', nama_kategori='$nama_kategori'");
    if($query){
        echo json_encode(array('message' => 'kategori data successfully updated.'));
    } else {
        echo json_encode(array('message' => 'kategori data failed to update.'));
    }
}