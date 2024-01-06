<?php
require_once('connection.php');

$id_member = $_POST['id_member'];

if(!$id_member){
    echo json_encode(array('message' => 'required field is empty'));
} else {
    $query = mysqli_query($CON, "DELETE FROM pinjam WHERE id_member = '$id_member'");

    if($query){
        echo "success";
    } else {
        echo "fail";
    }
}
?>