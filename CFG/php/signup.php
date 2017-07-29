<?php
    $resJson = ['hasError'=>0];
    if(!isset($_POST['email']) || !isset($_POST['password'])){
        echo 'Field is not set!';
        exit; 
    } 
    if(empty($_POST['email']) || empty($_POST['password'])){
        echo 'Fields value cannot be empty!';
        exit; 
    } 
    if(strlen(trim($_POST['email'])) <= 0){
        echo 'Email cannot be white space!';
        exit; 
    } 
    if(strlen(trim($_POST['password'])) <= 0){
        echo 'Password cannot be white space!';
        exit; 
    }

    // Remove all illegal characters from email
    $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);
    //Validate e-mail
    if(!filter_var($email, FILTER_VALIDATE_EMAIL) == true){
        echo 'Email id is not valid!';
        exit; 

    }

    include("../php/config.php");

    $email = mysqli_real_escape_string($con,trim(filter_var($_POST['email'], FILTER_SANITIZE_EMAIL)));
    $origin = mysqli_real_escape_string($con,trim($_POST['origin']));
    $destination = mysqli_real_escape_string($con,trim($_POST['destination']));
    $key = "AIzaSyAW1n8vtFckqBym8CWvBfPVhsU8KpefZvo";
    $parameters="units=imperial&origins=".$origin."&destinations=".$destination."&key=".$key;


    $getData = json_decode(file_get_contents('https://maps.googleapis.com/maps/api/distancematrix/json?'.$parameters, false));
    if($getData->status == "OK" && $getData->rows[0]->elements[0]->status == "OK"){

        $data = array();
        while($row = mysqli_fetch_assoc($run))
            array_push($data,$row); 
        $distance = round((($getData->rows[0]->elements[0]->distance->value)/1000),1);
        $origin = $getData->origin_addresses[0];
        $destination = $getData->destination_addresses[0];
        $duration = round((($getData->rows[0]->elements[0]->duration->value)/60),0);

        $query = "INSERT INTO `distance`(`id`, `cust_id`, `distance`) VALUES ($id,$cust_id,$distance')";
        if(!mysqli_query($con,$query)){
            mysqli_close($con);
            $resJson['hasError']++;
            $resJson['error'] = 'Rides not viewed due to some reason!';
            print_r(json_encode(array("response"=>($resJson)))); 

            $name = $_POST["name"];
            $email = $_POST["email"];
            $mobileNo = $_POST["mobileNo"];
            $query = "INSERT INTO `customer`(`name`,`email`,`mobileNo`,`distance`) VALUES ('$name','$email','$mobileNo','distance')";
            if(!mysqli_query($con,$query)){
                mysqli_close($con);
                echo 'User not signuped due to some reason!';
            exit; 
            }
            mysqli_close($con);
            echo "true";
            exit; 
        }

        exit;     

    }
    else{
        $resJson['hasError']++;
        $resJson['error'] = 'Requested data is invalid! :(';
        print_r(json_encode(array("response"=>($resJson)))); 
        exit; 
    }

?>