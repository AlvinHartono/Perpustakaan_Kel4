<?php
require_once('connection.php');

$id_librarian = $_POST['id_librarian'];

if(!$id_librarian){
    echo json_encode(array('message' => 'required field is empty'));
} else {
    $query = mysqli_query($CON, "DELETE FROM librarian WHERE id_librarian = '$id_librarian'");

    if($query){
        echo "success";
    } else {
        echo "fail";
    }
}
?>