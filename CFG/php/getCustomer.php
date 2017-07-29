<?php
    $resJson = ['hasError'=>0];
    if(!isset($_POST['email'])){
        $resJson['hasError']++;
        $resJson['error'] = 'Field is not set!';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 
    }
    if(empty($_POST['email'])){
        $resJson['hasError']++;
        $resJson['error'] = 'Fields value cannot be empty!';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 
    } 
    if(strlen(trim($_POST['email'])) <= 0){
        $resJson['hasError']++;
        $resJson['error'] = 'Email cannot be white space!';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 
    }

    // Remove all illegal characters from email
    $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);
    //Validate e-mail
    if(!filter_var($email, FILTER_VALIDATE_EMAIL) == true){
        $resJson['hasError']++;
        $resJson['error'] = 'Email id is not valid!';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 

    }

    include("../process/config.php");
    $email = mysqli_real_escape_string($con,trim(filter_var($_POST['email'], FILTER_SANITIZE_EMAIL)));

    $query = "SELECT `id` FROM `user` WHERE `email`='$email'";
    $run = mysqli_query($con,$query);
    if(mysqli_num_rows($run) != 1){
        mysqli_close($con);
        $resJson['hasError']++;
        $resJson['error'] = 'User does not exist!';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 
    }
    $row = mysqli_fetch_assoc($run);
    $uid = $row['id'];
    
    $query = "SELECT * FROM `customer`";
    $run = mysqli_query($con,$query);
    if(mysqli_num_rows($run) <= 0){
        mysqli_close($con);
        $resJson['hasError']++;
        $resJson['error'] = 'No Data to show!';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 
    }
    $resJson['msg']=array();
    $i=0;
    $arr = array();
    while($row = mysqli_fetch_assoc($run)){
        $arr[] = $row;
    }


    mysqli_close($con);
    print_r(json_encode(array($resJson)));
    exit;
?>