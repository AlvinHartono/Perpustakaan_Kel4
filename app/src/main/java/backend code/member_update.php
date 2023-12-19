<?php

require_once('connection.php');

$id_member = $_POST['id_member'];
$first_name_member = $_POST['first_name_member'];
$last_name_member = $_POST['last_name_member'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];
$password = $_POST['password'];

if(!$first_name_member || !$last_name_member ||!$email ||!$no_telp ||!$password){
    echo json_encode(array('message' => 'required field is empty.'));
} else {
    $query = mysqli_query($CON, "UPDATE member SET id_member='$id_member', email='$email', no_telp='$no_telp', password='$password'");
    if($query){
        echo json_encode(array('message' => 'Member data successfully updated.'));
    } else {
        echo json_encode(array('message' => 'Member data failed to update.'));
    }
}



