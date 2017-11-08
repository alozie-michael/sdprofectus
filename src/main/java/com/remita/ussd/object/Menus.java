package com.remita.ussd.object;

import com.remita.ussd.dao.PushRequest;

public class Menus {


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
                request.setUssdContent(" Remita - Transfer \n\n Enter DAN: \n\n 0> Back ");
                break;
            }case 5:{
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
            case 3:
                request.setUssdContent(" Remita - Airtime \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 4:{
                request.setUssdContent(" Remita - Airtime \n\n Successful. \n Your new account balance is #59,200. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processBalance(PushRequest request, int step){

        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Balance \n\n Select Your Account: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Balance \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Balance \n\n Your balance is #59,200. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processPayTSAAndBillers(PushRequest request, int step){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Enter RRR: \n\n 0> Back  ");
                break;
            case 2:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            case 3:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Select Your Account To Debit Bank: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            case 4:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 5:{
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Successful. \n Your just paid the sum of #59,200 to FAAN. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }
}


newRequest.setUssdContent(" Welcome to Remita \n\n 1> Transfer \n 2> Airtime \n 3> Balance \n 4> Pay TSA and Billers \n 5> Get loan \n 6> RRR \n 7> Receipt \n 8> Register \n\n 9> Next ");