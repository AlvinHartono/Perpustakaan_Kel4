<?php
require_once('connection.php');

$id_kategori = $_POST['id_kategori'];

if(!$id_kategori){
    echo json_encode(array('message' => 'required field is empty'));
} else {
    $query = mysqli_query($CON, "DELETE FROM kategori WHERE id_kategori = '$id_kategori'");

    if($query){
        echo "success";
    } else {
        echo "fail";
    }
}
?>