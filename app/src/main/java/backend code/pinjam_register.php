<?php

require_once('connection.php');

$id_buku = $_POST['id_buku'];
$id_member = $_POST['id_member'];
$tgl_peminjaman = $_POST['tgl_peminjaman'];
$tgl_pengembalian = $_POST['tgl_pengembalian'];

if(!$id_buku|| !$id_member|| !$tgl_peminjaman|| !$tgl_pengembalian){
    echo "required field is empty.";
}
else {
    $query = mysqli_query($CON, "INSERT INTO buku (id_buku, id_member, tgl_peminjaman, tgl_pengembalian) VALUES ('$id_buku', '$id_member', '$tgl_peminjaman', '$tgl_pengembalian'");
    if($query){
        echo 'pinjam data successfully added.';
    } else {
        echo "pinjam data failed to add.";
    }
}
?>