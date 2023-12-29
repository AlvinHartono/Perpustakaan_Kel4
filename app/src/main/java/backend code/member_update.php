<?php

require_once('connection.php');

$id_member = $_POST['id_member'];
$first_name_member = $_POST['first_name_member'];
$last_name_member = $_POST['last_name_member'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];

$id_member = intval($id_member);

// echo "Query: " . "UPDATE member SET first_name_member = '$first_name_member', last_name_member = '$last_name_member',email= '$email' , no_telp= '$no_telp'  WHERE id_member= $id_member;";

if(!$first_name_member ||!$email ||!$no_telp){
    echo "member data is not complete";
} else {
    $query = mysqli_query($CON, "UPDATE member SET first_name_member = '$first_name_member', last_name_member = '$last_name_member',email= '$email' , no_telp= '$no_telp'  WHERE id_member= $id_member;");
    
    if($query){
        echo "success";
    } else {
        echo "failed";
    }
}



