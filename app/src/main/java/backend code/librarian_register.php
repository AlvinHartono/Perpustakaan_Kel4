<?php

require_once('connection.php');

$nama_librarian = $_POST['nama_librarian'];
$email = $_POST['email'];
$no_telp = $_POST['no_telp'];
$password = $_POST['password'];

if(!$nama_librarian || !$email ||!$no_telp ||!$password){
    echo "required field is empty.";
}

else {
    $query = mysqli_query($CON, "INSERT INTO librarian (nama_librarian,  email, no_telp, password) VALUES ('$nama_librarian', '$email', '$no_telp','".md5("$password")."')");
    if($query){
        echo 'Librarian data successfully added.';
    } else {
        echo "Librarian data failed to add.";
    }
}
?>