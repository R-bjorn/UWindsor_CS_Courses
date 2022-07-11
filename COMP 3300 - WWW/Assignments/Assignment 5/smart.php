<?php
$no_error = TRUE;
if  (array_key_exists('what', $_GET))
{

  switch ($_GET['what']) {
    case '1':
      $data = 'alpha.png';
      $num = 1;
      break;
    
    case '2':
      $data = 'sample.css';
      $num = 2;
      break;

    case '3':
      $data = 'sample.js';
      $num = 3;
      break;

    default:
      $no_error = FALSE;
      break;
  }
}else
{
  $no_error = FALSE;
}

if($no_error === TRUE)
{
  if($num == 1)
  {
    header('Content-Type: image/png');
    @readfile($data);
  }
  if($num == 2)
  {
    header('Content-Type: text/css');
    @readfile($data);
  }
  if($num == 3)
  {
    header('Content-Type: text/JavaScript');
    @readfile($data);
  }
}
else
{
  http_response_code(404);
  header('Content-Type: text/html');
  echo <<<ZZEOF
<!DOCTYPE html>
<html>
<body>
<h1>File NOT found!</h1>
</body>
</html>
ZZEOF;
}



?>
