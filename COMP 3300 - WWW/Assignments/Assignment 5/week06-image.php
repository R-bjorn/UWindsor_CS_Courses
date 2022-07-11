<?php
$no_error = TRUE;
if (array_key_exists('id', $_GET))
{
  switch ($_GET['id'])
  {
    case '1':
      $img = 'alpha.png';
      break;

    default:
      $no_error = FALSE;
      break;
  }
}
else
  $no_error = FALSE;

if ($no_error === TRUE)
{
  header('Content-Type: image/png');
  @readfile($img);
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

// http://YOUR_SITE/4/image.php?id=1
// http://YOUR_SITE/4/image.php?id=322
// http://YOUR_SITE/4/image.php

// MIME Types:
//   text/plain
//   text/css
//   text/html
//   text/xml
//   image/png
//   image/jpeg
//   image/svg
//   application/javascript
//   application/pdf

?>
