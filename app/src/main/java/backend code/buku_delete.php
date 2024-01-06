<?php
require_once('connection.php');

$id_buku = $_POST['id_buku'];

if(!$id_buku){
    echo json_encode(array('message' => 'required field is empty'));
} else {
    $query = mysqli_query($CON, "DELETE FROM buku WHERE id_buku = '$id_buku'");

    if($query){
        echo "success";
    } else {
        echo "fail";
    }
}
?>