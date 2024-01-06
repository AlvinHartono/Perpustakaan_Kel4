<?php

require_once('connection.php');

$id_librarian = $_POST['id_librarian'];
$first_name_librarian = $_POST['first_name_librarian'];
$last_name_librarian = $_POST['last_name_librarian'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];

$id_librarian = intval($id_librarian);

// echo "Query: " . "UPDATE librarian SET first_name_librarian = '$first_name_librarian', last_name_librarian = '$last_name_librarian',email= '$email' , no_telp= '$no_telp'  WHERE id_librarian= $id_librarian;";

if(!$first_name_librarian ||!$email ||!$no_telp){
    echo "librarian data is not complete";
} else {
    $query = mysqli_query($CON, "UPDATE librarian SET first_name_librarian = '$first_name_librarian', last_name_librarian = '$last_name_librarian',email= '$email' , no_telp= '$no_telp'  WHERE id_librarian= $id_librarian;");
    
    if($query){
        echo "success";
    } else {
        echo "failed";
    }
}



