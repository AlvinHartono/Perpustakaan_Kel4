<?php

require_once('connection.php');

$first_name_librarian = $_POST['first_name_librarian'];
$last_name_librarian = $_POST['last_name_librarian'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];
$password = $_POST['password'];

if(!$first_name_librarian || !$email ||!$no_telp ||!$password){
    echo "required field is empty.";
}

else {
    $query = mysqli_query($CON, "INSERT INTO librarian (first_name_librarian, last_name_librarian, email, no_telp, password) VALUES ('$first_name_librarian', '$last_name_librarian', '$email', '$no_telp','".md5("$password")."')");
    if($query){
        echo 'Librarian data successfully added.';
    } else {
        echo "Librarian data failed to add.";
    }
}
?>