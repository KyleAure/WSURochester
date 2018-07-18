<?php
function getDescription($youtubeID){
    //API key to get requested information
    $apiKey = 'AIzaSyDb_E6oWg3wQd7zq_t6oSY1WlxGceHAQMU';

    //How we want to connect
    $arrContextOptions=array(
        "ssl"=>array(
            "cafile" => "c:/inetpub/certs/cacert.pem",
            "verify_peer"=> true,
            "verify_peer_name"=> true,
        ),
    );

    //Streamming context
    $context = stream_context_create( $arrContextOptions );

    // returns a single line of JSON that contains the video description. Not a giant request.
    $videoDescriptionJSON = file_get_contents("https://www.googleapis.com/youtube/v3/videos?id=".$youtubeID."&key=".$apiKey."&fields=items(snippet(description))&part=snippet", false, $context );

    // despite @ suppress, it will be false if it fails
    if ($videoDescriptionJSON) {
        $videoDescription = json_decode($videoDescriptionJSON, true);
        return $videoDescription['items'][0]['snippet']['description'];
    } else {
    return false;
    }
}
?>