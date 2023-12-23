<?php
if(isset($_POST["no_telp"]) && isset($_POST['password'])){
    require_once('connection.php');
    require_once('validate.php');
    $no_telp = $_POST['no_telp'];
    $password = $_POST['password'];
    $no_telp = validate($_POST['no_telp']);
    $password = validate($_POST['password']);
    $sql = "SELECT no_telp, password FROM librarian WHERE no_telp = '$no_telp' AND password ='".md5("$password")."'";
    $row = $CON->query($sql);
    if($row->num_rows > 0){
        //  $response["error_text"] = "true";   
        echo "true";
    } else {
        // $response["error_text"] = "false";
        echo "false";
    }
}
?>
