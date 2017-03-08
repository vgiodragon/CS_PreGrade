<?php
$con=mysqli_connect("localhost","u588311430_root","123456","u588311430_bbdd");
$sql="CREATE TABLE usuario(usuario CHAR(30),clave CHAR(30),rol CHAR(30))";
if (mysqli_query($con,$sql))
{
   echo "La tabla ha sido creada con éxito";
}
?>