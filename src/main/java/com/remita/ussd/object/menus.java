package com.remita.ussd.object;

import com.remita.ussd.dao.PushRequest;

import java.util.HashMap;
import java.util.Map;

public class menus {


    public PushRequest processTransfer(PushRequest request, int step){


        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Transfer \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Transfer \n\n Select Recipient Bank: \n 1> United Bank of Africa \n 2> Access Bank \n 3> Akunna Matata Bank \n\n 0> Back ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Transfer \n\n Select Your Account To Debit Bank: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            }case 4:{
                request.setUssdContent(" Remita - Transfer \n\n Successful. \n Your new account balance is #300,000. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processAirtime(PushRequest request, int step){


        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Airtime \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Airtime \n\n Please Enter Telephone no: \n\n 0> Back  ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Airtime \n\n Successful. \n Your new account balance is #300,000. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }
}
