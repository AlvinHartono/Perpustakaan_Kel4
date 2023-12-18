<?php

require_once('connection.php');

$first_name_member = $_POST['first_name_member'];
$last_name_member = $_POST['last_name_member'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];
$password = $_POST['password'];

if(!$first_name_member || !$email ||!$no_telp ||!$password){
    echo "required field is empty.";
}

else {
    $query = mysqli_query($CON, "INSERT INTO member (first_name_member, last_name_member, email, no_telp, password) VALUES ('$first_name_member', '$last_name_member', '$email', '$no_telp','".md5("$password")."')");
    if($query){
        echo 'Member data successfully added.';
    } else {
        echo "Member data failed to add.";
    }
}
?>