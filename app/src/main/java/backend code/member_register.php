<?php

require_once('connection.php');

$id_member = $_POST['id_member'];
$nama_member = $_POST['nama_member'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];
$password = $_POST['password'];

if(!$nama_member ||!$email ||!$no_telp ||!$password){
    echo json_encode(array('message' => 'required field is empty.'));
} else {
    $query = mysqli_query($CON, "INSERT INTO member VALUES ('$nama_member', '$email', '$no_telp', '$password')");
    if($query){
        echo json_encode(array('message' => 'Member data successfully added.'));
    } else {
        echo json_encode(array('message' => 'Member data failed to add.'));
    }
}
?>