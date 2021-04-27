<?php
require "connect.php";

// $nama = $_GET["hobby_id"];
$hobby_id = $_GET["hobby_id"];
$id_hobby = $_GET["id"];
// $password = $_GET["password"];

$sql = "SELECT * FROM kegemaran, hobby WHERE kegemaran.hobby_id = '$hobby_id'=hobby.id = '$id_hobby'";
$result = mysqli_query($con, $sql);

if (!mysqli_num_rows($result) > 0) {
    $status = "Failed";
    echo json_encode(array("response"=>$status));
} else {
    $row = mysqli_fetch_assoc($result);
    $id_hobby = $row['nama_hobby'];
    $status = "Ok";
    echo json_encode(array("response"=>$status, "nama"=>$nama));
}
mysqli_close($con);

?>