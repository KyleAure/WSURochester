<!-- Tristin Harvell's video.php page -->
<?php
//TODO: Change reporting method after testing has been completed.
//error_reporting(E_ALL);
//ini_set('display_errors', E_ALL);
?>

<!DOCTYPE html>
<html lang="en">
<title>Video</title>
<!-- Meta Tags -->
<meta name="author" content="Tristin Harvell">
<meta name="description" content="Informative video page for internet addiction">
<meta name="keywords" content="Internet, addiction, phones, life, time, boredom">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Style Links -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="shortcut icon" href="favicon.ico">
<link rel="shortcut icon" type="image/png" href="assets/favicon.png">
<!-- Script Links -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/header.js"></script>
    
<!-- Include Header -->
<?php include_once 'inc/header.php'; ?>

<header>
  Instructional Videos
</header>
    
<!-- Body -->
<?php include_once 'inc/videoDescription.php'; ?>
<?php include_once 'inc/videobody.php'; ?>
    
<!-- Footer -->
<?php include_once 'inc/footer.php'; ?>
</body>
</html>
