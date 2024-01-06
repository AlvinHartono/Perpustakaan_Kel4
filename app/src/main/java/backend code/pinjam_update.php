<?php

require_once('connection.php');

$id_buku = $_POST['id_buku'];
$id_member = $_POST['id_member'];
$tgl_peminjaman = $_POST['tgl_peminjaman'];
$tgl_pengembalian = $_POST['tgl_pengembalian'];

if(!$id_buku|| !$id_member|| !$tgl_peminjaman|| !$tgl_pengembalian){
    echo json_encode(array('message' => 'required field is empty.'));
} else {
    $query = mysqli_query($CON, "UPDATE pinjam SET id_buku='$id_buku', id_member='$id_member', tgl_peminjaman='$tgl_peminjaman', tgl_pengembalian='$tgl_pengembalian'");
    if($query){
        echo json_encode(array('message' => 'pinjam data successfully updated.'));
    } else {
        echo json_encode(array('message' => 'pinjam data failed to update.'));
    }
}