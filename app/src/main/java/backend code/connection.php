<?php
$HOST = 'localhost';
$USER = 'root';
$PASS = '';
$DB = 'perpustakaan_kel4';

$CON = mysqli_connect($HOST, $USER, $PASS, $DB);
if(!$CON){
    die("Connection falied: ".mysqli_connect_error());
} else {
}
?>