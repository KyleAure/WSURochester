<?php
//TODO: Change reporting method after testing has been completed.
//error_reporting(E_ALL);
//ini_set('display_errors', E_ALL);

//mailer classes
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;
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
<script src="js/header.js"></script>
</head>

<!-- Header -->
<?php include 'inc/header.php'; ?>

<div class="bannerimage">
    <img src="assets/bannerimage1.jpg" onload="this.width=screen.width;" alt="bannerimage1">
    <h1>WELCOME!</h1>
    <h2>
        <?php
            echo $_POST['firstname'] . " " . $_POST['lastname'];
        ?>
    </h2>
</div>
<div class="confirmRegistration">
    <h1>Your registration was successful!</h1>
    <p>We will be reaching out to you shortly at <?php echo $_POST['email']?> with more information </p>
    <p>In the mean time check us out on our social media accounts.</p>

    <div class="socialmedia">
        <ul>
            <li><a href="https://www.facebook.com/" id="facebook"><img alt="facebook" src="assets/socialmedia/facebook.png"></a></li>
            <li><a href="https://www.instagram.com/" id="instagram"><img alt="instagram" src="assets/socialmedia/instagram.png"></a></li>
            <li><a href="https://twitter.com/" id="twitter"><img alt="twitter" src="assets/socialmedia/twitter.png"></a></li>
        </ul>
    </div>
</div>

<?php
//doublecheck captcha
$secretKey = '6LePZVMUAAAAAPeAHHX-DjiRxYQW5HJCzykI9dwW';

if(!empty($_POST['g-recaptcha-response'])) {
    $responseKey = $_POST['g-recaptcha-response'];
}else{
    $responseKey = "";
}

$userIP = $_SERVER['REMOTE_ADDR'];
$url = "https://www.google.com/recaptcha/api/siteverify?secret=$secretKey&response=$responseKey&remoteip=$userIP";
$responseJSON = file_get_contents($url);
$response = json_decode($responseJSON);

//only send email if success
if($response->success){
    //mailer php files
    require 'inc/phpmailer/Exception.php';
    require 'inc/phpmailer/PHPMailer.php';
    require 'inc/phpmailer/SMTP.php';
    //This is where email is constructed and sent
    $mail = new PHPMailer();
    $mail->IsSMTP();
    $mail->Host = '172.17.17.100';
    $mail->Port = 25;
    $mail->SMTPAuth = false;
    $mail->SMTPSecure = false;
    $mail->setFrom('no-reply@qwep.com', 'Kyle Aure');
    $mail->addAddress($_POST['email'], $_POST['firstname'] . " " . $_POST['lastname']);
    $mail->Subject  = 'A warm welcome from Internet Addictions';
    $mail->isHTML();
    $mail->Body = file_get_contents('inc/email.html');

    if(!$mail->send()) {
        echo 'Message was not sent.';
        echo 'Mailer error: ' . $mail->ErrorInfo;
    }

} else {
    //echo($responseJSON); use for testing
    echo '<div id="errors"><p>Email failed to send.</p><p>Could not verify identity.</p></div>';
}
?>

<?php include_once 'inc/footer.php'; ?>
</body>
</html>