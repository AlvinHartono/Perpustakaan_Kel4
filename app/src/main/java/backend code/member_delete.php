<?php
require_once('connection.php');

$id_member = $_GET['id_member'];

if(!$id_member){
    echo json_encode(array('message' => 'required field is empty'));
} else {
    $query = mysqli_query($CON, "DELETE FROM member WHERE id_member = '$id_member'");

    if($query){
        echo json_encode(array('message'=> 'member data has successfully deleted.'));
    } else {
        echo json_encode(array('message'=> 'member data failed to delete.'));
    }
}

?>