<?php
require "connect.php";

$nama = $_GET["nama"];
$password = $_GET["password"];

$sql = "SELECT * FROM mahasantri WHERE nama = '$nama' AND password = '$password' ";
$result = mysqli_query($con, $sql);

if (!mysqli_num_rows($result) > 0) {
    $status = "Failed";
    echo json_encode(array("response"=>$status));
} else {
    $row = mysqli_fetch_assoc($result);
    $nama = $row['nama'];
    $status = "Ok";
    echo json_encode(array("response"=>$status, "nama"=>$nama));
}
mysqli_close($con);

?>