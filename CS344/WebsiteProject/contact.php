<?php
//TODO: Change reporting method after testing has been completed.
//error_reporting(E_ALL);
//ini_set('display_errors', E_ALL);
?>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Contact Us</title>
<!-- Meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Kyle Aure">
<meta name="description" content="Contact us page to give users a way to contact the authors">
<meta name="keywords" content="Internet, addiction, phones, life, time, boredom, contact">
<!-- Style links -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/stylesheet.css">
<link rel="shortcut icon" href="assets/favicon.ico">
<link rel="shortcut icon" type="image/png" href="assets/favicon.png">
<!-- Script links -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/jquery.mask.js"></script>
<script src="js/header.js"></script>
</head>

<!-- Include Header -->
<?php include_once 'inc/header.php'; ?>

<!-- Map under header -->
<div class="map-responsive">
    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11477.44770323063!2d-92.4224197!3d44.0139149!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xf6018fe3f0c03935!2sWinona+State+University!5e0!3m2!1sen!2sus!4v1523803859834" 
    onload="this.width=screen.width;" style="border:0" allowfullscreen></iframe>
</div>

<header> Contact Us</header>

<!-- Start Body -->
<?php include 'inc/contactform.php'; ?>
<script src="js/contactform.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>

<!-- Footer -->
<?php include_once 'inc/footer.php'; ?>
</body>
</html>