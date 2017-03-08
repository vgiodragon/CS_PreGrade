<?php
$con=mysqli_connect("localhost","u588311430_root","123456","u588311430_bbdd");
if (mysqli_connect_errno($con))
{
   echo "Error al conectar con MySQL: " . mysqli_connect_error();
}
$usuario = $_POST['usuario'];
$clave = $_POST['clave'];
$result = mysqli_query($con,"SELECT rol FROM usuario where usuario='$usuario' and clave='$clave'");

if(!$result){
  echo array('data'=> "error de tabla");
  return;
}
$row = mysqli_fetch_array($result);
$data = $row[0];

if($data){
 $arr = array('data' => $data, 'otro' => 'Felipe');
}else{
 $arr = array('data' => "usuario no existe");
}
 echo json_encode($arr);
 
mysqli_close($con);
?> 