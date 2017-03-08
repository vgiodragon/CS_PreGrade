<?php
$con=mysqli_connect("localhost","u588311430_root","123456","u588311430_bbdd");
$sql="INSERT INTO usuario(usuario, clave, rol) VALUES ('admin', 'admin','administrador')";
if (mysqli_query($con,$sql))
{
   echo "Los valores se han insertado con éxito";
}
?>